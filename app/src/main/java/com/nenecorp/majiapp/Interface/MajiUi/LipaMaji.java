package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Dialogs.NotificationDialog;
import com.nenecorp.majiapp.Utility.Resources.Animations;

public class LipaMaji extends AppCompatActivity {
    Animations animations;
    View currentView, layoutOptions, layoutCard, layoutBank, layoutMpesa;

    @Override
    public void onBackPressed() {
        boolean c = currentView == layoutCard || currentView == layoutBank;
        if (c) {
            layoutOptions.setVisibility(View.VISIBLE);
            layoutBank.setVisibility(View.GONE);
            layoutCard.setVisibility(View.GONE);
            currentView = layoutOptions;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipa_maji);
        layoutOptions = findViewById(R.id.ALM_layoutOptions);
        layoutCard = findViewById(R.id.ALM_layoutCard);
        layoutBank = findViewById(R.id.ALM_layoutBank);
        layoutMpesa = findViewById(R.id.ALM_layoutMpesa);
        currentView = layoutOptions;
        animations = new Animations(this);
        findViewById(R.id.LM_txtMpesa).setVisibility(View.INVISIBLE);
        findViewById(R.id.ALM_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.ALM_btnSummary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = findViewById(R.id.ALM_layoutSummary);
                if (layout.getVisibility() != View.VISIBLE) {
                    layout.setVisibility(View.VISIBLE);
                    layout.startAnimation(animations.fadeIn);
                } else {
                    layout.setVisibility(View.GONE);
                }
            }
        });
        findViewById(R.id.ALM_btnMpesa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBank.setVisibility(View.GONE);
                layoutOptions.setVisibility(View.GONE);
                layoutMpesa.setVisibility(View.VISIBLE);
                layoutCard.setVisibility(View.GONE);
                layoutMpesa.startAnimation(animations.fadeIn);
                currentView = layoutMpesa;
            }
        });
        findViewById(R.id.ALM_btnCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBank.setVisibility(View.GONE);
                layoutOptions.setVisibility(View.GONE);
                layoutMpesa.setVisibility(View.GONE);
                layoutCard.setVisibility(View.VISIBLE);
                layoutCard.startAnimation(animations.fadeIn);
                currentView = layoutCard;
            }
        });
        findViewById(R.id.ALM_btnBank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBank.setVisibility(View.VISIBLE);
                layoutOptions.setVisibility(View.GONE);
                layoutCard.setVisibility(View.GONE);
                layoutMpesa.setVisibility(View.GONE);
                layoutBank.startAnimation(animations.fadeIn);
                currentView = layoutBank;
            }
        });
        findViewById(R.id.ALM_btnPayment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean m, c, b;
                m = layoutMpesa.getVisibility() == View.VISIBLE;
                c = layoutCard.getVisibility() == View.VISIBLE;
                b = layoutBank.getVisibility() == View.VISIBLE;
                View layout = findViewById(R.id.ALM_layoutSummary);
                if (layout.getVisibility() != View.VISIBLE && layoutMpesa.getVisibility() != View.VISIBLE) {
                    layout.setVisibility(View.VISIBLE);
                    layout.startAnimation(animations.fadeIn);
                } else if (b || c || m) {
                    NotificationDialog dialog = new NotificationDialog(LipaMaji.this, R.style.DialogTheme);
                    dialog.setTitle("Your payment has been received");
                    dialog.setListener(new NotificationDialog.DialogListener() {
                        @Override
                        public void onDismiss() {
                            finish();
                        }
                    });
                } else {
                    layout.setVisibility(View.GONE);
                    layoutBank.setVisibility(View.GONE);
                    layoutOptions.setVisibility(View.GONE);
                    layoutMpesa.setVisibility(View.VISIBLE);
                    layoutCard.setVisibility(View.GONE);
                    layoutMpesa.startAnimation(animations.fadeIn);
                    currentView = layoutMpesa;

                }
            }
        });
    }

    private void completePayment() {
    }
}
