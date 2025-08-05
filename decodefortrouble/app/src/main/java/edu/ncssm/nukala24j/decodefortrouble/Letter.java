package edu.ncssm.nukala24j.decodefortrouble;

public class Letter {

    // Private instance variables for the Letter class
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private char letter;
    private int size;

    // Constructor for the Letter class
    public Letter(int xp, int yp, char let) {
        // Initializing instance variables with provided values
        xPos = xp;
        yPos = yp;
        letter = let;
        xVel = 0;
        yVel = 0;
        size = 25;
    }

    // Getter method to retrieve the letter
    public char getLetter() {
        return letter;
    }

    // Getter method to retrieve the size
    public int getSize() {
        return size;
    }

    // Getter method to retrieve the x-coordinate position
    public int getxPos() {
        return xPos;
    }

    // Getter method to retrieve the y-coordinate position
    public int getyPos() {
        return yPos;
    }

    // Getter method to retrieve the x-axis velocity
    public int getxVel() {
        return xVel;
    }

    // Getter method to retrieve the y-axis velocity
    public int getyVel() {
        return yVel;
    }

    // Setter method to update the x-axis velocity and return a boolean indicating success
    public boolean setxVel(int xv) {
        xVel = xv;
        return true;
    }

    // Setter method to update the y-axis velocity and return a boolean indicating success
    public boolean setyVel(int yv) {
        yVel = yv;
        return true;
    }

    // Setter method to update the letter and return a boolean indicating success
    public boolean setLetter(char let) {
        letter = let;
        return true;
    }

    // Setter method to update the x-coordinate position and return a boolean indicating success
    public boolean setxPos(int xp) {
        xPos = xp;
        return true;
    }

    // Setter method to update the y-coordinate position and return a boolean indicating success
    public boolean setyPos(int yp) {
        yPos = yp;
        return true;
    }

    // Method to update the position of the letter based on its velocity
    public void move() {
        xPos += xVel;
        yPos += yVel;
    }

}
