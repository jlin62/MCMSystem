/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import DAO.DatabaseException;
import Entity.Submission;
import Model.SubmissionModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class SubmissionTableModel {
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
    
    public SubmissionTableModel(Integer confId, String tableName) throws DatabaseException {
        SubmissionModel model = new SubmissionModel();
        List<Submission> itemData;
        if (confId != 0) {
            itemData = model.getSubmissionByConfId(confId);
        } else {
            itemData = model.getAllSubmission();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "Author Id", "Paper Id", 
                    "Author Order", "Corresponding Author"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[5];
                    data[0] = ((Submission) list.get(i)).getId();
                    data[1] = ((Submission) list.get(i)).getAuthorId();
                    data[2] = ((Submission) list.get(i)).getPaperId();
                    data[3] = ((Submission) list.get(i)).getAuthorOrd();
                    data[4] = ((Submission) list.get(i)).isIsCorrespondingAuthor();
                   
                    allData[i] = data;
                }
                break;
            case "submissionTable":
                columnName = new String[]{"Author Id", "Paper Id", 
                    "Author Order", "Corresponding Author", "","",""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[7];
                    data[0] = ((Submission) list.get(i)).getAuthorId();
                    data[1] = ((Submission) list.get(i)).getPaperId();
                    data[2] = ((Submission) list.get(i)).getAuthorOrd();
                    data[3] = ((Submission) list.get(i)).isIsCorrespondingAuthor();
                    data[4] = "Update";
                    data[5] = "Delete";
                    data[6] = ((Submission) list.get(i)).getId();
                    allData[i] = data;
                }
                break;
        }
    }
    
}
