package com.example.trabalhofinal.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.data.Serie;

import java.util.List;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.SerieViewHolder> {
    private List<Serie> seriesList;
    private Context context;
    private OnItemClickListener listener;

    public SerieAdapter(List<Serie> seriesList, Context context, OnItemClickListener listener) {
        this.seriesList = seriesList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SerieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new SerieViewHolder(view);
    }

    public void onBindViewHolder(@NonNull SerieViewHolder holder, int position) {
        final Serie currentSerie = seriesList.get(position);
        Serie serie = seriesList.get(position);
        holder.tvName.setText(serie.getNome());
        holder.tvGenre.setText(serie.getGenero());
        holder.tvSeasons.setText(String.valueOf(serie.getQuantidadeTemporadas()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public class SerieViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvGenre;
        TextView tvSeasons;

        public SerieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_serie_name);
            tvGenre = itemView.findViewById(R.id.tv_serie_genre);
            tvSeasons = itemView.findViewById(R.id.tv_serie_seasons);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }


}
