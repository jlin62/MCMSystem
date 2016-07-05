/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.Submission;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class SubmissionModel {

    public List<Submission> getSubmissionByConfId(Integer confId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getSubmissionByConfId(confId);
    }
    

    public List<Submission> getAllSubmission() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllSubmission();
    }

    public void updateSubmissionItem(Submission updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updateSubmissionItem(updatedData);
    }

    public void insertNewSubmissionItem(Submission updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewSubmissionItem(updatedData);
    }

    public int getSubmissionId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getSubmissionId();
    }

    public void deleteSubmissionItem(int itemId) throws DatabaseException {
         FIT5148BDAO dao = new FIT5148BDAO();
         dao.deleteSubmissionItem(itemId);
    }
    
}
