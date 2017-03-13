package com.example.achraf.loginaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.achraf.loginaccount.helper.DBApplication;
import com.example.achraf.loginaccount.helper.DatabaseHelper;
import com.example.achraf.loginaccount.helper.SessionManager;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editusername, editpassword;
    Button btnlogin, btnLinkToRegister;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);
        myDb = ((DBApplication) getApplication()).getDB();


        editusername = (EditText) findViewById(R.id.TextUserName);
        editpassword = (EditText) findViewById(R.id.TextPassword);
        btnlogin = (Button) findViewById(R.id.buttonlogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        if (!myDb.IsThereUser()) {
            myDb.insertData("achraf", "123");
            Log.e("AUTH","user achraf created successefully !");
        }

        signIn();
        Register();
    }


    public void signIn() {

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get The User name and Password
                String username = editusername.getText().toString().trim();
                String password = editpassword.getText().toString().trim();

                if (username.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please give a username !", Toast.LENGTH_SHORT).show();

                } else if (password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please give a password !", Toast.LENGTH_SHORT).show();

                } else {

                    String storedPassword = myDb.getSinlgeEntry(username);

                    if (password.equals(storedPassword)) {
                        Toast.makeText(getApplicationContext(),"Logged in successfully" , Toast.LENGTH_SHORT).show();
                        Log.e("AUTH","Logged in successfully");
                        session.setLoginStatus(true);
                        session.setLoginUsename(username);

                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Please check your credentials !", Toast.LENGTH_SHORT).show();
                        Log.e("AUTH","Please check your credentials !");
                    }
                }

            }
        });


    }

    private void Register() {
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}


