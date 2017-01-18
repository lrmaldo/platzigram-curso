package lrmaldo.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lrmaldo.platzigram.view.ContainerActivity;
import lrmaldo.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    Button login ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     login  = (Button) findViewById(R.id.btn_login);


          /*clickear boton login*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContainerActivity.class);
                startActivity(intent);
            }
        });

    }

  public void createAccount(View view){
      Intent intent = new Intent(this, CreateAccountActivity.class);
      startActivity(intent);
  }
}
