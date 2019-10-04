package com.nenecorp.majiapp.Interface.AppStart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.Interface.MajiUi.Home;
import com.nenecorp.majiapp.R;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findViewById(R.id.ASI_btnGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, Home.class));
                finish();
            }
        });
        findViewById(R.id.ASI_btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, Home.class));
                finish();
            }
        });
        findViewById(R.id.ASI_btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, Register.class));
                finish();
            }
        });
        findViewById(R.id.ASI_btnResetPin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ResetPin.class));
                finish();
            }
        });
    }
}
