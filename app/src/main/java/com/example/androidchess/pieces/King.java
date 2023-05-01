package com.example.androidchess.pieces;
import com.example.androidchess.chess.Board;

/**
 * Class description for the King Piece.
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class King extends Piece{
    /** Color of the piece*/
    public String color;
    /** Current x-Coordinate of the piece*/
    public int x_pos,
    /** Current Y-Coordinate of the piece*/
    y_pos;
    /** boolean if the piece moved prior*/
    public boolean hasMoved;
    /**boolean if in Check*/
    public boolean isInCheck;
    public String imageID;


    /**
     * Constructor for the King piece.
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece
     * @param x X coordinate
     * @param y Y Coordinate
     */
    public King (String color, int x, int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
        this.hasMoved = false;
        this.imageID = color + "king";
    }

    public String getImageID(){
        return this.imageID;
    }

    @Override
    public boolean validMove(int oldX, int oldY, int newX, int newY, boolean isCapture, Board b){
        int diff = (int)(Math.hypot(newX - oldX, newY - oldY));
        //System.out.println(diff);
        if(diff == 1){
            updatePosition(newX, newY);
            return true;
        }
        else if(newX == 6
                && !this.hasMoved
                && (newY == 0 || newY == 7)
                && b.board[7][newY] instanceof Rook
                && !(b.board[7][newY]).getHasMoved()){
            Castle(newX, newY, b);
            return true;
        }
        else if(newX == 2
                && !this.hasMoved
                && (newY == 0 || newY == 7)
                && b.board[0][newY] instanceof Rook
                && !(b.board[0][newY]).getHasMoved()){
            Castle(newX, newY, b);
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
        return color + "K";
    }
    @Override
    public boolean getHasMoved(){
        return hasMoved;
    }

    /***
     * Moves the king based on Chess castle rules.
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param newX Final X-Coordinate for the King
     * @param newY Final Y-Coordinate for the King
     * @param b Board state
     */
    public void Castle(int newX, int newY, Board b) {//need tofixthis
        int offset;
        if(newX == 2){offset = 1;} else {offset = -1;}
        switch (newY) {
            case (0):
                updatePosition(newX, newY);
                b.board[newX + offset][newY] = new Rook(this.color, newX-1, newY);
                b.board[newX + offset][newY].setHasMoved(true);
                b.clearSquare(0, newY);
                break;
            case (7):
                updatePosition(newX, newY);
                b.board[newX - 2][newY] = new Rook(this.color, newX-1, newY);
                b.board[newX - 2][newY].setHasMoved(true);
                b.clearSquare(0, newY);
                break;
        }
    }
}
