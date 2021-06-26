package com.example.myapplication;

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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {
    private Button add;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
   private TextView txt;
    private Button newcarpopup_cancel,newcarpopup_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
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
                new MyProductsData("Ain Soltan",R.drawable.ainsoltan),
                new MyProductsData("Oulmes Tropical",R.drawable.oulmes),
                new MyProductsData("Magnum",R.drawable.magnum),
                new MyProductsData("Glamour Vert",R.drawable.glamourvert),
                new MyProductsData("Lm Bleu",R.drawable.lmbleu),
        };

        MyProductsAdapter myProductsAdapter = new MyProductsAdapter(myProductsData,MainActivity5.this);
        recyclerView.setAdapter(myProductsAdapter);

    }
    public void addNewCarDialog(){
        dialogBuilder=new AlertDialog.Builder(this);
        final View carPopupView = getLayoutInflater().inflate(R.layout.popup3, null);
        txt= carPopupView.findViewById(R.id.textView);
        newcarpopup_save= (Button) carPopupView.findViewById(R.id.btn6);
        newcarpopup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Toast.makeText(MainActivity5.this, "Successfuly Added", Toast.LENGTH_SHORT).show();

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