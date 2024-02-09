package org.jp441.mymediatracker;

public class User {
    private String userName;
    private int itemsAdded;

    public User(){}

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, int itemsAdded){
        this(userName);
        this.itemsAdded = itemsAdded;
    }

    public String getUserName(){
        return userName;
    }

    public int getItemsAdded(){
        return itemsAdded;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setItemsAdded(int itemsAdded){
        this.itemsAdded = itemsAdded;
    }

    @Override
    public String toString(){
        return userName + " " + itemsAdded;
    }
}


