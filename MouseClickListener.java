package com.richardriispere.quizme;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MouseClickListener implements View.OnClickListener {

    static int rightAnswers = 0;
    static int wrongAnswers = 0;


    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        String answer;
        Object source = v.getId();


                if (source.equals(R.id.answer1BtnID)) {
                    answer = GameWindow.answerButton1.getText().toString();
                    if (answer.equals(GameWindow.correctAnswer)) {
                        /*
                        GameWindow.answerLabel.setText("Correct answer. " + GameWindow.explanation);
                        */
                        GameWindow.answerButton1.setBackgroundColor(Color.GREEN);
                        GameWindow.answerButton1.setOnClickListener(null);
                        GameWindow.answerButton2.setOnClickListener(null);
                        GameWindow.answerButton3.setOnClickListener(null);

                        GameWindow.newqButton.setVisibility(View.VISIBLE);
                        GameWindow.explainBtn.setVisibility(View.VISIBLE);
                        GameWindow.newqButton.setOnClickListener(GameWindow.clicklistener);

                        rightAnswers++;

                        GameWindow.timer.cancel();
                    } else {
                        /*
                        GameWindow.answerLabel.setText("Right answer: " + GameWindow.correctAnswer
                                + ". " + GameWindow.explanation);
                        */
                        GameWindow.answerButton1.setBackgroundColor(Color.RED);
                        GameWindow.answerButton1.setOnClickListener(null);
                        GameWindow.answerButton2.setOnClickListener(null);
                        GameWindow.answerButton3.setOnClickListener(null);

                        GameWindow.newqButton.setVisibility(View.VISIBLE);
                        GameWindow.explainBtn.setVisibility(View.VISIBLE);
                        GameWindow.newqButton.setOnClickListener(GameWindow.clicklistener);

                        wrongAnswers++;
                        GameWindow.timer.cancel();
                    }
                }
                if (source.equals(R.id.answer2BtnID)) {
                    answer = GameWindow.answerButton2.getText().toString();
                    if (answer.equals(GameWindow.correctAnswer)) {
                        /*
                        GameWindow.answerLabel.setText("Correct answer. " + GameWindow.explanation);
                        */
                        GameWindow.answerButton2.setBackgroundColor(Color.GREEN);
                        GameWindow.answerButton1.setOnClickListener(null);
                        GameWindow.answerButton2.setOnClickListener(null);
                        GameWindow.answerButton3.setOnClickListener(null);

                        GameWindow.newqButton.setVisibility(View.VISIBLE);
                        GameWindow.explainBtn.setVisibility(View.VISIBLE);
                        GameWindow.newqButton.setOnClickListener(GameWindow.clicklistener);

                        rightAnswers++;
                        GameWindow.timer.cancel();
                    } else {
                        /*
                        GameWindow.answerLabel.setText("Right answer: " + GameWindow.correctAnswer
                                + ". " + GameWindow.explanation);
                        */
                        GameWindow.answerButton2.setBackgroundColor(Color.RED);
                        GameWindow.answerButton1.setOnClickListener(null);
                        GameWindow.answerButton2.setOnClickListener(null);
                        GameWindow.answerButton3.setOnClickListener(null);

                        GameWindow.newqButton.setVisibility(View.VISIBLE);
                        GameWindow.explainBtn.setVisibility(View.VISIBLE);
                        GameWindow.newqButton.setOnClickListener(GameWindow.clicklistener);

                        wrongAnswers++;
                        GameWindow.timer.cancel();
                    }
                }
                if (source.equals(R.id.answer3BtnID)) {
                    answer = GameWindow.answerButton3.getText().toString();
                    if (answer.equals(GameWindow.correctAnswer)) {
                        GameWindow.answerButton3.setBackgroundColor(Color.GREEN);
                        /*
                        GameWindow.answerLabel.setText("Correct answer. " + GameWindow.explanation);
                        */
                        GameWindow.answerButton1.setOnClickListener(null);
                        GameWindow.answerButton2.setOnClickListener(null);
                        GameWindow.answerButton3.setOnClickListener(null);

                        GameWindow.newqButton.setVisibility(View.VISIBLE);
                        GameWindow.explainBtn.setVisibility(View.VISIBLE);
                        GameWindow.newqButton.setOnClickListener(GameWindow.clicklistener);

                        rightAnswers++;
                        GameWindow.timer.cancel();
                    } else {
                        /*
                        GameWindow.answerLabel.setText("Right answer: " + GameWindow.correctAnswer
                                + ". " + GameWindow.explanation);
                        */
                        GameWindow.answerButton3.setBackgroundColor(Color.RED);
                        GameWindow.answerButton1.setOnClickListener(null);
                        GameWindow.answerButton2.setOnClickListener(null);
                        GameWindow.answerButton3.setOnClickListener(null);

                        wrongAnswers++;

                        GameWindow.newqButton.setVisibility(View.VISIBLE);
                        GameWindow.explainBtn.setVisibility(View.VISIBLE);
                        GameWindow.newqButton.setOnClickListener(GameWindow.clicklistener);

                        GameWindow.timer.cancel();
                    }
                }

                if (source.equals(R.id.nextqBtnID)) {
                    try {
                        GameWindow.setGame();
                        System.out.print("setGame() clicked");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }



        }


}
