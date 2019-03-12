package com.richardriispere.quizme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class GameActivity extends Activity {


    GameWindow gameWindow;
    AssetManager assetManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_window);

        GameWindow.newqButton = (Button) findViewById(R.id.nextqBtnID);
        GameWindow.menuButton = (Button) findViewById(R.id.menuBtnID);
        GameWindow.questionLabel = (TextView) findViewById(R.id.questionTextID);
        GameWindow.answerButton1 = (Button) findViewById(R.id.answer1BtnID);
        GameWindow.answerButton2 = (Button) findViewById(R.id.answer2BtnID);
        GameWindow.answerButton3 = (Button) findViewById(R.id.answer3BtnID);
        GameWindow.explainBtn = (FloatingActionButton) findViewById(R.id.explainBtnID);
        GameWindow.answerLabel = (TextView) findViewById(R.id.answerTextID);
        GameWindow.timerbar = (ProgressBar) findViewById(R.id.timerBarID);
        GameWindow.timertxt = (TextView) findViewById(R.id.timerCounterID);


        gameWindow = new GameWindow(this);



        GameWindow.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting a new Intent
                Intent menuScreen = new Intent(getApplicationContext(), MenuActivity.class);

                //Sending data to another Activity
                menuScreen.putExtra("rightAnswers", MouseClickListener.rightAnswers);
                menuScreen.putExtra("wrongAnswers", MouseClickListener.wrongAnswers);

                Log.e("n", MouseClickListener.rightAnswers+"."+ MouseClickListener.wrongAnswers);

                startActivity(menuScreen);
            }
        });


        GameWindow.explainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast toast = Toast.makeText(getApplicationContext(), GameWindow.explanation, Toast.LENGTH_LONG);
                    toast.show();
                }

        });

    }
}
