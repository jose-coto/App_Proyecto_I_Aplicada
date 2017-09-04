package connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cr.ac.ucr.app_proyecto_i_aplicada.LoginActivity;

/**
 * Created by Machito on 30/8/2017.
 */

public class BCCRConection extends AsyncTask<Void, Void, Float> {

    private Float valueDolar = null;

    @Override
    protected Float doInBackground(Void... voids) {
        try {
            final String indicatorCode = "317";
            String date = getDate();
            final String name = "Jose";
            final String subNivel = "N";


            String url = "http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx/"
                    + "ObtenerIndicadoresEconomicosXML?tcIndicador=" + indicatorCode
                    + "&tcFechaInicio=" + date
                    + "&tcFechaFinal=" + date
                    + "&tcNombre=" + name
                    + "&tnSubNiveles=" + subNivel;
            try {
                URL ficheroUrl = new URL(url);

                BufferedReader br = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));

                String entrada;
                String cadena = "";

                if (br != null) {
                    while ((entrada = br.readLine()) != null) {
                        cadena = cadena + entrada;
                    }
                }

                int pos = cadena.indexOf("R&gt;");

                valueDolar = Float.parseFloat(cadena.substring(pos + 5, pos + 17));
                Log.d("Dollar", valueDolar.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.print(valueDolar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueDolar;
    }

    private static String getDate() {
        Calendar calendario = GregorianCalendar.getInstance();
        //Me obtiene la fecha del sistema
        Date fecha = calendario.getTime();
        //me perimite darle forma a la fecha
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

        String fechaModificada = formatoDeFecha.format(fecha);


        return fechaModificada;
    }
}
