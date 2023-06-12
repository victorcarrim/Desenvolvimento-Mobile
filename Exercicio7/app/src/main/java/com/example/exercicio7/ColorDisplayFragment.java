package com.example.exercicio7;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ColorDisplayFragment extends Fragment {

    private FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_display, container, false);
        frameLayout = view.findViewById(R.id.frameLayout);
        return view;
    }

    public void changeColor(int colorResId) {
        int color = getResources().getColor(colorResId);
        frameLayout.setBackgroundColor(color);
    }
}
