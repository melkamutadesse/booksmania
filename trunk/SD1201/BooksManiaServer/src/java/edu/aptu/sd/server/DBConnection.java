package edu.aptu.sd.server;

import edu.aptu.sd.library.BookRecord;
import edu.aptu.sd.library.BooksResult;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBConnection implements IDBConnection {

    private IConfigRW configRW = new ConfigRW();
    private Configuration config = null;

    private DBConnection() {
        try {
            config = configRW.readConfig();
            if (config == null) {
                config = new Configuration();
                configRW.writeConfig(config);
                Logger.getLogger(DBConnection.class.getName()).log(Level.INFO, "Config file was created");
                JOptionPane.showMessageDialog(null, "Config file was created.\nEdit conf.config to set parameters.\nNow default parameters are used.", "Attention", JOptionPane.INFORMATION_MESSAGE);
            }

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Exception while connecting DB!\n" + ex.getMessage());
            System.exit(-1);
        }
    }

    private static DBConnection instance;

    public static DBConnection getInstance(){
        if (instance == null)
            instance = new DBConnection();
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            conn.close();
        } finally {
            super.finalize();
        }
    }


    //--------------------------------------------------------------

    private Connection conn;

    public int addBook(int clientID, String author, String title, int size) {
        try {
            if (clientID == 0 || author.isEmpty() || title.isEmpty() || size == 0)
                return 0;

            Statement st = conn.createStatement();
            int res = st.executeUpdate("INSERT INTO Books (author, title, fileSize) VALUES ('" + author + "', '" + title + "', " + size + ");");
            int id = 0;
            if (res == 1) {
                ResultSet res2 = st.executeQuery("SELECT * FROM Books WHERE author LIKE '" + author + "' AND title LIKE '" + title + "' AND fileSize = " + size + ";");
                if (res2.last())
                    id = res2.getInt("id");
            }
            if (id != 0) {
                int res3 = st.executeUpdate("INSERT INTO Users_Books VALUES (" + id + ", " + clientID + ");");
                if (res3 == 1)
                    return id;
            }
        } catch (Exception ex) {//SQLException
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean addBookCopy(int clientID, int bookID) {
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate("INSERT INTO Users_Books VALUES (" + bookID + ", " + clientID + ");");
            return res == 1;
        } catch (Exception ex) {//SQLException
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public BooksResult search(String searchString) {
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT Books.id, author, title, fileSize, address FROM Books JOIN Users_Books ON (Books.id = Users_Books.book_id) JOIN Users ON (Users_Books.user_id = Users.id) WHERE ((title LIKE '%" + searchString + "%') OR (author LIKE '%" + searchString + "%')) AND status LIKE 'online';");
            BooksResult list = new BooksResult();
            while (res.next())
            {
                int id = res.getInt("id");
                BookRecord rec = null;
                for(int i = 0; i < list.getData().size(); ++i) {
                    if (list.getData().get(i).getId() == id) {
                        rec = list.getData().get(i);
                        break;
                    }
                }
                if (rec == null) {
                    rec = new BookRecord(id, res.getString("author"), res.getString("title"), res.getInt("fileSize"));
                    list.getData().add(rec);
                }
                rec.getAddresses().add(res.getString("address"));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int registerUser(String address) {
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate("INSERT INTO Users (address, status) VALUES ('" + address + "', 'online');");
            if (res == 1) {
                ResultSet res2 = st.executeQuery("SELECT * FROM Users WHERE address LIKE '" + address + "';");
                if (res2.first())
                {
                    return res2.getInt("id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean setUserStatus(int clientID, boolean online, String address) {
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate("UPDATE Users SET status='" + (online ? "online" : "offline") + "', address='" + address + "' WHERE id=" + clientID + ";");
            return res == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean removeBook(int clientID, int bookID) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM Users_Books WHERE user_id=" + clientID + " AND book_id=" + bookID + ";");
            ResultSet res = st.executeQuery("SELECT * FROM Users_Books WHERE book_id=" + bookID + ";");
            if (!res.first())
            {
                int res2 = st.executeUpdate("DELETE FROM Books WHERE id=" + bookID + ";");
                return res2 == 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
