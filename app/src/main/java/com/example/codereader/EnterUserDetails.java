package com.example.codereader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EnterUserDetails extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText cust_name,cust_number,plan_name,da_planted,da_watered,da_sprayed;
    Button btnadd_customer;
    ReceiveCustDetails cut;
    String Code;
    char x;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatePickerDialog.OnDateSetListener plantedSetListener;
    DatePickerDialog.OnDateSetListener sprayedSetListener;
    DatePickerDialog.OnDateSetListener wateredSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Code=intent.getStringExtra("");
        setContentView(R.layout.activity_enter_user_details);
        cust_name=findViewById(R.id.CustomerName);
        cust_number=findViewById(R.id.CustomerNumber);
        plan_name=findViewById(R.id.PlantName);
        da_planted=findViewById(R.id.PlantingDate);
        da_watered=findViewById(R.id.LastlyWatered);
        da_sprayed=findViewById(R.id.LastlySprayed);
        btnadd_customer=findViewById(R.id.addbtn);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("CUSTOMERS DETAILS");

        da_planted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(EnterUserDetails.this,plantedSetListener,year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMinDate((long) (System.currentTimeMillis()-(60*60*24*364.25*10*1000)));
                datePickerDialog.show();
            }
        });
         plantedSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                da_planted.setText(dateFormat.format(calendar.getTime()));
            }
        };

        da_sprayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(EnterUserDetails.this,sprayedSetListener,year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMinDate((long) (System.currentTimeMillis()-(60*60*24*364.25*10*1000)));
                datePickerDialog.show();
            }
        });
        sprayedSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                da_sprayed.setText(dateFormat.format(calendar.getTime()));
            }
        };

        da_watered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(EnterUserDetails.this,wateredSetListener,year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMinDate((long) (System.currentTimeMillis()-(60*60*24*364.25*10*1000)));
                datePickerDialog.show();
            }
        });
        wateredSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                da_watered.setText(dateFormat.format(calendar.getTime()));
            }
        };

        btnadd_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer();
            }
        });


    }

    private void addCustomer() {
       String nameText=cust_name.getText().toString().toLowerCase().trim();
       String numberText=cust_number.getText().toString().trim();
       String plantText=plan_name.getText().toString().toLowerCase().trim();
       String panDateTxt=da_planted.getText().toString().trim();
       String watDText=da_watered.getText().toString().trim();
       String sprDatxt=da_sprayed.getText().toString().trim();

       if(nameText.isEmpty()){
           cust_name.setError("please enter customer name");
           cust_name.requestFocus();
       }
        else if(numberText.isEmpty()){
            cust_number.setError("please enter customer phone number");
            cust_number.requestFocus();
        }
        else if(plantText.isEmpty()){
            plan_name.setError("please enter name of plant");
            plan_name.requestFocus();
        }
        else if(panDateTxt.isEmpty()){
            da_planted.setError("please choose date of planting");
            da_planted.requestFocus();
        }
        else if(sprDatxt.isEmpty()){
            da_sprayed.setError("select last date of spraying");
            da_sprayed.requestFocus();
        }
        else if(watDText.isEmpty()){
            da_watered.setError("select last day of watering");
            da_watered.requestFocus();
        }


        else{

           customer cust=new customer(Code,nameText,numberText,plantText,panDateTxt,watDText,sprDatxt);
           progressDialog=new ProgressDialog(EnterUserDetails.this);
           progressDialog.setCanceledOnTouchOutside(true);
           progressDialog.setTitle("adding customer");
           progressDialog.setMessage("please wait...");
           progressDialog.show();
           firebaseDatabase.getReference("CUSTOMERS DETAILS");

           firebaseDatabase.getReference("CUSTOMERS DETAILS");
           databaseReference.child(Code).setValue(cust).addOnSuccessListener(new OnSuccessListener<Void>() {
               @Override
               public void onSuccess(Void unused) {
                   progressDialog.dismiss();
                   Toast.makeText(EnterUserDetails.this, "Customer Added successfully", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(getApplicationContext(),AddingUser.class));
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   progressDialog.dismiss();
                   Toast.makeText(EnterUserDetails.this, "Network Error", Toast.LENGTH_SHORT).show();
               }
           });

        }
    }
}