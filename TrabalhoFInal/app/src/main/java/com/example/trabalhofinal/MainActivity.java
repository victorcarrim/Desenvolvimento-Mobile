package com.example.trabalhofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.trabalhofinal.adapter.SerieAdapter;
import com.example.trabalhofinal.data.DatabaseHelper;
import com.example.trabalhofinal.data.Serie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SerieAdapter.OnItemClickListener {

    private DatabaseHelper db;
    private SerieAdapter adapter;
    private List<Serie> seriesList;

    private static final int ADD_SERIE_REQUEST = 1;
    private static final int EDIT_SERIE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        seriesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SerieAdapter(seriesList, this, this);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                startActivityForResult(intent, ADD_SERIE_REQUEST);
            }
        });

        loadSeriesFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSeriesFromDatabase();
    }

    private void loadSeriesFromDatabase() {
        seriesList.clear();
        seriesList.addAll(db.getAllSeries());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Serie clickedSerie = seriesList.get(position);

        Intent intent = new Intent(this, AddEditActivity.class);
        intent.putExtra("serie", clickedSerie);
        startActivityForResult(intent, EDIT_SERIE_REQUEST);
    }

    @Override
    public void onDeleteClick(int position) {
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Serie serie = (Serie) data.getSerializableExtra("serie");

            if (requestCode == ADD_SERIE_REQUEST) {
                db.addSerie(serie);
            } else if (requestCode == EDIT_SERIE_REQUEST) {
                db.updateSerie(serie);
            }

            loadSeriesFromDatabase();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}