package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class ScannerActivity extends AppCompatActivity implements View.OnClickListener {
    Button scanBtn;
    DatabaseReference reference;
    private AlertDialog.Builder dialogBuilder;
    private TextView name;
    private ElegantNumberButton quantity;
    private ImageView img;
    private MyProductsData myProductsData2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        scanBtn = findViewById(R.id.scan_btn);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }
    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!= null){
            if(result.getContents() != null){
                FirebaseApp.initializeApp(ScannerActivity.this);
                reference = FirebaseDatabase.getInstance().getReference().child("Codebar");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Information2 info = snapshot.getValue(Information2.class);
                            String code = info.getCode();
                            String ProductName = info.getProductName();
                            String PrImage = info.getImage();
                            String tst = result.getContents();
                            if (code.equals(tst)) {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(ScannerActivity.this);
                                final View productPopupView = getLayoutInflater().inflate(R.layout.popup4, null);
                                name = (TextView) productPopupView.findViewById(R.id.danke);
                                name.setText(ProductName);
                                quantity = (ElegantNumberButton) productPopupView.findViewById(R.id.zid);

                                quantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                                    @Override
                                    public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                                        String Elegant = String.valueOf(newValue);
                                        myProductsData2 = new MyProductsData(ProductName, Elegant);
                                        FirebaseApp.initializeApp(ScannerActivity.this);
                                        reference = FirebaseDatabase.getInstance().getReference();
                                        reference.child("Command").child(ProductName).setValue(myProductsData2);
                                    }
                                });

                                img = (ImageView) productPopupView.findViewById(R.id.imgbtq);
                                new ImageLoadTask(PrImage, img).execute();

                                builder2.setView(productPopupView);
                                AlertDialog dialog = builder2.create();
                                dialog.show();




                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseApp.initializeApp(ScannerActivity.this);
                        reference = FirebaseDatabase.getInstance().getReference().child("Codebar");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Information2 info = snapshot.getValue(Information2.class);
                                    String code = info.getCode();
                                    String ProductName = info.getProductName();
                                    String PrImage = info.getImage();
                                    String tst = result.getContents();
                                    if (code.equals(tst)) {
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(ScannerActivity.this);
                                        final View productPopupView = getLayoutInflater().inflate(R.layout.popup4, null);
                                        name = (TextView) productPopupView.findViewById(R.id.danke);
                                        name.setText(ProductName);
                                        quantity = (ElegantNumberButton) productPopupView.findViewById(R.id.zid);

                                        quantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                                            @Override
                                            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                                                String Elegant = String.valueOf(newValue);
                                                myProductsData2 = new MyProductsData(ProductName, Elegant);
                                                FirebaseApp.initializeApp(ScannerActivity.this);
                                                reference = FirebaseDatabase.getInstance().getReference();
                                                reference.child("Command").child(ProductName).setValue(myProductsData2);
                                            }
                                        });

                                        img = (ImageView) productPopupView.findViewById(R.id.imgbtq);
                                        new ImageLoadTask(PrImage, img).execute();

                                        builder2.setView(productPopupView);
                                        AlertDialog dialog = builder2.create();
                                        dialog.show();




                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                 */

            }
            else {
                Toast.makeText(this,"No Results",Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}