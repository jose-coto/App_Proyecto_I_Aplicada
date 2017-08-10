package cr.ac.ucr.app_proyecto_i_aplicada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegisterLogin;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.tvRegisterLogin= (TextView)findViewById(R.id.tvRegister);

        this.tvRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivities(view,"registerActivity");
            }
        });
    }

    public void launchActivities(View view,String activityName) {
        Intent i= null;
        if(activityName.equalsIgnoreCase("registerActivity")) {
            i = new Intent(this, RegisterActivity.class);
        }else if(activityName.equalsIgnoreCase((""))){
            i = new Intent(this, MainActivity.class);
        }
        startActivity(i);
    }
}
