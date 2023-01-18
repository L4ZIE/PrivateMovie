package be;

import bll.IdGen;

public class Category {

    private int id;
    private String name;

    public Category (String name){
        this.name = name;
        this.id = IdGen.createCategoryId();
    }
    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}
