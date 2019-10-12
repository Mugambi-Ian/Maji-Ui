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
import com.nenecorp.majiapp.Utility.Dialogs.NotificationDialog;
import com.nenecorp.majiapp.Utility.Dialogs.SelectListItemsDialog;
import com.nenecorp.majiapp.Utility.NeneViews.QuantityBar;
import com.nenecorp.majiapp.Utility.Resources.Animations;
import com.nenecorp.majiapp.Utility.Resources.DataChannel;
import com.nenecorp.majiapp.Utility.Resources.Drawables;

import java.util.ArrayList;

public class BowserServices extends AppCompatActivity {
    View layoutRequests, layoutNewBowser, layoutPayments, layoutOptions, layoutMpesa, layoutCard;
    Animations animations;
    ArrayList<Request> requests;
    RequestAdapter requestAdapter;
    Request currentRequest;
    TextView paymentBtn;
    private SelectListItemsDialog listItemsDialog;
    private static final String D_WP = "Your water provider";


    @Override
    public void onBackPressed() {
        boolean x = layoutRequests.getVisibility() == View.VISIBLE;
        boolean p = layoutPayments.getVisibility() == View.VISIBLE;
        boolean m = layoutMpesa.getVisibility() == View.VISIBLE,
                c = layoutCard.getVisibility() == View.VISIBLE,
                o = layoutOptions.getVisibility() == View.VISIBLE;
        if (p) {
            if (m || c) {
                layoutMpesa.setVisibility(View.GONE);
                layoutCard.setVisibility(View.GONE);
                layoutOptions.startAnimation(animations.fadeIn);
                layoutOptions.setVisibility(View.VISIBLE);
                paymentBtn.setText("Proceed to payment");
            } else if (o) {
                layoutPayments.setVisibility(View.GONE);
                layoutRequests.setVisibility(View.VISIBLE);
                layoutNewBowser.setVisibility(View.GONE);
                layoutRequests.startAnimation(animations.fadeIn);
            }
        } else if (!x) {
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
        if (DataChannel.exRequest != null) {
            requests.add(DataChannel.exRequest);
            DataChannel.exRequest = null;
        }
        requestAdapter = new RequestAdapter(this, R.layout.activity_bowser_services, requests);
        ((ListView) findViewById(R.id.ABS_rList)).setAdapter(requestAdapter);
        layoutRequests = findViewById(R.id.ABS_layoutRequests);
        layoutNewBowser = findViewById(R.id.ABS_layoutNewBowser);
        layoutPayments = findViewById(R.id.ABS_layoutPayment);
        layoutOptions = findViewById(R.id.LBP_layoutOptions);
        layoutMpesa = findViewById(R.id.LBP_layoutMpesa);
        layoutCard = findViewById(R.id.LBP_layoutCard);
        paymentBtn = findViewById(R.id.LBP_btnNext);
        animations = new Animations(this);
        findViewById(R.id.ABS_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        findViewById(R.id.LNB_btnProceed).setOnClickListener(new View.OnClickListener() {
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
                    x.setQuantity(quantityBar.getQty() + " Bowsers");
                    x.setStatus(Request.T);
                    x.setLocation(location.getText().toString());
                    x.setDate("11:41 am 11/04/2019");
                    requests.add(x);
                    requestAdapter.notifyDataSetChanged();
                    layoutNewBowser.setVisibility(View.GONE);
                    layoutRequests.setVisibility(View.VISIBLE);
                    layoutRequests.startAnimation(animations.fadeIn);
                }
            }
        });
        findViewById(R.id.LNB_btnWP).setOnClickListener(new View.OnClickListener() {
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
            final Request x = getItem(position);
            parentView.findViewById(R.id.LIBR_btnCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
                }
            });
            ((TextView) parentView.findViewById(R.id.LIBR_name)).setText(x.getLocation());
            ((TextView) parentView.findViewById(R.id.LIBR_qty)).setText(x.getQuantity());
            ((TextView) parentView.findViewById(R.id.LIBR_price)).setText("KES " + x.getPrice());
            ((TextView) parentView.findViewById(R.id.LIBR_date)).setText(x.getDate());
            TextView btnPayNow = parentView.findViewById(R.id.LIBR_btnPayNow);
            TextView status = parentView.findViewById(R.id.LIBR_status);
            status.setText(x.getStatus());
            if (x.getStatus().equals(Request.T)) {
                btnPayNow.setBackground(drawables.btnBlue);
                btnPayNow.setEnabled(true);
                status.setBackground(drawables.btnYellow);
            } else if (x.getStatus().equals(Request.P)) {
                btnPayNow.setBackground(drawables.btnGrey);
                btnPayNow.setEnabled(false);
            } else {
                btnPayNow.setBackground(drawables.btnGrey);
                btnPayNow.setEnabled(false);
                status.setBackground(drawables.btnGrey);
            }
            btnPayNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentRequest = x;
                    payForRequest();
                }
            });
            return parentView;
        }
    }

    private void payForRequest() {
        paymentBtn.setText("Proceed to payment");
        layoutRequests.setVisibility(View.GONE);
        layoutNewBowser.setVisibility(View.GONE);
        layoutCard.setVisibility(View.GONE);
        layoutMpesa.setVisibility(View.GONE);
        layoutPayments.setVisibility(View.VISIBLE);
        layoutOptions.setVisibility(View.VISIBLE);
        layoutPayments.startAnimation(animations.fadeIn);
        findViewById(R.id.LPO_btnMpesa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payViaMpesa();
            }
        });
        findViewById(R.id.LPO_btnCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payViaCard();
            }
        });
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentBtn.getText().toString().equals("Proceed to payment")) {
                    payViaMpesa();
                } else if (paymentBtn.getText().toString().equals("Pay now")) {
                    NotificationDialog dialog = new NotificationDialog(BowserServices.this, R.style.DialogTheme);
                    dialog.setListener(new NotificationDialog.DialogListener() {
                        @Override
                        public void onDismiss() {
                            finish();
                        }
                    });
                    dialog.setTitle("Your payment has been received");
                }
            }
        });
        ((TextView) findViewById(R.id.LMB_price)).setText("KES " + currentRequest.getPrice());
        ((TextView) findViewById(R.id.LMB_qty)).setText(currentRequest.getQuantity());
        ((TextView) findViewById(R.id.LCB_price)).setText("KES " + currentRequest.getPrice());
        ((TextView) findViewById(R.id.LCB_qty)).setText(currentRequest.getQuantity());
    }

    private void payViaCard() {
        layoutOptions.setVisibility(View.GONE);
        layoutCard.startAnimation(animations.fadeIn);
        layoutMpesa.setVisibility(View.GONE);
        layoutCard.setVisibility(View.VISIBLE);
        paymentBtn.setText("Pay now");
    }

    private void payViaMpesa() {
        layoutOptions.setVisibility(View.GONE);
        layoutMpesa.startAnimation(animations.fadeIn);
        layoutCard.setVisibility(View.GONE);
        layoutMpesa.setVisibility(View.VISIBLE);
        paymentBtn.setText("Pay now");

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
