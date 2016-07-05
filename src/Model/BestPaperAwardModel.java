/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.BestPaperAward;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class BestPaperAwardModel {

    public List<BestPaperAward> getBestPaperAwardByConfId(Integer confId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getBestPaperAwardByConfId(confId);
    }

    public List<BestPaperAward> getAllBestPaperAward() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllBestPaperAward();
    }

    public void updateBestPaperAwardItem(BestPaperAward updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updateBestPaperAwardItem(updatedData);
    }

    public void insertNewBestPaperAwardItem(BestPaperAward updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewBestPaperAwardItem(updatedData);
    }

    public void deleteBestPaperAwardItem(int itemId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.deleteBestPaperAwardItem(itemId);
    }

    public int getBestPaperAwardId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getBestPaperAwardId();
    }
    
}
