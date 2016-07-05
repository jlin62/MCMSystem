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
public class Paper {
    private int id;
    private int conferenceTrackId;
    private String paperTitle;
    private String paperType;
    private String paperAbstract;
    private String submissionDate;

    public Paper(int id, int conferenceTrackId, String paperTitle, String paperType, String paperAbstract, String submissionDate) {
        this.id = id;
        this.conferenceTrackId = conferenceTrackId;
        this.paperTitle = paperTitle;
        this.paperType = paperType;
        this.paperAbstract = paperAbstract;
        this.submissionDate = submissionDate;
    }

    public Paper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConferenceTrackId() {
        return conferenceTrackId;
    }

    public void setConferenceTrackId(int conferenceTrackId) {
        this.conferenceTrackId = conferenceTrackId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
}
