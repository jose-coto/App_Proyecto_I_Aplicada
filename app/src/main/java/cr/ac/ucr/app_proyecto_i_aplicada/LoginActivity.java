package cr.ac.ucr.app_proyecto_i_aplicada;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import connection.Constants;
import domain.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private TextView tvRegisterLogin;
    private Button btnLogin;
    private User user;
    private ProgressDialog progressDialog;
    private Login login;
    private Boolean resultLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etEmail=(EditText)findViewById(R.id.etEmailLogin);
        this.etPassword=(EditText)findViewById(R.id.etPasswordLogin);
        this.tvRegisterLogin= (TextView)findViewById(R.id.tvRegister);
        this.btnLogin=(Button)findViewById(R.id.btnLogin);


        if(connection()) {//verificamos que exista conexion a internet

            //Evento del click sobre registrarse
            this.tvRegisterLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });

            //Evento del click sobre el buton de Login
            this.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                 //   Intent intent=new Intent(LoginActivity.this, CoreVisesActivity.class);
                   // startActivity(intent);
                ///*
                    if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                        if (emailValidator(etEmail.getText().toString())) {
                            //Creamos un usario con los valores que se ingresan en los editText
                            user = new User();
                            user.setEmail(etEmail.getText().toString());
                            user.setPassword(etPassword.getText().toString());

                            //Ventana de dialogo que se muestra mientas se ejecuta el web services
                            progressDialog = new ProgressDialog(LoginActivity.this);
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDialog.setMessage("Procesando...");
                            progressDialog.setCancelable(true);
                            progressDialog.setMax(100);

                            //Llamado del web service
                            login = new Login();
                            try {
                                resultLogin = login.execute(user.getEmail(), user.getPassword()).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                            //Si lo que nos devuelve el web service es true se inicia sesion
                            if (resultLogin) {
                                //Lanzamos el siguiente activity
                                Intent intent=new Intent(LoginActivity.this, CoreVisesActivity.class);
                                intent.putExtra("email", user.getEmail());
                                startActivity(intent);
                            } else {//Se muestra un mensaje de error
                                Toast.makeText(getApplicationContext(), "Problemas al autentificar\nPor favor intente nuevamente", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Por favor ingresa una dirreción de correo válida", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Por favor completa todos los espacios", Toast.LENGTH_SHORT).show();
                    }//*/
                }
            });
        }
    }

    //Metodo que permite estar lanzando los activities
    private void launchActivities(View view,String activityName) {
        Intent i= null;
        if(activityName.equalsIgnoreCase("registerActivity")) {
            i = new Intent(this, RegisterActivity.class);
        }else if(activityName.equalsIgnoreCase("CoreVisesActivity")) {
            i = new Intent(this, CoreVisesActivity.class);
        }else if(activityName.equalsIgnoreCase((""))){
            i = new Intent(this, MainActivity.class);
        }
        startActivity(i);
    }

    private boolean emailValidator (String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    protected Boolean connection(){
        if(wifiConnect()){
            Toast.makeText(this,"Conexión a internet detectada",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            if(dataConnect()){
                Toast.makeText(this,"Conexión a internet detectada",Toast.LENGTH_SHORT).show();
                return true;
            }else{
                Toast.makeText(this,"No se detecto conexión a internet",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    protected Boolean wifiConnect(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Boolean dataConnect(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Clase que nos permite ejecutar el proceso secundario que llama
    //al web service que valida el login
    public class Login extends AsyncTask<String,Integer,Boolean>{

        private Boolean resultado;

        @Override
        protected void onPreExecute() {
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Login.this.cancel(true);
                }
            });

            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            resultado=false;
            try  {
                SoapObject request= new SoapObject(Constants.NAMESPACE,Constants.METHODNAMELOGIN);

                request.addProperty("email", params[0]);
                request.addProperty("password", params[1]);

                SoapSerializationEnvelope envelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.dotNet=true;

                envelope.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(Constants.URL);
                try{
                    transporte.call(Constants.SOAPACTIONLOGIN,envelope);

                    //Todo el manejo del resultado
                    SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
                    resultado= Boolean.parseBoolean(result.toString());

                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultado;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            progressDialog.setProgress(progreso);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Bienvenido a CoreVises!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(LoginActivity.this, "Acción cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
