package com.example.androidchess;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.*;
import java.util.ArrayList;

/**
 * Class description of Userlist
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class GameList implements Parcelable {
    /**List of Users**/
    public ArrayList<MoveList> games;

    public GameList(){
        this.games = new ArrayList<>();
    }


    protected GameList(Parcel in) {
        games = in.createTypedArrayList(MoveList.CREATOR);
    }

    public static final Creator<GameList> CREATOR = new Creator<GameList>() {
        @Override
        public GameList createFromParcel(Parcel in) {
            return new GameList(in);
        }

        @Override
        public GameList[] newArray(int size) {
            return new GameList[size];
        }
    };

    public ArrayList<MoveList> getGames(){
        return games;
    }

    public void addGames(MoveList game){
        games.add(game);
    }


    public String toString(){
        return games.toString();
    }

    public boolean isEmpty(){
        return games.isEmpty();
    }

    public int size(){
        return games.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeTypedList(games);
    }
}
