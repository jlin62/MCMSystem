/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Author;
import Entity.BestPaperAward;
import Entity.Conference;
import Entity.ConferenceTrack;
import Entity.PCMember;
import Entity.Paper;
import Entity.Review;
import Entity.Submission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class FIT5148BDAO {

    private final Connection con;
    private CallableStatement callableStatement = null;
    private ResultSet results = null;
    private String query = "";

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION
            = "jdbc:oracle:thin:@hippo.its.monash.edu.au:1521:FIT5148B";
    private static final String DB_USER = "S22533206";
    private static final String DB_PASSWORD = "student";

    public FIT5148BDAO() throws DatabaseException {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException("Unable to load driver!", ex);
        } catch (SQLException sqlEx) {
            throw new DatabaseException("Cannot connect to database!", sqlEx);
        }
    }

    public List<ConferenceTrack> getAllData() throws DatabaseException {
        query = "select TRACKS.ID,TRACKS.CONFERENCE_ID,conf.CONFERENCE_NAME,"
                + "TRACKS.TRACK_NAME,TRACKS.DESCRIPTION\n"
                + "from TRACKS inner join CONFERENCES@FIT5148A conf on \n"
                + "TRACKS.CONFERENCE_ID = conf.ID";
        List<ConferenceTrack> allTracks = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                ConferenceTrack tracks = new ConferenceTrack();
                tracks.setId(results.getInt("ID"));
                tracks.setConferenceId(results.getInt("CONFERENCE_ID"));
                tracks.setConferenceTrackName(results.getString("TRACK_NAME"));
                tracks.setDescription(results.getString("DESCRIPTION"));
                Conference conference = new Conference();
                conference.setConferenceName(results.getString("CONFERENCE_NAME"));
                tracks.setConference(conference);
                allTracks.add(tracks);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllData() from ConferenceTrack", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllData() from ConferenceTrack " + sqlEx.toString());
            }
        }
        return allTracks;
    }

    public List<ConferenceTrack> getTrackByConferenceName(String conferenceName) throws DatabaseException {
        query = "select TRACKS.ID,TRACKS.CONFERENCE_ID,conf.CONFERENCE_NAME,"
                + "TRACKS.TRACK_NAME,TRACKS.DESCRIPTION\n"
                + "from TRACKS inner join CONFERENCES@FIT5148A conf on \n"
                + "TRACKS.CONFERENCE_ID = conf.ID where conf.CONFERENCE_NAME Like '%"
                + conferenceName + "%'";
        List<ConferenceTrack> allTracks = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                ConferenceTrack tracks = new ConferenceTrack();
                tracks.setId(results.getInt("ID"));
                tracks.setConferenceId(results.getInt("CONFERENCE_ID"));
                tracks.setConferenceTrackName(results.getString("TRACK_NAME"));
                tracks.setDescription(results.getString("DESCRIPTION"));
                Conference conference = new Conference();
                conference.setConferenceName(results.getString("CONFERENCE_NAME"));
                tracks.setConference(conference);
                allTracks.add(tracks);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getTrackByConferenceName()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getTrackByConferenceName() " + sqlEx.toString());
            }
        }
        return allTracks;
    }

    public void updateTrackItem(ConferenceTrack updatedData) throws DatabaseException {
        query = "UPDATE TRACKS\n"
                + "SET CONFERENCE_ID = " + updatedData.getConferenceId()
                + ", \n"
                + "TRACK_NAME = '" + updatedData.getConferenceTrackName()
                + "', \n"
                + "TRACKS.DESCRIPTION = '" + updatedData.getDescription()
                + "' where TRACKS.ID= " + updatedData.getId();
        int result = 0;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updateTrackItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: updateTrackItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateTrackItem() " + sqlEx.toString());
            }
        }
    }

    public void insertNewTrackItem(ConferenceTrack updatedData) throws DatabaseException {
        int result;
        query = "insert into TRACKS(CONFERENCE_ID,TRACK_NAME,DESCRIPTION) VALUES ("
                + updatedData.getConferenceId() + ", '"
                + updatedData.getConferenceTrackName() + "', '"
                + updatedData.getDescription() + "')";
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewTrackItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: insertNewTrackItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewConferenceItem() " + sqlEx.toString());
            }
        }
    }

    public int getTrackId() throws DatabaseException {
        query = "select max(ID) ID  from TRACKS";
        int id = 0;
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                id = results.getInt("ID");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getTrackId()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getTrackId() " + sqlEx.toString());
            }
        }
        return id;
    }

    public void detleteItem(int itemId) throws DatabaseException {
        query = "DELETE FROM TRACKS WHERE ID =  " + itemId;
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: deleteItem() in ConferenceTrack");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: deleteItem() in ConferenceTrack", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: deleteItem() in ConferenceTrack " + sqlEx.toString());
            }
        }
    }

    public List<Author> getAuthorsByCountry(String country) throws DatabaseException {
        query = "select ID,FIRST_NAME,SURNAME,AFFILIATION,COUNTRY,EMAIL,CONTACT_NUMBER\n"
                + "from AUTHORS where COUNTRY = '" + country + "'";
        List<Author> allAuthors = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                Author author = new Author();
                author.setId(results.getInt("ID"));
                author.setFirstName(results.getString("FIRST_NAME"));
                author.setSurname(results.getString("SURNAME"));
                author.setAffiliation(results.getString("AFFILIATION"));
                author.setCountry(results.getString("COUNTRY"));
                author.setEmail(results.getString("EMAIL"));
                author.setContactNumber(results.getString("CONTACT_NUMBER"));
                allAuthors.add(author);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllAuthorData()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllAuthorData() " + sqlEx.toString());
            }
        }
        return allAuthors;
    }

    public List<Author> getAllAuthorData() throws DatabaseException {
        query = "select ID,FIRST_NAME,SURNAME,AFFILIATION,COUNTRY,EMAIL,CONTACT_NUMBER\n"
                + "from AUTHORS";
        List<Author> allAuthors = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                Author author = new Author();
                author.setId(results.getInt("ID"));
                author.setFirstName(results.getString("FIRST_NAME"));
                author.setSurname(results.getString("SURNAME"));
                author.setAffiliation(results.getString("AFFILIATION"));
                author.setCountry(results.getString("COUNTRY"));
                author.setEmail(results.getString("EMAIL"));
                author.setContactNumber(results.getString("CONTACT_NUMBER"));
                allAuthors.add(author);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllAuthorData()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllAuthorData() " + sqlEx.toString());
            }
        }
        return allAuthors;
    }

    public void updateAuthorItem(Author updatedData) throws DatabaseException {
        query = "update AUTHORS\n"
                + "set FIRST_NAME = '" + updatedData.getFirstName() + "', "
                + "SURNAME = '" + updatedData.getSurname() + "', "
                + "AFFILIATION = '" + updatedData.getAffiliation() + "', "
                + "COUNTRY = '" + updatedData.getCountry() + "', "
                + "EMAIL = '" + updatedData.getEmail() + "', "
                + "CONTACT_NUMBER = '" + updatedData.getContactNumber() + "' "
                + "where ID = " + updatedData.getId();
        int result = 0;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updateAuthorItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: updateAuthorItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateAuthorItem() " + sqlEx.toString());
            }
        }
    }

    public void insertNewAuthorItem(Author updatedData) throws DatabaseException {
        query = "insert into AUTHORS (FIRST_NAME,SURNAME,AFFILIATION,COUNTRY,"
                + "EMAIL,CONTACT_NUMBER) Values("
                + "'" + updatedData.getFirstName() + "', "
                + "'" + updatedData.getSurname() + "', "
                + "'" + updatedData.getAffiliation() + "', "
                + "'" + updatedData.getCountry() + "', "
                + "'" + updatedData.getEmail() + "' ,"
                + "'" + updatedData.getContactNumber() + "')";
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewAuthorItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: insertNewAuthorItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewAuthorItem() " + sqlEx.toString());
            }
        }
    }

    public int getAuthorId() throws DatabaseException {
        query = "select max(ID) ID  from AUTHORS";
        int id = 0;
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                id = results.getInt("ID");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getTrackId()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getTrackId() " + sqlEx.toString());
            }
        }
        return id;
    }

    public void detleteAuthorItem(int itemId) throws DatabaseException {
        query = "DELETE FROM AUTHORS WHERE ID =  " + itemId;
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: detleteAuthorItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: detleteAuthorItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: detleteAuthorItem()" + sqlEx.toString());
            }
        }
    }

    public void updatePCMemberItem(PCMember updatedData) throws DatabaseException {
        query = "update PCMEMBERS\n"
                + "set  TRACK_ID= " + updatedData.getTrackId()
                + ", FIRST_NAME  = '" + updatedData.getFirstName()
                + "', SURNAME = '" + updatedData.getSurname()
                + "', TITLE= '" + updatedData.getTitle()
                + "', MEMBER_POSITION = '" + updatedData.getMemberPosition()
                + "', AFFILIATION= '" + updatedData.getAffiliation()
                + "', EMAIL = '" + updatedData.getEmail()
                + "' where ID = " + updatedData.getId();
        int result = 0;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updatePCMemberItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: updatePCMemberItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updatePCMemberItem() " + sqlEx.toString());
            }
        }

    }

    public void insertNewPCMemberItem(PCMember updatedData) throws DatabaseException {
        query = "insert into PCMEMBERS (TRACK_ID,FIRST_NAME,SURNAME,TITLE,MEMBER_POSITION,\n"
                + "AFFILIATION,EMAIL) values("
                + updatedData.getTrackId()+ ", '"
                + updatedData.getFirstName() + "', '"
                + updatedData.getSurname() + "', '"
                + updatedData.getTitle() + "', '"
                + updatedData.getMemberPosition() + "', '"
                + updatedData.getAffiliation() + "', '"
                + updatedData.getEmail() + "')";
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewPCMemberItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: insertNewPCMemberItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewPCMemberItem() " + sqlEx.toString());
            }
        }
    }

    public int getPCMemberId() throws DatabaseException {
        query = "select max(ID) ID  from PCMEMBERS";
        int id = 0;
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                id = results.getInt("ID");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getPCMemberId()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getPCMemberId() " + sqlEx.toString());
            }
        }
        return id;
    }

    public void detletePCMemberItem(int itemId) throws DatabaseException {
        query = "DELETE FROM PCMEMBERS WHERE ID =  " + itemId;
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: detletePCMemberItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: detletePCMemberItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: detletePCMemberItem()" + sqlEx.toString());
            }
        }
    }

    public List<PCMember> getPCMembersByAffiliation(String affiliation) throws DatabaseException {
        query = "select ID,TRACK_ID,FIRST_NAME,SURNAME,TITLE,"
                + "MEMBER_POSITION,AFFILIATION,EMAIL from PCMEMBERS "
                + "WHERE AFFILIATION = '" + affiliation + "'";

        List<PCMember> allPCMembers = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                PCMember member = new PCMember();
                member.setId(results.getInt("ID"));
                member.setFirstName(results.getString("FIRST_NAME"));
                member.setSurname(results.getString("SURNAME"));
                member.setAffiliation(results.getString("AFFILIATION"));
                member.setMemberPosition(results.getString("MEMBER_POSITION"));
                member.setEmail(results.getString("EMAIL"));
                member.setTitle(results.getString("TITLE"));
                member.setTrackId(results.getInt("TRACK_ID"));
                allPCMembers.add(member);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllPCMembers()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllPCMembers() " + sqlEx.toString());
            }
        }
        return allPCMembers;
    }

    public List<PCMember> getAllPCMembers() throws DatabaseException {
        query = "select ID,TRACK_ID,FIRST_NAME,SURNAME,TITLE,"
                + "MEMBER_POSITION,AFFILIATION,EMAIL from PCMEMBERS";

        List<PCMember> allPCMembers = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                PCMember member = new PCMember();
                member.setId(results.getInt("ID"));
                member.setFirstName(results.getString("FIRST_NAME"));
                member.setSurname(results.getString("SURNAME"));
                member.setAffiliation(results.getString("AFFILIATION"));
                member.setMemberPosition(results.getString("MEMBER_POSITION"));
                member.setEmail(results.getString("EMAIL"));
                member.setTitle(results.getString("TITLE"));
                member.setTrackId(results.getInt("TRACK_ID"));
                allPCMembers.add(member);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllPCMembers()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllPCMembers() " + sqlEx.toString());
            }
        }
        return allPCMembers;
    }

    public List<Review> getReviewsByRecommendation(String recommendation) throws DatabaseException {
        query = "select * from REVIEWS where RECOMMEDATIONS = '"
                + recommendation + "'";
        List<Review> allReviews = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                Review review = new Review();
                review.setPcMemberId(results.getInt("PC_MEMBER_ID"));
                review.setPaperId(results.getInt("PAPER_ID"));
                review.setRecommendations(results.getNString("RECOMMEDATIONS"));
                review.setDueDate(results.getString("DUE_DATE"));
                review.setReviewedDate(results.getString("REVIEWED_DATE"));
                review.setComments(results.getString("COMMENTS"));
                review.setId(results.getInt("ID"));
                allReviews.add(review);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllReviews()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllReviews() " + sqlEx.toString());
            }
        }
        return allReviews;
    }

    public List<Review> getAllReviews() throws DatabaseException {
        query = "select * from REVIEWS";
        List<Review> allReviews = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                Review review = new Review();
                review.setPcMemberId(results.getInt("PC_MEMBER_ID"));
                review.setPaperId(results.getInt("PAPER_ID"));
                review.setRecommendations(results.getNString("RECOMMEDATIONS"));
                review.setDueDate(results.getString("DUE_DATE"));
                review.setReviewedDate(results.getString("REVIEWED_DATE"));
                review.setComments(results.getString("COMMENTS"));
                review.setId(results.getInt("ID"));
                allReviews.add(review);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllReviews()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllReviews() " + sqlEx.toString());
            }
        }
        return allReviews;
    }

    public void updateReviewItem(Review updatedData) throws DatabaseException {
        query = "update REVIEWS SET "
                + "PC_MEMBER_ID = " + updatedData.getPcMemberId() + ", "
                + "PAPER_ID = " + updatedData.getPaperId() + ", "
                + "DUE_DATE = to_date('" + updatedData.getDueDate().split(" ")[0] + "', 'YYYY-MM-DD'), "
                + "REVIEWED_DATE = to_date('" + updatedData.getReviewedDate().split(" ")[0] + "', 'YYYY-MM-DD'), "
                + "RECOMMEDATIONS = '" + updatedData.getRecommendations() + "', "
                + "COMMENTS= '" + updatedData.getComments() + "' "
                + "Where ID = " + updatedData.getId();
        int result = 0;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updateReviewItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: updateReviewItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateReviewItem() " + sqlEx.toString());
            }
        }
    }

    public void insertNewReviewItem(Review updatedData) throws DatabaseException {
        int result;
        query = "INSERT INTO REVIEWS (PC_MEMBER_ID,PAPER_ID,RECOMMEDATIONS,DUE_DATE,"
                + "REVIEWED_DATE,COMMENTS) VALUES ("
                + updatedData.getPcMemberId() + ", "
                + updatedData.getPaperId() + ", "
                + "'" + updatedData.getRecommendations() + "', "
                + "to_date('" + updatedData.getDueDate().split(" ")[0] + "','YYYY-MM-DD'), "
                + "to_date('" + updatedData.getReviewedDate().split(" ")[0] + "','YYYY-MM-DD'), "
                + "'" + updatedData.getComments() + "')";
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewReviewItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: insertNewReviewItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewReviewItem() " + sqlEx.toString());
            }
        }
    }

    public int getReviewId() throws DatabaseException {
        query = "select max(ID) ID  from REVIEWS";
        int id = 0;
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                id = results.getInt("ID");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getReviewId()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getReviewId() " + sqlEx.toString());
            }
        }
        return id;
    }

    public void detleteReviewItem(int itemId) throws DatabaseException {
        query = "DELETE FROM REVIEWS WHERE ID =  " + itemId;
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: detleteReviewItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: detleteReviewItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: detleteReviewItem() " + sqlEx.toString());
            }
        }
    }

    public List<Paper> getPaperByConfId(Integer confId) throws DatabaseException {
        query = "Select p.ID,TRACK_ID,TITLE,PAPERTYPE,ABSTRACT,"
                + "SUBMISSION_DATE,CONFERENCE_ID from PAPERS p inner join TRACKS t "
                + "on p.TRACK_ID = t.ID where t.CONFERENCE_ID = " + confId;
        List<Paper> allPaper = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                Paper paper = new Paper();
                paper.setId(results.getInt("ID"));
                paper.setConferenceTrackId(results.getInt("TRACK_ID"));
                paper.setPaperTitle(results.getString("TITLE"));
                paper.setPaperTitle(results.getString("PAPERTYPE"));
                paper.setPaperAbstract(results.getString("ABSTRACT"));
                paper.setSubmissionDate(results.getString("SUBMISSION_DATE"));
                allPaper.add(paper);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getPaperByConfId()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getPaperByConfId() " + sqlEx.toString());
            }
        }
        return allPaper;
    }

    public List<Paper> getAllPapers() throws DatabaseException {
        query = "Select * from PAPERS";
        List<Paper> allPaper = new ArrayList<>();
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                Paper paper = new Paper();
                paper.setId(results.getInt("ID"));
                paper.setConferenceTrackId(results.getInt("TRACK_ID"));
                paper.setPaperTitle(results.getString("TITLE"));
                paper.setPaperType(results.getString("PAPERTYPE"));
                paper.setPaperAbstract(results.getString("ABSTRACT"));
                paper.setSubmissionDate(results.getString("SUBMISSION_DATE"));
                allPaper.add(paper);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getAllPapers()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllPapers() " + sqlEx.toString());
            }
        }
        return allPaper;
    }

    public void updatePaperItem(Paper updatedData) throws DatabaseException {
        query = "UPDATE PAPERS SET TRACK_ID = "
                + updatedData.getConferenceTrackId() + ", TITLE = "
                + "'" + updatedData.getPaperTitle() + "', PAPERTYPE = "
                + "'" + updatedData.getPaperType() + "', ABSTRACT = "
                + "'" + updatedData.getPaperAbstract() + "', SUBMISSION_DATE = "
                + "to_date('" + updatedData.getSubmissionDate().split(" ")[0]
                + "','YYYY-MM-DD') where ID = " + updatedData.getId();

        int result = 0;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updatePaperItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: updatePaperItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateConference() " + sqlEx.toString());
            }
        }
    }

    public void insertNewPaperItem(Paper updatedData) throws DatabaseException {
        query = "insert into PAPERS(TRACK_ID,TITLE,PAPERTYPE,ABSTRACT,SUBMISSION_DATE)\n"
                + "values ("
                + updatedData.getConferenceTrackId() + ", "
                + "'" + updatedData.getPaperTitle() + "', "
                + "'" + updatedData.getPaperType() + "', "
                + "'" + updatedData.getPaperAbstract() + "', "
                + "to_date('" + updatedData.getSubmissionDate().split(" ")[0]
                + "','YYYY-MM-DD'))";
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewPaperItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: insertNewPaperItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewPaperItem() " + sqlEx.toString());
            }
        }

    }

    public void deletePaperItem(int itemId) throws DatabaseException {
        query = "DELETE FROM papers WHERE ID =  " + itemId;
        int result;
        try {
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: deletePaperItem()");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: deletePaperItem()", ex);
        } finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: deletePaperItem() " + sqlEx.toString());
            }
        }
    }

    public int getPaperId() throws DatabaseException {
        query = "select max(ID) ID  from PAPERS";
        int id = 0;
        try {
            callableStatement = con.prepareCall(query);
            results = callableStatement.executeQuery();
            while (results.next()) {
                id = results.getInt("ID");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot execute query! Source: getPaperId()", ex);
        } finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getPaperId() " + sqlEx.toString());
            }
        }
        return id;
    }

    public List<BestPaperAward> getBestPaperAwardByConfId(Integer confId) throws DatabaseException {
        query = "select BESTPAPERAWARDS.ID,TRACK_ID,PAPER_TITLE,AWARD_PRICE,"
                + "CONFERENCE_ID from BESTPAPERAWARDS inner join TRACKS on "
                + "BESTPAPERAWARDS.TRACK_ID = TRACKS.ID where TRACKS.CONFERENCE_ID = "
                + confId;
        List<BestPaperAward> allAwards = new ArrayList<>();
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                BestPaperAward award = new BestPaperAward();
                award.setAwardPrice(results.getDouble("AWARD_PRICE"));
                award.setConferenceTrackID(results.getInt("TRACK_ID"));
                award.setPaperTitle(results.getString("PAPER_TITLE"));
                award.setId(results.getInt("ID"));
                allAwards.add(award);
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getAllBestPaperAward()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllBestPaperAward() " + sqlEx.toString());
            }
        }
        return allAwards;
    }

    public List<BestPaperAward> getAllBestPaperAward() throws DatabaseException {
        query = "select * from BESTPAPERAWARDS";
        List<BestPaperAward> allAwards = new ArrayList<>();
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                BestPaperAward award = new BestPaperAward();
                award.setAwardPrice(results.getDouble("AWARD_PRICE"));
                award.setConferenceTrackID(results.getInt("TRACK_ID"));
                award.setPaperTitle(results.getString("PAPER_TITLE"));
                award.setId(results.getInt("ID"));
                allAwards.add(award);
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getAllBestPaperAward()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllBestPaperAward() " + sqlEx.toString());
            }
        }
        return allAwards;     
    }

    public void updateBestPaperAwardItem(BestPaperAward updatedData) throws DatabaseException {
        query = "UPDATE BESTPAPERAWARDS SET "
                + "TRACK_ID = " + updatedData.getConferenceTrackID() +", "
                +"PAPER_TITLE = '" + updatedData.getPaperTitle() + "', "
                +"AWARD_PRICE = " +updatedData.getAwardPrice() 
                +" where ID=" + updatedData.getId();
        
        int result = 0;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updateBestPaperAwardItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: updateBestPaperAwardItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateBestPaperAwardItem() " + sqlEx.toString());
            }
        }
    }

    public void insertNewBestPaperAwardItem(BestPaperAward updatedData) throws DatabaseException {
        query = "INSERT INTO BESTPAPERAWARDS(TRACK_ID,PAPER_TITLE,AWARD_PRICE) "
                +"VALUES ("
                +updatedData.getConferenceTrackID() + ", "
                +"'" + updatedData.getPaperTitle() + "', "
                +updatedData.getAwardPrice() + ")";
        int result;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewBestPaperAwardItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: insertNewBestPaperAwardItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewBestPaperAwardItem() " + sqlEx.toString());
            }
        }    
    }

    public void deleteBestPaperAwardItem(int itemId) throws DatabaseException {
        query = "DELETE FROM BESTPAPERAWARDS WHERE ID =  " + itemId;
        int result;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: deleteBestPaperAwardItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: deleteBestPaperAwardItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: deleteBestPaperAwardItem() " + sqlEx.toString());
            }
        }      
    }

    public int getBestPaperAwardId() throws DatabaseException {
        query = "select max(ID) ID  from BESTPAPERAWARDS";
        int id=0;
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                id = results.getInt("ID");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getBestPaperAwardId()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getBestPaperAwardId() " + sqlEx.toString());
            }
        }
        return id;  
    }

    public List<Submission> getSubmissionByConfId(Integer confId) throws DatabaseException {
        query = "Select * from SUBMISSIONS s inner join PAPERS p "
                + "on s.PAPER_ID = p.ID inner join TRACKS t "
                + "on p.TRACK_ID = t.ID where t.CONFERENCE_ID = " + confId;
        List<Submission> allSubmission = new ArrayList<>();
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                Submission submission = new Submission();
                submission.setAuthorId(results.getInt("AUTHOR_ID"));
                submission.setPaperId(results.getInt("PAPER_ID"));
                submission.setAuthorOrd(results.getInt("AUTHOR_ORDER"));
                submission.setIsCorrespondingAuthor(results.getBoolean("IS_CORRESPONDING_AUTHOR"));               
                submission.setId(results.getInt("ID"));
                allSubmission.add(submission);
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getAllSubmission()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllSubmission() " + sqlEx.toString());
            }
        }
        return allSubmission;
    }

    public List<Submission> getAllSubmission() throws DatabaseException {
        query = "Select * from SUBMISSIONS";
        List<Submission> allSubmission = new ArrayList<>();
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                Submission submission = new Submission();
                submission.setAuthorId(results.getInt("AUTHOR_ID"));
                submission.setPaperId(results.getInt("PAPER_ID"));
                submission.setAuthorOrd(results.getInt("AUTHOR_ORDER"));
                submission.setIsCorrespondingAuthor(results.getBoolean("IS_CORRESPONDING_AUTHOR"));               
                submission.setId(results.getInt("ID"));
                allSubmission.add(submission);
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getAllSubmission()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getAllSubmission() " + sqlEx.toString());
            }
        }
        return allSubmission;
    }

    public void updateSubmissionItem(Submission updatedData) throws DatabaseException {
        int is_correspondingAuthor = 0;
        if (updatedData.isIsCorrespondingAuthor()){
            is_correspondingAuthor = 1;
        }
        query = "UPDATE SUBMISSIONS SET "
                +"AUTHOR_ID = " + updatedData.getAuthorId() + ", "
                +"PAPER_ID = " + updatedData.getPaperId() + ", "
                +"AUTHOR_ORDER= " + updatedData.getAuthorOrd() + ", "
                +"IS_CORRESPONDING_AUTHOR = " + is_correspondingAuthor 
                + " where ID = " + updatedData.getId();
        
        int result = 0;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to update record! Source: updateSubmissionItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: updateSubmissionItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: updateSubmissionItem() " + sqlEx.toString());
            }
        }
                
    }

    public void insertNewSubmissionItem(Submission updatedData) throws DatabaseException {
        int result;
        int is_correspondingAuthor = 0;
        if (updatedData.isIsCorrespondingAuthor()){
            is_correspondingAuthor = 1;
        }
        query = "insert into SUBMISSIONS (AUTHOR_ID,PAPER_ID,AUTHOR_ORDER,IS_CORRESPONDING_AUTHOR) "
                + "VALUES ("
                + updatedData.getAuthorId() + ", "
                + updatedData.getPaperId() + ", "
                + updatedData.getAuthorOrd() + ", "
                + is_correspondingAuthor + ")";
         try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: insertNewSubmissionItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: insertNewSubmissionItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: insertNewSubmissionItem() " + sqlEx.toString());
            }
        }      
        
    }

    public int getSubmissionId() throws DatabaseException {
        query = "select max(ID) ID  from SUBMISSIONS";
        int id=0;
        try{
            callableStatement = con.prepareCall(query);         
            results = callableStatement.executeQuery();            
            while(results.next()){
                id = results.getInt("ID");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: getSubmissionId()", ex);
        }finally {
            try {
                con.close();
                results = null;
                query = "";
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: getSubmissionId() " + sqlEx.toString());
            }
        }
        return id;  
    }

    public void deleteSubmissionItem(int itemId) throws DatabaseException {
        query = "DELETE FROM SUBMISSIONS WHERE ID =  " + itemId;
        int result;
        try{
            callableStatement = con.prepareCall(query);
            result = callableStatement.executeUpdate(query);
            if (result == 0) {
                throw new DatabaseException("Unable to delete record! Source: deleteSubmissionItem()");
            }
        }catch(SQLException ex){
            throw new DatabaseException("Cannot execute query! Source: deleteSubmissionItem()", ex);
        }finally {
            try {
                con.close();
                query = null;
            } catch (SQLException sqlEx) {
                System.out.println("Unable to disconnect! Source: deleteSubmissionItem() " + sqlEx.toString());
            }
        }      
    }
}
