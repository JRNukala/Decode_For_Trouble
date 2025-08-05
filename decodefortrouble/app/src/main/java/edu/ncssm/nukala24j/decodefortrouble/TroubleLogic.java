package edu.ncssm.nukala24j.decodefortrouble;

import java.util.ArrayList;

public class TroubleLogic {
    // Instance variables
    private int roll;
    private Piece[] player1Piece;
    private Piece[] player2Piece;
    private boolean turn;
    private ArrayList<Integer> tiles;

    // Constructor initializes the game state
    public TroubleLogic(){
        turn = true;
        roll = 0;
        tiles = new ArrayList<>();
        player1Piece = new Piece[2];
        player2Piece = new Piece[2];
        player1Piece[0] = new Piece(1);
        player1Piece[1] = new Piece(1);
        player2Piece[0] = new Piece(19);
        player2Piece[1] = new Piece(19);
    }

    // Setter for the roll quantity
    public boolean setRollQuantity(int roll){
        this.roll = roll;
        return true;
    }

    // Setter for the tiles
    public boolean setTiles(ArrayList<Integer> tiles){
        this.tiles = tiles;
        return true;
    }

    // Getters for player pieces and tiles
    public Piece[] getPlayer1Piece(){
        return player1Piece;
    }

    public Piece[] getPlayer2Piece(){
        return player2Piece;
    }

    public ArrayList<Integer> getTiles() {
        return tiles;
    }

    public int getRoll(){
        return roll;
    }

    // Move a piece on the board
    public void moveTile(int quant, boolean whichPiece){
        if (turn) {
            if (whichPiece) {
                // Move player 1 piece 1
                System.out.println(tiles);
                System.out.println(player1Piece[0].getCurrTile());
                int ind = tiles.indexOf(player1Piece[0].getCurrTile());
                System.out.println("ind: " + ind);
                ind += quant;
                if (ind >= tiles.size() - 1){
                    ind = ind % tiles.size();
                }
                doesCapture("p1p1");
                player1Piece[0].setCurrTile(tiles.get(ind));
            } else {
                // Move player 1 piece 2
                int ind = tiles.indexOf(player1Piece[1].getCurrTile());
                ind += quant;
                if (ind >= tiles.size() - 1){
                    ind = ind % tiles.size();
                }
                doesCapture("p1p2");
                player1Piece[1].setCurrTile(tiles.get(ind));
            }
        } else {
            if (whichPiece){
                // Move player 2 piece 1
                int ind = tiles.indexOf(player2Piece[0].getCurrTile());
                ind += quant;
                if (ind >= tiles.size() - 1){
                    ind = ind % tiles.size();
                }
                doesCapture("p2p1");
                player2Piece[0].setCurrTile(tiles.get(ind));
            } else {
                // Move player 2 piece 2
                int ind = tiles.indexOf(player2Piece[1].getCurrTile());
                ind += quant;
                if (ind >= tiles.size() - 1){
                    ind = ind % tiles.size();
                }
                doesCapture("p2p2");
                player2Piece[1].setCurrTile(tiles.get(ind));
            }
        }
        turn = !turn;
    }

    // Check if a capture occurs and update the board
    public boolean doesCapture(String player) {
        if (player.equals("p1p1")) {
            if (player1Piece[0].getCurrTile() == player2Piece[0].getCurrTile()) {
                player2Piece[0].setCurrTile(20);
            }
            if (player1Piece[0].getCurrTile() == player2Piece[1].getCurrTile()) {
                player2Piece[1].setCurrTile(20);
            }
        }
        if (player.equals("p1p2")) {
            if (player1Piece[1].getCurrTile() == player2Piece[0].getCurrTile()) {
                player2Piece[0].setCurrTile(19);
            }
            if (player1Piece[1].getCurrTile() == player2Piece[1].getCurrTile()) {
                player2Piece[1].setCurrTile(19);
            }
        }
        if (player.equals("p2p1")) {
            if (player2Piece[0].getCurrTile() == player1Piece[0].getCurrTile()) {
                player1Piece[0].setCurrTile(19);
            }
            if (player2Piece[0].getCurrTile() == player1Piece[1].getCurrTile()) {
                player1Piece[1].setCurrTile(19);
            }
        }
        if (player.equals("p2p2")) {
            if (player2Piece[1].getCurrTile() == player1Piece[0].getCurrTile()) {
                player1Piece[0].setCurrTile(1);
            }
            if (player2Piece[1].getCurrTile() == player1Piece[1].getCurrTile()) {
                player1Piece[1].setCurrTile(1);
            }
        }
        return true;
    }

    // Get the current player's turn
    public boolean whichPlayer(){
        return turn;
    }
}
