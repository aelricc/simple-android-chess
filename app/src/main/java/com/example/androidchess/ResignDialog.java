package com.example.androidchess;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class ResignDialog extends AppCompatDialogFragment {

    String color;
    String endgame;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String messagetext = color + " wins by resignation\n\nWould you like this save this game?";
        String titletext = color + " Wins!";
        if(Objects.equals(endgame, "draw")){
            messagetext = "Would you like this save this game?";
            titletext = "Draw!";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titletext)
                .setMessage(messagetext)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEndgame(String end) {
        this.endgame = end;
    }
}
