/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import TableModel.ReviewTableModel;
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
public class ReviewFrameView extends JFrame {
    
    private JTable table;
    private JTable sResultTbl;
    private DefaultTableModel model;
    private final ReviewTableModel myModel;
    private final ReviewTableModel myModel2;
    private final JButton insertBtn;
    private final JTextField recommendationTextField;
    private final JButton recommendationSearchButton;

    private final JPanel searchReviewPanel;
//    private ConferenceTablePanel conferenceTablePanel;
    private final JScrollPane reviewTableScrollPane;
    private final JScrollPane reviewTableResultScrollPane;
//    private final ConferenceInfoPanel conferenceInfoPanel;

    public String getRecommendation() {
        return recommendationTextField.getText();
    }

    public void setCity(String city) {
        recommendationTextField.setText(city);
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
    
    public ReviewFrameView() throws DatabaseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panelGroup = new JPanel(new BorderLayout());
        setTitle("Review Frame");

        this.searchReviewPanel = new JPanel(new FlowLayout());
        searchReviewPanel.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Please enter recommendation",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N
        recommendationTextField = new JTextField(20);
        recommendationTextField.setMaximumSize(new Dimension(20, 5));//width, height
        this.recommendationSearchButton = new JButton("Search Review By Recommendations");
        recommendationSearchButton.setMaximumSize(new Dimension(10, 5));
        searchReviewPanel.add(recommendationTextField);
        searchReviewPanel.add(recommendationSearchButton);

        panelGroup.add(this.searchReviewPanel, BorderLayout.NORTH);
//        this.conferenceTablePanel = new ConferenceTablePanel(getCity());
        sResultTbl = new JTable(20, 10);
        sResultTbl.setName("searchTable");
        myModel = new ReviewTableModel(getRecommendation(),"searchTable");
        model = new DefaultTableModel(myModel.getAllData(), myModel.getColumnName());
        sResultTbl.setFillsViewportHeight(true);
        sResultTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        sResultTbl.setGridColor(Color.GRAY);
        sResultTbl.setModel(model);
        reviewTableResultScrollPane = new JScrollPane(sResultTbl);
        reviewTableResultScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Search Result",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        reviewTableResultScrollPane.setPreferredSize(new Dimension(280, 100));
        panelGroup.add(reviewTableResultScrollPane, BorderLayout.CENTER);

        table = new JTable(20, 10);
        table.setName("reviewTable");
        myModel2 = new ReviewTableModel(getRecommendation(),"reviewTable");
        model = new DefaultTableModel(myModel2.getAllData(), myModel2.getColumnName());
        table.setFillsViewportHeight(true);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(Color.GRAY);
        table.setModel(model);
        table.removeColumn(table.getColumnModel().getColumn(8));
        this.reviewTableScrollPane = new JScrollPane(table);
        reviewTableScrollPane.setBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Insert/Update/Delete Review Details",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new java.awt.Font("Arial", 1, 12))); // NOI18N 
        reviewTableScrollPane.setPreferredSize(new Dimension(980, 300));
//        conferenceTableScrollPane.add(table);
        panelGroup.add(reviewTableScrollPane, BorderLayout.SOUTH);
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

    public void addInsertNewReviewListener(ActionListener insertBtn) {
        this.insertBtn.addActionListener(insertBtn);
    }

    public void addSearchReviewByRecommendationListener(ActionListener recommendationSearchButton) {
        this.recommendationSearchButton.addActionListener(recommendationSearchButton);
    }
    
}
