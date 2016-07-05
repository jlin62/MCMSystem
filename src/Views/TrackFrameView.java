/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.DatabaseException;
import TableModel.TrackTableModel;
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
public class TrackFrameView extends JFrame{
    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final TrackTableModel myModel;
    private final TrackTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField conferenceNameTextField;
    private final JButton conferenceNameSearchButton;

    private final JPanel searchTrackPanel;
    private final JScrollPane trackTableScrollPane;
    private final JScrollPane trackResultScrollPane;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTable getsResultTbl() {
        return sResultTbl;
    }

    public void setsResultTbl(JTable sResultTbl) {
        this.sResultTbl = sResultTbl;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
    
    public String getConferenceName() {
        return this.conferenceNameTextField.getText();
    }

    public void setConferenceName(String conferenceName) {
        conferenceNameTextField.setText(conferenceName);
    }
    
    public TrackFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("Conference Track Frame");

        this.searchTrackPanel = new JPanel(new FlowLayout());
        searchTrackPanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter conference name",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        this.conferenceNameTextField = new JTextField(20);
        conferenceNameTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.conferenceNameSearchButton = new JButton("Search By Conference Name");
        conferenceNameSearchButton.setMaximumSize(new Dimension(10, 5));
        searchTrackPanel.add(conferenceNameTextField);
        searchTrackPanel.add(conferenceNameSearchButton);

        panelGroup.add(this.searchTrackPanel, BorderLayout.NORTH);
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new TrackTableModel(this.getConferenceName(),"searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        trackResultScrollPane = new JScrollPane(sResultTbl);
        trackResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        trackResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(trackResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("trackTable");
        myModel2 = new TrackTableModel(this.getConferenceName(),"trackTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.removeColumn(table.getColumnModel().getColumn(5));
        this.trackTableScrollPane = new JScrollPane(table);
        trackTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete Conference Track Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        trackTableScrollPane.setPreferredSize(new Dimension(980, 300));
        panelGroup.add(trackTableScrollPane, BorderLayout.SOUTH);
        add(panelGroup, BorderLayout.NORTH);
        insertBtn = new JButton("Insert");
        add(insertBtn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }
    
    public void addInsertNewTrackListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchTrackByConferenceNameListener(ActionListener conferenceNameSearchButton) {
        this.conferenceNameSearchButton.addActionListener(conferenceNameSearchButton);
    }
    
}
