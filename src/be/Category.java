package be;



public class Category {

    private int id;
    private String name;

    public Category (int index,String name){
        this.name = name;
        this.id = index;
    }
    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}
