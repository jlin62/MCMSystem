/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DatabaseException;
import DAO.FIT5148BDAO;
import Entity.PCMember;
import java.util.List;

/**
 *
 * @author jialu_lin
 */
public class PCMemberModel {

    public void updatePCMemberItem(PCMember updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.updatePCMemberItem(updatedData);
    }

    public void insertNewPCMemberItem(PCMember updatedData) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.insertNewPCMemberItem(updatedData);
    }

    public int getPCMemberId() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getPCMemberId();
    }

    public void detletePCMemberItem(int itemId) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        dao.detletePCMemberItem(itemId);
    }

    public List<PCMember> getPCMembersByAffiliation(String affiliation) throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getPCMembersByAffiliation(affiliation);
    }

    public List<PCMember> getAllPCMembers() throws DatabaseException {
        FIT5148BDAO dao = new FIT5148BDAO();
        return dao.getAllPCMembers();
    }

}
