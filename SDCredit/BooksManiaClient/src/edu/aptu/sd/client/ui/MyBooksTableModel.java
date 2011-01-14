/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client.ui;

import edu.aptu.sd.client.configuration.Configuration;
import edu.aptu.sd.library.BookRecord;
import edu.aptu.sd.library.BooksList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikita
 */
public class MyBooksTableModel extends AbstractTableModel {
    private List<BookRecord> books = new LinkedList<BookRecord>();
    private List<String> pathes = new LinkedList<String>();
    private Configuration conf;

    public MyBooksTableModel(BooksList books, Configuration conf) {
        this.conf = conf;

        for (int i = 0; i < books.getData().size(); ++i) {
            if (conf.getBooks().containsKey( books.getData().get(i).getId() )) {
                this.books.add(books.getData().get(i));
                this.pathes.add(conf.getBooks().get( books.getData().get(i).getId() ));
            }
        }
    }

    public int getRowCount() {
        return books.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BookRecord br = books.get(rowIndex);
        String str = pathes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return br.getAuthor();
            case 1:
                return br.getTitle();
            case 2:
                return br.getSize();
            case 3:
                return str;
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Author";
            case 1:
                return "Title";
            case 2:
                return "Size";
            case 3:
                return "Path";
        }
        return null;
    }

}
