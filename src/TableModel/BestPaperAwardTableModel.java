/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;
import DAO.DatabaseException;
import Entity.BestPaperAward;
import Model.BestPaperAwardModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jialu_lin
 */
public class BestPaperAwardTableModel {
    
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
    
    public BestPaperAwardTableModel(Integer confId, String tableName) throws DatabaseException {
        BestPaperAwardModel model = new BestPaperAwardModel();
        List<BestPaperAward> itemData;
        if (confId != 0) {
            itemData = model.getBestPaperAwardByConfId(confId);
        } else {
            itemData = model.getAllBestPaperAward();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "Conference Track ID", "Paper Title", 
                    "AWARD PRICE"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[4];
                    data[0] = ((BestPaperAward) list.get(i)).getId();
                    data[1] = ((BestPaperAward) list.get(i)).getConferenceTrackID();
                    data[2] = ((BestPaperAward) list.get(i)).getPaperTitle();
                    data[3] = ((BestPaperAward) list.get(i)).getAwardPrice();
                    allData[i] = data;
                }
                break;
            case "bestPaperAwardTable":
                columnName = new String[]{"Conference Track ID", "Paper Title", 
                    "AWARD PRICE","","",""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[6];
                    data[0] = ((BestPaperAward) list.get(i)).getConferenceTrackID();
                    data[1] = ((BestPaperAward) list.get(i)).getPaperTitle();
                    data[2] = ((BestPaperAward) list.get(i)).getAwardPrice();
                    data[3] = "Update";
                    data[4] = "Delete";
                    data[5] = ((BestPaperAward) list.get(i)).getId();
                    allData[i] = data;
                }
                break;
        }
    }
    
}
