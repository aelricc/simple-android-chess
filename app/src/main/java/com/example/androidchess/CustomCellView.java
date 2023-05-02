package com.example.androidchess;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomCellView extends RelativeLayout {

    TextView nameText;
    TextView dateText;

    public CustomCellView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.row_item, this);
        nameText = (TextView)getRootView().findViewById(R.id.name);
        dateText = (TextView)getRootView().findViewById(R.id.date);
    }

    public void display(String name, String date) {
        nameText.setText(name);
        dateText.setText(date);
    }
}
