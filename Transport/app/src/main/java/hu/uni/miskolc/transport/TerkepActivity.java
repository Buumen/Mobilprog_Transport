package hu.uni.miskolc.transport;

import androidx.fragment.app.FragmentActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hu.uni.miskolc.transport.databinding.ActivityTerkepBinding;

public class TerkepActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityTerkepBinding binding;
    private Button btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTerkepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Szerezze be a SupportMapFragment-et, és kap értesítést, ha a térkép használatra kész.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);

        /*setContentView(R.layout.activity_terkep);*/
        btnNavigate = (Button) findViewById(R.id.btnNavigate);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { startNavigation(); }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // hozzáadunk egy markert Borsodnádasdon és mozgassuk a kamerát
        LatLng borsodnadasd = new LatLng(48.11295, 20.24333);
        mMap.addMarker(new MarkerOptions()
                .position(borsodnadasd).draggable(true).title("Borsodnadasd - PA-ROL Kft."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(borsodnadasd));
    }

    private void startNavigation() {
        try
        {
            // Waze indítása és a PA-ROL Kft. hely megkeresése
            String url = "https://waze.com/ul?q=PA-ROL Kft.";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity( intent );
        }
        catch ( ActivityNotFoundException ex  )
        {
            // ha a Waze nincs telepítve, nyissa meg a Google Playen
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
            startActivity(intent);
        }
    }
}