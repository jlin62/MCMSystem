/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148ADAO;
import Entity.Conference;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class ConferenceModel {

    public List<Conference> getAllData() throws DatabaseException {
        FIT5148ADAO dao = new FIT5148ADAO();
        List<Conference> allConferences = dao.getAllConferences();
        return allConferences;
    }

    public void DetleteItem(int itemId) throws DatabaseException {
        FIT5148ADAO dao = new FIT5148ADAO();
        dao.deleteItem(itemId);
        
    }

    public void updateConferenceItem(Conference updatedData) throws DatabaseException{
        FIT5148ADAO dao = new FIT5148ADAO();
        dao.updateConferenceItem(updatedData);
    }

    public void insertNewConferenceItem(Conference updatedData) throws DatabaseException {
        FIT5148ADAO dao = new FIT5148ADAO();
        dao.insertNewConferenceItem(updatedData);
    }

    public int getConferenceId() throws DatabaseException {
        FIT5148ADAO dao = new FIT5148ADAO();
        return dao.getConferenceId();
    }

    public List<Conference> getConferenceByCity(String city) throws DatabaseException {
        FIT5148ADAO dao = new FIT5148ADAO();
        List<Conference> allConferences = dao.getConferenceByCity(city);
        return allConferences;
    }
    
}
