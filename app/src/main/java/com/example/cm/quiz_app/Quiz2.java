package com.example.cm.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cm.quiz_app.Helper.ScoreHelper;
import com.github.lzyzsd.circleprogress.CircleProgress;

public class Quiz2 extends AppCompatActivity {

    int score = 1;
    Button blnext;
    RadioGroup gr1;
    RadioButton b1, b2;
    TextView tvscore;
    CircleProgress c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        gr1 = findViewById(R.id.gr1);
        b1 = findViewById(R.id.etOption1);
        b2 = findViewById(R.id.etOption2);
        blnext = findViewById(R.id.blNext);

        /*
        Si vous démarrez une activité avec des données, par exemple en faisant
Intent intent = new Intent(context, SomeActivity.class);
intent.putExtra("someKey", someData);
vous pouvez récupérer ces données en utilisant getIntent dans la nouvelle activité:
Intent intent = getIntent();
intent.getExtra("someKey") ...
Donc, ce n'est pas pour gérer les données de retour d'une activité,
comme onActivityResult, mais c'est pour passer des données à une nouvelle activité.
        */

        // Intent i = getIntent();
        //  int int1 = i.getIntExtra("score", 0);


        blnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b1.isChecked()) {
                    ScoreHelper.increment();
                }

                Intent intent = new Intent(Quiz2.this, Quiz3.class);
                startActivity(intent);
                //else if (b2.isChecked() ){
                //  Toast.makeText(getApplicationContext(),"Reponse incorrect", Toast.LENGTH_SHORT).show();
                // b1.setChecked(false);
                //b2.setChecked(false);

            }
        });
    }
}

