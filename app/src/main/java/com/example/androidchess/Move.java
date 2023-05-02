package com.example.androidchess;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Move implements Parcelable {
    int oldX, oldY, newX, newY;
    String type;

    public Move(int oldX, int oldY, int newX, int newY){
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
        this.type = null;
    }

    public Move(){
    }

    public Move(String s){
        this.type = s;
    }

    protected Move(Parcel in) {
        oldX = in.readInt();
        oldY = in.readInt();
        newX = in.readInt();
        newY = in.readInt();
        type = in.readString();
    }

    public static final Creator<Move> CREATOR = new Creator<Move>() {
        @Override
        public Move createFromParcel(Parcel in) {
            return new Move(in);
        }

        @Override
        public Move[] newArray(int size) {
            return new Move[size];
        }
    };

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

    public String toString(){
        return "( " + oldX + ", " + oldY + ") " + "to " + "( " + newX + ", " + newY + ") ";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(oldX);
        parcel.writeInt(oldY);
        parcel.writeInt(newX);
        parcel.writeInt(newY);
        parcel.writeString(type);
    }
}
