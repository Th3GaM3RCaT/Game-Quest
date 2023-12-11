package com.thecurseds.gamequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Context context;
    private List<Resegna> resegnaList;


    public ReviewAdapter(Context context, List<Resegna> resegnaList){
        this.context = context;
        this.resegnaList = resegnaList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtResegna;
        private RatingBar rtbCalificacion;
        private ImageView imgResegnador;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            txtResegna = view.findViewById(R.id.txt_resegna);
            rtbCalificacion = view.findViewById(R.id.valoracion);
            imgResegnador = view.findViewById(R.id.img_user_resegna);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.resegna, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.imgResegnador.setImageBitmap(resegnaList.get(position).getReviewer());
        viewHolder.rtbCalificacion.setNumStars(resegnaList.get(position).getAssessment());
        viewHolder.txtResegna.setText(resegnaList.get(position).getReview());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return resegnaList.size();
    }
}

