package hu.uni.miskolc.transport;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourRVAdapter extends RecyclerView.Adapter<TourRVAdapter.ViewHolder> {

    // változók a tömblistánkhoz és a kontextushoz
    private ArrayList<TourModal> tourModalArrayList;
    private Context context;

    // konstruktor
    public TourRVAdapter(ArrayList<TourModal> tourModalArrayList, Context context) {
        this.tourModalArrayList = tourModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // az alábbi sorban felfújjuk az elrendezésünket
        // fájl az újrahasznosító nézet tételeihez.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // az alábbi sorban adatokat állítunk be
        // az újrahasznosító nézet elemről alkotott nézeteinkhez.
        TourModal modal = tourModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getTourName());
        holder.courseDescTV.setText(modal.getTourDescription());
        holder.courseDurationTV.setText(modal.getTourLength());
        holder.courseTracksTV.setText(modal.getTourTracks());

        // Az alábbi sorban kattintásfigyelőt kell hozzáadni az újrahasznosító nézethez.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // az alábbi sorban "szándékot" nevezünk.
                Intent i = new Intent(context, UpdateTourActivity.class);

                // itt minden értéket átadunk.
                i.putExtra("name", modal.getTourName());
                i.putExtra("description", modal.getTourDescription());
                i.putExtra("duration", modal.getTourLength());
                i.putExtra("tracks", modal.getTourTracks());

                // Activity indítása
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        // visszaadja a tömblistánk méretét
        return tourModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // változók létrehozása szöveges mezőkhöz
        private TextView courseNameTV, courseDescTV, courseDurationTV, courseTracksTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // szöveges mezők inicializálása
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseTracksTV = itemView.findViewById(R.id.idTVCourseTracks);
        }
    }
}

