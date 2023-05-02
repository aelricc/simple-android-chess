package com.example.androidchess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MoveList implements Serializable {

    public ArrayList<Move> moves;
    String Name;
    Date date;

    static final long serialVersionUID = 1L;

    public MoveList(){
        this.moves = new ArrayList<>();
        this.Name = null;
        this.date = null;
    }

    public void addMove(Move m){
        this.moves.add(m);
    }

    public void setName(String s){
        this.Name = s;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public void clear(){
        this.moves.clear();
    }

    public void undo(){
        moves.remove(moves.size()-1);
    }

    public boolean isEmpty(){
        return moves.isEmpty();
    }


}
