package com.nenecorp.majiapp.Interface.MajiUi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.DataModels.Request;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Dialogs.SelectListItemsDialog;
import com.nenecorp.majiapp.Utility.NeneViews.QuantityBar;
import com.nenecorp.majiapp.Utility.Resources.DataChannel;

import java.util.ArrayList;

public class ExhausterServices extends AppCompatActivity {

    private static final String D_WP = "Your water provider";
    private SelectListItemsDialog listItemsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhauster_services);
        findViewById(R.id.AES_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final ArrayList<String> wpList = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            String x = "" + ch;
            x = x.toUpperCase();
            wpList.add(x + " River Water");
            wpList.add(x + " Borehole Water");
            wpList.add(x + " Water Company");
        }
        final TextView wP = findViewById(R.id.AES_waterProvider);
        final TextView billTextView = findViewById(R.id.AES_price);
        final TextView qtyTextView = findViewById(R.id.AES_qty);
        final TextView contact = findViewById(R.id.AES_Contact);
        final QuantityBar quantityBar = findViewById(R.id.AES_qBar);
        final EditText location = findViewById(R.id.AES_location);
        quantityBar.setOutPutListener(new QuantityBar.OutPutListener() {
            @Override
            public void currentQuantity(int q) {
                billTextView.setText((q * 1500) + " KES");
                qtyTextView.setText("" + q);
            }
        });
        findViewById(R.id.AES_btnProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getText().length() == 0) {
                    contact.setError("Fill Field.");
                } else if (wP.getText().toString().equals(D_WP)) {
                    Toast.makeText(ExhausterServices.this, "Select your water provider", Toast.LENGTH_SHORT).show();
                } else if (location.getText().length() == 0) {
                    location.setError("Fill Field.");
                } else {
                    Request x = new Request(location.getText().toString());
                    x.setPrice(quantityBar.getQty() * 1250);
                    x.setQuantity(quantityBar.getQty() + " Exhausters");
                    x.setStatus(Request.T);
                    x.setLocation(location.getText().toString());
                    x.setDate("11:41 am 11/04/2019");
                    DataChannel.exRequest = x;
                    startActivity(new Intent(ExhausterServices.this, BowserServices.class));
                    finish();
                }
            }
        });
        findViewById(R.id.AES_btnWP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemsDialog = new SelectListItemsDialog(ExhausterServices.this, R.style.DialogTheme, wpList);
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

    }
}
