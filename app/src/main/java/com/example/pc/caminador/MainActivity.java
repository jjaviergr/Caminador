package com.example.pc.caminador;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity  {



    private Intent Graba,Recibe;
    private String latitud,longitud,fecha;

    private Button Salir,Grabar,Recibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv1 = (TextView) findViewById(R.id.textView2);
        Salir = (Button) findViewById(R.id.button);
        Grabar = (Button) findViewById(R.id.button3);
        Recibir = (Button) findViewById(R.id.button4);

        /*Intent intent = new Intent(this, RelacionaAlumnosActivity.class);
        startActivity(intent);*/


    }

    public void onclickGrabar(View v){
        Graba = new Intent(this, Grabar_Coordenadas.class);
        startActivity(Graba);
    }

    public void onClickRecibir(View v){
        Recibe = new Intent(this, Receptor_Coordenadas.class);
        startActivity(Recibe);
    }


    public void onclickbutton(View v) {
        finish();
    }



}
