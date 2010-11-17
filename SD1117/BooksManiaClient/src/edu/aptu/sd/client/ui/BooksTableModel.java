/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client.ui;

import edu.aptu.sd.library.BookRecord;
import edu.aptu.sd.library.BooksResult;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikita
 */
public class BooksTableModel extends AbstractTableModel {
    private BooksResult books;

    public BooksTableModel(BooksResult books) {
        this.books = books;
    }

    public int getRowCount() {
        return books.getData().size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BookRecord br = books.getData().get(rowIndex);
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

}
