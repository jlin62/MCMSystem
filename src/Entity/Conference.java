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
public class Conference {
    private int id;
    private String conferenceName;
    private String conferenceYear;
    private String startDate;
    private String endDate;
    private String country;
    private String city;
    private String venue;
    private String email;

    public Conference() {
    }
    
    public Conference(int id, String conferenceName, String conferenceYear, String startDate, String endDate, String country, String city, String venue, String email) {
        this.id = id;
        this.conferenceName = conferenceName;
        this.conferenceYear = conferenceYear;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.city = city;
        this.venue = venue;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceYear() {
        return conferenceYear;
    }

    public void setConferenceYear(String conferenceYear) {
        this.conferenceYear = conferenceYear;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
