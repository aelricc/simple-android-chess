package com.example.androidchess;

import java.io.*;
import java.util.ArrayList;

/**
 * Class description of Userlist
 *
 * @author Ashhad Siddiqui and John Bailon
 */
public class GameList implements Serializable {
    /**List of Users**/
    public ArrayList<MoveList> actualGameList;
    public static final String storeDir = "dat";
    public static final String storeFile = "Games.dat";
    static final long serialVersionUID = 1L;

    /**
     * Constructor for a UserList
     *
     * @author Ashhad Siddiqui and John Bailon
     //* @param users List of Users
     */
    public GameList(ArrayList<MoveList> games){
        this.actualGameList = games;
    }

    /**
     * Adds a user to the userlist
     *
     * @author Ashhad Siddiqui and John Bailon
     // @param user User to be added
     */
    public void add(MoveList game){
        actualGameList.add(game);
    }

    /**
     * Gets the user at the given position
     *
     * @author Ashhad Siddiqui and John Bailon
     * @param i position of the user
     * @return Requested user
     */
    public MoveList get(int i){
        return actualGameList.get(i);
    }

    /**
     * Getter for the user list
     *
     * @author Ashhad Siddiqui and John Bailon
     * @return List of users
     */
    public ArrayList<MoveList> getActualGameList(){
        return actualGameList;
    }

    /**
     * Saves users for data persistence
     *
     * //@param userlist Lists of users
     * @author Ashhad Siddiqui and John Bailon
     * @throws IOException If the file is missing
     */
    public static void saveGame(GameList gamelist)
            throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Games.dat"));
        oos.writeObject(gamelist);
    }

    /**
     * Reads a list of users from a file
     *
     * @author Ashhad Siddiqui and John Bailon
     * @return List of Users
     * @throws IOException If the file is missing
     * @throws ClassNotFoundException If the class is missing
     */
    public static GameList readGames() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Games.dat"));
        return (GameList) ois.readObject();
    }
}
