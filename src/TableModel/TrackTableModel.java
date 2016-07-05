/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import DAO.DatabaseException;
import Entity.ConferenceTrack;
import Model.TrackModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jialu_lin
 */
public class TrackTableModel extends AbstractTableModel {

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

    public TrackTableModel(String conferenceName, String tableName) throws DatabaseException {
        TrackModel model = new TrackModel();
        List<ConferenceTrack> itemData;
        if (!"".equals(conferenceName)) {
            itemData = model.getTrackByConferenceName(conferenceName);
        } else {
            itemData = model.getAllData();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "ConferenceID", "ConferenceName", "TrackName", "Description"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[5];
                    data[0] = ((ConferenceTrack) list.get(i)).getId();
                    data[1] = ((ConferenceTrack) list.get(i)).getConferenceId();
                    data[2] = ((ConferenceTrack) list.get(i)).getConference().getConferenceName();
                    data[3] = ((ConferenceTrack) list.get(i)).getConferenceTrackName();
                    data[4] = ((ConferenceTrack) list.get(i)).getDescription();
                    allData[i] = data;
                }
                break;
            case "trackTable":
                columnName = new String[]{"ConferenceID","TrackName", "Description", "", "", ""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[6];
                    data[0] = ((ConferenceTrack) list.get(i)).getConferenceId();
                    data[1] = ((ConferenceTrack) list.get(i)).getConferenceTrackName();
                    data[2] = ((ConferenceTrack) list.get(i)).getDescription();
                    data[3] = "Update";
                    data[4] = "Delete";
                    data[5] = ((ConferenceTrack) list.get(i)).getId();
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
//        switch (getColumnName(col)) {
//            case "TrackName": case "Description":
//                return true;
//            default:
//                return false;
//        }
    }
}
