package com.richardriispere.quizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends Activity {

    static TextView rightAnswers;
    static Button menuButton;

    private int rightanswersInt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_window);

        rightAnswers = (TextView) findViewById(R.id.rightanswersTxtID);
        menuButton = (Button) findViewById(R.id.menuBtnID);

        if(MouseClickListener.rightAnswers > 0) {
            rightanswersInt = (100/(MouseClickListener.rightAnswers + MouseClickListener.wrongAnswers))*MouseClickListener.rightAnswers;
            rightAnswers.setText("Right answers: " + rightanswersInt + "%");
        }else{
            rightAnswers.setText("Right answers: 0%");
        }

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting a new Intent
                Intent menuScreen = new Intent(getApplicationContext(), MenuActivity.class);



                startActivity(menuScreen);
            }
        });
    }

}
