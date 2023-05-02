package com.example.androidchess;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class MoveList implements Parcelable{

    public ArrayList<Move> moves;
    String Name;
    String date;

    int orderAdded;

    public MoveList() {
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

    public void addMove(Move m) {
        this.moves.add(m);
    }

    public Move getMove(int i) {
        return moves.get(i);
    }

    public void setName(String s) {
        this.Name = s;
    }

    public String getName() {
        return this.Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderAdded(int i) {
        this.orderAdded = i;
    }

    public int getOrderAdded() {
        return this.orderAdded;
    }

    public void clear() {
        this.moves.clear();
    }

    public void undo() {
        moves.remove(moves.size() - 1);
    }

    public boolean isEmpty() {
        return moves.isEmpty();
    }

    public String toString() {
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

    public static Comparator<MoveList> StuNameComparator = new Comparator<MoveList>() {
        // Comparing attributes of students
        public int compare(MoveList s1, MoveList s2) {
            String MovelistName1
                    = s1.getName().toUpperCase();
            String MovelistName2
                    = s2.getName().toUpperCase();

            // Returning in ascending order
            return MovelistName1.compareTo(
                    MovelistName2);
        }
    };

    public static Comparator<MoveList> time = new Comparator<MoveList>() {
        // Method
        public int compare(MoveList s1, MoveList s2) {

            int rollno1 = s1.getOrderAdded();
            int rollno2 = s2.getOrderAdded();
            return rollno1 - rollno2;
        }
    };
}
