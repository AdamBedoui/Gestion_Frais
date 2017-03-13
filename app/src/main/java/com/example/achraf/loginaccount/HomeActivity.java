package com.example.achraf.loginaccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.achraf.loginaccount.helper.DBApplication;
import com.example.achraf.loginaccount.helper.DatabaseHelper;
import com.example.achraf.loginaccount.helper.SessionManager;

public class HomeActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    Toolbar toolbar_home;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar_home = (Toolbar) findViewById(R.id.toolbar_home);
        toolbar_home.setTitle("Home");
        setSupportActionBar(toolbar_home);

        session = new SessionManager(getApplicationContext());

        myDb = new DatabaseHelper(this);
        myDb = ((DBApplication) getApplication()).getDB();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();

        switch (item_id){
            case  R.id.btn_logout:
                session.setLoginStatus(false);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;

            case  R.id.btn_profile:
                session.setLoginStatus(false);
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;

            default:
                break;
        }
        return true;
    }
}
