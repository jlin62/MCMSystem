/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import DAO.DatabaseException;
import Entity.Conference;
import Model.ConferenceModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jialu_lin
 */
public class ConferenceTableModel extends AbstractTableModel {

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

    public ConferenceTableModel(String city, String tableName) throws DatabaseException {
        ConferenceModel model = new ConferenceModel();
        List<Conference> itemData;
        if (!"".equals(city)) {
            itemData = model.getConferenceByCity(city);
        } else {
            itemData = model.getAllData();
        }

//        List<Conference> itemData = new ArrayList();
//        Conference item1 = new Conference(1, "conferenceName1", "2012", 
//                "2012-05-01", "2012-05-11", "USA", "SF", "Ferry Building B", 
//                "some1@conference.come");
//        Conference item2 = new Conference(2, "conferenceName2", "2013", 
//                "2013-06-11", "2013-07-10", "USA", "Seattle", "Microsoft 101", 
//                "some2@conference.come");
//        Conference item3 = new Conference(3, "conferenceName3", "2014", 
//                "2014-01-11", "2014-02-10", "USA", "NY", "Google office B", 
//                "some3@conference.come");
//        itemData.add(item1);
//        itemData.add(item2);
//        itemData.add(item3);
        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "Name", "Year", "StartDate", "EndDate", "Country", "City", "Venue",
                    "Email"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[9];
                    data[0] = ((Conference) list.get(i)).getId();
                    data[1] = ((Conference) list.get(i)).getConferenceName();
                    data[2] = ((Conference) list.get(i)).getConferenceYear();
                    data[3] = ((Conference) list.get(i)).getStartDate();
                    data[4] = ((Conference) list.get(i)).getEndDate();
                    data[5] = ((Conference) list.get(i)).getCountry();
                    data[6] = ((Conference) list.get(i)).getCity();
                    data[7] = ((Conference) list.get(i)).getVenue();
                    data[8] = ((Conference) list.get(i)).getEmail();
                    allData[i] = data;
                }
                break;
            case "ConferenceTable":
                columnName = new String[]{"Name", "Year", "StartDate", "EndDate", "Country", "City", "Venue",
                    "Email", "", "", ""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[11];
                    data[0] = ((Conference) list.get(i)).getConferenceName();
                    data[1] = ((Conference) list.get(i)).getConferenceYear();
                    data[2] = ((Conference) list.get(i)).getStartDate();
                    data[3] = ((Conference) list.get(i)).getEndDate();
                    data[4] = ((Conference) list.get(i)).getCountry();
                    data[5] = ((Conference) list.get(i)).getCity();
                    data[6] = ((Conference) list.get(i)).getVenue();
                    data[7] = ((Conference) list.get(i)).getEmail();
                    data[8] = "Update";
                    data[9] = "Delete";
                    data[10] = ((Conference) list.get(i)).getId();
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
    public Object getValueAt(int rowIndex, int columnIndex
    ) {
        return allData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col
    ) {
        allData[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    //Return the name of the column
    @Override
    public String getColumnName(int col
    ) {
        return columnName[col];
    }

    //return the class of this column type
    @Override
    public Class getColumnClass(int c
    ) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col
    ) {
        return true;
    }
}
