package hu.uni.miskolc.transport;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewTours extends AppCompatActivity {

    // változók létrehozása a tömblistánkhoz,
    // dbhandler, adapter és recycler nézet.
    private ArrayList<TourModal> tourModalArrayList;
    private DBHandler dbHandler;
    private TourRVAdapter tourRVAdapter;
    private RecyclerView toursRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tours);

        // az összes változó inicializálása.
        tourModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewTours.this);

        // a kurzustömb lekérése
        // lista a db kezelő osztályból.
        tourModalArrayList = dbHandler.readTours();

        // az alábbi sorban, amely átadja a tömbünket, elveszett az adapterosztályunkban.
        tourRVAdapter = new TourRVAdapter(tourModalArrayList, ViewTours.this);
        toursRV = findViewById(R.id.idRVCourses);

        // setting layout manager és recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewTours.this, RecyclerView.VERTICAL, false);
        toursRV.setLayoutManager(linearLayoutManager);

        // setting our adapter és recycler view.
        toursRV.setAdapter(tourRVAdapter);
    }
}
