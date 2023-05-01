package com.example.androidchess.pieces;
import com.example.androidchess.chess.*;

/**
 * Class description for bishop pieces.
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class Bishop extends Piece implements LinearPiece{
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
     * Constructor for a Bishop
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece
     * @param x X-coordinate
     * @param y Y-Coordinate
     */
    public Bishop (String color, int x, int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
        this.hasMoved = false;
        this.imageID = color + "bishop";

    }
    @Override
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture, Board b){
        if((Math.abs(newX - oldX) == Math.abs(newY - oldY)) && !hasPiecesInBetween(oldX, oldY, newX, newY, b)){ // need to add && !hasPiecesinBetween()
            updatePosition(newX, newY);
            return true;
        }
        return false;
    }
    @Override
    public String getColor(){
        return this.color;
    }

    public String getImageID(){
        return this.imageID;
    }

    @Override
    public String toString(){
        return color + "B";
    }
}
