package hu.uni.miskolc.transport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateTourActivity extends AppCompatActivity {

    // változók a szerkesztési szöveghez, a gombokhoz, a karakterláncokhoz és a dbhandler osztályhoz.
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button updateCourseBtn;
    private DBHandler dbHandler;
    private Button deleteCourseBtn;
    String courseName, courseDesc, courseDuration, courseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tour);

        // az összes változó inicializálva.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        // inicializáljuk a dbhandler osztályunkat.
        dbHandler = new DBHandler(UpdateTourActivity.this);

        // az alábbi sorokban olyan adatokat kapunk, amelyek
        // átmentünk az adapter osztályunkon.
        courseName = getIntent().getStringExtra("name");
        courseDesc = getIntent().getStringExtra("description");
        courseDuration = getIntent().getStringExtra("duration");
        courseTracks = getIntent().getStringExtra("tracks");

        // adatok beállítása a szöveg szerkesztéséhez
        // frissítési tevékenységünkről.
        courseNameEdt.setText(courseName);
        courseDescriptionEdt.setText(courseDesc);
        courseTracksEdt.setText(courseTracks);
        courseDurationEdt.setText(courseDuration);

        // a kattintásfigyelő hozzáadása a "túra" frissítése gombhoz.
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // a metóduson belül a frissített túrát hívunk
                // metódussal, és átadjuk az összes szerkesztési szövegértékünket.
                dbHandler.updateTour(courseName, courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), courseTracksEdt.getText().toString(), courseDurationEdt.getText().toString());

                // üzenet megjelenítése, hogy a frissítés sikeres.
                Toast.makeText(UpdateTourActivity.this, "A túra frissítése sikeres..", Toast.LENGTH_SHORT).show();

                // main indítása
                Intent i = new Intent(UpdateTourActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // az onclick figyelő törléséhez gomb hozzáadása a "túra" törléséhez.
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // metódus meghívása a "túra" törlésére.
                dbHandler.deleteTour(courseName);
                Toast.makeText(UpdateTourActivity.this, "A túra törlése sikeres..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateTourActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
