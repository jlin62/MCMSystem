/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DatabaseException;
import Entity.Conference;
import javax.swing.Action;
import Views.ConferenceFrameView;
import Model.ConferenceModel;
import Views.ButtonColumn;
import TableModel.ConferenceTableModel;
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
public class ConferenceController {

    private ConferenceFrameView m_view;
    private ConferenceModel m_model;
    private Action updateActionInfo;
    private Action deleteActionInfo;

    public ConferenceController(final ConferenceFrameView m_view, final ConferenceModel m_model) {
        this.m_view = m_view;
        this.m_model = m_model;
        m_view.addInsertNewConferenceListener(new addNewConferenceItemListener());
        m_view.addSearchConferenceByCityListener(new SearchConferenceByCityListener());

        updateActionInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //Constructor the conference item data for update/insert
                m_view.getModel().fireTableDataChanged();
                Conference updatedData = new Conference();
                if (m_view.getModel().getValueAt(modelRow, 0) != null) {
                    updatedData.setConferenceName(m_view.getModel().getValueAt(modelRow, 0).toString());
                } else {
                    updatedData.setConferenceName("");
                }
                if (m_view.getModel().getValueAt(modelRow, 1) != null) {
                    updatedData.setConferenceYear(m_view.getModel().getValueAt(modelRow, 1).toString());
                } else {
                    updatedData.setConferenceYear("");
                }
                if (m_view.getModel().getValueAt(modelRow, 2) != null) {
                    updatedData.setStartDate(m_view.getModel().getValueAt(modelRow, 2).toString());
                } else {
                    updatedData.setStartDate("1999-01-01");
                }
                if (m_view.getModel().getValueAt(modelRow, 3) != null) {
                    updatedData.setEndDate(m_view.getModel().getValueAt(modelRow, 3).toString());
                } else {
                    updatedData.setEndDate("1999-12-31");
                }
                if (m_view.getModel().getValueAt(modelRow, 4) != null) {
                    updatedData.setCountry(m_view.getModel().getValueAt(modelRow, 4).toString());
                } else {
                    updatedData.setCountry("");
                }
                if (m_view.getModel().getValueAt(modelRow, 5) != null) {
                    updatedData.setCity(m_view.getModel().getValueAt(modelRow, 5).toString());
                } else {
                    updatedData.setCity("");
                }
                if (m_view.getModel().getValueAt(modelRow, 6) != null) {
                    updatedData.setVenue(m_view.getModel().getValueAt(modelRow, 6).toString());
                } else {
                    updatedData.setVenue("");
                }
                if (m_view.getModel().getValueAt(modelRow, 7) != null) {
                    updatedData.setEmail(m_view.getModel().getValueAt(modelRow, 7).toString());
                } else {
                    updatedData.setEmail("");
                }
                updatedData.setId(Integer.parseInt(m_view.getModel().getValueAt(modelRow, 10).toString()));
                try {
                    //if the id of the item is not zero, then model will call DAO to update the record.
                    if (updatedData.getId() > 0) {
                        m_model.updateConferenceItem(updatedData);
                    } else {
                        m_model.insertNewConferenceItem(updatedData);
                        m_view.getModel().setValueAt(m_model.getConferenceId(), modelRow, 10);
                        
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
                int itemId = Integer.parseInt(m_view.getModel().getValueAt(modelRow, 10).toString());
                int selection = JOptionPane.showConfirmDialog(
                        m_view, "Are you sure to delete item " + m_view.getModel().getValueAt(modelRow, 0).toString() + "?",
                        "Selection : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        m_model.DetleteItem(itemId);
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
        ButtonColumn updateButtonColumn = new ButtonColumn(m_view.getTable(), updateActionInfo, 8);
        ButtonColumn deleteButtonColumn = new ButtonColumn(m_view.getTable(), deleteActionInfo, 9);
    }

    class SearchConferenceByCityListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.getModel().fireTableDataChanged();
            try {
                ConferenceTableModel myModel = new ConferenceTableModel(m_view.getCity(), "searchTable");
                m_view.getsResultTbl().setModel(
                        new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
                        null, JOptionPane.ERROR_MESSAGE);
            }
//            if (!"".equals(m_view.getCity())) {
//                try {
//                    ConferenceTableModel myModel = new ConferenceTableModel(m_view.getCity(), "searchTable");
//                    m_view.getsResultTbl().setModel(
//                            new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));
//
//                } catch (DatabaseException ex) {
//                    Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                try {
//                    ConferenceTableModel myModel = new ConferenceTableModel(m_view.getCity(), "searchTable");
//                    m_view.getsResultTbl().setModel(
//                            new DefaultTableModel(myModel.getAllData(), myModel.getColumnName()));
//                } catch (DatabaseException ex) {
//                    JOptionPane.showMessageDialog(m_view, ex.toString() + "\n" + ex.getCause().getMessage(),
//                            null, JOptionPane.ERROR_MESSAGE);
//                }
//            }
        }
    }

    class addNewConferenceItemListener implements ActionListener {

        /**
         * When the button is clicked, a new row will be add to the jtable, with
         * specified default values.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = new Object[]{"", "", "", "", "", "", "", "", "Update", "Delete", "0"};
            m_view.getModel().addRow(data);
            m_view.getModel().fireTableDataChanged();

        }
    }

}
