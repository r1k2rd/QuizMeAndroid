package com.richardriispere.quizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PreGameActivity extends Activity {

    static CheckBox mathChkBox;
    static CheckBox geographyChkBox;
    static CheckBox natureChkBox;
    static CheckBox traditionsChkBox;

    static Button startGoBtn;

    static TextView alertText;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_window);

        mathChkBox = (CheckBox) findViewById(R.id.mathsCheckBoxID);
        geographyChkBox = (CheckBox) findViewById(R.id.geographyChkBoxID);
        natureChkBox = (CheckBox) findViewById(R.id.natureChkBoxID);
        traditionsChkBox = (CheckBox) findViewById(R.id.traditionsChkBoxID);

        startGoBtn = (Button) findViewById(R.id.startgoBtnID);

        alertText = (TextView) findViewById(R.id.chkBoxAlertID);

        startGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emptyBoxes() == true) {

                    //Starting a new Intent
                    Intent gameScreen = new Intent(getApplicationContext(), GameActivity.class);
                    alertText.setVisibility(View.INVISIBLE);
                    startActivity(gameScreen);

                }else if(emptyBoxes() == false){
                    alertText.setText("Check at least one of the boxes");
                    alertText.setVisibility(View.VISIBLE);
                             }

                }
        });
    }

    public boolean emptyBoxes(){
        if(mathChkBox.isChecked() == false
                && geographyChkBox.isChecked() == false
                && traditionsChkBox.isChecked() == false
                && natureChkBox.isChecked() == false){
            System.out.println("All boxes are unchecked");
            return false;
        }else{
            System.out.println("At least one of the boxes is checked");
            return true;
        }
    }
}
