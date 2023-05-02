package com.example.androidchess;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MoveList implements Parcelable {

    public ArrayList<Move> moves;
    String Name;
    String date;

    public MoveList(){
        this.moves = new ArrayList<>();
        this.Name = null;
        this.date = null;
    }

    protected MoveList(Parcel in) {
        Name = in.readString();
    }

    public static final Creator<MoveList> CREATOR = new Creator<MoveList>() {
        @Override
        public MoveList createFromParcel(Parcel in) {
            return new MoveList(in);
        }

        @Override
        public MoveList[] newArray(int size) {
            return new MoveList[size];
        }
    };

    public void addMove(Move m){
        this.moves.add(m);
    }

    public Move getMove(int i){
        return moves.get(i);
    }

    public void setName(String s){
        this.Name = s;
    }

    public String getName(){
        return this.Name;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
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

    public String toString(){
        return Name + ": " + this.moves.toString() + " Date: " + date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(Name);
    }
}
