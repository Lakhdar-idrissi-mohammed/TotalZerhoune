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

public class MainActivity4 extends AppCompatActivity {
    private Button add;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newcarpopup_marque,newcarpopup_immatriculation,newcarpopup_préstation,newcarpopup_prix;
    private Button newcarpopup_cancel,newcarpopup_save;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
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
                new MyProductsData("Total Wash",R.drawable.totalwash),
                new MyProductsData("Anti Crevaison",R.drawable.bombe),
                new MyProductsData("Baume Cuir",R.drawable.baumecuir),
                new MyProductsData("Liquide Refrodissement",R.drawable.liquide),
                new MyProductsData("Nettoyant Jante",R.drawable.nettoyantjantes),
        };

        MyProductsAdapter myProductsAdapter = new MyProductsAdapter(myProductsData,MainActivity4.this);
        recyclerView.setAdapter(myProductsAdapter);

    }
    public void addNewCarDialog(){
        dialogBuilder=new AlertDialog.Builder(this);
        final View carPopupView = getLayoutInflater().inflate(R.layout.popup2, null);
        newcarpopup_marque= (EditText) carPopupView.findViewById(R.id.newcarpopup_marque);
        newcarpopup_immatriculation= (EditText) carPopupView.findViewById(R.id.newcarpopup_immatriculation);
        newcarpopup_préstation= (EditText) carPopupView.findViewById(R.id.newcarpopup_préstation);
        newcarpopup_prix= (EditText) carPopupView.findViewById(R.id.newcarpopup_prix);
        newcarpopup_save= (Button) carPopupView.findViewById(R.id.btn6);
        newcarpopup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Marque = newcarpopup_marque.getText().toString();
                String Immatriculation = newcarpopup_immatriculation.getText().toString();
                String Préstation = newcarpopup_préstation.getText().toString();
                String Prix = newcarpopup_prix.getText().toString();
                if (!Marque.isEmpty() && !Immatriculation.isEmpty() && !Préstation.isEmpty() && !Prix.isEmpty()) {
                    Carinfos2 carinfos = new Carinfos2(Marque, Immatriculation, Préstation, Prix);
                    FirebaseApp.initializeApp(MainActivity4.this);
                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("Lavage").child(Immatriculation).setValue(carinfos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            newcarpopup_marque.setText("");
                            newcarpopup_immatriculation.setText("");
                            newcarpopup_préstation.setText("");
                            newcarpopup_prix.setText("");
                            Toast.makeText(MainActivity4.this, "Successfuly Added", Toast.LENGTH_SHORT).show();
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