package com.example.exercicio7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ColorClickListener {

    private ColorFragment colorFragment;
    private ColorDisplayFragment colorDisplayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_color);
        colorFragment.setColorClickListener(this);

        colorDisplayFragment = new ColorDisplayFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, colorDisplayFragment)
                .commit();
    }

    @Override
    public void onColorClicked(int colorResId) {
        colorDisplayFragment.changeColor(colorResId);
    }
}