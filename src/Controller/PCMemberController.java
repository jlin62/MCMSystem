/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.PCMember;
import Model.PCMemberModel;
import TableModel.PCMemberTableModel;
import Views.ButtonColumn;
import Views.PCMemberFrameView;
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
public class PCMemberController {

    PCMemberFrameView m_view;
    PCMemberModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;

    public PCMemberController(final PCMemberFrameView m_view, final PCMemberModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewPCMemberListener(new insertNewPCMemberListener());
        m_view.addSearchPCMemberByAffiliationListener(new searchPCMemberByAffiliationListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                PCMember updatedData = new PCMember();
                updatedData.setTrackId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 0).toString()));

                if (m_view.getModel().getValueAt(modelRow, 1) != null) {
                    updatedData.setFirstName(m_view.getModel().getValueAt(modelRow, 1).toString());
                } else {
                    updatedData.setFirstName("");
                }

                if (m_view.getModel().getValueAt(modelRow, 2) != null) {
                    updatedData.setSurname(m_view.getModel().getValueAt(modelRow, 2).toString());
                } else {
                    updatedData.setSurname("");
                }

                if (m_view.getModel().getValueAt(modelRow, 3) != null) {
                    updatedData.setTitle(m_view.getModel().getValueAt(modelRow, 3).toString());
                } else {
                    updatedData.setTitle("");
                }

                if (m_view.getModel().getValueAt(modelRow, 4) != null) {
                    updatedData.setMemberPosition(m_view.getModel().getValueAt(modelRow, 4).toString());
                } else {
                    updatedData.setMemberPosition("");
                }

                if (m_view.getModel().getValueAt(modelRow, 5) != null) {
                    updatedData.setAffiliation(m_view.getModel().getValueAt(modelRow, 5).toString());
                } else {
                    updatedData.setAffiliation("");
                }

                if (m_view.getModel().getValueAt(modelRow, 6) != null) {
                    updatedData.setEmail(m_view.getModel().getValueAt(modelRow, 6).toString());
                } else {
                    updatedData.setEmail("");
                }

                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 9).toString()));
                if (updatedData.getId() > 0) {
                    try {
                        m_model.updatePCMemberItem(updatedData);
                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                                null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        m_model.insertNewPCMemberItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getPCMemberId(), modelRow, 9);
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
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 9).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.detletePCMemberItem(itemId);
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
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 7);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 8);
    }

    class searchPCMemberByAffiliationListener implements ActionListener {

        public searchPCMemberByAffiliationListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                PCMemberTableModel myModel = new PCMemberTableModel(m_view.getAffiliation(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class insertNewPCMemberListener implements ActionListener {

        public insertNewPCMemberListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "", "", "", "", "", "Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();
        }
    }

}
