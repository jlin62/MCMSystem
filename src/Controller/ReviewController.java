/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.Review;
import javax.swing.Action;
import Views.ReviewFrameView;
import Model.ReviewModel;
import Views.ButtonColumn;
import TableModel.ReviewTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jialu_lin
 */
public class ReviewController {
    
    private ReviewFrameView m_view;
    private ReviewModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;
    
       public ReviewController(final ReviewFrameView m_view, final ReviewModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewReviewListener(new insertNewReviewListener());
        m_view.addSearchReviewByRecommendationListener(new searchReviewByRecommendationListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                Review updatedData = new Review();
               updatedData.setPcMemberId(
                       Integer.parseInt(m_view.getModel().getValueAt(modelRow, 0).toString()));
               updatedData.setPaperId(
                       Integer.parseInt(m_view.getModel().getValueAt(modelRow, 1).toString()));
               updatedData.setRecommendations(m_view.getModel().getValueAt(modelRow, 2).toString());
               updatedData.setDueDate(m_view.getModel().getValueAt(modelRow, 3).toString());
               updatedData.setReviewedDate(m_view.getModel().getValueAt(modelRow, 4).toString());
               if (m_view.getModel().getValueAt(modelRow, 5) != null) {
                    updatedData.setComments(m_view.getModel().getValueAt(modelRow, 5).toString());
                } else {
                    updatedData.setComments("");
                }
                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 8).toString()));
                try {
                    //if the id of the item is not zero, then model will call DAO to update the record.
                    if (updatedData.getId() > 0) {
                        m_model.updateReviewItem(updatedData);
                    } else {
                        m_model.insertNewReviewItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getReviewId(), modelRow, 8);
                        
//                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
//                                null, JOptionPane.INFORMATION_MESSAGE);                
                    }
                    m_view.getModel().fireTableDataChanged();
                    JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                            null, JOptionPane.INFORMATION_MESSAGE);
                } catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        deleteActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 8).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.detleteReviewItem(itemId);
                        m_view.getModel().fireTableDataChanged();
                        m_view.getModel().removeRow(modelRow);
                        JOptionPane.showMessageDialog(m_view,
                                "You've successfully deleted item from the system!", 
                                null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(), null, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 6);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 7);
    }

    class searchReviewByRecommendationListener implements ActionListener {

        public searchReviewByRecommendationListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                ReviewTableModel myModel = new ReviewTableModel(m_view.getRecommendation(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class insertNewReviewListener implements ActionListener {

        public insertNewReviewListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "", "", "", "", "Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();
        }
    }
    
}
