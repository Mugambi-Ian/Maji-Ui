package com.nenecorp.majiapp.Interface.AccountWizard;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Dialogs.NotificationDialog;

public class AccountMissing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_missing);
        findViewById(R.id.AAM_btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationDialog z = new NotificationDialog(AccountMissing.this, R.style.DialogTheme);
                z.setListener(new NotificationDialog.DialogListener() {
                    @Override
                    public void onDismiss() {
                        finish();
                    }
                });
            }
        });
    }
}
