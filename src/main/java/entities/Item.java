package entities;

public class Item {

    private String name;
    private String description;
    private Boolean onLot;
    private String owner;
    //m.b. image
    public Item(String name, String description, String owner){

        this.name = name;
        this.description = description;
        this.onLot = false;
        this.owner = owner;
    }
    public void changeOwner(String newOwner){
        owner=newOwner;
    }
    public boolean engage(){
        if(onLot==false)onLot=true;
        else return false;
        return true;
    }
    public boolean isEngaged(){
        return onLot;
    }

    public String itemInfo(){
        return "Name: "+name + "\ndescription: "+description+"\nowner: "+ owner;
    }
}
