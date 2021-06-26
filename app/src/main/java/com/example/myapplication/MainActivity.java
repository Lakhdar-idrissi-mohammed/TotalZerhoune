package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    EditText usern,pswd;

    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usern=(EditText)  findViewById(R.id.user);
        pswd =(EditText) findViewById(R.id.pwd);
        button =(Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();

            }
        });

    }
    public void Check() {
        if (usern.getText().toString().equals("admin") && pswd.getText().toString().equals("Total") ) {
            Toast.makeText(this, "Succes Login",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Login Failed",Toast.LENGTH_SHORT).show();
        }

    }
}