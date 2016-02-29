package com.example.pc.caminador;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivityDobleArrayString extends AppCompatActivity {


    //private final String NAMESPACE = "http://rastreriza.esy.es";
    private final String NAMESPACE = "http://192.168.1.10/receta_soap_wdsl";
    private final String URL = "http://192.168.1.10/receta_soap_wdsl/servicio_con_wdsl.php?wsdl";
    //private final String URL = "http://rastreriza.esy.es/servicio_con_wdsl.php?wsdl";
     private final String SOAP_ACTION_PREFIX = "/";
    private final String METHOD_NAME_1 = "insertar";

    private String TAG = "Depuración:";
    //   private static String nombre_receta, tipo_receta, preparacion_receta, presentacion_receta;


    private TextView tv1;

    private String latitud,longitud,fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView2);



        //String [] una_coordenada = new String[3];
        latitud = "20";
        longitud = "30";
        fecha = "40";
        //Vcoordenadas.add(una_coordenada);
      /*  una_coordenada[1][0] = "50";
        una_coordenada[1][1] = "60";
        una_coordenada[1][2] = "70";*/

        AsyncCallWS task = new AsyncCallWS();
        //Call execute
        task.execute();

    }


    public String peticiones ( SoapSerializationEnvelope envelope, String metodo, String propiedad, String valor1,String valor2,String valor3){
        String cadena;
        //Create request
        SoapObject request = new SoapObject(NAMESPACE, metodo);
        //bodyOut is the body object to be sent out with this envelope
        //Add the property to request object
        request.addProperty(propiedad, valor1);request.addProperty(propiedad, valor2);request.addProperty(propiedad, valor3);

        envelope.bodyOut = request;
        HttpTransportSE transport = new HttpTransportSE(URL);
        try {
            transport.call(NAMESPACE + SOAP_ACTION_PREFIX + metodo, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //bodyIn is the body object received with this envelope
        if (envelope.bodyIn != null) {
            //getProperty() Returns a specific property at a certain index.
            cadena=((SoapObject) envelope.bodyIn).getProperty(0).toString();
            // nombre_receta= envelope.bodyIn.toString();
        }
        else cadena="Error";
        return cadena;
    }

    public void setCoordenadas(String latitud,String longitud,String fecha) {
        try {
            // SoapEnvelop.VER11 is SOAP Version 1.1 constant
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);


             peticiones(envelope,METHOD_NAME_1,"GLatitud",latitud,longitud,fecha);


        } catch (Exception e) {
            e.printStackTrace();

           
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            setCoordenadas(latitud,longitud,fecha);
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
