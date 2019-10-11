package com.nenecorp.majiapp.Interface.MajiUi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.DataModels.Request;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Dialogs.ConfirmationDialog;
import com.nenecorp.majiapp.Utility.Dialogs.SelectListItemsDialog;
import com.nenecorp.majiapp.Utility.NeneViews.QuantityBar;
import com.nenecorp.majiapp.Utility.Resources.Animations;
import com.nenecorp.majiapp.Utility.Resources.Drawables;

import java.util.ArrayList;

public class BowserServices extends AppCompatActivity {
    View layoutRequests, layoutNewBowser;
    Animations animations;
    ArrayList<Request> requests;
    RequestAdapter requestAdapter;
    private SelectListItemsDialog listItemsDialog;
    private static final String D_WP = "Your water provider";


    @Override
    public void onBackPressed() {
        boolean x = layoutRequests.getVisibility() == View.VISIBLE;
        if (x) {
            layoutRequests.setVisibility(View.VISIBLE);
            layoutNewBowser.setVisibility(View.GONE);
            layoutRequests.startAnimation(animations.fadeIn);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowser_services);
        requests = new ArrayList<>();
        requestAdapter = new RequestAdapter(this, R.layout.activity_bowser_services, requests);
        ((ListView) findViewById(R.id.ABS_rList)).setAdapter(requestAdapter);
        layoutRequests = findViewById(R.id.ABS_layoutRequests);
        layoutNewBowser = findViewById(R.id.ABS_layoutNewBowser);
        animations = new Animations(this);
        findViewById(R.id.ABS_btnNB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutNewBowser.setVisibility(View.VISIBLE);
                layoutNewBowser.startAnimation(animations.fadeIn);
                layoutRequests.setVisibility(View.GONE);
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
        final TextView wP = findViewById(R.id.LNB_waterProvider);
        final TextView billTextView = findViewById(R.id.LNB_price);
        final TextView qtyTextView = findViewById(R.id.LNB_qty);
        final TextView contact = findViewById(R.id.LNB_Contact);
        final QuantityBar quantityBar = findViewById(R.id.LNB_qBar);
        final EditText location = findViewById(R.id.LNB_location);
        quantityBar.setOutPutListener(new QuantityBar.OutPutListener() {
            @Override
            public void currentQuantity(int q) {
                billTextView.setText((q * 1500) + " KES");
                qtyTextView.setText("" + q);
            }
        });
        findViewById(R.id.LNB_btnWP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getText().length() == 0) {
                    contact.setError("Fill Field.");
                } else if (wP.getText().toString().equals(D_WP)) {
                    Toast.makeText(BowserServices.this, "Select your water provider", Toast.LENGTH_SHORT).show();
                } else if (location.getText().length() == 0) {
                    location.setError("Fill Field.");
                } else {
                    Request x = new Request(location.getText().toString());
                    x.setPrice(quantityBar.getQty() * 1250);
                    x.setQuantity(quantityBar.getQty());
                    x.setStatus(Request.P);
                    x.setLocation(location.getText().toString());
                    x.setDate("11:41 am 11/04/2019");
                    requests.add(x);
                    x.setStatus(Request.T);
                    requests.add(x);
                }
            }
        });
        findViewById(R.id.LNB_btnProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemsDialog = new SelectListItemsDialog(BowserServices.this, R.style.DialogTheme, wpList);
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

    private class RequestAdapter extends ArrayAdapter<Request> {
        Drawables drawables;

        public RequestAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Request> objects) {
            super(context, resource, objects);
            drawables = new Drawables(context);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
            View parentView = convertView;
            if (parentView == null) {
                parentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_request, parent, false);
            }
            Request x = getItem(position);
            parentView.findViewById(R.id.LIBR_btnCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
                }
            });
            ((TextView) parentView.findViewById(R.id.LIBR_name)).setText(x.getLocation());
            ((TextView) parentView.findViewById(R.id.LIBR_qty)).setText(x.getQuantity() + " bowsers");
            ((TextView) parentView.findViewById(R.id.LIBR_price)).setText("KES " + x.getPrice());
            ((TextView) parentView.findViewById(R.id.LIBR_date)).setText(x.getDate());
            TextView btnPayNow = parentView.findViewById(R.id.LIBR_btnPayNow);
            if (x.getStatus().equals(Request.T)) {
                btnPayNow.setBackground(drawables.btnBlue);
                btnPayNow.setEnabled(true);
            }
            if (x.getStatus().equals(Request.P)) {
                btnPayNow.setBackground(drawables.btnGrey);
                btnPayNow.setEnabled(false);
            }

            return parentView;
        }
    }

    private void removeItem(final int position) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(this, R.style.DialogTheme);
        confirmationDialog.setListener(new ConfirmationDialog.DialogListener() {
            @Override
            public void onDismiss(boolean state) {
                if (state) {
                    requests.remove(position);
                    requestAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}
