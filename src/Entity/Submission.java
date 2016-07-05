/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author jialu_lin
 */
public class Submission {
    private int id;
    private int authorId;
    private int paperId;
    private int authorOrd;
    private boolean isCorrespondingAuthor;

    public Submission(int id, int authorId, int paperId, int authorOrd, boolean isCorrespondingAuthor) {
        this.id = id;
        this.authorId = authorId;
        this.paperId = paperId;
        this.authorOrd = authorOrd;
        this.isCorrespondingAuthor = isCorrespondingAuthor;
    }

    public Submission() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getAuthorOrd() {
        return authorOrd;
    }

    public void setAuthorOrd(int authorOrd) {
        this.authorOrd = authorOrd;
    }

    public boolean isIsCorrespondingAuthor() {
        return isCorrespondingAuthor;
    }

    public void setIsCorrespondingAuthor(boolean isCorrespondingAuthor) {
        this.isCorrespondingAuthor = isCorrespondingAuthor;
    }
    
    
}
