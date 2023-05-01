package com.example.androidchess;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.androidchess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Piece[] strings;
    List<Integer> selectedPositions = new ArrayList<>();

    CustomAdapter(Piece[] strings) {
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Piece current = (Piece)this.getItem(position);
        int xpos = position/8;
        int ypos = position%8;
        int thisColor = Color.LTGRAY;
        if ((ypos % 2 == 0 && xpos % 2 !=0) || (ypos % 2 != 0 && xpos % 2 == 0)) {
            thisColor = Color.DKGRAY;
        }
        CustomView customView = (convertView == null) ?
                new CustomView(parent.getContext(), thisColor) : (CustomView) convertView;
        customView.display(current.getImageID(), String.valueOf(strings[position]), selectedPositions.contains(position));
        return customView;
    }

}
