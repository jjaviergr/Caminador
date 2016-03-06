package com.example.pc.caminador;

/**
 * Created by pc on 06/03/2016.
 */
/*   getCadenaResponse{return=anyType{array=hola; array=adios; }; return=anyType{array=hello; array=bye; }; return=anyType{array=bonjour; array=au revoir; }; }*/

public class experimento {
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView)findViewById(R.id.tv);

        HttpTransportSE transport = new HttpTransportSE(URL);
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        try {
            transport.call(NAMESPACE + METHOD_NAME, envelope);
            SoapObject response = (SoapObject)envelope.bodyIn;

            int cols = response.getPropertyCount();
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < cols; i++) {
                Object objectResponse = (Object) response.getProperty(i);
                String result = objectResponse.toString();
                String[] array = result.split(";");
                for (int j = 0; j < array.length-1; j++) {
                    String ss = array[j].substring(array[j].indexOf('='), array[j].length());
                    list.add(ss);
                    Log.v("TEST", ss);
                }
                matrix.add(list);
            }

        */
/* Here you have the matrix of strings in 'matrix' *//*


        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    /*public String[] getStringArray() throws Exception
    {
        SoapObject Table = (SoapObject)getEnvelope().bodyIn;

        String []output=null;
        if(Table!=null)
        {
            int count= Table.getPropertyCount();
            output = new String[count];
            for(int i=0;i<count;i++)
            {
                output[i]=Table.getProperty(i).toString();
            }
        }
        return output;

    }

    // or if you want 2 dimensional array ,

    public String[][] getStringTable() throws Exception
    {
        SoapObject Table =(SoapObject)getEnvelope().bodyIn;

        String [][]output=null;
        if(Table!=null)
        {
            SoapObject row = (SoapObject) Table.getProperty(0);

            if(row!=null)
            {
                int rCount = Table.getPropertyCount();
                int cCount = ((SoapObject)Table.getProperty(0)).getPropertyCount();
                output = new String[rCount][cCount];
                for(int i=0;i<rCount;i++)
                {
                    for(int j=0;j<cCount;j++)
                        output[i][j] =((SoapObject)    Table.getProperty(i)).getProperty(j).toString();
                }

            }
        }
        return output;

    }*/
}

