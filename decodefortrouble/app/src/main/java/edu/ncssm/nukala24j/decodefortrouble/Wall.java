package edu.ncssm.nukala24j.decodefortrouble;

// The Wall class represents a rectangular wall with specified dimensions and position.
public class Wall {
    // Private variables to store the x and y positions, width, and height of the wall.
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    // Constructor to initialize the wall with specified x and y positions, width, and height.
    public Wall(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    // Method to set the width of the wall and return a boolean indicating success.
    public boolean setWidth(int w) {
        width = w;
        return true;
    }

    // Method to set the height of the wall and return a boolean indicating success.
    public boolean setHeight(int h) {
        height = h;
        return true;
    }

    // Method to get the current width of the wall.
    public int getWidth() {
        return width;
    }

    // Method to get the current height of the wall.
    public int getHeight() {
        return height;
    }

    // Method to get the x position of the wall.
    public int getPX() {
        return xPos;
    }

    // Method to get the y position of the wall.
    public int getPY() {
        return yPos;
    }
}
