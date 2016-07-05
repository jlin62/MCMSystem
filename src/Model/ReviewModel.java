/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.Review;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class ReviewModel {

    public List<Review> getReviewsByRecommendation(String recommendation) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getReviewsByRecommendation(recommendation);
    }

    public List<Review> getAllReviews() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllReviews();
    }

    public void updateReviewItem(Review updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updateReviewItem(updatedData);
    }

    public void insertNewReviewItem(Review updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewReviewItem(updatedData);
    }

    public int getReviewId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getReviewId();
    }

    public void detleteReviewItem(int itemId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.detleteReviewItem(itemId);
    }
    
}
