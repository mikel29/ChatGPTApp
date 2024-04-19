package com.femm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import elements.QuestionsDef;
import elements.Question;

public class StepActivityDev extends AppCompatActivity {

    private Question question;
    private String questionTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setQuestion();


        LinearLayout l=new LinearLayout(getApplicationContext());

        TextView label1=new TextView(getApplicationContext());
        label1.setText(this.question.getQuestion());

        l.addView(label1);

        setContentView(l);

       /* Intent i=getIntent();
        question q=i.getParcelableExtra("questionData");

        Toast.makeText(getApplicationContext(),q.getQuestion(), Toast.LENGTH_LONG).show();*/
    }





    public void  setQuestion(){


        int step=getSharedPreferences(MainActivity.USER_DETAILS_KEY,MODE_PRIVATE).getInt("step",0);
        if(step>=1){

            for ( Question element : QuestionsDef.getQuestions()) {
                if (element.getStep()==step) {
                    this.question = element;
                    break;
                }
            }

            if (this.question != null) {
                Toast.makeText(getApplicationContext(), this.question.getQuestion(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "NO hay resultados", Toast.LENGTH_SHORT).show();
            }
        }
    }






}