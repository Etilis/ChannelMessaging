package alexis.desmazieres.channelmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements OnDownloadCompleteListener, View.OnClickListener {

    protected String Id;
    protected String Password;
    Button ValidateButton;

    public LoginActivity() {
        EditText txtFieldId = (EditText) findViewById(R.id.txtId);
        EditText txtFieldPassword = (EditText) findViewById(R.id.txtPassword);
        Id = txtFieldId.getText().toString();
        Password = txtFieldPassword.getText().toString();
        ValidateButton = (Button) findViewById(R.id.btnValider);
        ValidateButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View v){
        Downloader d = new Downloader();
        d.setListDownload(this);
        d.execute();
    }

    @Override
    public void onDownloadCompleted(String content) {

    }
}
