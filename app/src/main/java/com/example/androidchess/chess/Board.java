package com.example.androidchess.chess;
import com.example.androidchess.Move;
import com.example.androidchess.pieces.*;
import java.util.Objects;

/**
 * Class description for the Chess Gameboard.
 *
 * @author Ashhad Siddiqui and John Bailon
 *
 */
public class Board {
    /** Array that tracks pieces */
    public Piece[][] board;
    /** Array that tracks the empty board*/
    public Piece[][] tiles;
    /** Size of the board*/
    public Piece[][] prevState;
    public static int SIZE = 8;
    /** Position of the white King*/
    public int[] whiteKingLocation;
    /** Position of the black King*/
    public int[] blackKingLocation;
    /** Boolean if the white king is in check */
    public boolean isInCheckWhite;
    /** Boolean if the black king is in check */
    public boolean isInCheckBlack;

    /**
     * Defined constructor for a Board instance.
     *
     * @author Ashhad Siddiqui and John Bailon
     */
    public Board() {
        //toDo: change to Piece object instead?
        board = new Piece[SIZE][SIZE];
        tiles = new Piece[SIZE][SIZE];
        prevState = new Piece[SIZE][SIZE];
        BlankSquare b = new BlankSquare("b");
        BlankSquare w = new BlankSquare("w");
        /*

        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                //toDo: how will array indices reflect letter-number inputs?
                BlankSquare tile = (i + j) % 2 == 0 ? b : w;
                board[i][j] = tile; //actual board
                board[i][j] = tile; //tile map
            }
        }
         */
        for (int i = 0; i < 64; i++) {
            int xpos = i / 8;
            int ypos = i % 8;
            BlankSquare tile = w;
            if ((ypos % 2 == 0 && xpos % 2 != 0) || (ypos % 2 != 0 && xpos % 2 == 0)) {
                tile = b;
            }
            board[xpos][ypos] = tile;
            tiles[xpos][ypos] = tile;
        }

            //testing adding pieces
            for (int i = 0; i < SIZE; i++) {
                Pawn whitepawn = new Pawn("w", i, 1);
                board[i][6] = whitepawn;
            }

            for (int i = 0; i < SIZE; i++) {
                Pawn blackpawn = new Pawn("b", i, 6);
                board[i][1] = blackpawn;
            }

            Rook WRook1 = new Rook("w", 0, 7);
            board[0][7] = WRook1;
            Rook WRook2 = new Rook("w", 7, 7);
            board[7][7] = WRook2;

            Rook BRook1 = new Rook("b", 0, 0);
            board[0][0] = BRook1;
            Rook BRook2 = new Rook("b", 7, 0);
            board[7][0] = BRook2;

            King whiteKing = new King("w", 4, 7);
            board[4][7] = whiteKing;
            whiteKingLocation = new int[2];
            whiteKingLocation[0] = 4;
            whiteKingLocation[1] = 0;

            King blackKing = new King("b", 4, 0);
            board[4][0] = blackKing;
            blackKingLocation = new int[2];
            blackKingLocation[0] = 4;
            blackKingLocation[1] = 7;

            Knight whiteKnight1 = new Knight("w", 1, 7);
            board[1][7] = whiteKnight1;
            Knight whiteKnight2 = new Knight("w", 6, 7);
            board[6][7] = whiteKnight2;


            Knight blackKnight1 = new Knight("b", 1, 0);
            board[1][0] = blackKnight1;
            Knight blackKnight2 = new Knight("b", 6, 0);
            board[6][0] = blackKnight2;

            Bishop whiteBishop1 = new Bishop("w", 5, 7);
            board[5][7] = whiteBishop1;
            Bishop whiteBishop2 = new Bishop("w", 2, 7);
            board[2][7] = whiteBishop2;

            Bishop blackBishop1 = new Bishop("b", 5, 0);
            board[5][0] = blackBishop1;
            Bishop blackBishop2 = new Bishop("b", 2, 0);
            board[2][0] = blackBishop2;

            Queen blackQueen = new Queen("b", 3, 0);
            board[3][0] = blackQueen;

            Queen whiteQueen = new Queen("w", 3, 7);
            board[3][7] = whiteQueen;

        }

