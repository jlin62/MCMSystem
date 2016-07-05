/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import DAO.DatabaseException;
import Entity.Review;
import Model.ReviewModel;

/**
 *
 * @author jialu_lin
 */
public class ReviewTableModel extends AbstractTableModel{
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

    public ReviewTableModel(String recommendation, String tableName) throws DatabaseException {
        ReviewModel model = new ReviewModel();
        List<Review> itemData;
        if (!"".equals(recommendation)) {
            itemData = model.getReviewsByRecommendation(recommendation);
        } else {
            itemData = model.getAllReviews();
        }

        allData = new Object[itemData.size()][];
        ArrayList list = new ArrayList(itemData);
        switch (tableName) {
            case "searchTable":
                columnName = new String[]{"ID", "PC_MemberID", "Paper_ID", "Recommendations",
                    "Due Date", "Reviewed Date", "Comments"};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[7];
                    data[0] = ((Review) list.get(i)).getId();
                    data[1] = ((Review) list.get(i)).getPcMemberId();
                    data[2] = ((Review) list.get(i)).getPaperId();
                    data[3] = ((Review) list.get(i)).getRecommendations();
                    data[4] = ((Review) list.get(i)).getDueDate();
                    data[5] = ((Review) list.get(i)).getReviewedDate();
                    data[6] = ((Review) list.get(i)).getComments();                   
                    allData[i] = data;
                }
                break;
            case "reviewTable":
                columnName = new String[]{"PC_MemberID", "Paper_ID", "Recommendations",
                    "Due Date", "Reviewed Date", "Comments", "", "", ""};
                for (int i = 0; i < list.size(); i++) {
                    Object[] data = new Object[9];
                    data[0] = ((Review) list.get(i)).getPcMemberId();
                    data[1] = ((Review) list.get(i)).getPaperId();
                    data[2] = ((Review) list.get(i)).getRecommendations();
                    data[3] = ((Review) list.get(i)).getDueDate();
                    data[4] = ((Review) list.get(i)).getReviewedDate();
                    data[5] = ((Review) list.get(i)).getComments();     
                    data[6] = "Update";
                    data[7] = "Delete";
                    data[8] = ((Review) list.get(i)).getId();
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
