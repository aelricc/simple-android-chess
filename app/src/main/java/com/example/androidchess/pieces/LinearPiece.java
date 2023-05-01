package com.example.androidchess.pieces;
import com.example.androidchess.chess.*;

/**
 * Interface that describes how piece collision is handled
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public interface LinearPiece {

    /**
     * Checks if there are pieces in between starting and ending position
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param oldX initial X-Coordinate
     * @param oldY initial Y-Coordinate
     * @param newX final X-Coordinate
     * @param newY final Y-Coordinate
     * @param b Board state
     * @return True of there are pieces between, false otherwise
     */
    default boolean hasPiecesInBetween(int oldX, int oldY, int newX, int newY, Board b){
        //Check Vert (Up/Down)
        if(oldX == newX && oldY != newY){
            //If equal, newY > oldY, go up (+1)
            int counter = Math.abs(newY - oldY) == (newY - oldY) ? 1 : -1;

            for(int i = oldY+counter; i != newY; i +=counter){
                if(!(b.board[oldX][i] instanceof BlankSquare)){
                    return true;
                }
            }
        }
        //Check Horizontal (Left/Right)
        if(oldX != newX && oldY == newY ){
            //If equal, newX > oldX, go right (+1)
            int counter = Math.abs(newX - oldX) == (newX - oldX) ? 1 : -1;

            for(int i=oldX+counter; i != newX; i+=counter){
                if(!(b.board[i][oldY] instanceof BlankSquare)){
                    return true;
                }
            }
        }

        //Check Diagonal
        int diffX = newX - oldX;
        int diffY = newY - oldY;
        if(Math.abs(diffX) == Math.abs(diffY)){

            //Top Right
            if(diffX > 0 && diffY > 0){
                int j = oldY+1;
                for(int i=oldX+1; i<newX; i++){
                    System.out.println(i);
                    if(!(b.board[i][j] instanceof BlankSquare)){
                        return true;
                    }
                    j++;
                }
            }

            //Top Left
            if(diffX < 0 && diffY > 0){
                int j = oldY-1;
                for(int i=oldX-1; i>newX; i--){
                    if(!(b.board[i][j] instanceof BlankSquare)){
                        return true;
                    }
                    j++;
                }
            }

            //Bottom Left
            if(diffX < 0 && diffY < 0){
                int j = oldY-1;
                for(int i=oldX-1; i>newX; i--){
                    if(!(b.board[i][j] instanceof BlankSquare)){
                        return true;
                    }
                    j--;
                }
            }

            //Bottom Right
            if(diffX > 0 && diffY < 0){
                int j = oldY+1;
                for(int i=oldX+1; i<newX; i++){
                    if(!(b.board[i][j] instanceof BlankSquare)){
                        return true;
                    }
                    j--;
                }
            }
        }

        return false;
    }
}
