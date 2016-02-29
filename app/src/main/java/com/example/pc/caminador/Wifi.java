package com.example.pc.caminador;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 29/02/2016.
 */
public class Wifi extends AppCompatActivity {



    public boolean Detecta_Wifi()    {

        try {
            //Recogemos el servicio ConnectivityManager
            //el cual se encarga de todas las conexiones del terminal
            ConnectivityManager conMan = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            //Recogemos el estado del 3G
            //como vemos se recoge con el parámetro 0
            NetworkInfo.State internet_movil = conMan.getNetworkInfo(0).getState();
            //Recogemos el estado del wifi
            //En este caso se recoge con el parámetro 1
            NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
            //Miramos si el internet 3G está conectado o conectandose...
            if (internet_movil == NetworkInfo.State.CONNECTED || internet_movil == NetworkInfo.State.CONNECTING) {
                //Toast.makeText(getApplicationContext(), "Conectado por 3G", Toast.LENGTH_LONG).show();
               // tv1.setText(tv1.getText() + "No Conectado a Wifi");
                //return (false); //Activamos con el dispositivo real
                return (true);
                //Si no esta por 3G comprovamos si está conectado o conectandose al wifi...
            } else {
                if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                    ///////////////
                    //El movil está conectado por WIFI
                    //En este ejemplo mostraríamos mensaje por pantalla
                    //Toast.makeText(getApplicationContext(), "Conectado por WIFI" , Toast.LENGTH_LONG).show();
                    //tv1.setText(tv1.getText() + "Conectado a Wifi");
                    return (true);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
           // tv1.setText(tv1.getText() + "Excepcion.No Conectado a Wifi");
            return (false);
        }

        return (false);

    }  }