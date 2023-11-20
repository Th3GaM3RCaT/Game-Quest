package com.thecurseds.gamequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class game_ad extends RecyclerView.Adapter<game_ad.ViewHolder> {

    private ArrayList<game_md> gameList;
    private Context context;

    public game_ad(ArrayList<game_md> gameList, Context context) {
        this.gameList = gameList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gamely, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder holder, int position) {
        game_md model = gameList.get(position);

        holder.gameName.setText(model.getNombre());
        holder.gamePrecio.setText(model.getPrecio());
        holder.gameDescripcion.setText(model.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView gameImageView;
        private TextView gameName, gameDescripcion, gamePrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.TvNombre);
            gamePrecio = itemView.findViewById(R.id.TvPrecio);
            gameDescripcion = itemView.findViewById(R.id.TvDescripcion);
        }
    }
}

