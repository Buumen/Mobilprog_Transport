package hu.uni.miskolc.transport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DBMainActivity extends AppCompatActivity {

    // változók létrehozása az edittext, a gomb és a dbhandler számára
    private EditText tourNameEdt, tourTracksEdt, tourLengthEdt, tourDescriptionEdt;
    private Button addTourBtn;
    //private Button readTourBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // az összes változónkat inicializálva.
        tourNameEdt = findViewById(R.id.idEdtTourName);
        tourTracksEdt = findViewById(R.id.idEdtTourTracks);
        tourLengthEdt = findViewById(R.id.idEdtTourLength);
        tourDescriptionEdt = findViewById(R.id.idEdtTourDescription);
        addTourBtn = findViewById(R.id.idBtnAddTour);
        //readTourBtn = findViewById(R.id.idBtnReadTour);

        // új "dbhandler" osztály létrehozása
        // és kontextusunkat átadjuk neki.
        dbHandler = new DBHandler(DBMainActivity.this);

        // kattintásra figyelőt kell hozzáadni a "Túra hozzáadása" gombhoz.
        addTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // az összes szerkesztett szövegmező adatainak lekérése.
                String tourName = tourNameEdt.getText().toString();
                String tourTracks = tourTracksEdt.getText().toString();
                String tourLength = tourLengthEdt.getText().toString();
                String tourDescription = tourDescriptionEdt.getText().toString();

                // annak ellenőrzése, hogy a szövegmezők üresek-e vagy sem.
                if (tourName.isEmpty() && tourTracks.isEmpty() && tourLength.isEmpty() && tourDescription.isEmpty()) {
                    Toast.makeText(DBMainActivity.this, "Kérlek tölts ki minden adatot..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // az alábbi sorban egy metódust hívunk meg új hozzáadására
                // természetesen az sqlite adatokat, és minden értékünket átadjuk neki.
                dbHandler.addNewTour(tourName, tourLength, tourDescription, tourTracks);

                // az adatok hozzáadása után egy érvényesítő üzenetet jelenítünk meg.
                Toast.makeText(DBMainActivity.this, "Az új túra hozzáadva.", Toast.LENGTH_SHORT).show();
                tourNameEdt.setText("");
                tourLengthEdt.setText("");
                tourTracksEdt.setText("");
                tourDescriptionEdt.setText("");
            }
        });

        /*readTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // új tevékenység megnyitása Intent-en keresztül.
                Intent i = new Intent(DBMainActivity.this, ViewTours.class);
                startActivity(i);
            }
        });*/
    }
}

