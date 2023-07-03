package com.example.trabalhofinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trabalhofinal.data.DatabaseHelper;
import com.example.trabalhofinal.data.Serie;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddEditActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    private Serie currentSerie;
    private EditText editName, editGenre, editSeasons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        editName = findViewById(R.id.edit_name);
        editGenre = findViewById(R.id.edit_genre);
        editSeasons = findViewById(R.id.edit_seasons);

        Button buttonSave = findViewById(R.id.button_save);
        Button buttonDelete = findViewById(R.id.button_delete);

        currentSerie = (Serie) getIntent().getSerializableExtra("serie");
        if (currentSerie != null) {
            editName.setText(currentSerie.getNome());
            editGenre.setText(currentSerie.getGenero());
            editSeasons.setText(String.valueOf(currentSerie.getQuantidadeTemporadas()));
            buttonDelete.setVisibility(View.VISIBLE);  // Mostra o botão de Delete se estamos editando uma série
        } else {
            buttonDelete.setVisibility(View.GONE);  // Esconde o botão de Delete se estamos adicionando uma nova série
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSerie();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSerie();
            }
        });
    }

    private void saveSerie() {
        String name = editName.getText().toString();
        String genre = editGenre.getText().toString();
        String seasons = editSeasons.getText().toString();

        if (name.isEmpty() || genre.isEmpty() || seasons.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int seasonsNumber;
        try {
            seasonsNumber = Integer.parseInt(seasons);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de temporadas inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Serie serie;
        if (currentSerie != null) {
            serie = currentSerie;
            serie.setNome(name);
            serie.setGenero(genre);
            serie.setQuantidadeTemporadas(seasonsNumber);
        } else {
            serie = new Serie(name, genre, seasonsNumber);
        }

        DatabaseHelper db = new DatabaseHelper(this);
        if (currentSerie != null) {
            db.updateSerie(serie);
        } else {
            db.addSerie(serie);
        }

        Toast.makeText(this, "Série salva", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteSerie() {
        if (currentSerie != null) {
            DatabaseHelper db = new DatabaseHelper(AddEditActivity.this);
            db.deleteSerie(currentSerie);
            Toast.makeText(AddEditActivity.this, "Série deletada", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
            if (data != null) {
                // Definindo a foto para a Serie antes de finalizar a atividade
                Serie serie = new Serie();

                // Atualizando a foto para a Serie
                Intent intent = new Intent();
                intent.putExtra("serie", serie);
                setResult(RESULT_OK, intent);

            }
        }
    }

}