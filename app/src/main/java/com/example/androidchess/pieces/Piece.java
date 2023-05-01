package com.example.androidchess.pieces;
import com.example.androidchess.chess.*;

/**
 * Superclass description for chess pieces
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class Piece {
    /** Color of the piece*/
    public String color;
    /** Current x-Coordinate of the piece*/
    public int x_pos,
    /** Current Y-Coordinate of the piece*/
    y_pos;
    /** boolean if the piece moved prior*/
    public boolean hasMoved;

    String imageID;

    /**
     * Checks if the requested move is a legal chess move
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param oldX initial X-Coordinate
     * @param oldY initial Y-Coordinate
     * @param newX final X-Coordinate
     * @param newY final Y-Coordinate
     * @param isCapture If pawn has captured
     * @param b Board state
     * @return True if the move is legal, false if it is illegal
     */
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture,  Board b){
        return false;
    }

    /**
     * Moves the piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param newX Final X-Coordinate
     * @param newY Final Y-Coordinate
     */
    public void updatePosition(int newX, int newY){
        this.x_pos = newX;
        this.y_pos = newY;
        this.hasMoved = true;
    }

    /**
     * Gets the color of the piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @return Color of the piece
     */
    public String getColor(){
        return this.color;
    }

    public String getImageID(){
        return this.imageID;
    }

    /**
     * Gets the move status of a piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @return True if a piece has moved, false otherwise
     */
    public boolean getHasMoved(){
        return hasMoved;
    }
    /**
     * Setter for the move status of a piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param hasMoved boolean for piece movement
     */
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }

    /**
     * Converts piece object into a string
     *
     * @author Ashhad Siddiqui and John Bailon
     * @return String representation of the piece
     */
    public String toString(){
        return "no";
    }

    /**
     * Sets check
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param b board state
     */
    public void setCheck(boolean b) {

    }
}
