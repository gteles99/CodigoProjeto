package com.example.appteste;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Sobre extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback{
    private static boolean LOCATION_PERMISSION_REQUEST_CODE = false;
    public GoogleMap mapa;
    public LatLng locaiizacao = new LatLng(-23.560605, -48.041783);
    public LatLng locaiizacao1 = new LatLng(23.572926,-48.024849);
    private Button btnatu;
    private GeoDataClient geoDataClient;
    private FusedLocationProviderClient mfusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.nossoMapa);
        mapFragment.getMapAsync(Sobre.this);
        geoDataClient = Places.getGeoDataClient(Sobre.this, null);
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        metodobotao();
        ativapermissao();
    }

        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            this.mapa = googleMap;
            mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locaiizacao,17);
            mapa.animateCamera(update);
            Circle circle = mapa.addCircle(new CircleOptions()
            .center(locaiizacao)
                            .radius(100)
                    .strokeColor(Color.RED)
                    .fillColor(Color.TRANSPARENT)
            );
            mapa.addMarker(new MarkerOptions().position(locaiizacao).title("UBS BH"));
            mapa.addMarker(new MarkerOptions().position(locaiizacao1).title("UBS Mesquita"));
        }

        private void metodobotao(){
        btnatu=(Button)findViewById(R.id.btnatu);
        btnatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              atualizaosualocalizacao();
            }
        });
    }

    private void atualizaosualocalizacao() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mapa.addMarker(new MarkerOptions().position(locaiizacao).title("Outro posto"));
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                    mapa.animateCamera(update);
                    mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

        private void ativapermissao () {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
            {
                LOCATION_PERMISSION_REQUEST_CODE = PermissionUtils.validate(this, 1, Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }
    }

