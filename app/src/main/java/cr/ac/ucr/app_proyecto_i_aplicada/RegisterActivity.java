package cr.ac.ucr.app_proyecto_i_aplicada;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import connection.Constants;
import domain.User;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText etIdentification;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etAddress;
    private EditText etPhone;
    private Button btnRegister;
    private User user;
    private ProgressDialog progressDialog;
    private Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar=(Toolbar)findViewById(R.id.registerToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registro");

        etIdentification=(EditText)findViewById(R.id.etIdenticationRegister);
        etName=(EditText)findViewById(R.id.etNameRegister);
        etEmail=(EditText)findViewById(R.id.etEmailRegister);
        etPassword=(EditText)findViewById(R.id.etPasswordRegister);
        etAddress=(EditText)findViewById(R.id.etAddressRegister);
        etPhone=(EditText)findViewById(R.id.etPhoneRegister);
        btnRegister=(Button) findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creamos un usario con los valores que se ingresan en los editText
                //user = new User();
                //user.setIdentificacionNumber(Integer.parseInt(etIdentification.getText().toString()));
                //user.setName(etName.getText().toString());
                //user.setEmail(etEmail.getText().toString());
                //user.setPassword(etPassword.getText().toString());
                //user.setAddress(etAddress.getText().toString());
                //user.setPhoneNumber(etPhone.toString());

                Intent intent = new Intent(getApplicationContext(), CoreVisesActivity.class);
                startActivity(intent);
                /*
                //Ventana de dialogo que se muestra mientas se ejecuta el web services
                progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Procesando...");
                progressDialog.setCancelable(true);
                progressDialog.setMax(100);

                //Llamado del web service
                register=new Register();
                register.execute(user);
                */

                /*if(!etIdentification.getText().toString().isEmpty() && !etName.getText().toString().isEmpty() &&
                        !etEmail.getText().toString().isEmpty() && !etAddress.getText().toString().isEmpty() &&
                        !etPassword.getText().toString().isEmpty() && !etPhone.getText().toString().isEmpty()){
                    if(emailValidator(etEmail.getText().toString())){
                        user=new User();
                        user.setIdentificacionNumber(Integer.parseInt(etIdentification.getText().toString()));
                        user.setName(etName.getText().toString());
                        user.setEmail(etEmail.getText().toString());
                        user.setPassword(etPassword.getText().toString());
                        user.setAddress(etAddress.getText().toString());
                        user.setPhoneNumber(etPhone.getText().toString());

                        //Todo lo del registro del usuario
                        etIdentification.setText("");
                        etName.setText("");
                        etEmail.setText("");
                        etPassword.setText("");
                        etAddress.setText("");
                        etPhone.setText("");

                        Intent i = new Intent(getApplicationContext(), CoreVisesActivity.class);
                    }else{
                        Toast.makeText(getApplicationContext(),"Por favor ingresa una dirrecion de correo valida",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Por favor completa todos los espacios",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }



    private boolean emailValidator (String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    //Clase que nos permite ejecutar el proceso secundario que llama
    //al web service que valida el login
    public class Register extends AsyncTask<User,Integer,Void> {

        private Boolean resultado;

        @Override
        protected void onPreExecute() {
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Register.this.cancel(true);
                }
            });

            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(User... params) {
            try  {
                SoapObject request= new SoapObject(Constants.NAMESPACE,Constants.METHODNAMEINSERT);

                request.addProperty("identificationCard", params[0].getIdentificacionNumber());
                request.addProperty("email", params[0].getEmail());
                request.addProperty("address",params[0].getAddress());
                request.addProperty("fullName",params[0].getName());
                request.addProperty("phone",params[0].getPhoneNumber());
                request.addProperty("password",params[0].getPassword());

                SoapSerializationEnvelope envelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.dotNet=true;

                envelope.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(Constants.URL);
                try{
                    transporte.call(Constants.SOAPACTIONINSERT,envelope);

                    //Todo el manejo del resultado
                    SoapObject result=(SoapObject) envelope.getResponse();

                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            progressDialog.setProgress(progreso);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Toast.makeText(RegisterActivity.this, "Bienvenido a CoreVises!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(RegisterActivity.this, "Acción cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
