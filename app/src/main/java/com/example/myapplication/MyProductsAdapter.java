package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {
    DatabaseReference reference;
    DatabaseReference reference2;
    MyProductsData[] myProductsData;
    MyProductsData myProductsData2;
    Context context;

    public MyProductsAdapter(MyProductsData[] myProductsData,MainActivity3 activity) {
        this.myProductsData = myProductsData;
        this.context = activity;
    }
    public MyProductsAdapter(MyProductsData[] myProductsData,MainActivity4 activity) {
        this.myProductsData = myProductsData;
        this.context = activity;
    }
    public MyProductsAdapter(MyProductsData[] myProductsData,MainActivity5 activity) {
        this.myProductsData = myProductsData;
        this.context = activity;
    }
    public MyProductsAdapter(MyProductsData[] myProductsData,MainActivity6 activity) {
        this.myProductsData = myProductsData;
        this.context = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.productslist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyProductsData myProductsDataList = myProductsData[position];
        holder.textViewName.setText(myProductsDataList.getProductName());
        holder.productImage.setImageResource(myProductsDataList.getProductImage());
        holder.btn_quantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                String Name = myProductsDataList.getProductName().toString();
                String Elegant = String.valueOf(newValue);
                myProductsData2= new MyProductsData(Name,Elegant);
                FirebaseApp.initializeApp(context);
                reference = FirebaseDatabase.getInstance().getReference();
                reference.child("Command").child(Name).setValue(myProductsData2);
                /*FirebaseApp.initializeApp(context);
                reference2 = FirebaseDatabase.getInstance().getReference().child("Stock");
               reference2.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                      for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                          Information info = snapshot.getValue(Information.class);
                          int txt = info.getStock();
                          if (Name==info.getProductName()){
                              myProductsData2= new MyProductsData(Name,Elegant);
                              FirebaseApp.initializeApp(context);
                              reference = FirebaseDatabase.getInstance().getReference();
                              reference.child("Command").child(Name).setValue(myProductsData2);}
                      }
                   }

                   @Override
                   public void onCancelled(@NonNull  DatabaseError error) {

                   }
               });
                 */


            }

        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myProductsDataList.getProductName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myProductsData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView textViewName;
         ElegantNumberButton btn_quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            btn_quantity = (ElegantNumberButton) itemView.findViewById(R.id.btn_Quantity);


        }
    }

}

