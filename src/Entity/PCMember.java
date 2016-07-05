/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class PCMember {
    private int id;
    private int trackId;
    private String firstName;
    private String surname;
    private String title;
    private String memberPosition;
    private String affiliation;
    private String email;

    public PCMember(int id, int trackId, String firstName, String surname, String title, String memberPosition, String affiliation, String email) {
        this.id = id;
        this.trackId = trackId;
        this.firstName = firstName;
        this.surname = surname;
        this.title = title;
        this.memberPosition = memberPosition;
        this.affiliation = affiliation;
        this.email = email;
    }

    public PCMember() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemberPosition() {
        return memberPosition;
    }

    public void setMemberPosition(String memberPosition) {
        this.memberPosition = memberPosition;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
