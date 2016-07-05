/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.DatabaseException;
import TableModel.BestPaperAwardTableModel;
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
public class BestPaperAwardFrameView extends JFrame{
    
    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final BestPaperAwardTableModel myModel;
    private final BestPaperAwardTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField confIdTextField;
    private final JButton confIdSearchButton;

    private final JPanel searchBestPaperAwardPanel;
    private final JScrollPane bestPaperAwardTableScrollPane;
    private final JScrollPane bestPaperAwardResultScrollPane;
    
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
    
    public BestPaperAwardFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("Best Paper Award Frame");

        this.searchBestPaperAwardPanel = new JPanel(new FlowLayout());
        searchBestPaperAwardPanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter conference id",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        this.confIdTextField = new JTextField(20);
        confIdTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.confIdSearchButton = new JButton("Search By ConferenceId");
        confIdSearchButton.setMaximumSize(new Dimension(10, 5));
        searchBestPaperAwardPanel.add(confIdTextField);
        searchBestPaperAwardPanel.add(confIdSearchButton);

        panelGroup.add(this.searchBestPaperAwardPanel, BorderLayout.NORTH);
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new BestPaperAwardTableModel(this.getConferenceId(), "searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        bestPaperAwardResultScrollPane = new JScrollPane(sResultTbl);
        bestPaperAwardResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        bestPaperAwardResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(bestPaperAwardResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("bestPaperAwardTable");
        myModel2 = new BestPaperAwardTableModel(this.getConferenceId(), "bestPaperAwardTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.removeColumn(table.getColumnModel().getColumn(5));
        this.bestPaperAwardTableScrollPane = new JScrollPane(table);
        bestPaperAwardTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete Best Paper Award Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        bestPaperAwardTableScrollPane.setPreferredSize(new Dimension(980, 300));
        panelGroup.add(bestPaperAwardTableScrollPane, BorderLayout.SOUTH);
        add(panelGroup, BorderLayout.NORTH);
        insertBtn = new JButton("Insert");
        add(insertBtn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    public void addInsertNewBestPaperAwardListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchBestPaperAwardByConferenceIdListener(ActionListener confIdSearchButton) {
        this.confIdSearchButton.addActionListener(confIdSearchButton);
    }

    
}
