/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.DatabaseException;
import TableModel.AuthorTableModel;
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
public class AuthorFrameView extends JFrame{
    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final AuthorTableModel myModel;
    private final AuthorTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField countryTextField;
    private final JButton countrySearchButton;

    private final JPanel searchAuthorPanel;
    private final JScrollPane authorTableScrollPane;
    private final JScrollPane authorResultScrollPane;
    
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
    
    public String getCountry() {
        return this.countryTextField.getText();
    }

    public void setConferenceName(String conferenceName) {
        countryTextField.setText(conferenceName);
    }
    
    public AuthorFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("Author Frame");

        this.searchAuthorPanel = new JPanel(new FlowLayout());
        searchAuthorPanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter country",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        this.countryTextField = new JTextField(20);
        countryTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.countrySearchButton = new JButton("Search Author By Country");
        countrySearchButton.setMaximumSize(new Dimension(10, 5));
        searchAuthorPanel.add(countryTextField);
        searchAuthorPanel.add(countrySearchButton);

        panelGroup.add(this.searchAuthorPanel, BorderLayout.NORTH);
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new AuthorTableModel(this.getCountry(),"searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        authorResultScrollPane = new JScrollPane(sResultTbl);
        authorResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        authorResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(authorResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("authorTable");
        myModel2 = new AuthorTableModel(this.getCountry(),"authorTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.removeColumn(table.getColumnModel().getColumn(8));
        this.authorTableScrollPane = new JScrollPane(table);
        authorTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete Author Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        authorTableScrollPane.setPreferredSize(new Dimension(980, 300));
        panelGroup.add(authorTableScrollPane, BorderLayout.SOUTH);
        add(panelGroup, BorderLayout.NORTH);
        insertBtn = new JButton("Insert");
        add(insertBtn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }
    
    public void addInsertNewAuthorListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchAuthorByCountryListener(ActionListener countrySearchButton) {
        this.countrySearchButton.addActionListener(countrySearchButton);
    }
    
}
