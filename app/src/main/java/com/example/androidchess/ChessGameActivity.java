package com.example.androidchess;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidchess.chess.Board;
import com.example.androidchess.pieces.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ChessGameActivity extends AppCompatActivity {

    static Piece[] numbers = new Piece[64];
    static Piece[][] actualBoard = new Piece[8][8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Board testBoard = new Board();
        BlankSquare b = new BlankSquare("b");
        BlankSquare w = new BlankSquare("w");
        /**
        for(int i = 0; i < 64; i++) {
            int xpos = i/8;
            int ypos = i%8;
            BlankSquare tile = w;
            if ((ypos % 2 == 0 && xpos % 2 !=0) || (ypos % 2 != 0 && xpos % 2 == 0)) {
                tile = b;
            }
                tile.updatePosition(xpos, ypos);
                numbers[i] = tile;
        }
         **/

        numbers = flattenArray(testBoard.board, numbers);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessgame);

        GridView gridView = (GridView) findViewById(R.id.chessboard);
        final CustomAdapter adapter = new CustomAdapter(numbers);
        gridView.setAdapter(adapter);
        final CustomView[] prevSelected = new CustomView[1];
        final boolean[] isFirstPieceSelected = {false};
        final int[] prevX = {0};
        final int[] prevY = {0};
        final String[] color = {null};

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int xpos = position%8;
                int ypos = position/8;
                /*
                Snackbar.make(v, "pos x is: "+ xpos+"  pos y is: "+ypos, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                 */
                int selectedIndex = adapter.selectedPositions.indexOf(position);
                /*
                    if (selectedIndex > -1) {
                        adapter.selectedPositions.remove(selectedIndex);
                        ((CustomView)v).display(false);
                    } else {
                    /*
                 */

                if(isFirstPieceSelected[0]){
                    ((CustomView)v).display(false);
                    ((CustomView)prevSelected[0]).display(false);
                    try {
                        testBoard.movePiece(color[0], prevX[0], prevY[0], xpos, ypos);
                        Snackbar.make(v, "Safely moved piece from: " + prevX[0] + ", " + prevY[0] + " to "+ xpos + ", " + ypos, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        numbers = flattenArray(testBoard.board, numbers);
                        adapter.notifyDataSetChanged();
                        gridView.invalidate();
                        adapter.selectedPositions.clear();
                        isFirstPieceSelected[0] = false;
                    } catch (Exception e) {
                        Snackbar.make(v, "Invalid move!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        adapter.selectedPositions.clear();
                        isFirstPieceSelected[0] = false;
                    }
                }
                else{
                    color[0] = ((Piece)adapter.getItem(position)).getColor();
                    adapter.selectedPositions.add(position);
                    ((CustomView)v).display(true);
                    prevSelected[0] = (CustomView)v;
                    prevY[0] = ypos;
                    prevX[0] = xpos;
                    isFirstPieceSelected[0] = true;
                }

            }
            //}
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