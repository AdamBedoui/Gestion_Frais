package com.example.achraf.loginaccount;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.achraf.loginaccount.helper.DBApplication;
import com.example.achraf.loginaccount.helper.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editSurname , editPassword;
    Button buttonCreateAccount,btnShowAll,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb=new DatabaseHelper(this);
        myDb = ((DBApplication)getApplication()).getDB();
        editSurname=(EditText)findViewById(R.id.editTextUserName);
        editPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
        btnShowAll=(Button)findViewById(R.id.btnShowAll);
        btn_back=(Button)findViewById(R.id.btn_back);
        ADDaccount();
        Viewaccount();
        Back();

    }

    private void Back() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    public void ADDaccount(){
        buttonCreateAccount.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        boolean isInserted=myDb.insertData(editSurname.getText().toString(),editPassword.getText().toString());
                        if (isInserted)
                            Toast.makeText(RegisterActivity.this,"Account Is Add",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegisterActivity.this,"Account Not Add",Toast.LENGTH_LONG).show();
                    }

                }
        );
    }

    public void Viewaccount(){

        btnShowAll.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        Cursor res =myDb.getAllData();
                        if (res.getCount()==0){
                            showMessage("ERROR","Nothing Account");
                            return;
                        }
                        StringBuilder buffer=new StringBuilder();
                        while (res.moveToNext()){
                            buffer.append("Id: ").append(res.getString(0)).append("\n");
                            buffer.append("Username: ").append(res.getString(1)).append("\n");
                            buffer.append("Password: ").append(res.getString(2)).append("\n\n");
                        }
                        showMessage("Data",buffer.toString());
                        Log.e("AUTH",buffer.toString());
                    }

                }
        );
    }

    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setMessage(Message);
        builder.show();
    }

}
