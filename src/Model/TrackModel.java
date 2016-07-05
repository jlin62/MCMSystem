/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.ConferenceTrack;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class TrackModel {

    public List<ConferenceTrack> getTrackByConferenceName(String conferenceName) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getTrackByConferenceName(conferenceName);
    }

    public List<ConferenceTrack> getAllData() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllData();
    }

    public void updateTrackItem(ConferenceTrack updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updateTrackItem(updatedData);
    }

    public void insertNewTrackItem(ConferenceTrack updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewTrackItem(updatedData);
    }

    public int getTrackId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getTrackId();
    }

    public void DetleteItem(int itemId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.detleteItem(itemId);
    }

}
