package com.android2023.evaluacionii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    double lat1,lng1,lat2,lng2;
    GoogleMap googleMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lat1 = getIntent().getDoubleExtra ("Latitud 1", 0);
        lng1 = getIntent().getDoubleExtra("Longitud 1", 0);
        lat2 = getIntent().getDoubleExtra("Latitud 2", 0);
        lng2 = getIntent().getDoubleExtra("Longitud 2", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMaps = map;
        this.googleMaps.setOnMapClickListener(this);
        this.googleMaps.setOnMapLongClickListener(this);

        LatLng sydney = new LatLng(-34, 151);
        LatLng direccion1 = new LatLng(lat1,lng1);
        LatLng direccion2 = new LatLng(lat2,lng2);

        map.addMarker(new MarkerOptions().position(sydney).title("Marcador Sydney"));
        map.addMarker(new MarkerOptions().position(direccion1).title("Marcador Direccion 1"));
        map.addMarker(new MarkerOptions().position(direccion2).title("Marcador Direccion 2"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }
}