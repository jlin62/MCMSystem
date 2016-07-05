/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import DAO.DatabaseException;
import Entity.PCMember;
import Model.PCMemberModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jialu_lin
 */
public class PCMemberTableModel extends AbstractTableModel{
    private String columnName[];
    private Object[][] allData;

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
    
    public PCMemberTableModel(String affiliation, String tableName) throws DatabaseException {
        PCMemberModel model = new PCMemberModel();
        List<PCMember> itemData;
        if (!"".equals(affiliation)) {
            itemData = model.getPCMembersByAffiliation(affiliation);
        } else {
            itemData = model.getAllPCMembers();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID","CONFERENCE Track ID","First Name", "Surname", "TITLE", 
                    "MEMBER POSITION", "AFFILIATION", "Email"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[8];
                    data[0] = ((PCMember) list.get(i)).getId();
                    data[1] = ((PCMember) list.get(i)).getTrackId();
                    data[2] = ((PCMember) list.get(i)).getFirstName();
                    data[3] = ((PCMember) list.get(i)).getSurname();
                    data[4] = ((PCMember) list.get(i)).getTitle();
                    data[5] = ((PCMember) list.get(i)).getMemberPosition();
                    data[6] = ((PCMember) list.get(i)).getAffiliation();
                    data[7] = ((PCMember) list.get(i)).getEmail();
                    allData[i] = data;
                }
                break;
            case "pcMemberTable":
                columnName = new String[]{"CONFERENCE Track ID","First Name", "Surname", "TITLE", 
                    "MEMBER POSITION", "AFFILIATION", "Email","","",""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[10];
                    data[0] = ((PCMember) list.get(i)).getTrackId();
                    data[1] = ((PCMember) list.get(i)).getFirstName();
                    data[2] = ((PCMember) list.get(i)).getSurname();
                    data[3] = ((PCMember) list.get(i)).getTitle();
                    data[4] = ((PCMember) list.get(i)).getMemberPosition();
                    data[5] = ((PCMember) list.get(i)).getAffiliation();
                    data[6] = ((PCMember) list.get(i)).getEmail();
                    data[7] = "Update";
                    data[8] = "Delete";
                    data[9] = ((PCMember) list.get(i)).getId();
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
