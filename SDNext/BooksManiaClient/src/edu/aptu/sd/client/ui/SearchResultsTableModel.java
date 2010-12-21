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
public class SearchResultsTableModel extends AbstractTableModel {
    private List<BookRecord> books = new LinkedList<BookRecord>();
    private Configuration conf;

    public SearchResultsTableModel(BooksList books, Configuration conf) {
        this.conf = conf;

        for (int i = 0; i < books.getData().size(); ++i) {
            if (!conf.getBooks().containsKey( books.getData().get(i).getId() )) {
                this.books.add(books.getData().get(i));
            }
        }
    }

    public int getRowCount() {
        return getBooks().size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BookRecord br = getBooks().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return br.getAuthor();
            case 1:
                return br.getTitle();
            case 2:
                return br.getSize();
            case 3:
                return br.getAddresses().size();
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
                return "Seeds";
        }
        return null;
    }

    /**
     * @return the books
     */
    public List<BookRecord> getBooks() {
        return books;
    }

}
