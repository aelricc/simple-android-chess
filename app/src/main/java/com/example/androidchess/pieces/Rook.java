package com.example.androidchess.pieces;
import com.example.androidchess.chess.*;

/**
 * Class description for the Rook Piece
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class Rook extends Piece implements LinearPiece{
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
     * Constructor for Rook Piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Rook (String color, int x, int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
        this.hasMoved = false;
        this.imageID = color + "rook";

    }

    public String getImageID(){
        return this.imageID;
    }
    @Override
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture, Board b) {
        if(oldX == newX || newY == oldY && !hasPiecesInBetween(oldX, oldY, newX, newY, b)){
            updatePosition(newX, newY);
            hasMoved = true;
            return true;
        }
        return false;
    }

    @Override
    public String getColor(){
        return this.color;
    }

    @Override
    public boolean getHasMoved(){
        return hasMoved;
    }

    @Override
    public String toString(){
        return color + "R";
    }
}
