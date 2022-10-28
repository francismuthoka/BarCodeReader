package com.example.codereader;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class AddingUser extends AppCompatActivity {
   Button scanCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);
        scanCode=findViewById(R.id.scanCode);
        scanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
    }

    private void scanCode() {
        ScanOptions options=new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLoucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLoucher=registerForActivityResult(new ScanContract(),result -> {
        if(result.getContents()!=null){
            Intent intent=new Intent(AddingUser.this,EnterUserDetails.class);
            String Code=result.getContents();
            intent.putExtra("",Code);
            startActivity(intent);
        }
    });
}