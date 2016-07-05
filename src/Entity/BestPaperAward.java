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
public class BestPaperAward {
    private int id;
    private int conferenceTrackID;
    private String paperTitle;
    private double awardPrice;

    public BestPaperAward(int id, int conferenceTrackID, String paperTitle, double awardPrice) {
        this.id = id;
        this.conferenceTrackID = conferenceTrackID;
        this.paperTitle = paperTitle;
        this.awardPrice = awardPrice;
    }

    public BestPaperAward() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConferenceTrackID() {
        return conferenceTrackID;
    }

    public void setConferenceTrackID(int conferenceTrackID) {
        this.conferenceTrackID = conferenceTrackID;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public double getAwardPrice() {
        return awardPrice;
    }

    public void setAwardPrice(double awardPrice) {
        this.awardPrice = awardPrice;
    }
}
