package com.example.codereader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentResult;

public class CustomeDetails extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    TextView c_name,c_number,p_name,p_date,p_water,p_sprayed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_details);

        c_name=findViewById(R.id.Cutomer_Name);
        c_number=findViewById(R.id.customer_number);
        p_name=findViewById(R.id.plant_name);
        p_date=findViewById(R.id.planting_date);
        p_water=findViewById(R.id.lastly_watered);
        p_sprayed=findViewById(R.id.lastly_sprayed);
        Intent intent= getIntent();
        String code=intent.getStringExtra("");
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("CUSTOMERS DETAILS");

        progressDialog=new ProgressDialog(CustomeDetails.this);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setTitle("fetching data");
        progressDialog.setMessage("please wait...");
        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ReceiveCustDetails cust=dataSnapshot.getValue(ReceiveCustDetails.class);
                    if(code.equals(cust.getCustomer_Code())){
                        c_name.setText(cust.getCustomer_name());
                        c_number.setText(cust.getCustomer_Number());
                        p_name.setText(cust.getPlant_Name());
                        p_date.setText(cust.getDate_planted());
                        p_sprayed.setText(cust.getLastly_sprayed());
                        p_water.setText(cust.getLastly_Watered());
                        progressDialog.dismiss();

                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(CustomeDetails.this,"Network error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}