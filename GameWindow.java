package com.richardriispere.quizme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import static com.richardriispere.quizme.R.*;
import static com.richardriispere.quizme.R.color.*;

/*
Vt. getMathsList() meetodit. Parandada see, et valed vastused sama väärtusega
vahepeal.
 */

public class GameWindow {

    static TextView questionLabel;
    static TextView answerLabel;

    static AssetManager assetManager;
    static InputStream ip;


    static Button answerButton1;
    static Button answerButton2;
    static Button answerButton3;
    static Button menuButton;
    static FloatingActionButton explainBtn;

    static ProgressBar timerbar;
    static CountDownTimer timer;
    static TextView timertxt;
    static int x;

    static String q, a, e, explanation;

    static String correctAnswer;
    static Button newqButton;

    static int checkedBoxes = 0;

    protected Canvas canvas = new Canvas();

    static GameWindow gameWindow;

    static MouseClickListener clicklistener = new MouseClickListener();


    public GameWindow(Context context){


        try {
            assetManager = context.getAssets();
            gameWindow.setGame();

            System.out.println("setGame() called");
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            System.out.println("setGame() not called");
            System.out.println(e);
        }

    }


    //Reads 3 text files and saves the text as lists of string lines in another list and returns it
    public static ArrayList getLists() throws IOException {

        ArrayList<String> theQuestions = new ArrayList<String>();
        ArrayList<String> theAnswers = new ArrayList<String>();
        ArrayList<String> theExplanations = new ArrayList<String>();

        if(PreGameActivity.traditionsChkBox.isChecked() == true) {
            fillLists(theQuestions, theAnswers, theExplanations, "Traditions");
            checkedBoxes++;
        }
        if(PreGameActivity.geographyChkBox.isChecked() == true) {
            fillLists(theQuestions, theAnswers, theExplanations, "Geography");
            checkedBoxes++;
        }
        if(PreGameActivity.natureChkBox.isChecked() == true) {
            fillLists(theQuestions, theAnswers, theExplanations, "Nature");
            checkedBoxes++;
        }
        ArrayList list = new ArrayList(3);
        list.add(theQuestions);
        list.add(theAnswers);
        list.add(theExplanations);

        return list;

    }
    /*
    This method takes the previous list and randomly chooses
    a question, an answer and an explanation and returns it
    as a list with 3 spaces.
     */
    public static ArrayList<String> getQandA() throws IOException {

        ArrayList list = getLists();


        ArrayList<String> theQuestions = (ArrayList<String>) list.get(0);
        ArrayList<String> theAnswers = (ArrayList<String>) list.get(1);
        ArrayList<String> theExplanations = (ArrayList<String>) list.get(2);

        ArrayList<String> QandA = new ArrayList<String>();

        Random r = new Random();
        String again = "Y";
        String in;

        if(theAnswers.size() > 0) {
            int random = r.nextInt(theAnswers.size());
            String question = theQuestions.get(random);
            String answer = theAnswers.get(random);
            String explanation = theExplanations.get(random);

            QandA.add(question);
            QandA.add(answer);
            QandA.add(explanation);
        }




        if(PreGameActivity.mathChkBox.isChecked() == true) {
            System.out.println("MathChkBox is checked");
            System.out.println(checkedBoxes+1);
            if(r.nextInt(checkedBoxes+1)+1 == 1 && checkedBoxes >= 1){
                QandA = getMathsList();
            }
            else if(checkedBoxes == 0){
                QandA = getMathsList();
            }
        }

        return QandA;

    }

    /*
    Sets the question text of the question label and returns
    3 answer options.
     */
    public static String[] getAnswerOptions() throws IOException{

        ArrayList<String> QandA = getQandA();
        q = QandA.get(0);
        a = QandA.get(1);
        e = QandA.get(2);

        System.out.println("");


        String[] answerOptions1 = a.split(";");
        String[] answerOptions = new String[4];
        answerOptions[0] = answerOptions1[0];
        answerOptions[1] = answerOptions1[1];
        answerOptions[2] = answerOptions1[2];
        answerOptions[3] = e;

        GameWindow.answerLabel.setText("");
        GameWindow.questionLabel.setText(q);



        return answerOptions;

    }


    /*
    Finds the correct answer from 3 answer options
    (The right answer contains an asterisk.
     */

    public static String rightAnswer(String[] answerOptions) throws IOException{

        String correctAnswer = "";

        for(String answer : answerOptions) {
            if(answer.contains("*")) {
                answer = answer.replace("*", "");
                correctAnswer = answer;

            }
        }
        return correctAnswer;
    }

