package edu.ncssm.nukala24j.decodefortrouble;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class LetterLogic {
    private Letter p;
    private ArrayList<Letter> lcList;
    private long time;
    private TextView timeText;
    private Wall bottom;
    private Wall top;
    private Wall left;
    private Wall right;
    private int width;
    private int height;
    private int level=1;
    private char currUpLetter;
    private boolean gameOver;
    public LetterLogic(){
        currUpLetter=' ';
        //p = new Letter(getWidth(), getHeight(), currUpLetter);
        p = new Letter(500, 500, currUpLetter);
        lcList = new ArrayList<>();
        left = new Wall(0,0,0, 0);
        bottom = new Wall(0, 0, 0, 0);
        top = new Wall(0, 0, 0, 0);
        right = new Wall(0, 0, 0, 0);
        time = System.currentTimeMillis();
        gameOver=false;

    }

    public boolean alterPlayerVelocities(int velX, int velY){
        p.setxVel(velX);
        p.setyVel(velY);

        return true;
    }
    public boolean alterGameArea(int width, int height){
        if( this.width == -1 && this.height == -1 ){
            p.setxPos(width /2 );
            p.setyPos(height/2);
        }
System.out.println("wall: "+width);
        // Position your walls
        left = new Wall(0-width, 0, width, height);
        right = new Wall(width, 0, width, height);
        top = new Wall(0, 0 - height, width, height);
        bottom = new Wall(0, height, width, height);
        this.width=width;
        this.height=height;
        initLevel();
        return true;
    }
    public void hitsBottom(){
        boolean hit=false;
        for (int i=0; i<lcList.size(); i++) {
            if (lcList.get(i).getyPos() > height - lcList.get(i).getSize()) {
                lcList.get(i).setyPos(lcList.get(i).getSize() + 1);
                hit = true;
            }
        }
    }
    public boolean gameTick(long timePassed){
        p.move();
        collision();
        hitsBottom();
        Log.d("tick",timePassed+"");
        if (timePassed%1000==0){
            System.out.println(timePassed/1000);
        }
        for (int i = 0; i<lcList.size(); i++){
            lcList.get(i).move();
        }
        if (p.getxPos() + p.getSize() > right.getPX()) {
            p.setxPos(right.getPX()-p.getSize());
            p.setxVel(p.getxVel()*-1);
        }
        if (p.getyPos() + p.getSize() > bottom.getPY()) {
            p.setyPos(bottom.getPY() - p.getSize() - 1);
            p.setyVel(p.getyVel()*-1);

        }
        if (p.getxPos() - p.getSize() < left.getPX()+left.getWidth()) {
            p.setxPos(left.getPX() + left.getWidth() + p.getSize());
            p.setxVel(p.getxVel()*-1);

        }

        if (p.getyPos() - p.getSize() < top.getPY()+top.getHeight()) {
            p.setyPos(top.getPY() + top.getHeight() + p.getSize());
            p.setyVel(p.getyVel()*-1);
        }
        return true;
    }
    public int collision(){

        for (int i = 0; i<lcList.size(); i++){
            Letter lc = lcList.get(i);
            if (Math.pow(Math.abs(lc.getxPos() - p.getxPos()), 2) + Math.pow(Math.abs(lc.getyPos() - p.getyPos()), 2) <= Math.pow(lc.getSize()*2, 2)) {
                if (Character.toUpperCase(lc.getLetter())==p.getLetter()){
                    if (level<=3){
                        lcList.clear();
                    }
                    if (level<=8) {
                        level++;
                    }
                    initLevel();
                    p.setxPos(width/2);
                    p.setyPos(height);
                    Log.d("collide","yes");
                    return 1;
                }
                else{
                    if (!(p.getyPos()>height-p.getSize())){
                        setGameOver(true);
                        return 2;
                    }
                    return 3;

                }
            }
        }
        return 3;
    }
    public boolean isGameOver(){
        if (time>30000){
            gameOver=true;
            return true;
        }
        return false;
    }
    public void setGameOver(boolean g){
        gameOver=g;
    }
    public boolean getGameOver(){
        return gameOver;
    }
    public void initLevel(){
        currUpLetter=(char) (((int) (Math.floor(Math.random() * 26))) + 65);
        p.setLetter(currUpLetter);
        int quantLower = 3 + (level - 1);
        int space = Math.round(right.getPX()/(quantLower+1));
        System.out.println(space);
        int lcSize=lcList.size();
        int correctIndex = (int)(Math.floor(Math.random()*quantLower)+1);
        //if (level<=3) {
//            for (int i = 1; i<=quantLower; i++){
//                char lcLetter;
//                //if (i==correctIndex){
//                //    lcLetter=Character.toLowerCase(currUpLetter);
//                //}
//                //else{
//                lcLetter = (char) (((int) (Math.floor(Math.random() * 26))) + 97);
//                if (lcLetter==Character.toLowerCase(currUpLetter)){
//                    lcLetter=(char)(((int)lcLetter)+1);
//                }
//                //}
//                int startx=i*space;
//                int starty=50;
//                Letter lc = new Letter(startx, starty, lcLetter);
//                lc.setyVel(3);
//                lcList.add(lc);
//            }
            for (int i= 0; i<lcSize+quantLower; i++){
                char lcLetter;

                lcLetter = (char) (((int) (Math.floor(Math.random() * 26))) + 97);
                if (lcLetter==Character.toLowerCase(currUpLetter)) {
                    lcLetter = (char) (((int) lcLetter) + 1);
                }

                    //}
                if (i>=lcSize){
                    int startx=(i-lcSize+1)*space;
                    int starty=50;
                    Letter lc = new Letter(startx, starty, lcLetter);
                    lc.setyVel(3);
                    lcList.add(lc);
                }
                else{
                    lcList.get(i).setLetter(lcLetter);
                }

            }

            lcList.get(correctIndex-1).setLetter(Character.toLowerCase(currUpLetter));
        //}

//        else if (level<=6){
//
//
//        }
//        else{
//
//        }
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getLevel(){
        return level;
    }
    public Letter getPlayer(){
        return p;
    }
    public char getCurrUpLetter(){
        return currUpLetter;
    }
    public ArrayList<Letter> getLcLetters(){
        return lcList;
    }
}
