package edu.ncssm.nukala24j.decodefortrouble;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TroubleActivity extends AppCompatActivity {
    private TroubleLogic logic;
    private ArrayList<Button> tiles;
    private ArrayList<Integer> tilesQuants;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize TroubleLogic and set content view
        logic = new TroubleLogic();
        setContentView(R.layout.activity_trouble);

        // Retrieve UI elements
        Button piece1 = findViewById(R.id.piece1);
        Button piece2 = findViewById(R.id.piece2);
        TextView turn = findViewById(R.id.turn);
        Button roll = findViewById(R.id.roll);
        GridLayout grid = findViewById(R.id.boardContainer);

        // Set grid dimensions
        grid.setRowCount(10);
        grid.setColumnCount(10);

        // Initialize lists to store buttons and their corresponding values
        tiles = new ArrayList<>();
        tilesQuants = new ArrayList<>();

        // Set background color for piece1 button
        piece1.setBackgroundColor(Color.BLUE);

        // Add buttons to the top row
        for (int i = 0; i < 10; i++) {
            Button button = new Button(this);
            int buttonValue = i + 1;

            GridLayout.Spec row = GridLayout.spec(0, GridLayout.FILL, 1f);
            GridLayout.Spec col = GridLayout.spec(i, GridLayout.FILL, 1f);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);

            lp.setGravity(Gravity.FILL);
            button.setLayoutParams(lp);
            grid.addView(button);
            tiles.add(button);
            tilesQuants.add(buttonValue);
        }

        // Add buttons to the right column
        for (int i = 1; i < 10; i++) {
            Button button = new Button(this);
            int buttonValue = i + 10;

            GridLayout.Spec row = GridLayout.spec(i, GridLayout.FILL, 1f);
            GridLayout.Spec col = GridLayout.spec(9, GridLayout.FILL, 1f);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);

            lp.setGravity(Gravity.FILL);
            button.setLayoutParams(lp);
            grid.addView(button);
            tiles.add(button);
            tilesQuants.add(buttonValue);
        }

        // Add buttons to the bottom row
        for (int i = 8; i >= 0; i--) {
            Button button = new Button(this);
            int buttonValue = 20 + i;

            GridLayout.Spec row = GridLayout.spec(9, GridLayout.FILL, 1f);
            GridLayout.Spec col = GridLayout.spec(i, GridLayout.FILL, 1f);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);

            lp.setGravity(Gravity.FILL);
            button.setLayoutParams(lp);
            grid.addView(button);
            tiles.add(button);
            tilesQuants.add(buttonValue);
        }

        // Add buttons to the left column
        for (int i = 8; i > 0; i--) {
            Button button = new Button(this);
            int buttonValue = 30 + i;

            GridLayout.Spec row = GridLayout.spec(i, GridLayout.FILL, 1f);
            GridLayout.Spec col = GridLayout.spec(0, GridLayout.FILL, 1f);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);

            lp.setGravity(Gravity.FILL);
            button.setLayoutParams(lp);
            grid.addView(button);
            tiles.add(button);
            tilesQuants.add(buttonValue);
        }

        // Set the initial configuration of the game board
        logic.setTiles(tilesQuants);
        updateTiles();

        // Set click listeners for buttons
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollQuant = LetterActivity.gameView.getLogic().getLevel();
                if (roll.getText().toString().contains("You rolled")){
                    Intent intent = new Intent(getBaseContext(), LetterActivity.class);
                    startActivity(intent);
                    piece1.setEnabled(true);
                    piece2.setEnabled(true);
                    roll.setText("What did I roll?");
                }
                roll.setText("You rolled a "+rollQuant+", click to go back");
                logic.setRollQuantity(rollQuant);
                roll.setEnabled(false);
            }
        });

        piece1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollQuant = LetterActivity.gameView.getLogic().getLevel();
                logic.moveTile(rollQuant, true);
                updateTiles();
                if (logic.whichPlayer()){
                    turn.setText("Player 1's turn");
                }
                else{
                    turn.setText("Player 2's turn");
                }
                piece1.setEnabled(false);
                piece2.setEnabled(false);
                roll.setEnabled(true);
            }
        });

        piece2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollQuant = LetterActivity.gameView.getLogic().getLevel();
                logic.moveTile(rollQuant, false);
                updateTiles();
                if (logic.whichPlayer()){
                    turn.setText("Player 1's turn");
                }
                else{
                    turn.setText("Player 2's turn");
                }
                piece1.setEnabled(false);
                piece2.setEnabled(true);
                roll.setEnabled(true);
            }
        });
    }

    // Getter for TroubleLogic instance
    public TroubleLogic getLogic(){
        return logic;
    }

    // Update the text on buttons to represent the current state of the game board
    public void updateTiles(){
        int player1Piece1 = logic.getPlayer1Piece()[0].getCurrTile();
        int player1Piece2 = logic.getPlayer1Piece()[1].getCurrTile();
        int player2Piece1 = logic.getPlayer2Piece()[0].getCurrTile();
        int player2Piece2 = logic.getPlayer2Piece()[1].getCurrTile();

        for (int i = 1; i <= tiles.size(); i++){
            Button curr = tiles.get(i - 1);

            if (i == player1Piece1 && i == player1Piece2){
                curr.setText("bothP1");
            }
            else if(i == player1Piece1){
                curr.setText("p1p1");
            }
            else if(i == player1Piece2){
                curr.setText("p1p2");
            }

            if (i == player2Piece1 && i == player2Piece2){
                curr.setText("bothP2");
            }
            else if(i == player2Piece1){
                curr.setText("p2p1");
            }
            else if(i == player2Piece2){
                curr.setText("p2p2");
            }

            if (i != player1Piece1 && i != player1Piece2 && i != player2Piece1 && i != player2Piece2){
                curr.setText("");
            }
        }
    }
}
