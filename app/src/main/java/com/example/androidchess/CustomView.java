package com.example.androidchess;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidchess.pieces.Bishop;
import com.example.androidchess.pieces.Knight;
import com.example.androidchess.pieces.Queen;
import com.example.androidchess.pieces.Rook;

public class CustomView extends FrameLayout {

    TextView textView;
    ImageView mImageView;
    int myColor;

    public CustomView(Context context, int color) {
        super(context);
        this.myColor = color;
        LayoutInflater.from(context).inflate(R.layout.custom_view, this);
        textView = (TextView)getRootView().findViewById(R.id.textView);
    }

    public void display(String filepath, String text, boolean isSelected) {
        mImageView = (ImageView) findViewById(R.id.imageView);
        switch (filepath) {
            case "bbishop" -> mImageView.setImageResource(R.drawable.b_bishop);
            case "wbishop" -> mImageView.setImageResource(R.drawable.w_bishop);
            case "blanksquare" -> mImageView.setImageResource(R.drawable.blanksquare);
            case "bking" -> mImageView.setImageResource(R.drawable.b_king);
            case "wking" -> mImageView.setImageResource(R.drawable.w_king);
            case "bpawn" -> mImageView.setImageResource(R.drawable.b_pawn);
            case "wpawn" -> mImageView.setImageResource(R.drawable.w_pawn);
            case "bqueen" -> mImageView.setImageResource(R.drawable.b_queen);
            case "wqueen" -> mImageView.setImageResource(R.drawable.w_queen);
            case "brook" -> mImageView.setImageResource(R.drawable.b_rook);
            case "wrook" -> mImageView.setImageResource(R.drawable.w_rook);
            case "bknight" -> mImageView.setImageResource(R.drawable.b_knight);
            case "wknight" -> mImageView.setImageResource(R.drawable.w_knight);

        }
        //textView.setText(text);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        textView.setBackgroundColor(isSelected? Color.RED : myColor);
    }

}