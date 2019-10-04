package com.nenecorp.majiapp.Interface.AppStart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.R;

public class ResetPin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);
        findViewById(R.id.ARP_btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPin.this,SignIn.class));
                finish();
            }
        });
        findViewById(R.id.ARP_btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPin.this,SignIn.class));
                finish();
            }
        });
    }
}
