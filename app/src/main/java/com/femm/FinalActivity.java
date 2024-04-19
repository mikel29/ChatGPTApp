package com.femm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FinalActivity extends AppCompatActivity {

    private String name;
    private int age;
    private int finalAge;
    private double[] scores = new double[13];
    private double[] scoresIntent = new double[13];
    private double score;

    private TextView lblEdadOriginal, lblPuntos, lblSuma, lblNombre, lblEdadAparento;
    private TextView lblRespuesta;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age", 0);
        scoresIntent = intent.getDoubleArrayExtra("scores");

        progressBar = findViewById(R.id.progressBar);

        lblSuma = findViewById(R.id.lblPuntos);
        lblEdadOriginal = findViewById(R.id.lblEdadOriginal);
        lblEdadAparento = findViewById(R.id.lblEdadAparento);
        lblPuntos = findViewById(R.id.lblPuntos);

        lblRespuesta = findViewById(R.id.lblRespuesta);
        lblNombre = findViewById(R.id.lblNombre);

        for (int i = 0; i < scoresIntent.length; i++) {
            scores[i] = scoresIntent[i];
        }

        for (int i = 0; i < scores.length; i++) {
            score += scores[i];
        }

        finalAge = age + (int) score;

        lblNombre.setText(name);
        lblEdadOriginal.setText(String.valueOf(age));
        lblSuma.setText(String.valueOf(score));

        lblEdadAparento.setText(String.valueOf( finalAge ));

        // Lamamos al metodo calChatGPT
        callChatGPT();
    }

    public void callChatGPT() {
        RequestQueue requestQueue = Volley.newRequestQueue(FinalActivity.this);
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://api.openai.com/v1/chat/completions";
        JSONObject jsonBody = new JSONObject();

        if (age < 0 || finalAge < 0) {
            Toast.makeText(FinalActivity.this, "Las edades deben ser valores positivos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            String mensajeRespuesta;
            if (age >= finalAge) {
                mensajeRespuesta = " dame consejos para Mantener mi estado físico ya que tengo ";
            } else {
                mensajeRespuesta = " dame consejos para mejorar mi estado físico ya que tengo ";
            }

            JSONArray messagesArray = new JSONArray();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "Mi nombre es " + name +
                    mensajeRespuesta + age + " años pero aparento de " + finalAge);
            messagesArray.put(systemMessage);

            jsonBody.put("messages", messagesArray);
            jsonBody.put("max_tokens", 3000);
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("temperature", 0.7);
            jsonBody.put("top_p", 1);
            jsonBody.put("frequency_penalty", 0);
            jsonBody.put("presence_penalty", 0);

        } catch (JSONException e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(FinalActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                response -> {
                    // Maneja la respuesta de la API de ChatGPT
                    try {
                        String completions = response.getJSONArray("choices").getJSONObject(0).getString("message");
                        JSONObject assistantMessage = new JSONObject(completions);
                        String content = assistantMessage.getString("content");
                        lblRespuesta.setText(content);
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(FinalActivity.this, "Error al procesar la respuesta de la API.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Maneja los errores de la solicitud
                    progressBar.setVisibility(View.GONE);
                    if (error.networkResponse != null) {
                        String errorMessage = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        Log.e("API Error", errorMessage);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("API Error", "Error de conexión o desconocido");
                        Toast.makeText(getApplicationContext(), "Error de conexión o desconocido.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer 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");
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}