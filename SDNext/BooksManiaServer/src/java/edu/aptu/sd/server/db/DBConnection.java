package edu.aptu.sd.server.db;

import edu.aptu.sd.library.BookRecord;
import edu.aptu.sd.library.BooksList;
import edu.aptu.sd.library.gui.GUIFactory;
import edu.aptu.sd.library.gui.messagebox.MessageType;
import edu.aptu.sd.server.configuration.ConfigRW;
import edu.aptu.sd.server.configuration.Configuration;
import edu.aptu.sd.server.configuration.IConfigRW;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection implements ILibrary {

    private IConfigRW configRW = new ConfigRW();
    private Configuration config = null;

    private DBConnection() throws DBException {
        try {
            config = configRW.readConfig();
            if (config == null) {
                config = new Configuration();
                configRW.writeConfig(config);
                Logger.getLogger(DBConnection.class.getName()).log(Level.INFO, "Config file was created");
                GUIFactory.getMessageBox().show("Config file was created.\nEdit conf.config to set parameters.\nNow default parameters are used.", "Attention", MessageType.INFO);
            }

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            Properties properties = new Properties();
            properties.setProperty("user", config.getUser());
            properties.setProperty("password", config.getPassword());
            //properties.setProperty("useUnicode","true");
            //properties.setProperty("characterEncoding","Cp1251");

            conn = DriverManager.getConnection(config.getUrl(), properties);

            Statement st = conn.createStatement();

//            st.executeUpdate("SET NAMES 'UTF8'");
//            st.executeUpdate("SET collation_connection='utf8_general_ci'");
//            st.executeUpdate("SET collation_server='utf8_general_ci'");
//            st.executeUpdate("SET character_set_client='utf8'");
//            st.executeUpdate("SET character_set_connection='utf8'");
//            st.executeUpdate("SET character_set_results='utf8'");
//            st.executeUpdate("SET character_set_server='utf8'");

            st.executeUpdate("UPDATE Users SET status = 'offline';");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            GUIFactory.getMessageBox().show("Exception while connecting DB!\n" + ex.getMessage(), "Error", MessageType.ERROR);
            throw new DBException(ex);
        }
    }

    private static DBConnection instance = new DBConnection();

    public static DBConnection getInstance(){
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
        if (clientID == 0 || author.isEmpty() || title.isEmpty() || size == 0)
            return 0;
        try {
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
        if (clientID == 0 || bookID == 0)
            return false;
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate("INSERT INTO Users_Books VALUES (" + bookID + ", " + clientID + ");");
            return res == 1;
        } catch (Exception ex) {//SQLException
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public BooksList search(String searchString) {
        System.out.println(searchString);
        BooksList list = new BooksList();
        try {
            Statement st = conn.createStatement();
            ResultSet res;
            res = st.executeQuery("SELECT Books.id, author, title, fileSize, address FROM Books JOIN Users_Books ON (Books.id = Users_Books.book_id) JOIN Users ON (Users_Books.user_id = Users.id) WHERE ((title LIKE '%" + searchString + "%') OR (author LIKE '%" + searchString + "%')) AND status LIKE 'online';");
            //res = st.executeQuery("SELECT * FROM Books;");
            Map<Integer, BookRecord> map = new HashMap<Integer, BookRecord>();
            while (res.next())
            {
                System.out.println("FOUND: " + res.getString("author") + " " + res.getString("title"));

                int id = res.getInt("id");
                BookRecord rec;
                if (map.containsKey(id)) {
                    rec = map.get(id);
                } else {
                    rec = new BookRecord(id, res.getString("author"), res.getString("title"), res.getInt("fileSize"));
                    list.getData().add(rec);
                    map.put(id, rec);
                }
                rec.getAddresses().add(res.getString("address"));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int registerUser(String address) {
        if (address.isEmpty())
            return 0;
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate("INSERT INTO Users (address, status) VALUES ('" + address + "', 'online');");
            if (res == 1) {
                ResultSet res2 = st.executeQuery("SELECT id FROM Users ORDER BY id DESC LIMIT 1;");
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
        if (clientID == 0 || address.isEmpty())
            return false;
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate("UPDATE Users SET status='" + (online ? "online" : "offline") + "', address='" + address + "' WHERE id=" + clientID + ";");
            System.out.println(res);
            return res == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean removeBook(int clientID, int bookID) {
        if (clientID == 0 || bookID == 0)
            return false;
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
