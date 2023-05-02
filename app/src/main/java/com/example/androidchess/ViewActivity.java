package com.example.androidchess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidchess.chess.Board;
import com.example.androidchess.pieces.BlankSquare;
import com.example.androidchess.pieces.Piece;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewActivity extends AppCompatActivity{

    static Piece[] numbers = new Piece[64];
    MoveList thisGame;
    PLAYER[] current;
    GAMESTATE game;

    int moveCounter;

    public enum GAMESTATE{
        /** Active game */
        ACTIVE,
        /** Black wins */
        WIN,
        /** White wins */
        /** Game is a draw */
        DRAW,

        CLOSED
    }

    public enum PLAYER{
        BLACK, WHITE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game = GAMESTATE.ACTIVE;
        current = new PLAYER[1];
        current[0] = PLAYER.WHITE;
        Board testBoard = new Board();
        BlankSquare b = new BlankSquare("b");
        BlankSquare w = new BlankSquare("w");

        numbers = flattenArray(testBoard.board, numbers);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgame);

        GridView gridView = (GridView) findViewById(R.id.chessboard);
        final CustomAdapter adapter = new CustomAdapter(numbers);
        gridView.setAdapter(adapter);
        final TextView status = (TextView) findViewById(R.id.TurnName);
        status.setText("White's move!");

        //savedGame.clear();
        thisGame = getIntent().getParcelableExtra("game");

        final Button next = findViewById(R.id.next);
        moveCounter = 0;
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(moveCounter != thisGame.getSize()-1){
                    Move currMove = thisGame.getMove(moveCounter);

                    String color = "w";
                    if(current[0] == PLAYER.BLACK){
                        color = "b";
                    }
                    game = GAMESTATE.WIN;
                    try {
                        testBoard.movePiece(color, currMove.getOldX(), currMove.getOldY(), currMove.getNewX(), currMove.getNewY());
                        numbers = flattenArray(testBoard.board, numbers);
                        adapter.notifyDataSetChanged();
                        gridView.invalidate();
                        if(current[0] == PLAYER.WHITE){
                            current[0] = PLAYER.BLACK;
                            status.setText("Black's move!");
                        }
                        else{
                            current[0] = PLAYER.WHITE;
                            status.setText("White's move!");
                        }
                        moveCounter++;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    finish();
                }
            }
        });
    }

    public Piece[] flattenArray(Piece[][] threedee, Piece[] twodee){
        int counter = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                twodee[counter] = threedee[j][i];
                counter++;
            }
        }
        return twodee;
    }
}
