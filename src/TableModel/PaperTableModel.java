/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import DAO.DatabaseException;
import Entity.Paper;
import Model.PaperModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class PaperTableModel {
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
    
    public PaperTableModel(Integer confId, String tableName) throws DatabaseException {
        PaperModel model = new PaperModel();
        List<Paper> itemData;
        if (confId != 0) {
            itemData = model.getPaperByConfId(confId);
        } else {
            itemData = model.getAllPapers();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "Conference Track ID", "Paper Title", 
                    "Paper Type", "Paper Abstract", "Submission Date"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[6];
                    data[0] = ((Paper) list.get(i)).getId();
                    data[1] = ((Paper) list.get(i)).getConferenceTrackId();
                    data[2] = ((Paper) list.get(i)).getPaperTitle();
                    data[3] = ((Paper) list.get(i)).getPaperType();
                    data[4] = ((Paper) list.get(i)).getPaperAbstract();
                    data[5] = ((Paper) list.get(i)).getSubmissionDate();
                    
                    allData[i] = data;
                }
                break;
            case "paperTable":
                columnName = new String[]{"Conference Track ID", "Paper Title", 
                    "Paper Type", "Paper Abstract", "Submission Date","","",""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[8];
                    data[0] = ((Paper) list.get(i)).getConferenceTrackId();
                    data[1] = ((Paper) list.get(i)).getPaperTitle();
                    data[2] = ((Paper) list.get(i)).getPaperType();
                    data[3] = ((Paper) list.get(i)).getPaperAbstract();
                    data[4] = ((Paper) list.get(i)).getSubmissionDate();
                    data[5] = "Update";
                    data[6] = "Delete";
                    data[7] = ((Paper) list.get(i)).getId();
                    allData[i] = data;
                }
                break;
        }
    }
    
}
