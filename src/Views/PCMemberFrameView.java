/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.DatabaseException;
import TableModel.PCMemberTableModel;
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
public class PCMemberFrameView extends JFrame{
    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final PCMemberTableModel myModel;
    private final PCMemberTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField affTextField;
    private final JButton affiliatonSearchButton;

    private final JPanel searchPCMemberPanel;
    private final JScrollPane pcMemberTableScrollPane;
    private final JScrollPane pcMemberResultScrollPane;
    
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
    
    public String getAffiliation() {
        return this.affTextField.getText();
    }

    public void setAffiliation(String conferenceName) {
        affTextField.setText(conferenceName);
    }
    
    public PCMemberFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("PC Member Frame");

        this.searchPCMemberPanel = new JPanel(new FlowLayout());
        searchPCMemberPanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter affiliation",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        this.affTextField = new JTextField(20);
        affTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.affiliatonSearchButton = new JButton("Search Member By Affiliation");
        affiliatonSearchButton.setMaximumSize(new Dimension(10, 5));
        searchPCMemberPanel.add(affTextField);
        searchPCMemberPanel.add(affiliatonSearchButton);

        panelGroup.add(this.searchPCMemberPanel, BorderLayout.NORTH);
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new PCMemberTableModel(this.getAffiliation(),"searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        pcMemberResultScrollPane = new JScrollPane(sResultTbl);
        pcMemberResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        pcMemberResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(pcMemberResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("authorTable");
        myModel2 = new PCMemberTableModel(this.getAffiliation(),"pcMemberTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.removeColumn(table.getColumnModel().getColumn(9));
        this.pcMemberTableScrollPane = new JScrollPane(table);
        pcMemberTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete PC Member Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        pcMemberTableScrollPane.setPreferredSize(new Dimension(980, 300));
        panelGroup.add(pcMemberTableScrollPane, BorderLayout.SOUTH);
        add(panelGroup, BorderLayout.NORTH);
        insertBtn = new JButton("Insert");
        add(insertBtn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }
    
    public void addInsertNewPCMemberListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchPCMemberByAffiliationListener(ActionListener affiliatonSearchButton) {
        this.affiliatonSearchButton.addActionListener(affiliatonSearchButton);
    }
    
    
}
