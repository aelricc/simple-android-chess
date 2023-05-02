package com.example.androidchess;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidchess.chess.Board;
import com.example.androidchess.pieces.*;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;

public class ChessGameActivity extends AppCompatActivity implements NoticeResignDialog.NoticeDialogListener, NoticeSaveGame.NoticeSaveGameListener {

    static Piece[] numbers = new Piece[64];
    static Piece[][] actualBoard = new Piece[8][8];
    MoveList savedGame;
    PLAYER[] current;
    GAMESTATE game;


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
        setContentView(R.layout.activity_chessgame);

        GridView gridView = (GridView) findViewById(R.id.chessboard);
        final CustomAdapter adapter = new CustomAdapter(numbers);
        gridView.setAdapter(adapter);
        final CustomView[] prevSelected = new CustomView[1];
        final boolean[] isFirstPieceSelected = {false};
        final int[] prevX = {0};
        final int[] prevY = {0};
        final TextView status = (TextView) findViewById(R.id.TurnName);
        status.setText("White's move!");

        //savedGame.clear();
        MoveList savedGame = new MoveList();

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

                String color = "w";
                if(current[0] == PLAYER.BLACK){
                    color = "b";
                }

                if(isFirstPieceSelected[0]){
                    ((CustomView)v).display(false);
                    ((CustomView)prevSelected[0]).display(false);
                    try {
                        testBoard.movePiece(color, prevX[0], prevY[0], xpos, ypos);
                        savedGame.addMove(new Move(prevX[0], prevY[0], xpos, ypos));
                       //Snackbar.make(v, "Safely moved piece from: " + prevX[0] + ", " + prevY[0] + " to "+ xpos + ", " + ypos, Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                        numbers = flattenArray(testBoard.board, numbers);
                        adapter.notifyDataSetChanged();
                        gridView.invalidate();
                        isFirstPieceSelected[0] = false;
                        if(current[0] == PLAYER.WHITE){
                            current[0] = PLAYER.BLACK;
                            status.setText("Black's move!");
                        }
                        else{
                            current[0] = PLAYER.WHITE;
                            status.setText("White's move!");
                        }
                        adapter.selectedPositions.clear();
                    } catch (Exception e) {
                        //Snackbar.make(v, "Invalid move!", Snackbar.LENGTH_LONG)
                                //.setAction("Action", null).show();
                        adapter.selectedPositions.clear();
                        isFirstPieceSelected[0] = false;
                    }
                }
                else{
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

        final Button resign = findViewById(R.id.resign);
        resign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game = GAMESTATE.WIN;
                openResignDialog();
            }
        });

        final Button draw = findViewById(R.id.draw);
        draw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game = GAMESTATE.DRAW;
                openResignDialog();
            }
        });

        final Button undo = findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(current[0] == PLAYER.WHITE && !savedGame.isEmpty()){
                    testBoard.undo();
                    savedGame.undo();
                    Snackbar.make(v, "Undid black's last move", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    numbers = flattenArray(testBoard.board, numbers);
                    adapter.notifyDataSetChanged();
                    gridView.invalidate();
                    current[0] = PLAYER.BLACK;
                    status.setText("Black's move!");
                }
                else if (current[0] == PLAYER.BLACK && !savedGame.isEmpty()){
                    testBoard.undo();
                    savedGame.undo();
                    Snackbar.make(v, "Undid white's last move", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    numbers = flattenArray(testBoard.board, numbers);
                    adapter.notifyDataSetChanged();
                    gridView.invalidate();
                    current[0] = PLAYER.WHITE;
                    status.setText("White's move!");
                }
                else{
                    Snackbar.make(v, "No moves to undo!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });

        final Button AI = findViewById(R.id.AI);
        AI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(current[0] == PLAYER.WHITE){
                    Move random = testBoard.random("w");
                    savedGame.addMove(random);
                    Snackbar.make(v, "AI moved for white!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    numbers = flattenArray(testBoard.board, numbers);
                    adapter.notifyDataSetChanged();
                    gridView.invalidate();
                    isFirstPieceSelected[0] = false;
                    current[0] = PLAYER.BLACK;
                    status.setText("Black's move!");
                    }
                else{
                    Move random = testBoard.random("b");
                    savedGame.addMove(random);
                    Snackbar.make(v, "AI moved for black!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    numbers = flattenArray(testBoard.board, numbers);
                    adapter.notifyDataSetChanged();
                    gridView.invalidate();
                    current[0] = PLAYER.WHITE;
                    status.setText("White's move!");}

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

    public void openResignDialog(){
        ResignDialog exampleDialog = new NoticeResignDialog();
        if(game == GAMESTATE.DRAW){
            exampleDialog.setEndgame("draw");
        }
        else if(current[0] == PLAYER.WHITE){
            exampleDialog.setColor("Black");
        }
        else if(current[0] == PLAYER.BLACK){
            exampleDialog.setColor("White");
        }
        exampleDialog.show(getSupportFragmentManager(), "NoticeResignDialog");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(ResignDialog dialog) {
        openSaveGame();
    }

    @Override
    public void onDialogNegativeClick(ResignDialog dialog) {
        finish();
    }

    public void openSaveGame(){
        SaveGame game = new SaveGame();
        game.show(getSupportFragmentManager(), "save dialog");
    }

    @Override
    public void onDialogPositiveSaveClick(SaveGame dialog, String name) throws IOException, ClassNotFoundException {
        GameList pog = GameList.readGames();
        savedGame.setName(name);
        pog.add(savedGame);
        GameList.saveGame(pog);
        finish();
    }

    @Override
    public void onDialogNegativeSaveClick(SaveGame dialog) {
        finish();
    }

}