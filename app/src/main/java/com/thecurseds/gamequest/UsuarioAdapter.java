package com.thecurseds.gamequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {
    private Context context;
    private List<UsuarioChat> UsuarioList;
    public UsuarioAdapter(Context context, List<UsuarioChat> UsuarioList){
        this.context = context;
        this.UsuarioList = UsuarioList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView Nombre;
        private TextView mensaje;
        public ViewHolder(View view) {
            super(view);
            foto = view.findViewById(R.id.img_profileChat);
            Nombre = view.findViewById(R.id.txt_nombreUsuario);
            mensaje = view.findViewById(R.id.txt_ultimoMensaje);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contacto, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.foto.setImageURI(UsuarioList.get(position).getFoto());
        viewHolder.Nombre.setText(UsuarioList.get(position).getNombre());
        viewHolder.mensaje.setText(UsuarioList.get(position).getMensaje());
    }
    @Override
    public int getItemCount() {
        return UsuarioList.size();
    }
}