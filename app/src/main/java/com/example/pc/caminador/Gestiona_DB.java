package com.example.pc.caminador;

/**
 * Created by pc on 06/03/2016.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

    public class Gestiona_DB extends AppCompatActivity
    {
        public static final String KEY_COOR_ROWID = "id";
        public static final String KEY_COOR_LATITUD = "latitud";
        public static final String KEY_COOR_LONGITUD="longitud";
        public static final String KEY_COOR_FECHA="fecha";
        
        private static final String TAG = "AdaptadorBD";

        private static final String DATABASE_NAME = "dbCacheGps";
        private static final String DATABASE_TABLE_COORDENADAS = "coordenadas";
      
        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_CREATE_TABLE_GPS =
                "create table "+DATABASE_TABLE_COORDENADAS+
                        " ("+KEY_COOR_ROWID+" integer primary key autoincrement, "

                        +KEY_COOR_LATITUD+" text , "
                        +KEY_COOR_LONGITUD+" text , "
                        +KEY_COOR_FECHA+" text)";


        private final Context context;
        private BaseDatosHelper BDHelper;
        private SQLiteDatabase bsSql;
        private String[] todasColumnas=new String[] {KEY_COOR_ROWID,KEY_COOR_LATITUD,
                KEY_COOR_LONGITUD,KEY_COOR_FECHA};


        public Gestiona_DB(Context ctx)
        {
            this.context = ctx;
            BDHelper = new BaseDatosHelper(context);
        }

        //--- abre una conexión a la BD para lectura/escritura
        public Gestiona_DB open() throws SQLException{
            bsSql = BDHelper.getWritableDatabase();
            return this;
        }

        //---cierra la base de datos---
        public void close(){
            BDHelper.close();
        }

        //inserta una fila en la BD a partir de un objeto Empresa
        public long insertarCoordenada(Coordenada c){
            ContentValues initialValues = new ContentValues();
            //initialValues.put(KEY_COOR_ROWID, emp.getNombreE());
            initialValues.put(KEY_COOR_LATITUD, c.getLatitud());
            initialValues.put(KEY_COOR_LONGITUD, c.getLongitud());
            initialValues.put(KEY_COOR_FECHA,c.getFecha());



            //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
            return bsSql.insert(DATABASE_TABLE_COORDENADAS, null, initialValues);
        }
        //inserta una fila en la BD a partir de un objeto Empresa
        public long insertarCoordenadas(String[][] c){
            String[] d;
            ContentValues initialValues=null;
            for (int i=0;i<c.length;i++)
            {
                d=c[i];
                initialValues = new ContentValues();
                initialValues.put(KEY_COOR_ROWID, c[0].toString());
                initialValues.put(KEY_COOR_LATITUD, c[1].toString());
                initialValues.put(KEY_COOR_LONGITUD, c[2].toString());
                initialValues.put(KEY_COOR_FECHA,c[3].toString());

            }




            //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
            return bsSql.insert(DATABASE_TABLE_COORDENADAS, null, initialValues);
        }


        //-- ELIMINAR
        //elimina la empresa identificada por numero
        public boolean borrarCoordenada(int id){
            //manda una sentencia DELETE a la BD para eliminar la fila identificada por numero
            return bsSql.delete(DATABASE_TABLE_COORDENADAS, KEY_COOR_ROWID + "=" + id, null) > 0;
        }


        //--CONSULTAR
        //consulta a la BD para obtener todas las coordenadas
        public Cursor getTodaslasCoordenadas() {
            return bsSql.query(DATABASE_TABLE_COORDENADAS, todasColumnas,null,null,null,null,null);
        }

        //consulta a una coordenada por 'id' (clave primaria)
        public Cursor getCoordenada(int numero) throws SQLException{
            Cursor mCursor = bsSql.query(true, DATABASE_TABLE_COORDENADAS, todasColumnas,
                    KEY_COOR_ROWID + "=" + numero,null,null,null,null,null);
            //si hay datos devueltos, apunta al principio
            if (mCursor != null)  mCursor.moveToFirst();
            return mCursor;
        }

        //-- MODIFICAR
        //actualiza los datos de una Coordenada en concreto
        public boolean actualizarEmpresa(Coordenada c){
            ContentValues args = new ContentValues();
            args.put(KEY_COOR_LATITUD, c.getLatitud());
            args.put(KEY_COOR_LONGITUD, c.getLongitud());
            args.put(KEY_COOR_FECHA, c.getLongitud());
            return bsSql.update(DATABASE_TABLE_COORDENADAS, args,KEY_COOR_ROWID + "=" + c.getId(), null) > 0;
        }


        //-- OTROS MÉTODOS
        //Devuelve cadena con los datos de una coordenada (la fila de un Cursor)
        public String mostrarCoordenada(Cursor c){
            String cadena=null;

            cadena=
                    "id: " + c.getString(0) + "\n" +
                            "Latitud: " + c.getString(1) + "\n" +
                            "Longitud: " + c.getString(2) + "\n" +
                            "Fecha: " + c.getString(3)+"\n";
            return cadena;
        }


        //Obtiene una lista de Coordenadas a través de un objeto Cursor
        public ArrayList<Coordenada> getAllCoordenadas() {
            //Lista de empresas
            ArrayList<Coordenada> listaCoordenadas = new ArrayList<Coordenada>();
            //objeto cursor que se llena con el resultado de la consulta que obtiene todos las empresas
            Cursor cursor = this.getTodaslasCoordenadas();
            //se posiciona al principio del cursor
            cursor.moveToFirst();
            //mientras hay datos en el cursor
            while (!cursor.isAfterLast()) {
                //genera una empresa
                Coordenada comment = cursorToCoordenada(cursor);
                //añade un contacto a la lista
                listaCoordenadas.add(comment);
                //avanza al siguiente
                cursor.moveToNext();
            }
            cursor.close();
            return listaCoordenadas;
        }

        //genera una Empresa a partir de un objeto Cursor
        public Coordenada cursorToCoordenada(Cursor cursor) {
            Coordenada e = new Coordenada();
            e.setId(cursor.getString(0));
            e.setLatitud(cursor.getString(1));
            e.setLongitud(cursor.getString(2));
            e.setFecha(cursor.getString(3));

            return e;
        }

        //**** subclase SQLiteOpenHelper***/

        //clase para crear la base de datos SQLite
        private static class BaseDatosHelper extends SQLiteOpenHelper
        {
            BaseDatosHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            @Override
            public void onCreate(SQLiteDatabase db)	{
                try{
                    //ejecuta la sentencia SQL de creación de la BD
                    db.execSQL(DATABASE_CREATE_TABLE_GPS);

                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
                Log.w(TAG, "Actualizando base de datos de la versión " + oldVersion
                        + " a "
                        + newVersion + ", borraremos todos los datos");
                //elimina tabla de la BD
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_COORDENADAS);
                //crea la nueva BD
                onCreate(db);
            }

            public Cursor recupera_coordenada(SQLiteDatabase db,String id)
            {

            /*Cursor c=db.rawQuery("SELECT "+
                    KEY_EMPRESAS_ROWID+","+
                    KEY_EMPRESAS_NOMBRE_EMPRESA+","+
                KEY_EMPRESAS_NOMBRE_RESPONSABLE+","+
                    KEY_EMPRESAS_APELLIDOS+","+
                KEY_EMPRESAS_EMAIL+","+
                    KEY_EMPRESAS_TELEFONO+","+
                KEY_EMPRESAS_DIRECCION+","+
                    KEY_EMPRESAS_WEB+
                    " FROM "+DATABASE_TABLE_EMPRESAS+
                " WHERE "+KEY_EMPRESAS_ROWID+"="+numero, null);
*/          String[] todasColumnas_empresas =new String[] {KEY_COOR_ROWID,
                    KEY_COOR_LATITUD,KEY_COOR_LONGITUD,KEY_COOR_FECHA};

                Cursor c=db.query(DATABASE_TABLE_COORDENADAS,todasColumnas_empresas ,KEY_COOR_ROWID+"="+id,null,null,null,null);
                if (c != null)
                    c.moveToFirst();
                return c;
            }
        }



    }

