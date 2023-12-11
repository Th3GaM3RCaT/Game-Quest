package com.thecurseds.gamequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransaccionAdapter extends RecyclerView.Adapter<TransaccionAdapter.ViewHolder> {

    private Context context;
    private List<Transaccion> transaccionList;

    public TransaccionAdapter(Context context, List<Transaccion> transaccionList){
        this.context = context;
        this.transaccionList = transaccionList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView juegoNombre;
        private TextView precioJuego;
        private TextView tipoTransaccion;
        private TextView fechaCompra;
        private TextView descripcionJuego;
        private TextView comprador;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            comprador = (TextView)view.findViewById(R.id.txt_comprador);
            juegoNombre = (TextView)view.findViewById(R.id.txt_juegoNombre);
            precioJuego = (TextView)view.findViewById(R.id.txt_precioJuego);
            tipoTransaccion = (TextView)view.findViewById(R.id.txt_tipoTransaccion);
            fechaCompra = (TextView)view.findViewById(R.id.txt_fechaCompra);
            descripcionJuego = (TextView)view.findViewById(R.id.txt_descripcion);
        }
    }




    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.transaccion, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.juegoNombre.setText(transaccionList.get(position).getJuego());
        viewHolder.comprador.setText(transaccionList.get(position).getComprador());
        viewHolder.descripcionJuego.setText(transaccionList.get(position).getDescripcion());
        viewHolder.tipoTransaccion.setText(transaccionList.get(position).getTipoTransaccion());
        viewHolder.fechaCompra.setText(transaccionList.get(position).getFecha());
        viewHolder.precioJuego.setText(transaccionList.get(position).getPrecio());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return transaccionList.size();
    }
}

