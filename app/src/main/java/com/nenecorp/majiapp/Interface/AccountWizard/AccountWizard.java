package com.nenecorp.majiapp.Interface.AccountWizard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.DataModels.Account;
import com.nenecorp.majiapp.Interface.MajiUi.Home;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Adapters.AccountAdapter;
import com.nenecorp.majiapp.Utility.Dialogs.SelectListItemsDialog;

import java.util.ArrayList;

public class AccountWizard extends AppCompatActivity {
    private static final String D_WP = "Your water provider";
    ArrayList<Account> accounts;
    SelectListItemsDialog listItemsDialog;
    ListView accountsListView;
    private AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_wizard);
        accounts = new ArrayList<>();
        final EditText actNo = findViewById(R.id.AAW_actNumber);
        final TextView wP = findViewById(R.id.AAW_waterProvider);
        accountsListView = findViewById(R.id.AAW_lstAcct);
        adapter = new AccountAdapter(this, R.layout.activity_account_wizard, accounts);
        accountsListView.setAdapter(adapter);
        adapter.setListener(new AccountAdapter.OnClickListener() {
            @Override
            public void deleteItem(int position) {
                accounts.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.AAW_btnAddAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actNo.getText().length() == 0) {
                    actNo.setError("Fill Value");
                } else if (wP.getText().toString().equals(D_WP)) {
                    Toast.makeText(AccountWizard.this, "Select your water provider", Toast.LENGTH_LONG).show();
                } else {
                    accounts.add(new Account(actNo.getText().toString(), wP.getText().toString()));
                    adapter.notifyDataSetChanged();
                    wP.setText(D_WP);
                    actNo.setText("");
                }
            }
        });
        final ArrayList<String> wpList = new ArrayList<>();
        char ch;
        for (ch = 'a'; ch <= 'z'; ch++) {
            String x = "" + ch;
            x = x.toUpperCase();
            wpList.add(x + " River Water");
            wpList.add(x + " Borehole Water");
            wpList.add(x + " Water Company");
        }
        findViewById(R.id.AAW_btnWP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemsDialog = new SelectListItemsDialog(AccountWizard.this, R.style.DialogTheme, wpList);
                listItemsDialog.setDialogResultListener(new SelectListItemsDialog.DialogResultListener() {
                    @Override
                    public void resultItemId(int id) {
                        if (id != -1) {
                            wP.setText(wpList.get(id));
                        }
                    }
                });
            }
        });
        findViewById(R.id.AAW_btnSkip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountWizard.this, Home.class));
                finish();
            }
        });
        findViewById(R.id.AAW_btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountWizard.this, Home.class));
            }
        });
        accountsListener();
    }

    private void accountsListener() {
        final Handler handler = new Handler();
        final int delay = 5;
        handler.postDelayed(new Runnable() {
            public void run() {
                if (accounts.size() == 0) {
                    if (accountsListView.getVisibility() == View.VISIBLE) {
                        accountsListView.setVisibility(View.GONE);
                        findViewById(R.id.AAW_btnDone).setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (accountsListView.getVisibility() != View.VISIBLE) {
                        accountsListView.setVisibility(View.VISIBLE);
                        findViewById(R.id.AAW_btnDone).setVisibility(View.VISIBLE);
                    }
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

}
