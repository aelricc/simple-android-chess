package com.example.androidchess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<MoveList> implements View.OnClickListener{
    private ArrayList<MoveList> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtDate;
    }

    public ListAdapter(ArrayList<MoveList> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        MoveList game =(MoveList)object;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MoveList game = getItem(position);
        String date = game.getDate();
        String name = game.getName();
        // Check if an existing view is being reused, otherwise inflate the view
        CustomCellView customCellView = (convertView == null) ?
                new CustomCellView(parent.getContext()) : (CustomCellView) convertView;
        customCellView.display(name, date);
        return customCellView;
    }
}
