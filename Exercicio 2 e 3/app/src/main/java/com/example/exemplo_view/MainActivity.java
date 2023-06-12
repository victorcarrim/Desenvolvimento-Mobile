package com.example.exemplo_view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplo_view.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnExibir.setOnClickListener(this);
        binding.btnLimpar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
      if(v.getId() == R.id.btnLimpar){
          binding.edtNome.setText("");
          binding.edtEmail.setText("");
          binding.edtTelefone.setText("");
          binding.swtNotificacao.setChecked(false);
          binding.rdgSexo.clearCheck();
          binding.chkMusica.setChecked(false);
          binding.chkCinema.setChecked(false);
          binding.chkEsporte.setChecked(false);
          binding.chkGastronomia.setChecked(false);

          binding.lblDados.setVisibility(View.INVISIBLE);
      } else if(v.getId() == R.id.btnExibir){
//          Toast.makeText(this,"Botão exibir acionado", Toast.LENGTH_SHORT).show();
          binding.lblDados.setVisibility(View.VISIBLE);
          binding.txtNome.setText("Nome: " + binding.edtNome.getText().toString());
          binding.txtEmail.setText("Email: " + binding.edtEmail.getText().toString());
          binding.txtTelefone.setText("Telefone: " + binding.edtTelefone.getText().toString());

          if(binding.swtNotificacao.isChecked()){
              binding.txtNotificacao.setText("Notificação: " + binding.swtNotificacao.getTextOn());
          } else{
              binding.txtNotificacao.setText("Notificação: " + binding.swtNotificacao.getTextOff());
          }

          int idRbSelecionado = binding.rdgSexo.getCheckedRadioButtonId();
          if(idRbSelecionado > 0){
              RadioButton rdbSelecionado = findViewById(idRbSelecionado);
              binding.txtSexo.setText("Sexo: " + rdbSelecionado.getText().toString());
          }

          String pref = "";
          if(binding.chkMusica.isChecked()) {
              pref = binding.chkMusica.getText().toString();
              pref +=" ";
          }
          if(binding.chkCinema.isChecked()) {
              pref += binding.chkCinema.getText().toString();
              pref +=" ";
          }
          if(binding.chkEsporte.isChecked()) {
              pref += binding.chkEsporte.getText().toString();
              pref +=" ";
          }
          if(binding.chkGastronomia.isChecked()) {
              pref += binding.chkGastronomia.getText().toString();
          }

          binding.txtPreferencia.setText("Preferências: " + pref);
      }
    }
}