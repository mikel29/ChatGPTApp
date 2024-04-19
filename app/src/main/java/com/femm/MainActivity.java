package com.femm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnContinuar;
    public static final String USER_DETAILS_KEY = "user_details";

    private SharedPreferences sf;

    private String name;
    private Integer age;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////INICIO AGREGAR GIF///////////////////////////////
        imageView = findViewById(R.id.imageViewGif);
        ImageView imageView2 = findViewById(R.id.imageViewGifLogo);

        // agregar el gif mediante librería Glide
        Glide.with(this).load(R.drawable.gif20).into(imageView);
        Glide.with(this).load(R.drawable.logogif).into(imageView2);
        /////////////////////////////FIN AGREGAR GIF///////////////////////////////////////


        this.sf = getSharedPreferences(USER_DETAILS_KEY, MODE_PRIVATE);
        this.txtNombre = findViewById(R.id.txtNombre);
        this.txtEdad = findViewById(R.id.txtEdad);
        this.btnContinuar = findViewById(R.id.btn_dinamico);

        this.btnContinuar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!txtNombre.getText().toString().isEmpty()) {
                            name = txtNombre.getText().toString();
                        } else {
                            txtNombre.setError(getResources().getString(R.string.err_edad_vacia));
                        }

                        if (!txtEdad.getText().toString().isEmpty()) {
                            age = Integer.parseInt(txtEdad.getText().toString());
                        } else {
                            txtEdad.setError(getResources().getString(R.string.err_edad_vacia));
                        }

                        if (!txtNombre.getText().toString().isEmpty() && !txtEdad.getText().toString().isEmpty()) {
                            Intent i = new Intent(getBaseContext(), QuestionDinamic.class);
                            i.putExtra("name", name);
                            i.putExtra("age", age);
                            startActivity(i);
                        }
                    }
                }
        );
    }

    public void startQuestionnaire(String name, Integer age) {

        SharedPreferences.Editor editor = this.sf.edit();
        editor.putString("name", name);
        editor.putInt("age", age);
        editor.apply();

        Intent i = new Intent(this, QuestionDinamic.class);

        startActivity(i);

    }
}