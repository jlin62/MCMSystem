/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import DAO.DatabaseException;
import Entity.Author;
import Model.AuthorModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jialu_lin
 */
public class AuthorTableModel extends AbstractTableModel{
    private String columnName[];
    private Object[][] allData;
    
    //Getters and Setters
    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public Object[][] getAllData() {
        return allData;
    }

    public void setAllData(Object[][] allData) {
        this.allData = allData;
    }
    
    public AuthorTableModel(String country, String tableName) throws DatabaseException {
        AuthorModel model = new AuthorModel();
        List<Author> itemData;
        if (!"".equals(country)) {
            itemData = model.getAuthorsByCountry(country);
        } else {
            itemData = model.getAllAuthorData();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "First Name", "Surname", "Affiliation", "Country",
                "Email", "Contact Number"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[7];
                    data[0] = ((Author) list.get(i)).getId();
                    data[1] = ((Author) list.get(i)).getFirstName();
                    data[2] = ((Author) list.get(i)).getSurname();
                    data[3] = ((Author) list.get(i)).getAffiliation();
                    data[4] = ((Author) list.get(i)).getCountry();
                    data[5] = ((Author) list.get(i)).getEmail();
                    data[6] = ((Author) list.get(i)).getContactNumber();
                    allData[i] = data;
                }
                break;
            case "authorTable":
                columnName = new String[]{"First Name","Surname", "Affiliation","Country",
                    "Email","Contact Number","", "", ""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[9];
                    data[0] = ((Author) list.get(i)).getFirstName();
                    data[1] = ((Author) list.get(i)).getSurname();
                    data[2] = ((Author) list.get(i)).getAffiliation();
                    data[3] = ((Author) list.get(i)).getCountry();
                    data[4] = ((Author) list.get(i)).getEmail();
                    data[5] = ((Author) list.get(i)).getContactNumber();
                    data[6] = "Update";
                    data[7] = "Delete";
                    data[8] = ((Author) list.get(i)).getId();
                    allData[i] = data;
                }
                break;
        }
    }

    @Override
    public int getRowCount() {
        return this.allData.length;
    }

    @Override
    public int getColumnCount() {
        return this.columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return allData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        allData[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    //Return the name of the column
    @Override
    public String getColumnName(int col) {
        return columnName[col];
    }

    //return the class of this column type
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    } 
}
