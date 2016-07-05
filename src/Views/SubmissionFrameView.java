/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.JFrame;
import DAO.DatabaseException;
import TableModel.SubmissionTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
public class SubmissionFrameView extends JFrame {
    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final SubmissionTableModel myModel;
    private final SubmissionTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField confIdTextField;
    private final JButton confIdSearchButton;

    private final JPanel searchSubmissionPanel;
    private final JScrollPane submissionTableScrollPane;
    private final JScrollPane submissionResultScrollPane;
    
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

    public Integer getConferenceId() {
        if("".equals(confIdTextField.getText())){
            return 0;
        }else{
            return Integer.parseInt(confIdTextField.getText());
        }
    }

    public void setConferenceId(Integer id) {
        confIdTextField.setText(id.toString());
    }
    
    public SubmissionFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("Submission Frame");

        this.searchSubmissionPanel = new JPanel(new FlowLayout());
        searchSubmissionPanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter conference id",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        this.confIdTextField = new JTextField(20);
        confIdTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.confIdSearchButton = new JButton("Search Submission By ConferenceId");
        confIdSearchButton.setMaximumSize(new Dimension(10, 5));
        searchSubmissionPanel.add(confIdTextField);
        searchSubmissionPanel.add(confIdSearchButton);

        panelGroup.add(this.searchSubmissionPanel, BorderLayout.NORTH);
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new SubmissionTableModel(this.getConferenceId(), "searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        submissionResultScrollPane = new JScrollPane(sResultTbl);
        submissionResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        submissionResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(submissionResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("submissionTable");
        myModel2 = new SubmissionTableModel(this.getConferenceId(), "submissionTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.removeColumn(table.getColumnModel().getColumn(6));
        this.submissionTableScrollPane = new JScrollPane(table);
        submissionTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete Submission Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        submissionTableScrollPane.setPreferredSize(new Dimension(980, 300));
        panelGroup.add(submissionTableScrollPane, BorderLayout.SOUTH);
        add(panelGroup, BorderLayout.NORTH);
        insertBtn = new JButton("Insert");
        add(insertBtn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    public void addInsertNewSubmissionListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchSubmissionByConferenceIdListener(ActionListener confIdSearchButton) {
        this.confIdSearchButton.addActionListener(confIdSearchButton);
    }

    
}
