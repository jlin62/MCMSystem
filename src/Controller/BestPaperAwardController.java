/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.BestPaperAward;
import Model.BestPaperAwardModel;
import TableModel.BestPaperAwardTableModel;
import Views.BestPaperAwardFrameView;
import Views.ButtonColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jialu_lin
 */
public class BestPaperAwardController {
    BestPaperAwardFrameView m_view;
    BestPaperAwardModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;
    
    public BestPaperAwardController(final BestPaperAwardFrameView m_view, final BestPaperAwardModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewBestPaperAwardListener(new insertNewBestPaperAwardListener());
        m_view.addSearchBestPaperAwardByConferenceIdListener(
                new searchBestPaperAwardByConferenceIdListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                BestPaperAward updatedData = new BestPaperAward();
                
                updatedData.setConferenceTrackID(Integer.parseInt(
                    m_view.getModel().getValueAt(modelRow, 0).toString()));
                
                if (m_view.getModel().getValueAt(modelRow, 1) != null) {
                    updatedData.setPaperTitle(m_view.getModel().getValueAt(modelRow, 1).toString());
                } else {
                    updatedData.setPaperTitle("");
                }
                
                if (m_view.getModel().getValueAt(modelRow, 2) != null) {
                    updatedData.setAwardPrice(Double.parseDouble(
                            m_view.getModel().getValueAt(modelRow, 2).toString()));
                } else {
                    updatedData.setAwardPrice(0.0);
                }
               
                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 5).toString()));
                if (updatedData.getId() > 0) {
                    try {
                        m_model.updateBestPaperAwardItem(updatedData);
                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                        null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        m_model.insertNewBestPaperAwardItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getBestPaperAwardId(), modelRow, 5);
                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                        null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                    
                    m_view.getModel().fireTableDataChanged();
//                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
//                                null, JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        };
        deleteActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 5).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.deleteBestPaperAwardItem(itemId);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                    m_view.getModel().fireTableDataChanged();
                    m_view.getModel().removeRow(modelRow);
                    JOptionPane.showMessageDialog(m_view,
                            "You've successfully deleted item from the system!",
                            null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 3);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 4);
    }

    class searchBestPaperAwardByConferenceIdListener implements ActionListener {

        public searchBestPaperAwardByConferenceIdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                BestPaperAwardTableModel myModel = new BestPaperAwardTableModel(m_view.getConferenceId(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class insertNewBestPaperAwardListener implements ActionListener {

        public insertNewBestPaperAwardListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "","Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();
        }
    }
}
