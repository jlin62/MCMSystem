/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.Submission;
import Model.SubmissionModel;
import TableModel.SubmissionTableModel;
import Views.SubmissionFrameView;
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
public class SubmissionController {
    SubmissionFrameView m_view;
    SubmissionModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;
    
    public SubmissionController(final SubmissionFrameView m_view, final SubmissionModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewSubmissionListener(new insertNewSubmissionListener());
        m_view.addSearchSubmissionByConferenceIdListener(
                new searchSubmissionByConferenceIdListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                Submission updatedData = new Submission();
                
                updatedData.setAuthorId(Integer.parseInt(
                    m_view.getModel().getValueAt(modelRow, 0).toString()));
                
                updatedData.setPaperId(Integer.parseInt(
                    m_view.getModel().getValueAt(modelRow, 1).toString()));
                
                updatedData.setAuthorOrd(Integer.parseInt(
                    m_view.getModel().getValueAt(modelRow, 2).toString()));               
                
                if (m_view.getModel().getValueAt(modelRow, 3).toString() == "1"){
                    updatedData.setIsCorrespondingAuthor(Boolean.TRUE);
                }else{
                    updatedData.setIsCorrespondingAuthor(Boolean.FALSE);
                }
                
                
//                if (m_view.getModel().getValueAt(modelRow, 3) != null) {
//                    
//                } else {
//                    updatedData.setIsCorrespondingAuthor(Boolean.FALSE);
//                }
                
                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 6).toString()));
                if (updatedData.getId() > 0) {
                    try {
                        m_model.updateSubmissionItem(updatedData);
                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                        null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        m_model.insertNewSubmissionItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getSubmissionId(), modelRow, 6);
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
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 6).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.deleteSubmissionItem(itemId);
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
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 4);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 5);
    }

    class searchSubmissionByConferenceIdListener implements ActionListener {

        public searchSubmissionByConferenceIdListener() {
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                SubmissionTableModel myModel = new SubmissionTableModel(m_view.getConferenceId(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class insertNewSubmissionListener implements ActionListener {

        public insertNewSubmissionListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "","","Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();
        }
    }
    
}
