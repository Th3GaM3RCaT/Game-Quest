package com.thecurseds.gamequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private ArrayList<Resegna> arrayList;
    public RecyclerAdapter(ArrayList<Resegna> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.resegna,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resegna resegna = arrayList.get(position);
        holder.resegna.setText(resegna.getResegna());
        holder.valoracion.setNumStars(resegna.getValoracion());
        holder.resegnador.setImageResource(resegna.getResegnador());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /*public class ViewHolder*
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView resegnador;
        TextView resegna;
        RatingBar valoracion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            resegnador = itemView.findViewById(R.id.img_user_resegna);
            resegna = itemView.findViewById(R.id.txt_resegna);
            valoracion = itemView.findViewById(R.id.valoracion);
        }
    }

}
