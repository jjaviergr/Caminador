package com.example.pc.caminador;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //private final String NAMESPACE = "http://rastreriza.esy.es";
    private final String NAMESPACE = "http://192.168.1.10/receta_soap_wdsl";
    private final String URL = "http://192.168.1.10/receta_soap_wdsl/servicio_con_wdsl.php?wsdl";
    //private final String URL = "http://rastreriza.esy.es/servicio_con_wdsl.php?wsdl";
     private final String SOAP_ACTION_PREFIX = "/";
    private final String METHOD_NAME_1 = "insertar_coordenadas";

    private String TAG = "Depuración:";
    //   private static String nombre_receta, tipo_receta, preparacion_receta, presentacion_receta;


    private TextView tv1;

    //List<String[]> Vcoordenadas;
    private String[][] Vcoordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView2);

        // Vcoordenadas =  new ArrayList<String[]>();
        Vcoordenadas=new String[1][3];
        //String [] una_coordenada = new String[3];
        Vcoordenadas[0][0] = "20";
        Vcoordenadas[0][1] = "30";
        Vcoordenadas[0][2] = "40";
        //Vcoordenadas.add(una_coordenada);
      /*  una_coordenada[1][0] = "50";
        una_coordenada[1][1] = "60";
        una_coordenada[1][2] = "70";*/

        AsyncCallWS task = new AsyncCallWS();
        //Call execute
        task.execute();

    }


    public String peticiones ( SoapSerializationEnvelope envelope, String metodo, String propiedad, String[][] coordenadas){
        String cadena="";
        //Create request
        SoapObject request = new SoapObject(NAMESPACE, metodo);
        //bodyOut is the body object to be sent out with this envelope
        //Add the property to request object
        request.addProperty(propiedad, coordenadas);
        MarshallArray vector=new MarshallArray();
        vector.register(envelope);
        envelope.bodyOut = request;
        HttpTransportSE transport = new HttpTransportSE(URL);
        try {
            transport.call(NAMESPACE + SOAP_ACTION_PREFIX + metodo, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //bodyIn is the body object received with this envelope
        /*if (envelope.bodyIn != null) {
            //getProperty() Returns a specific property at a certain index.
            cadena=((SoapObject) envelope.bodyIn).getProperty(0).toString();
            // nombre_receta= envelope.bodyIn.toString();
        }
        else cadena="Error";*/
        return cadena;
    }

    public void setCoordenadas(String[][] coordenadas) {
        try {
            // SoapEnvelop.VER11 is SOAP Version 1.1 constant
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);


             peticiones(envelope,METHOD_NAME_1,"cod_receta",coordenadas);


        } catch (Exception e) {
            e.printStackTrace();

           
        }
    }

    private class AsyncCallWS extends AsyncTask<String[][], Void, Void> {
        @Override
        protected Void doInBackground(String[][]... params) {
            Log.i(TAG, "doInBackground");
            setCoordenadas(Vcoordenadas);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");

        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
          //  tv1.setText(String.format("Obteniendo...%d", una_coordenada));
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
