package com.example.androidchess;

import java.util.ArrayList;

public class MoveList{

    public ArrayList<Move> moves;
    String Name;

    public MoveList(){
        moves = new ArrayList<>();
        Name = null;
    }

    public void addMove(Move m){
        this.moves.add(m);
    }

    public void setName(String s){
        this.Name = s;
    }

    public void clear(){
        this.moves.clear();
    }

    public void undo(){
        moves.remove(moves.size()-1);
    }


}
