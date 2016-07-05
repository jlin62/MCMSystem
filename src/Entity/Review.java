/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author jialu_lin
 */
public class Review {
    private int id;
    private int pcMemberId;
    private int paperId;
    private String recommendations;
    private String dueDate;
    private String reviewedDate;
    private String comments;

    public Review(int id, int pcMemberId, int paperId, String recommendations, String dueDate, String reviewedDate, String comments) {
        this.id = id;
        this.pcMemberId = pcMemberId;
        this.paperId = paperId;
        this.recommendations = recommendations;
        this.dueDate = dueDate;
        this.reviewedDate = reviewedDate;
        this.comments = comments;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPcMemberId() {
        return pcMemberId;
    }

    public void setPcMemberId(int pcMemberId) {
        this.pcMemberId = pcMemberId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(String reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
