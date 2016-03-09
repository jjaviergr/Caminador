package com.example.pc.caminador;

import android.app.Activity;
import android.os.Bundle;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Map extends AppCompatActivity {

    private GoogleMap mapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        //asigna el mapa
        mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        /**
         * gestiona el evento OnMapClick
         */
        mapa.setOnMapClickListener(new OnMapClickListener() {
            public void onMapClick(LatLng point) {
                //obtener coordenadas de pantalla
                //correspondientes a las geográficas pulsadas
                Projection proj = mapa.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(
                        Map.this,
                        "Click\n" + "Lat: " + point.latitude + "\n" + "Lng: "
                                + point.longitude + "\n" + "X: " + coord.x
                                + " - Y: " + coord.y, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        /**
         * gestiona el evento onMapLongClick
         */
        mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
            public void onMapLongClick(LatLng point) {
                Projection proj = mapa.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(
                        Map.this,
                        "Click Largo\n" + "Lat: " + point.latitude + "\n"
                                + "Lng: " + point.longitude + "\n" + "X: "
                                + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * gestiona el evento onCameraChange
         */
        mapa.setOnCameraChangeListener(new OnCameraChangeListener() {
            public void onCameraChange(CameraPosition position) {
                Toast.makeText(
                        Map.this,
                        "Cambio Cámara\n" + "Lat: " + position.target.latitude
                                + "\n" + "Lng: " + position.target.longitude
                                + "\n" + "Zoom: " + position.zoom + "\n"
                                + "Orientación: " + position.bearing + "\n"
                                + "ángulo: " + position.tilt,
                        Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * gestiona el evento onMarkerClick
         */
        mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(Map.this,
                        "Marcador pulsado:\n" + marker.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    /**
     * gestiona la pulsación de un elemento del menú
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.menu_marcadores:
                // agrega el marcador
                mostrarMarcador(40.5, -3.5);
                break;*/
            case R.id.menu_lineas:
                // muestra la líneas
                dibujarLineas();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * coloca un marcador en las coordenadas especificadas
     *
     * @param lat
     * @param lng
     *//*
    private void mostrarMarcador(double lat, double lng) {
        mapa.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                .title("País: España"));
    }*/

    /**
     * dibuja sobre el mapa las líneas especificadas
     */
    private void dibujarLineas() {
        // Dibujo con Lineas
        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(45.0, -12.0)).add(new LatLng(45.0, 5.0))
                .add(new LatLng(34.5, 5.0)).add(new LatLng(34.5, -12.0))
                .add(new LatLng(45.0, -12.0));

        lineas.width(8);
        lineas.color(Color.RED);

        mapa.addPolyline(lineas);

//		// Dibujo con polígonos
//		PolygonOptions rectangulo = new PolygonOptions().add(new LatLng(45.0,
//				-12.0), new LatLng(45.0, 5.0), new LatLng(34.5, 5.0),
//				new LatLng(34.5, -12.0), new LatLng(45.0, -12.0));
//
//		rectangulo.strokeWidth(8);
//		rectangulo.strokeColor(Color.RED);
//
//		mapa.addPolygon(rectangulo);
    }
}
/*
public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
*/