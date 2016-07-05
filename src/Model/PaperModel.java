/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.Paper;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class PaperModel {

    public List<Paper> getPaperByConfId(Integer confId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getPaperByConfId(confId);
    }

    public List<Paper> getAllPapers() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllPapers();
    }

    public void updatePaperItem(Paper updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updatePaperItem(updatedData);
    }

    public void insertNewPaperItem(Paper updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewPaperItem(updatedData);
    }

    public int getPaperId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getPaperId();
    }

    public void deletePaperItem(int itemId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.deletePaperItem(itemId);
    }
    
}
