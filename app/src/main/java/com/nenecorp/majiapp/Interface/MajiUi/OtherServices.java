package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Dialogs.NotificationDialog;
import com.nenecorp.majiapp.Utility.Resources.Animations;

public class OtherServices extends AppCompatActivity {

    private View layoutOptions, layoutCard, layoutMpesa, layoutWelcome, layoutPayment;
    TextView paymentBtn;
    Animations animations;

    @Override
    public void onBackPressed() {
        boolean p = layoutPayment.getVisibility() == View.VISIBLE,
                o = layoutOptions.getVisibility() == View.VISIBLE,
                m = layoutMpesa.getVisibility() == View.VISIBLE,
                c = layoutCard.getVisibility() == View.VISIBLE;
        if (p) {
            if (m || c) {
                layoutMpesa.setVisibility(View.GONE);
                layoutCard.setVisibility(View.GONE);
                layoutOptions.setVisibility(View.VISIBLE);
                layoutOptions.startAnimation(animations.fadeIn);
            } else if (o) {
                layoutPayment.setVisibility(View.GONE);
                layoutWelcome.setVisibility(View.VISIBLE);
                layoutWelcome.startAnimation(animations.fadeIn);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_services);
        animations = new Animations(this);
        layoutPayment = findViewById(R.id.AOS_layoutPOptions);
        layoutOptions = findViewById(R.id.LBP_layoutOptions);
        layoutWelcome = findViewById(R.id.AOS_layoutWelcome);
        layoutCard = findViewById(R.id.LBP_layoutCard);
        layoutMpesa = findViewById(R.id.LBP_layoutMpesa);
        paymentBtn  = findViewById(R.id.LBP_btnNext);
        findViewById(R.id.AOS_btnMtest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayment();
            }
        });
        findViewById(R.id.AOS_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void openPayment() {
        layoutWelcome.setVisibility(View.GONE);
        layoutPayment.setVisibility(View.VISIBLE);
        layoutPayment.startAnimation(animations.fadeIn);
        layoutOptions.setVisibility(View.VISIBLE);
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
                    NotificationDialog dialog = new NotificationDialog(OtherServices.this, R.style.DialogTheme);
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
        ((TextView) findViewById(R.id.LMB_price)).setText("KES  250");
        findViewById(R.id.LMB_qty).setVisibility(View.INVISIBLE);
        findViewById(R.id.LMB_qtyT).setVisibility(View.INVISIBLE);
        findViewById(R.id.LCB_qty).setVisibility(View.INVISIBLE);
        findViewById(R.id.LCB_qtyT).setVisibility(View.INVISIBLE);
        ((TextView) findViewById(R.id.LCB_price)).setText("KES 250");
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
}
