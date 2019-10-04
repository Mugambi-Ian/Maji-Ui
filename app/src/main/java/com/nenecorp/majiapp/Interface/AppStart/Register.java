package com.nenecorp.majiapp.Interface.AppStart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.Interface.AccountWizard.AccountWizard;
import com.nenecorp.majiapp.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.AR_btnGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, AccountWizard.class));
                finish();
            }
        });
        findViewById(R.id.AR_btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, AccountWizard.class));
                finish();
            }
        });
        findViewById(R.id.AR_btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, SignIn.class));
                finish();
            }
        });
    }
}
