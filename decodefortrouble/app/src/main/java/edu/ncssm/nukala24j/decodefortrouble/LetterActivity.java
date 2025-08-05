package edu.ncssm.nukala24j.decodefortrouble;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LetterActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    public static GameView gameView;
    private int vx;
    private int vy;

    // onCreate method is called when the activity is first created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        // Get reference to the GameView defined in the XML layout
        gameView = findViewById(R.id.gameView);

        // Initialize the sensor manager and register the accelerometer sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new Accelerometer(), accelerometer, SensorManager.SENSOR_DELAY_GAME);

        // Set up buttons and their click listeners
        Button backStart = findViewById(R.id.backStart);
        Button backTrouble = findViewById(R.id.backTrouble);

        // Back to Start button click listener
        backStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the StartActivity
                Intent intent = new Intent(getBaseContext(), StartActivity.class);
                // Finish the current activity
                finish();
            }
        });

        // Back to Trouble button click listener
        backTrouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the TroubleActivity
                Intent intent = new Intent(getBaseContext(), TroubleActivity.class);
                // Finish the current activity
                finish();
            }
        });
    }

    // Inner class to handle accelerometer events
    private class Accelerometer implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // Update the velocity based on accelerometer values
            vy = Math.round(sensorEvent.values[0] * 2);
            vx = Math.round(sensorEvent.values[1] * 2);
            // Update the player velocities in the GameView
            gameView.alterPlayerVelocities(vx, vy);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            // Accuracy of the sensor changed (not used in this example)
        }
    }
}
