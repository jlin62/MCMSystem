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
public class ConferenceTrack {
    private int id;
    private int conferenceId;
    private Conference conference;
    private String conferenceTrackName;
    private String description;

    public ConferenceTrack(int id, int conferenceId, String conferenceTrackName, String description) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.conferenceTrackName = conferenceTrackName;
        this.description = description;
    }

    public ConferenceTrack() {       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceTrackName() {
        return conferenceTrackName;
    }

    public void setConferenceTrackName(String conferenceTrackName) {
        this.conferenceTrackName = conferenceTrackName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
