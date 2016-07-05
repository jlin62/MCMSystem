/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.Author;
import Model.AuthorModel;
import TableModel.AuthorTableModel;
import Views.AuthorFrameView;
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
public class AuthorController {

    AuthorFrameView m_view;
    AuthorModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;

    public AuthorController(final AuthorFrameView m_view, final AuthorModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewAuthorListener(new insertNewAuthorListener());
        m_view.addSearchAuthorByCountryListener(new searchAuthorByCountryListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                Author updatedData = new Author();
                if (m_view.getModel().getValueAt(modelRow, 0) != null) {
                    updatedData.setFirstName(m_view.getModel().getValueAt(modelRow, 0).toString());
                } else {
                    updatedData.setFirstName("");
                }

                if (m_view.getModel().getValueAt(modelRow, 1) != null) {
                    updatedData.setSurname(m_view.getModel().getValueAt(modelRow, 1).toString());
                } else {
                    updatedData.setSurname("");
                }

                if (m_view.getModel().getValueAt(modelRow, 2) != null) {
                    updatedData.setAffiliation(m_view.getModel().getValueAt(modelRow, 2).toString());
                } else {
                    updatedData.setAffiliation("");
                }

                if (m_view.getModel().getValueAt(modelRow, 3) != null) {
                    updatedData.setCountry(m_view.getModel().getValueAt(modelRow, 3).toString());
                } else {
                    updatedData.setCountry("");
                }

                if (m_view.getModel().getValueAt(modelRow, 4) != null) {
                    updatedData.setEmail(m_view.getModel().getValueAt(modelRow, 4).toString());
                } else {
                    updatedData.setEmail("");
                }

                if (m_view.getModel().getValueAt(modelRow, 5) != null) {
                    updatedData.setContactNumber(m_view.getModel().getValueAt(modelRow, 5).toString());
                } else {
                    updatedData.setContactNumber("");
                }

                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 8).toString()));
                if (updatedData.getId() > 0) {
                    try {
                        m_model.updateAuthorItem(updatedData);
                        JOptionPane.showMessageDialog(m_view, "You've successfully updated/added new item into the system!",
                                null, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        m_model.insertNewAuthorItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getAuthorId(), modelRow, 8);
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
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 8).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.detleteAuthorItem(itemId);
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
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 6);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 7);
    }

    class searchAuthorByCountryListener implements ActionListener {

        public searchAuthorByCountryListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                AuthorTableModel myModel = new AuthorTableModel(m_view.getCountry(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class insertNewAuthorListener implements ActionListener {

        public insertNewAuthorListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "", "", "", "", "Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();
        }
    }

}
