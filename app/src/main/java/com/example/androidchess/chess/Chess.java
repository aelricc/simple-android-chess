package com.example.androidchess.chess;
import java.util.Objects;
import java.util.Scanner;

/**
 *  Driver for the Chess Project
 *
 *  @author Ashhad Siddiqui and John Bailon
 */
public class Chess {

    /**
     * Valid Gamestates
     * @author Ashhad Siddiqui and John Bailon
     */
    public enum GAMESTATE{
        /** Active game */
        ACTIVE,
        /** Black wins */
        BLACKWIN,
        /** White wins */
        WHITEWIN,
        /** Game is a draw */
        DRAW,

        UNDO
        }

    /**
     * Updates gamestate based on move conditions.
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param move String that checks for inputs that change
     * @param color String that indicates color team color
     * @return GAMESTATE based on move conditions
     */
    public static GAMESTATE isGame(String move, String color){
        if(move.contains("resign") && Objects.equals(color, "w")){
            System.out.println("Black wins!");
            return GAMESTATE.BLACKWIN;
        }
        else if(move.contains("resign") && Objects.equals(color, "b")){
            System.out.println("White wins!");
            return GAMESTATE.WHITEWIN;
        }
        else if(move.contains("draw?")){
            Scanner input = new Scanner(System.in);
            String draw = input.nextLine();
            if(Objects.equals(draw, "draw")){
                return GAMESTATE.DRAW;
            }
        }
        else if(move.contains("undo")){
            return GAMESTATE.UNDO;
        }
        return GAMESTATE.ACTIVE;
    }

    /**
     * Main Method for Chess Driver
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param args input
     */
    public static void main(String[] args){
        GAMESTATE game = GAMESTATE.ACTIVE;
        Board testBoard = new Board();
        Scanner input = new Scanner(System.in);

        while(game == GAMESTATE.ACTIVE){
            boolean hasWhiteMoved = false;
            boolean hasBlackMoved = false;

            //check if white is in check before white turn
            //testBoard.isInCheck("w", testBoard);
            testBoard.printBoard();

            while(!hasWhiteMoved){
                try{
                    System.out.print("White's move: ");
                    String whitemove = input.nextLine();

                    game = isGame(whitemove, "w");
                    if(game == GAMESTATE.UNDO){

                    }
                    else if(game != GAMESTATE.ACTIVE){break;}

                    //testBoard.movePiece("w", whitemove);
                    hasWhiteMoved = true;
                }catch (Exception illegal){
                    System.out.println("Illegal move!");
                }
            }

            if(game != GAMESTATE.ACTIVE){break;}

            //check if black is in check before black turn
            //testBoard.isInCheck("b", testBoard);
            testBoard.printBoard();

            while(!hasBlackMoved){
                try{
                    System.out.print("Black's move: ");
                    String blackmove = input.nextLine();

                    game = isGame(blackmove, "b");
                    if(game != GAMESTATE.ACTIVE){break;}

                    //testBoard.movePiece("b", blackmove);
                    hasBlackMoved = true;
                }catch (Exception illegal){
                    System.out.println("Illegal move!");
                }
            }

            if(game != GAMESTATE.ACTIVE){break;}

            //TODO: Implement check for black checkmate
        }
    }
}