    @SuppressLint("RestrictedApi")
    public static void setGame() throws IOException {


        String[] answerOptions = getAnswerOptions();

        newqButton.setVisibility(View.INVISIBLE);
        newqButton.setOnClickListener(null);
        explainBtn.setVisibility(View.INVISIBLE);

        answerButton1.setVisibility(View.VISIBLE);
        answerButton2.setVisibility(View.VISIBLE);
        answerButton3.setVisibility(View.VISIBLE);

        answerButton1.setBackgroundColor(Color.rgb(207,216,220));
        answerButton2.setBackgroundColor(Color.rgb(207,216,220));
        answerButton3.setBackgroundColor(Color.rgb(207,216,220));

        answerButton1.setOnClickListener(clicklistener);
        answerButton2.setOnClickListener(clicklistener);
        answerButton3.setOnClickListener(clicklistener);

        checkedBoxes = 0;

        x = 30;

        timerbar.setProgress(100);
        timer = new CountDownTimer(30000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {
                x--;
                timerbar.setProgress(x*100/(30000/1000));
                timertxt.setText(String.valueOf(x));
            }

            @Override
            public void onFinish() {
                if(answerButton1.getText().toString().equals(correctAnswer)){
                    answerButton1.setBackgroundColor(Color.GREEN);
                }
                if(answerButton2.getText().toString().equals(correctAnswer)){
                    answerButton2.setBackgroundColor(Color.GREEN);
                }
                if(answerButton3.getText().toString().equals(correctAnswer)){
                    answerButton3.setBackgroundColor(Color.GREEN);
                }

                GameWindow.explainBtn.setVisibility(View.VISIBLE);
            }
        };
        timer.start();

        correctAnswer = rightAnswer(answerOptions);

        int i = 0;
        for(String answer : answerOptions) {
            if(answer.contains("*")) {
                answerOptions[i] = answer.replace("*", "");
            }
            i++;
        }

        /*
        for(int z = 0; z < 3; z++) {
            if(answerOptions[z].length() > 85) {
                answerOptions[z] = stringToNextLine(answerOptions[z]);
            }
        }
        */



        Random random = new Random();
        int r = random.nextInt(2) ;

        explanation = answerOptions[3];

        GameWindow.answerButton1.setText(answerOptions[r]);

        for(String answer:answerOptions){
            System.out.println(answer);
        }



        if(answerButton1.getText().toString() == answerOptions[0]) {
            answerButton2.setText(answerOptions[1]);
            answerButton3.setText(answerOptions[2]);
        }

        if(answerButton1.getText().toString() == answerOptions[1]) {
            answerButton2.setText(answerOptions[2]);
            answerButton3.setText(answerOptions[0]);
        }

        if(answerButton1.getText().toString() == answerOptions[2]) {
            answerButton2.setText(answerOptions[0]);
            answerButton3.setText(answerOptions[1]);
        }


    }

    public static String getExplanation(String[] answerOptions) {
        String explanation = answerOptions[3];
        return explanation;
    }



