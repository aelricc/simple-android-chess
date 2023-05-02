package com.example.androidchess;


public class Move{
    int oldX, oldY, newX, newY;
    String type;
    public Move(int oldX, int oldY, int newX, int newY){
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
        this.type = null;
    }

    public Move(String s){
        this.type = s;
    }

    public String getType(){
        return type;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }
}
