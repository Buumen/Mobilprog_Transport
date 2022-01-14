package hu.uni.miskolc.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnBelepes;
    private Button btnAuto;
    private Button readTourBtn;
    private Button btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBelepes = (Button) findViewById(R.id.btnBelepes);
        readTourBtn = findViewById(R.id.btnLeiras);
        btnAuto = (Button) findViewById(R.id.btnAuto);
        btnCamera = (Button) findViewById(R.id.btnKamera);

        btnBelepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { TerkepActivity(); }
        });

        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { DBMainActivity(); }
        });

        readTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // új tevékenység megnyitása Intent-en keresztül.
                Intent i = new Intent(MainActivity.this, ViewTours.class);
                startActivity(i);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // új tevékenység megnyitása Intent-en keresztül.
                Intent i = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(i);
            }
        });
        
    }

    public void TerkepActivity() {
        Intent intentterkep = new Intent(this, TerkepActivity.class);
        startActivity(intentterkep);
    }

    public void DBMainActivity() {
        Intent intentdata = new Intent(this, DBMainActivity.class);
        startActivity(intentdata);
    }
}

