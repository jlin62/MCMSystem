/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import TableModel.ConferenceTableModel;
import DAO.DatabaseException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jialu_lin
 */
public class ConferenceFrameView extends JFrame {

    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final ConferenceTableModel myModel;
    private final ConferenceTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField cityTextField;
    private final JButton citySearchButton;

    private final JPanel searchConferencePanel;
//    private ConferenceTablePanel conferenceTablePanel;
    private final JScrollPane conferenceTableScrollPane;
    private final JScrollPane conferenceTableResultScrollPane;
//    private final ConferenceInfoPanel conferenceInfoPanel;

    public String getCity() {
        return cityTextField.getText();
    }

    public void setCity(String city) {
        cityTextField.setText(city);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JTable getsResultTbl() {
        return sResultTbl;
    }

    public void setsResultTbl(JTable sResultTbl) {
        this.sResultTbl = sResultTbl;
    }
    
    

    public ConferenceFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("Conference Frame");

        this.searchConferencePanel = new JPanel(new FlowLayout());
        searchConferencePanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter city",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        cityTextField = new JTextField(20);
        cityTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.citySearchButton = new JButton("Search By City");
        citySearchButton.setMaximumSize(new Dimension(10, 5));
        searchConferencePanel.add(cityTextField);
        searchConferencePanel.add(citySearchButton);

        panelGroup.add(this.searchConferencePanel, BorderLayout.NORTH);
//        this.conferenceTablePanel = new ConferenceTablePanel(getCity());
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new ConferenceTableModel(getCity(),"searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        sResultTbl.getColumnModel().getColumn(0).setPreferredWidth(10);
        sResultTbl.getColumnModel().getColumn(1).setPreferredWidth(150);
        sResultTbl.getColumnModel().getColumn(2).setPreferredWidth(10);
        sResultTbl.getColumnModel().getColumn(3).setPreferredWidth(70);
        sResultTbl.getColumnModel().getColumn(4).setPreferredWidth(70);
        sResultTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        sResultTbl.getColumnModel().getColumn(6).setPreferredWidth(50);
        sResultTbl.getColumnModel().getColumn(7).setPreferredWidth(100);
        sResultTbl.getColumnModel().getColumn(8).setPreferredWidth(100);
        conferenceTableResultScrollPane = new JScrollPane(sResultTbl);
        conferenceTableResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        conferenceTableResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(conferenceTableResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("ConferenceTable");
        myModel2 = new ConferenceTableModel(getCity(),"ConferenceTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(10).setMaxWidth(0);
        table.getColumnModel().getColumn(10).setMinWidth(0);
        table.getColumnModel().getColumn(10).setWidth(0);
        table.removeColumn(table.getColumnModel().getColumn(10));
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        //need to pass Component while creating a JScrollPane.
        this.conferenceTableScrollPane = new JScrollPane(table);
        conferenceTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete Conference Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        conferenceTableScrollPane.setPreferredSize(new Dimension(980, 300));
//        conferenceTableScrollPane.add(table);
        panelGroup.add(conferenceTableScrollPane, BorderLayout.SOUTH);
//          panelGroup.add(this.conferenceTablePanel.getConferenceTablePanel(), BorderLayout.CENTER);

//        this.conferenceInfoPanel = new ConferenceInfoPanel(true);
//        panelGroup.add(conferenceInfoPanel.getConferenceInfoPanel(), BorderLayout.SOUTH);
        add(panelGroup, BorderLayout.NORTH);
        insertBtn = new JButton("Insert");
        add(insertBtn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }
//    public void refreshData() throws DatabaseException{
//        this.conferenceTablePanel = new ConferenceTablePanel(getCity());
//        add(conferenceTablePanel.getConferenceTablePanel(),BorderLayout.CENTER);
//        pack();
//        
//    }

    public void addInsertNewConferenceListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchConferenceByCityListener(ActionListener citySearchButton) {
        this.citySearchButton.addActionListener(citySearchButton);
    }
}
