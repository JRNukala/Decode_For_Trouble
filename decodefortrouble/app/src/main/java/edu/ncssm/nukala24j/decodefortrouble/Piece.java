package edu.ncssm.nukala24j.decodefortrouble;

public class Piece {

    // Private member variable to store the current tile of the Piece
    private int currTile;

    // Constructor to initialize the Piece with a specified tile
    public Piece(int tile) {
        currTile = tile;
    }

    // Method to set the current tile of the Piece
    // Returns true, indicating the successful setting of the tile
    public boolean setCurrTile(int tile) {
        currTile = tile;
        return true;
    }

    // Method to get the current tile of the Piece
    public int getCurrTile() {
        return currTile;
    }
}
