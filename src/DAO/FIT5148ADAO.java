/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Conference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class FIT5148ADAO {
    private final Connection con;
    private CallableStatement callableStatement = null;
    private ResultSet results = null;
    private String query = "";
    
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = 
             "jdbc:oracle:thin:@hippo.its.monash.edu.au:1521:FIT5148A";
    
//    private static final String DB_CONNECTION_B = 
//             "jdbc:oracle:thin:@hippo.its.monash.edu.au:1521:FIT5148B";
     
    private static final String DB_USER = "S22533206";
    private static final String DB_PASSWORD = "student";

    
    public FIT5148ADAO() throws DatabaseException {
        try {
//            DriverManager.registerDriver(new Ora);
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException("Unable to load driver!", ex);
        } catch (SQLException sqlEx) {
            throw new DatabaseException("Cannot connect to database!", sqlEx);
        }
    }

    public List<Conference> getAllConferences() throws DatabaseException {      
        query = "select * from Conferences";

        List<Conference> allConference = new ArrayList<>();
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                Conference conference = new Conference();
                conference.setCity(results.getString("CITY"));
                conference.setConferenceName(results.getNString("CONFERENCE_NAME"));
                conference.setConferenceYear(String.valueOf(results.getInt("CONFERENCE_YEAR")));
                conference.setStartDate(results.getString("START_DATE"));
                conference.setEndDate(results.getString("END_DATE"));
                conference.setCountry(results.getString("COUNTRY"));
                conference.setVenue(results.getString("VENUE"));
                conference.setEmail(results.getString("EMAIL"));
                conference.setId(results.getInt("ID"));
                allConference.add(conference);
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getConference()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getConference() " + sqlEx.toString());
            }
        }
        return allConference;
    }

    public void updateConferenceItem(Conference updatedData) throws DatabaseException{
        query = "UPDATE CONFERENCES SET " 
                + "CONFERENCE_NAME = " + "' " + updatedData.getConferenceName() + "', "
                + "CONFERENCE_YEAR = " + Integer.parseInt(updatedData.getConferenceYear()) + ", "
                + "START_DATE = to_date('" +updatedData.getStartDate().split(" ")[0]+ "', 'YYYY-MM-DD'), "
                + "End_DATE = to_date('"+updatedData.getEndDate().split(" ")[0]+ "', 'YYYY-MM-DD'), "
                + "COUNTRY = '" +updatedData.getCountry() +"', "
                + "CITY= '" + updatedData.getCity() + "', "
                + "VENUE = '" + updatedData.getVenue() + "', "
                + "EMAIL = '" + updatedData.getEmail() + "' "
                + "Where ID = " + updatedData.getId();
       
        int result = 0;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updateConference()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: updateConference()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateConference() " + sqlEx.toString());
            }
        }
    }

    public void deleteItem(int itemId) throws DatabaseException {
        query = "DELETE FROM CONFERENCES WHERE ID =  " + itemId;
        int result;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: deleteItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: deleteItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: deleteItem() " + sqlEx.toString());
            }
        }      
    }

    public List<Conference> getConferenceByCity(String city) throws DatabaseException {
        query = "select * from Conferences WHERE City = '"+city+"'";
        List<Conference> allConference = new ArrayList<>();
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                Conference conference = new Conference();
                conference.setCity(results.getString("CITY"));
                conference.setConferenceName(results.getNString("CONFERENCE_NAME"));
                conference.setConferenceYear(String.valueOf(results.getInt("CONFERENCE_YEAR")));
                conference.setStartDate(results.getString("START_DATE"));
                conference.setEndDate(results.getString("END_DATE"));
                conference.setCountry(results.getString("COUNTRY"));
                conference.setVenue(results.getString("VENUE"));
                conference.setEmail(results.getString("EMAIL"));
                conference.setId(results.getInt("ID"));
                allConference.add(conference);
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getConferenceByCity()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getConferenceByCity() " + sqlEx.toString());
            }
        }
        return allConference;
    }

    public void insertNewConferenceItem(Conference updatedData) throws DatabaseException {
        int result;
        query = "INSERT INTO CONFERENCES (CONFERENCE_NAME,CONFERENCE_YEAR,START_DATE,"
                + "END_DATE,COUNTRY,CITY,VENUE,EMAIL) VALUES ("
                +"'"+updatedData.getConferenceName()+"', "
                +Integer.parseInt(updatedData.getConferenceYear()) + ", "
                +"to_date('" + updatedData.getStartDate().split(" ")[0]+ "','YYYY-MM-DD'), "
                +"to_date('" + updatedData.getEndDate().split(" ")[0]+ "','YYYY-MM-DD'), "
                +"'"+updatedData.getCountry() + "', "
                +"'"+updatedData.getCity() + "', "
                +"'"+updatedData.getVenue()+"', "
                +"'"+updatedData.getEmail()+"')";
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewConferenceItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: insertNewConferenceItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewConferenceItem() " + sqlEx.toString());
            }
        }      
        
    }

    public int getConferenceId() throws DatabaseException {
        query = "select max(ID) ID  from CONFERENCES";
        int id=0;
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                id = results.getInt("ID");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getConferenceId()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getConferenceId() " + sqlEx.toString());
            }
        }
        return id;  
    }
    

    
    
}
