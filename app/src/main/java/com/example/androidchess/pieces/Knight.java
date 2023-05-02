package com.example.androidchess.pieces;
import static java.lang.Math.abs;

import com.example.androidchess.chess.Board;

/**
 * Class description for Knight pieces
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class Knight extends Piece{
    /** Color of the piece*/
    public String color;
    /** Current x-Coordinate of the piece*/
    public int x_pos,
    /** Current Y-Coordinate of the piece*/
    y_pos;
    /** boolean if the piece moved prior*/
    public boolean hasMoved;

    public String imageID;

    /**
     * Constructor for a Knight Piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Knight (String color, int x, int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
        this.hasMoved = false;
        this.imageID = color + "knight";

    }

    public String getImageID(){
        return this.imageID;
    }
    @Override
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture, Board b){
        double s = ((double)newY - (double)oldY)/((double)newX - (double)oldX);
        double xdiff = abs(newX - oldX);
        double ydiff = abs(newY - oldY);
        //System.out.println(s);
        if((s == -2||s == 2||s == -0.5||s == 0.5) && (xdiff <= 2 && ydiff <=2)){
            updatePosition(newX, newY);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return color + "N";
    }
}
