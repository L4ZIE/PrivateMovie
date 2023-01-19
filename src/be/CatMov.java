package be;

import bll.IdGen;

public class CatMov {

    private int catMovID;

    private int catID;
    private int movID;

    public int getCatMovID() {
        return catMovID;
    }

    public void setCatMovID(int catMovID) {
        this.catMovID = catMovID;
    }

    public CatMov(int catMovId, int catID, int movID) {
        this.catID = catID;
        this.movID = movID;
        this.catMovID= catMovId;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }
}
