package com.thecurseds.gamequest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thecurseds.gamequest.R;
import com.thecurseds.gamequest.model.Game;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {


    Context context;
    ArrayList<Game> gameArrayList;

    public GameAdapter(Context context, ArrayList<Game> gameArrayList) {
        this.context = context;
        this.gameArrayList = gameArrayList;
    }

    @NonNull
    @Override
    public GameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_game_single,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.MyViewHolder holder, int position) {


        Game game = gameArrayList.get(position);
        holder.nombre.setText(game.getNombre());
        holder.precio.setText(game.getPrecio());
        holder.descripcion.setText(game.getPrecio());




    }

    @Override
    public int getItemCount() {
        return gameArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio, descripcion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.TvNombre);
            precio = itemView.findViewById(R.id.TvPrecio);
            descripcion = itemView.findViewById(R.id.TvDescripcion);

        }
    }
}
