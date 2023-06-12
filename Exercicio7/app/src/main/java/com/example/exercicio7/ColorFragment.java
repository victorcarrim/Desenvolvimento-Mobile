package com.example.exercicio7;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ColorFragment extends Fragment implements View.OnClickListener {

    private ColorClickListener colorClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color, container, false);

        Button btnBlack = view.findViewById(R.id.btnBlack);
        Button btnBlue = view.findViewById(R.id.btnBlue);
        Button btnGreen = view.findViewById(R.id.btnGreen);
        Button btnRed = view.findViewById(R.id.btnRed);
        Button btnYellow = view.findViewById(R.id.btnYellow);

        btnBlack.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnYellow.setOnClickListener(this);

        return view;
    }

    public void setColorClickListener(ColorClickListener listener) {
        this.colorClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        int colorResId = R.color.black;
        switch (v.getId()) {
            case R.id.btnBlack:
                colorResId = R.color.black;
                break;
            case R.id.btnBlue:
                colorResId = R.color.blue;
                break;
            case R.id.btnGreen:
                colorResId = R.color.green;
                break;
            case R.id.btnRed:
                colorResId = R.color.red;
                break;
            case R.id.btnYellow:
                colorResId = R.color.yellow;
                break;
        }
        if (colorClickListener != null) {
            colorClickListener.onColorClicked(colorResId);
        }
    }
}