package com.femm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionDinamic extends AppCompatActivity {

    private String name;
    private int age;
    private double[] scores = new double[13];
    private double[] scoresIntent = new double[13];
    private int questionPosition = 0;
    private int positionAnswer = 1;
    private String[] miArrayListResp;
    private String[] miArrayScore;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_dinamic);

        // Obtener el Intent que inició esta Activity
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age", 0);
        questionPosition = intent.getIntExtra("questionPosition", 0);
        positionAnswer = intent.getIntExtra("positionAnswer", 1);
        scoresIntent = intent.getDoubleArrayExtra("scores");

        if (questionPosition > 0) {
            for (int i = 0; i< scoresIntent.length ; i++) {
                    scores[i] = scoresIntent[i];
            }
        }


        Resources res = getResources();
        String[] miArrayListPreguntas = res.getStringArray(R.array.mi_array_preguntas);

        switch (positionAnswer) {
            case 1:
                miArrayListResp = res.getStringArray(R.array.resp1);
                miArrayScore = res.getStringArray(R.array.val0);
                break;
            case 2:
                miArrayListResp = res.getStringArray(R.array.resp2);
                miArrayScore = res.getStringArray(R.array.val1);
                break;
            case 3:
                miArrayListResp = res.getStringArray(R.array.resp3);
                miArrayScore = res.getStringArray(R.array.val2);
                break;
            case 4:
                miArrayListResp = res.getStringArray(R.array.resp4);
                miArrayScore = res.getStringArray(R.array.val3);
                break;
            case 5:
                miArrayListResp = res.getStringArray(R.array.resp5);
                miArrayScore = res.getStringArray(R.array.val4);
                break;
            case 6:
                miArrayListResp = res.getStringArray(R.array.resp6);
                miArrayScore = res.getStringArray(R.array.val5);
                break;
            case 7:
                miArrayListResp = res.getStringArray(R.array.resp7);
                miArrayScore = res.getStringArray(R.array.val6);
                break;
            case 8:
                miArrayListResp = res.getStringArray(R.array.resp8);
                miArrayScore = res.getStringArray(R.array.val7);
                break;
            case 9:
                miArrayListResp = res.getStringArray(R.array.resp9);
                miArrayScore = res.getStringArray(R.array.val8);
                break;
            case 10:
                miArrayListResp = res.getStringArray(R.array.resp10);
                miArrayScore = res.getStringArray(R.array.val9);
                break;
            case 11:
                miArrayListResp = res.getStringArray(R.array.resp11);
                miArrayScore = res.getStringArray(R.array.val10);
                break;
            case 12:
                miArrayListResp = res.getStringArray(R.array.resp12);
                miArrayScore = res.getStringArray(R.array.val11);
                break;
            case 13:
                miArrayListResp = res.getStringArray(R.array.resp13);
                miArrayScore = res.getStringArray(R.array.val12);
                break;
        }

        // FrameLayout
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));
        frameLayout.setPadding(25, 25, 25, 25);

        // LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(10, 10, 10, 10);

        // Space 1
        Space space1 = new Space(this);
        space1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.addView(space1);

        // TextView
        String preguntaDeseada = "";
        if (questionPosition >= 0 && questionPosition < miArrayListPreguntas.length) {
            preguntaDeseada = miArrayListPreguntas[questionPosition];
        }
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        ));
        textView.setText(preguntaDeseada);
        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(34);
        textView.setTypeface(null, Typeface.BOLD);
        linearLayout.addView(textView);

        // Space 2
        Space space2 = new Space(this);
        space2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.addView(space2);

        // RadioGroup
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        ));
        radioGroup.setPadding(25, 25, 25, 25);

        // RadioButton 1
        if (0 < miArrayListResp.length) {
            radioButton1 = new RadioButton(this);
            radioButton1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            radioButton1.setText(miArrayListResp[0]);
            radioButton1.setTextSize(16);
            // Asignar un ID único al RadioButton
            radioButton1.setId(View.generateViewId());
            radioGroup.addView(radioButton1);
        }

        // RadioButton 2
        if (1 < miArrayListResp.length) {
            radioButton2 = new RadioButton(this);
            radioButton2.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            radioButton2.setText(miArrayListResp[1]);
            radioButton2.setTextSize(16);
            // Asignar un ID único al RadioButton
            radioButton2.setId(View.generateViewId());
            radioGroup.addView(radioButton2);
        }

        // RadioButton 3
        if (2 < miArrayListResp.length) {
            radioButton3 = new RadioButton(this);
            radioButton3.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            radioButton3.setText(miArrayListResp[2]);
            radioButton3.setTextSize(16);
            // Asignar un ID único al RadioButton
            radioButton3.setId(View.generateViewId());
            radioGroup.addView(radioButton3);
        }

        // RadioButton 4
        if (3 < miArrayListResp.length) {
            radioButton4 = new RadioButton(this);
            radioButton4.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            radioButton4.setText(miArrayListResp[3]);
            radioButton4.setTextSize(16);
            // Asignar un ID único al RadioButton
            radioButton4.setId(View.generateViewId());
            radioGroup.addView(radioButton4);
        }

        // RadioButton 5
        if (4 < miArrayListResp.length) {
            radioButton5 = new RadioButton(this);
            radioButton5.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            radioButton5.setText(miArrayListResp[4]);
            radioButton5.setTextSize(16);
            // Asignar un ID único al RadioButton
            radioButton5.setId(View.generateViewId());
            radioGroup.addView(radioButton5);
        }

        // RadioButton 6
        if (5 < miArrayListResp.length) {
            radioButton6 = new RadioButton(this);
            radioButton6.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            radioButton6.setText(miArrayListResp[5]);
            radioButton6.setTextSize(16);
            // Asignar un ID único al RadioButton
            radioButton6.setId(View.generateViewId());
            radioGroup.addView(radioButton6);
        }

        // Boton Siguiente
        Button botonSiguiente = new Button(this);
        botonSiguiente.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        botonSiguiente.setText("Siguiente");
        botonSiguiente.setTextSize(16);

        linearLayout.addView(radioGroup);
        linearLayout.addView(botonSiguiente);
        frameLayout.addView(linearLayout);
        setContentView(frameLayout);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);

                if (selectedRadioButton.isChecked()) {
                    calculateScore();
                }
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(positionAnswer == 13)) {
                    Intent i = new Intent(getBaseContext(), QuestionDinamic.class);
                    i.putExtra("name", name);
                    i.putExtra("age", age);
                    i.putExtra("questionPosition", questionPosition + 1);
                    i.putExtra("positionAnswer", positionAnswer + 1);
                    i.putExtra("scores", scores);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getBaseContext(), FinalActivity.class);
                    i.putExtra("name", name);
                    i.putExtra("age", age);
                    i.putExtra("scores", scores);
                    startActivity(i);
                }
            }
        });
    }

    public void calculateScore() {

        scores[questionPosition] = 0.0;

        if (radioButton1.isChecked()) {
            scores[questionPosition] = Double.parseDouble(miArrayScore[0]);
        } else {
            if (radioButton2.isChecked()) {
                scores[questionPosition] = Double.parseDouble(miArrayScore[1]);
            } else {
                if (radioButton3.isChecked()) {
                    scores[questionPosition] = Double.parseDouble(miArrayScore[2]);
                } else {
                    if (radioButton4.isChecked()) {
                        scores[questionPosition] = Double.parseDouble(miArrayScore[3]);
                    } else {
                        if (radioButton5.isChecked()) {
                            scores[questionPosition] = Double.parseDouble(miArrayScore[4]);
                        } else {
                            if (radioButton6.isChecked()) {
                                scores[questionPosition] = Double.parseDouble(miArrayScore[5]);
                            }
                        }
                    }
                }
            }
        }
    }
} // Fin de la clase