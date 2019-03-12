package com.richardriispere.quizme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    static Button startButton;
    static Button statsButton;

    static Animation anim;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_window);

        startButton = (Button) findViewById(R.id.startBtnID);
        statsButton = (Button) findViewById(R.id.statsBtnID);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Starting a new Intent
                Intent preGameScreen = new Intent(getApplicationContext(), PreGameActivity.class);

                startActivity(preGameScreen);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent statsScreen = new Intent(getApplicationContext(), StatsActivity.class);

                startActivity(statsScreen);
            }
        });
    }
}
