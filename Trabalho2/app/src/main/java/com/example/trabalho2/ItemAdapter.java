package com.example.trabalho2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

public class ItemAdapter extends ArrayAdapter<String> {
    private HashSet<Integer> selectedItems = new HashSet<>();

    public ItemAdapter(@NonNull Context context, @NonNull ArrayList<String> objects) {
        super(context, R.layout.list_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(getItem(position));

        if (selectedItems.contains(position)) {
            convertView.setBackgroundColor(Color.LTGRAY);
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    public void addItem(String item) {
        add(item);
        notifyDataSetChanged();
    }

    public void editItem(int position, String item) {
        remove(getItem(position));
        insert(item, position);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        remove(getItem(position));
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        if (selectedItems.contains(position)) {
            selectedItems.remove(position);
        } else {
            selectedItems.add(position);
        }
        notifyDataSetChanged();
    }

    public void clearSelection() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public HashSet<Integer> getSelectedItems() {
        return selectedItems;
    }
}
