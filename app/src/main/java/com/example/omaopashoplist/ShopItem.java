package com.example.omaopashoplist;

public class ShopItem {
private String person ,ware;
private int menge;
private boolean wichtig;

//----------------------Konstrukturen----------------------------------------
    public ShopItem(String person, String ware, int menge, boolean wichtig) {
        this.person = person;
        this.ware = ware;
        this.menge = menge;
        this.wichtig = wichtig;
    }

    public ShopItem() {
    }
//----------------getter und setter------------------------------------------------
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getWare() {
        return ware;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public boolean isWichtig() {
        return wichtig;
    }

    public void setWichtig(boolean wichtig) {
        this.wichtig = wichtig;
    }
    //------------------toString-----------------------------------------------
    private String wichtigToString(){
        if(this.wichtig)
            return ", wichtig!";
        else
            return "";
    }
    public String toString(){
        return this.person+", "+this.ware+", "+Integer.toString(this.menge)+wichtigToString();
    }
}
