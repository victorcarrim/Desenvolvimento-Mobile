package com.example.exercicio8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterGems extends BaseAdapter {
    private Context context;
    private String[] gems = new String[]{"Steven", "Perl", "Garnet", "Ametista", "Rosa", "Branco", "Azul", "Amarelo", "Leao"};

    public AdapterGems(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return gems.length;
    }

    @Override
    public String getItem(int position) {
        return gems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String gem = gems[position];
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_gems, parent, false);
        TextView textView = v.findViewById(R.id.text);
        ImageView imageView = v.findViewById(R.id.image);

        textView.setText(gem);
        // Você pode trocar essa linha caso tenha os nomes das imagens diferentes dos nomes dos planetas
        int imageResId = context.getResources().getIdentifier(gem.toLowerCase(), "drawable", context.getPackageName());
        imageView.setImageResource(imageResId);
        return v;
    }
}