package com.example.pc.caminador;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by pc on 29/02/2016.
 */
public class Gps extends AppCompatActivity implements LocationListener {// Referencia al servicio de posicionamiento
    LocationManager locationManager;
    // Para almacenar la latitud y longitud
    double lat;
    double lon;

    public void inicializa_gps() {
        // latitud = (TextView) findViewById(R.id.tvLatitud2);
        //longitud = (TextView) findViewById(R.id.tvLongitud2);
        // obtenemos el servicio de posicionamiento
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (this.locationManager == null) {
            //Toast.makeText(this, "Error al recuperar el GPS", Toast.LENGTH_LONG).show();
            // tv1.setText(tv1.getText() + "Error al recuperar el GPS");
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 20, (LocationListener) this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        // guardamos los valores de latitud y longitud
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();


    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}
