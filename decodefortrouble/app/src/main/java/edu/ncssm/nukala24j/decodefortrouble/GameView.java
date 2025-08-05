package edu.ncssm.nukala24j.decodefortrouble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
    private final int GAME_TIME = 20;
    private AnimationTimer animTimer;
    private GameTimer gameTimer;
    public float screenWidth;
    public float screenHeight;
    private int px;
    private int py;
    private int count = 0;
    private LetterLogic logic;
    private long gameStartTime = (long) System.currentTimeMillis();

    // Constructor
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        logic = new LetterLogic();
        animTimer = new AnimationTimer();
        animTimer.start();

        gameTimer = new GameTimer();
        gameTimer.start();
    }

    // Getter for LetterLogic instance
    public LetterLogic getLogic() {
        return logic;
    }

    // Drawing on the canvas
    @Override
    protected void onDraw(Canvas c) {
        // Set background
        Drawable bg = getResources().getDrawable(R.drawable.background);
        bg.setBounds(0, 0, c.getWidth(), c.getHeight());
        bg.draw(c);
        screenHeight = c.getHeight();
        screenWidth = c.getWidth();

        // Set up paint for drawing
        Paint p = new Paint();
        p.setStyle((Paint.Style.FILL));
        p.setColor(Color.GREEN);

        Paint t = new Paint();
        t.setColor(Color.YELLOW);
        t.setTextSize(30);
        long timeDisplay = (long) ((System.currentTimeMillis() - gameStartTime) / 1000);

        // Draw time and level information
        c.drawText(timeDisplay + "", (screenWidth * 6) / 7, screenHeight / 7, t);
        c.drawText(logic.getLevel() + "", (screenWidth) / 7, screenHeight / 7, t);
        t.setColor(Color.BLACK);

        // Draw player circle and letter
        c.drawCircle((float) logic.getPlayer().getxPos(), (float) logic.getPlayer().getyPos(), logic.getPlayer().getSize(), p);
        float textX = (float) logic.getPlayer().getxPos() - t.measureText(Character.toString(logic.getPlayer().getLetter())) / 2;
        float textY = (float) logic.getPlayer().getyPos() + t.getTextSize() / 4;
        c.drawText(Character.toString(logic.getPlayer().getLetter()), textX, textY, t);

        // Draw letters on the canvas
        p.setColor(Color.RED);
        for (int i = 0; i < logic.getLcLetters().size(); i++) {
            Letter currLetter = logic.getLcLetters().get(i);
            if (logic.getLevel() >= 6) {
                float rotationAngle = (float) ((System.currentTimeMillis() - gameStartTime) * 0.4 % 360);
                c.save();
                c.rotate(rotationAngle, (float) currLetter.getxPos(), (float) currLetter.getyPos());
                c.drawCircle((float) currLetter.getxPos(), (float) currLetter.getyPos(), currLetter.getSize(), p);
                textX = (float) currLetter.getxPos() - t.measureText(Character.toString(currLetter.getLetter())) / 2;
                textY = (float) currLetter.getyPos() + t.getTextSize() / 4;
                c.drawText(Character.toString(currLetter.getLetter()), textX, textY, t);
                c.restore();
            } else {
                c.drawCircle((float) currLetter.getxPos(), (float) currLetter.getyPos(), currLetter.getSize(), p);
                textX = (float) currLetter.getxPos() - t.measureText(Character.toString(currLetter.getLetter())) / 2;
                textY = (float) currLetter.getyPos() + t.getTextSize() / 4;
                c.drawText(Character.toString(currLetter.getLetter()), textX, textY, t);
            }
        }
    }

    // Method to alter player velocities
    public boolean alterPlayerVelocities(int vx, int vy) {
        logic.alterPlayerVelocities(vx, vy);
        return true;
    }

    // Method called when the size of the view changes
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        logic.alterGameArea(w, h);
    }

    // Animation timer thread for invalidating the view
    private class AnimationTimer extends Thread {
        private boolean done = false;

        // Invalidates the game view so it can update
        public void run() {
            while (!done) {
                GameView.this.invalidate();
            }
        }
    }

    // Game timer thread for running the game and adding to the game ticks
    private class GameTimer extends Thread {
        private long lastUpdated = 0;
        private boolean done = false;

        // Constructor
        public GameTimer() {
            lastUpdated = System.currentTimeMillis();
        }

        // Running the game and adding to the game ticks
        public void run() {
            while (!done) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - lastUpdated;
                if (deltaTime >= GAME_TIME && !logic.getGameOver() && (currentTime - gameStartTime) / 1000 <= 30) {
                    logic.gameTick(deltaTime);
                    lastUpdated = currentTime;
                }
            }
        }
    }
}