    /**
     * Executes a move based on the command, if legal.
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the piece attempting to move
     * @throws Exception Illegal Move Exception
     */
    public void movePiece(String color, int curr_x, int curr_y, int new_x, int new_y) throws Exception {

        char pieceToPromote = 'Q';

        Piece current = board[curr_x][curr_y];
        Piece next = board[new_x][new_y];

        //if next square has a Piece, and if the Piece is the opposite color, then it is a capture
        boolean isCapture = !(next instanceof BlankSquare) && (!Objects.equals(next.getColor(), current.getColor()));
        boolean sameColorOccupied = !(next instanceof BlankSquare) && (Objects.equals(next.getColor(), current.getColor()));

        //checks if player is attempting to move piece of opposite color or if target square is occupied by same color
        if(!Objects.equals(current.getColor(), color) || sameColorOccupied){
            throw new Exception("Illegal move!");
        }
        else if(current.validMove(curr_x, curr_y, new_x, new_y, isCapture, this)){
            saveState();
            board[new_x][new_y] = current;
            //replacing tile that current piece leaves with original color, stored in tiles
            clearSquare(curr_x, curr_y);
            System.out.println(new_x + " " + new_y);
            //If Pawn, check if needs to be promoted
            if(current instanceof Pawn){
                ((Pawn)current).tryPromotion(new_x, new_y, pieceToPromote, this);
            }
        }
        else{
            throw new Exception("Illegal move!");
        }
    }

    public void undoMove(){

    }

    /**
     * Resets the square at (x,y) to a blank black or white square.
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param x x coordinate
     * @param y y coordinate
     *
     */
    public void clearSquare(int x, int y){
        board[x][y] = tiles[x][y];
    }

    /**
     * Prints the board into the terminal.
     *
     * @author Ashhad Siddiqui and John Bailon
     */
    public void printBoard(){
        //test
        for(int i = 7; i > -1; i--){ //simplifies referencing, 0,0 refers to bottom left (a1) instead of top left (a8)
            for(int j = 0; j < SIZE; j++) {
                System.out.print(board[j][i] + " ");
            }
            System.out.println(i+1);
        }
        System.out.println(" a  b  c  d  e  f  g  h ");
        System.out.println();
    }

    /**
     * Tests if the selected King is in check.
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color String that indicates color of King
     * @param b Board state
     * @return true if the selected King is in check, false if not
     */
    public boolean isInCheck(String color, Board b){
        //Find the location of King
        int kingX = blackKingLocation[0];
        int kingY = blackKingLocation[1];
        String opposingColor = "w";

        if(color.equals("w")){
            kingX = whiteKingLocation[0];
            kingY = whiteKingLocation[1];
            opposingColor = "b";
        }

        //Check the entire board
        for(int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                Piece current = b.board[i][j];

                if(current != null && current.getColor().equals(opposingColor)){
                    if(b.board[i][j].validMove(i, j, kingX, kingY, current.getHasMoved(), this)){
                        b.board[kingX][kingY].setCheck(true);
                        System.out.println("Check!");
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Checks if it is a safe move
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param curr_x initial X-Coordinate
     * @param curr_y initial Y-Coordinate
     * @param new_x final X-Coordinate
     * @param new_y final Y-Coordinate
     * @param isCapture boolean if the piece has captured
     * @param current Current piece
     * @param color Color of current piece
     * @return true if is a safemove, false otherwise
     */
    public boolean safeMove(int curr_x, int curr_y, int new_x, int new_y, boolean isCapture, Piece current, String color) {
        Board tempB = new Board();
        tempB.board = new Piece[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tempB.board[i][j] = board[i][j]; //temp board for checking
            }
            if (current.validMove(curr_x, curr_y, new_x, new_y, isCapture, this)) {
                tempB.board[new_x][new_y] = current;
                tempB.clearSquare(curr_x, curr_y);
            }
        }
        return !isInCheck(color, tempB);
    }

    public Move random(String color){
        boolean found = false;
        while(!found){
            int curr_x = (int)(Math.random()*(7+1)+0);
            int curr_y = (int)(Math.random()*(7+1)+0);
            int new_x =  (int)(Math.random()*(7+1)+0);
            int new_y =  (int)(Math.random()*(7+1)+0);
            try {
                movePiece(color, curr_x, curr_y, new_x, new_y);
                return new Move(curr_x, curr_y, new_x, new_y);
            }catch(Exception e) {
                continue;
            }
        }
        return null;
    }

    public void saveState(){
        for (int i = 0; i < 8; i++) {
            prevState[i] = board[i].clone();
        }
    }

    public void undo(){
        for (int i = 0; i < 8; i++) {
            board[i] = prevState[i].clone();
        }
    }
}
