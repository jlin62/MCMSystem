/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.Paper;
import Model.PaperModel;
import TableModel.PaperTableModel;
import Views.PaperFrameView;
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
public class PaperController {
    PaperFrameView m_view;
    PaperModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;
    
    public PaperController(final PaperFrameView m_view, final PaperModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewPaperListener(new insertNewPaperListener());
        m_view.addSearchPaperByConferenceIdListener(
                new searchPaperByConferenceIdListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                Paper updatedData = new Paper();
                
                updatedData.setConferenceTrackId(Integer.parseInt(
                    m_view.getModel().getValueAt(modelRow, 0).toString()));
                
                if (m_view.getModel().getValueAt(modelRow, 1) != null) {
                    updatedData.setPaperTitle(m_view.getModel().getValueAt(modelRow, 1).toString());
                } else {
                    updatedData.setPaperTitle("");
                }
                
                if (m_view.getModel().getValueAt(modelRow, 2) != null) {
                    updatedData.setPaperType(m_view.getModel().getValueAt(modelRow, 2).toString());
                } else {
                    updatedData.setPaperType("");
                }
                
                if (m_view.getModel().getValueAt(modelRow, 3) != null) {
                    updatedData.setPaperAbstract(m_view.getModel().getValueAt(modelRow, 3).toString());
                } else {
                    updatedData.setPaperAbstract("");
                }
                
                if (m_view.getModel().getValueAt(modelRow, 4) != null) {
                    updatedData.setSubmissionDate(m_view.getModel().getValueAt(modelRow, 4).toString());
                } else {
                    updatedData.setSubmissionDate("1999-01-01");
                }
                
                

                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 7).toString()));
                if (updatedData.getId() > 0) {
                    try {
                        m_model.updatePaperItem(updatedData);
                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                        null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        m_model.insertNewPaperItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getPaperId(), modelRow, 7);
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
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 7).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.deletePaperItem(itemId);
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
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 5);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 6);
    }

    class searchPaperByConferenceIdListener implements ActionListener {

        public searchPaperByConferenceIdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                PaperTableModel myModel = new PaperTableModel(m_view.getConferenceId(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class insertNewPaperListener implements ActionListener {

        public insertNewPaperListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "","","","Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();
        }
    }
    
}
