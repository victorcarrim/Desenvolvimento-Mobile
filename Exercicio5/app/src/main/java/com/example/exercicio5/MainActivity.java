package com.example.exercicio5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnIniciar;
    private TextView txtContar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);
        txtContar = findViewById(R.id.txtContar);

        btnIniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int min = 0; // Defina o valor de min desejado
        ProcessarTask task = new ProcessarTask(btnIniciar, txtContar);
        task.execute(min);
    }
}