package edu.ncssm.nukala24j.decodefortrouble;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    // Variable to keep track of the number of rolls (not used in the provided code)
    private int roll = 0;

    // Instances of other activities
    private LetterActivity letActivity;
    private GameView gameView;

    // Flag to check if it's the first click
    boolean isFirstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);

        // Initializing UI elements
        TextView ruleTextView = findViewById(R.id.rule);
        Button decodeButton = findViewById(R.id.decodeButton);
        Button troubleButton = findViewById(R.id.troubleButton);

        // Disable the 'Trouble' button initially
        troubleButton.setEnabled(false);

        // Set onClickListener for the 'Decode' button
        decodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the LetterActivity
                Intent intent = new Intent(getBaseContext(), LetterActivity.class);
                startActivity(intent);

                // Disable 'Decode' button and enable 'Trouble' button
                decodeButton.setEnabled(false);
                troubleButton.setEnabled(true);
            }
        });

        // Set onClickListener for the 'Trouble' button
        troubleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the button text is "Resume Trouble"
                if (troubleButton.getText().toString().equals("Resume Trouble")) {
                    // Handle resume logic if needed
                }

                // Create an intent to start the TroubleActivity
                Intent intent = new Intent(getBaseContext(), TroubleActivity.class);
                startActivity(intent);

                // Change button text to "Resume Trouble"
                troubleButton.setText("Resume Trouble");

                // Enable 'Decode' button and disable 'Trouble' button
                decodeButton.setEnabled(true);
                troubleButton.setEnabled(false);
            }
        });
    }
}
