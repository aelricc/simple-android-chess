package com.example.androidchess.pieces;
import com.example.androidchess.chess.*;

/**
 * Class description for the Queen piece
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class Queen extends Piece implements LinearPiece{
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
     * Constructor for Queen Piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Queen (String color, int x, int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
        this.hasMoved = false;
        this.imageID = color + "queen";

    }

    public String getImageID(){
        return this.imageID;
    }

    @Override
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture, Board b) {
        if(oldX == newX || newY == oldY || Math.abs(newX - oldX) == Math.abs(newY - oldY)){
            updatePosition(newX, newY);
            hasMoved = true;
            return true;
        }
        else return false;
    }

    @Override
    public String getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return color + "Q";
    }
}
