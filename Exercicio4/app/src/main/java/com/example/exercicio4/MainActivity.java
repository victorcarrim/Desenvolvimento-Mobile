package com.example.exercicio4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private TextView txtMostrar;
    private EditText edtTexto;
    private Button btMostrar;
    private SeekBar sbTamanho;
    private TextView txtTamanho;
    private CheckBox cbNegrito;
    private CheckBox cbItalico;
    private CheckBox cbMaiusculo;
    private RadioGroup rgCor;
    private RadioButton rbVermelho;
    private RadioButton rbVerde;
    private RadioButton rbAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMostrar.findViewById(R.id.txtMostrar);
        edtTexto.findViewById(R.id.edtTexto);
        btMostrar.findViewById(R.id.btMostrar);
        sbTamanho.findViewById(R.id.sbTamanho);
        txtTamanho.findViewById(R.id.txtTamanho);
        cbNegrito.findViewById(R.id.cbNegrito);
        cbItalico.findViewById(R.id.cbItalico);
        cbMaiusculo.findViewById(R.id.cbMaiusculo);
        rgCor.findViewById(R.id.rgCor);
        rbVermelho.findViewById(R.id.rbVermelho);
        rbVerde.findViewById(R.id.rbVerde);
        rbAzul.findViewById(R.id.rbAzul);

        btMostrar.setOnClickListener(this);
        sbTamanho.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Typeface tf = null;
        String texto = txtMostrar.getText().toString();

        txtMostrar.setText(edtTexto.getText().toString());

        if (cbMaiusculo.isChecked() == true) {
            txtMostrar.setTypeface(tf , Typeface.BOLD);
        }

        if (cbItalico.isChecked() == true) {
            txtMostrar.setTypeface(tf , Typeface.ITALIC);
        }

        if (cbMaiusculo.isChecked() == true) {
            txtMostrar.setText(texto.toUpperCase(Locale.ROOT));
        }

        if (rbVermelho.isChecked() == true) {
            txtMostrar.setTextColor(Color.parseColor("#FF0000"));
        }

        if (rbVerde.isChecked() == true) {
            txtMostrar.setTextColor(Color.parseColor("#3A6332"));
        }

        if (rbAzul.isChecked() == true) {
            txtMostrar.setTextColor(Color.parseColor("#120A8f"));
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        txtMostrar.setTextSize(progress);
        txtTamanho.setText(progress + "sp");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}