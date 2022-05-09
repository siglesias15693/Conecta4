package com.company;

public class Cell {
    private boolean ocuped;
    private char ch;

    public Cell(){
        ocuped=false;
        ch=' ';

    }

    public void setChar(char charact){
        ch=charact;
        this.setOcp();
    }
    public void setOcp(){ ocuped=true; }


    public char getChar(){ return ch; }
    public boolean getOcp(){
        return ocuped;
    }
}
