/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.Author;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class AuthorModel {

    public List<Author> getAuthorsByCountry(String country) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAuthorsByCountry(country);
    }

    public List<Author> getAllAuthorData() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllAuthorData();
    }

    public void updateAuthorItem(Author updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updateAuthorItem(updatedData);
    }

    public void insertNewAuthorItem(Author updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewAuthorItem(updatedData);
    }

    public int getAuthorId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAuthorId();
    }

    public void detleteAuthorItem(int itemId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.detleteAuthorItem(itemId);
    }

}
