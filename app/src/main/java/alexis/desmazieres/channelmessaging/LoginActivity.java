package alexis.desmazieres.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements OnDownloadCompleteListener, View.OnClickListener {

    protected String id;
    protected String password;
    Button ValidateButton;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }


    Handler mHandlerTada = new Handler(); // android.os.handler
    int mShortDelay = 4000; //milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ValidateButton = (Button) findViewById(R.id.btnValider);
        ValidateButton.setOnClickListener(this);

        mHandlerTada.postDelayed(new Runnable(){
            public void run(){
                // Your code here
                //YoYo.AnimationComposer
                mHandlerTada.postDelayed(this, mShortDelay);
            }
        }, mShortDelay);

    }

    @Override
    public void onClick(View v){



        EditText txtFieldId = (EditText) findViewById(R.id.etIdentifiant);
        EditText txtFieldPassword = (EditText) findViewById(R.id.etPassword);
        id =  "adesm"; //txtFieldId.getText().toString();
        password = "alexisdesmazieres";//txtFieldPassword.getText().toString();


        Downloader d = new Downloader(this);
        d.setListDownload(this);
        d.execute();

    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        Response response = gson.fromJson(content, Response.class);
        if(response == null){
            Toast toast = Toast.makeText(getApplicationContext(), "erreur de connexion", Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("accesstoken", response.getAccesstoken());
            editor.commit();
        }


        SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
        String accesstoken = settings.getString("accesstoken","default");

        if(accesstoken != "default") {
            try {
                Intent myIntent = new Intent(getApplicationContext(), ChannelActivity.class);
                startActivity(myIntent);
            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(), "erreur dans le lancement de l'application", Toast.LENGTH_LONG);
                toast.show();
            }
        }else{

            EditText txtFieldPassword = (EditText) findViewById(R.id.etPassword);
            txtFieldPassword.setText("");

            Toast toast = Toast.makeText(getApplicationContext(), "Vous ne passerer pas !!!!!!!!!!!!! (informations incorrects)", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
