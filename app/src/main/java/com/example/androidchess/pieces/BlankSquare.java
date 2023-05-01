package com.example.androidchess.pieces;

/**
 * Class description for BlankSquare pieces
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class BlankSquare extends Piece{
    /** Color of the piece*/
    public String color;
    public String imageID;

    /**
     * Constructor for a blank square
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param color Color of the square
     */
    public BlankSquare(String color){
        this.color = color;
        this.imageID = "blanksquare";
    }

    public String getImageID(){
        return this.imageID;
    }

    @Override
    public String getColor() {
        return color;
    }
    @Override
    public String toString(){
        if(color.equals("b")){
          return "##";
        }
        else{
            return "  ";
        }
    }
}