    public static void fillLists(ArrayList<String> theQuestions,
                                 ArrayList<String> theAnswers,
                                 ArrayList<String> theExplanations,
                                 String address) throws IOException {
        ip = assetManager.open(address + "/answers.txt");

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(ip));
            String string;
            while((string = bufferedReader.readLine()) != null) {
                theAnswers.add(string);

            }
        }catch(FileNotFoundException e) {
            System.out.println("Answers file was not found");
            System.out.println("Error: " + e);
        }finally {
            bufferedReader.close();
        }



        ip = assetManager.open(address + "/questions.txt");

        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(ip));
            String string;
            while((string = bufferedReader2.readLine()) != null) {
                theQuestions.add(string);
            }
        }catch(FileNotFoundException e) {
            System.out.println("Error: " + e);
        }finally {
            bufferedReader2.close();
        }



        ip = assetManager.open(address + "/explanations.txt");

        BufferedReader bufferedReader3 = null;
        try {
            bufferedReader3 = new BufferedReader(new InputStreamReader(ip));
            String string;
            while((string = bufferedReader3.readLine()) != null) {
                theExplanations.add(string);
            }
        }catch(FileNotFoundException e) {
            System.out.println("Error: " + e);
        }finally {
            bufferedReader3.close();
        }
    }
    /*
    public static String stringToNextLine(String word) {
        ArrayList<String> list= new ArrayList<String>();
        String[] sublist = word.split("");


        for(String letter: sublist) {
            list.add(letter);
        }

        word = "";
        list.add(0, "<html>");
        list.add(list.size(), "</html>");


        for(String particle : list) {
            word += particle;
        }

        return word;
    }
    */

    public static ArrayList<String> getMathsList(){

        ArrayList<String> mathsList = new ArrayList<String>();

        Random random = new Random();
        int randomNum1;
        int randomNum2;

        int bufferNum1;
        int bufferNum2;

        ArrayList<Integer> bufferNums = new ArrayList<Integer>();


        String[] mathOperatorList = new String[4];
        mathOperatorList[0] = "+";
        mathOperatorList[1] = "-";
        mathOperatorList[2] = "/";
        mathOperatorList[3] = "*";

        int opRandom = random.nextInt(mathOperatorList.length);

        String question = "";
        double correctAnswer = 0;
        double answerOption1 = 0;
        double answerOption2 = 0;

        int randomDecider1;
        int randomDecider2;

        if(mathOperatorList[opRandom] == "+" || mathOperatorList[opRandom] == "-"){

            randomNum1 = random.nextInt(50)+1;
            randomNum2 = random.nextInt(50)+1;

            bufferNum1 = random.nextInt(randomNum1);
            bufferNum2 = random.nextInt(randomNum2);

            bufferNums.add(bufferNum1);
            bufferNums.add(bufferNum2);

            if(mathOperatorList[opRandom] == "+") {
                question = randomNum1 + " + " + randomNum2;
                correctAnswer = randomNum1 + randomNum2;
            }
            else{
                question = randomNum1 + " - " + randomNum2;
                correctAnswer = randomNum1 - randomNum2;
            }



                randomDecider1 = random.nextInt(2);
                randomDecider2 = random.nextInt(2);

                if (randomDecider1 == 0) {
                    answerOption1 = correctAnswer + bufferNums.get(randomDecider2);
                }
                if (randomDecider1 == 1) {
                    answerOption1 = correctAnswer - bufferNums.get(randomDecider2);
                }
                if (randomDecider2 == 0) {
                    answerOption2 = correctAnswer + bufferNums.get(randomDecider1);
                }
                if (randomDecider2 == 1) {
                    answerOption2 = correctAnswer - bufferNums.get(randomDecider1);
                }




        }


        if(mathOperatorList[opRandom] == "/" || mathOperatorList[opRandom] == "*"){

            do {
                randomNum1 = random.nextInt(15) + 1;
                randomNum2 = random.nextInt(15) + 1;
                System.out.println(randomNum1/randomNum2);
            }while (Math.round(randomNum1/randomNum2) == 0);

            bufferNum1 = random.nextInt(randomNum1)/3;
            bufferNum2 = random.nextInt(randomNum2)/3;

            bufferNums.add(bufferNum1);
            bufferNums.add(bufferNum2);


                if (mathOperatorList[opRandom] == "/") {
                    question = randomNum1 + " / " + randomNum2;
                    correctAnswer = Math.round(Double.parseDouble(String.valueOf(randomNum1)) / Double.parseDouble(String.valueOf(randomNum2)) * 100);
                    correctAnswer = correctAnswer / 100;
                    System.out.println("Correct answer: " + correctAnswer / 100);


                    randomDecider1 = random.nextInt(2);
                    randomDecider2 = random.nextInt(2);

                    if (randomDecider1 == 0) {
                        answerOption1 = correctAnswer * (bufferNums.get(randomDecider2) + 1);
                    }
                    if (randomDecider1 == 1) {
                        answerOption1 = correctAnswer / (bufferNums.get(randomDecider2) + 1);
                    }
                    if (randomDecider2 == 0) {
                        answerOption2 = correctAnswer * (bufferNums.get(randomDecider1) + 1);
                    }
                    if (randomDecider2 == 1) {
                        answerOption2 = correctAnswer / (bufferNums.get(randomDecider1) + 1);
                    }

                    answerOption1 = Math.round(answerOption1 * 100);
                    answerOption1 = answerOption1 / 100;

                    answerOption2 = Math.round(answerOption2 * 100);
                    answerOption2 = answerOption2 / 100;

                } else {
                    question = randomNum1 + " * " + randomNum2;
                    correctAnswer = randomNum1 * randomNum2;


                    randomDecider1 = random.nextInt(2);
                    randomDecider2 = random.nextInt(2);

                    if (randomDecider1 == 0) {
                        answerOption1 = correctAnswer * (bufferNums.get(randomDecider2) + 1);
                    }
                    if (randomDecider1 == 1) {
                        answerOption1 = correctAnswer / (bufferNums.get(randomDecider2) + 1);
                    }
                    if (randomDecider2 == 0) {
                        answerOption2 = correctAnswer * (bufferNums.get(randomDecider1) + 1);
                    }
                    if (randomDecider2 == 1) {
                        answerOption2 = correctAnswer / (bufferNums.get(randomDecider1) + 1);
                    }

                }




        }

        String answers = "";

        if(correctAnswer % 1 == 0){
            answers = (int)correctAnswer + "*;" + (int)answerOption1 + ";" + (int)answerOption2;
        }else {
            answers = correctAnswer + "*;" + answerOption1 + ";" + answerOption2;
        }

        String explanation = String.valueOf(correctAnswer);

        mathsList.add(question);
        mathsList.add(answers);
        mathsList.add(explanation);

        return mathsList;
    }

}
