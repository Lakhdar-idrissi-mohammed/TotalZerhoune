package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    private Button add;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newcarpopup_marque,newcarpopup_immatriculation,newcarpopup_ep,newcarpopup_prix;
    private Button newcarpopup_cancel,newcarpopup_save;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        add=(Button) findViewById(R.id.btn5);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewCarDialog();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyProductsData[] myProductsData = new MyProductsData[]{
                new MyProductsData("Total Rubia",R.drawable.trtrg),
                new MyProductsData("Total Quartz",R.drawable.quartz),
                new MyProductsData("Total MotorOil",R.drawable.motoroil),
                new MyProductsData("Total FluidMatic",R.drawable.matic),
                new MyProductsData("HI-PERF",R.drawable.perf),
        };

        MyProductsAdapter myProductsAdapter = new MyProductsAdapter(myProductsData,MainActivity3.this);
        recyclerView.setAdapter(myProductsAdapter);


    }

    public void addNewCarDialog(){

        dialogBuilder=new AlertDialog.Builder(this);
        final View carPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        newcarpopup_marque= (EditText) carPopupView.findViewById(R.id.newcarpopup_marque);
        newcarpopup_immatriculation= (EditText) carPopupView.findViewById(R.id.newcarpopup_immatriculation);
        newcarpopup_ep= (EditText) carPopupView.findViewById(R.id.newcarpopup_ep);
        newcarpopup_prix= (EditText) carPopupView.findViewById(R.id.newcarpopup_prix);
        newcarpopup_save= (Button) carPopupView.findViewById(R.id.btn6);
        newcarpopup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Marque = newcarpopup_marque.getText().toString();
                String Immatriculation = newcarpopup_immatriculation.getText().toString();
                String Ep = newcarpopup_ep.getText().toString();
                String Prix = newcarpopup_prix.getText().toString();
                if (!Marque.isEmpty() && !Immatriculation.isEmpty() && !Ep.isEmpty() && !Prix.isEmpty()) {
                    Carinfos carinfos = new Carinfos(Marque, Immatriculation, Ep, Prix);
                    FirebaseApp.initializeApp(MainActivity3.this);
                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("Vidange").child(Immatriculation).setValue(carinfos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            newcarpopup_marque.setText("");
                            newcarpopup_immatriculation.setText("");
                            newcarpopup_ep.setText("");
                            newcarpopup_prix.setText("");
                            Toast.makeText(MainActivity3.this, "Successfuly Added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            });

        newcarpopup_cancel= (Button) carPopupView.findViewById(R.id.btn7);
        newcarpopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });

        dialogBuilder.setView(carPopupView);
        dialog= dialogBuilder.create();
        dialog.show();

    }


}