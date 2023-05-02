package com.example.androidchess.pieces;
import com.example.androidchess.chess.*;

import java.util.Objects;

/**
 * Class description for pawn pieces
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class Pawn extends Piece {
    /** Color of the piece*/
    public String color;
    /** Current x-Coordinate of the piece*/
    public int x_pos,
    /** Current Y-Coordinate of the piece*/
    y_pos;
    /** boolean if the piece moved prior*/
    public boolean hasMoved;
    /** boolean if the piece can enpassant*/
    public boolean enpassant = true;

    public String imageID;

    /**
     * Constructor for the Pawn
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Pawn (String color, int x, int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
        this.hasMoved = false;
        this.imageID = color + "pawn";

    }

    public String getImageID(){
        return this.imageID;
    }

    @Override
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture, Board board){
        if (Objects.equals(this.color, "b")){
            if(!isCapture && !hasMoved && oldX == newX && (newY-oldY == 2)){
                updatePosition(newX, newY);
                return true;
            }
            else if (!isCapture && oldX == newX && (newY-oldY == 1)){
                updatePosition(newX, newY);
                this.enpassant = false;
                return true;
            }
            else if(isCapture && (newY-oldY == 1) && (newX-oldX == 1||newX-oldX == -1)){
                updatePosition(newX, newY);
                this.enpassant = false;
                return true;
            }
            else if(tryEnpassant(newX, newY, board, "b") && (newY-oldY == 1) && (newX-oldX == 1||newX-oldX == -1)){
                board.clearSquare(newX, newY-1);
                updatePosition(newX, newY);
                this.enpassant = false;
                return true;
            }
            else{
                return false;
            }
        }
        else if (Objects.equals(this.color, "w")){
            if(!isCapture && !hasMoved && oldX == newX && (newY-oldY == -2)){
                updatePosition(newX, newY);
                return true;
            }
            else if (!isCapture && oldX == newX && (newY-oldY == -1)) {
                updatePosition(newX, newY);
                this.enpassant = false;
                return true;
            }
            else if(isCapture && (newY-oldY == -1) && (newX-oldX == 1||newX-oldX == -1)){
                updatePosition(newX, newY);
                this.enpassant = false;
                return true;
            }
            else if(tryEnpassant(newX, newY, board, "w") && (newY-oldY == -1) && (newX-oldX == 1||newX-oldX == -1)){
                board.clearSquare(newX, newY+1);
                updatePosition(newX, newY);
                this.enpassant = false;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Promotes the pawn into a piece
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param input Promotion Type
     * @param b Board state
     */
    public void tryPromotion(int x, int y, char input, Board b) {
        if ((Objects.equals(this.color, "w") && y == 0) || (Objects.equals(this.color, "b") && y == 7)) {
            switch (input) {
                case 'R' -> b.board[x][y] = new Rook(this.color, x, y);
                case 'Q' -> b.board[x][y] = new Queen(this.color, x, y);
                case 'N' -> b.board[x][y] = new Knight(this.color, x, y);
                case 'B' -> b.board[x][y] = new Bishop(this.color, x, y);
            }
        }
    }

    /**
     * Checks is if Enpassant is possible
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param newX final X-Coordinate
     * @param newY final Y-Coordinate
     * @param b Board State
     * @param color Color of the piece
     * @return True is enpassant is a legal move, false if not
     */
    public boolean tryEnpassant(int newX, int newY, Board b, String color){
        Piece wcheckpass = b.board[newX][newY+1];
        Piece bcheckpass = b.board[newX][newY-1];

        if(Objects.equals(color, "w") && (!Objects.equals(wcheckpass.getColor(), color))){
            return (wcheckpass instanceof Pawn && ((Pawn)wcheckpass).getEnPassant());
        }
        else if(Objects.equals(color, "b") && (!Objects.equals(bcheckpass.getColor(), color))){
            return (bcheckpass instanceof Pawn && ((Pawn)bcheckpass).getEnPassant());
        }
        else return false;
    }

    /**
     * Getter for Enpassant state
     *
     * @author Ashhad Siddiqui and John Bailon
     * @return True if a piece can Enpassant, false otherwise
     */
    public boolean getEnPassant(){
        return this.enpassant;
    }

    @Override
    public String getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return color + "p";
    }
}
