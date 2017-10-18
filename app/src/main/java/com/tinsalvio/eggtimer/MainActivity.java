package com.tinsalvio.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean counterIsActive = false;
    Button controlButton;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        controlButton.setText("Go!");
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60; // will round down
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);
         if (seconds < 10) {
            secondString = "0" + secondString;
         }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }


    public void controlTimer(View view){

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controlButton.setText("Stop");

            Log.d("Egg Timer", "Timer Controller Clicked");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000); // cast to integer because
                    // updateTimer uses integer as input
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhornmp3);
                    mplayer.start();
                    resetTimer();


                    Log.d("Egg Timer: ", "Finished");
                    Log.d("Egg Timer2: ", "Finished2");
                    Log.d("Egg Timer2: ", "Third save");
                    Log.d("Egg Timer2: ", "Fouth save");
                    Log.d("Egg Timer2: ", "Fifth save");

                }
            }.start();
        } else {
            resetTimer();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controlButton = (Button) findViewById(R.id.controllerButton);

        timerSeekBar.setMax(600); // max to 10 minutes 10 * 60 seconds
        timerSeekBar.setProgress(30); //

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
