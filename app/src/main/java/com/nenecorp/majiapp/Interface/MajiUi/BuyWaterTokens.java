package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Dialogs.NotificationDialog;
import com.nenecorp.majiapp.Utility.Resources.Animations;

public class BuyWaterTokens extends AppCompatActivity {
    View layoutMpesa, layoutCard, layoutOptions;
    Animations animations;

    @Override
    public void onBackPressed() {
        boolean c = layoutCard.getVisibility() == View.VISIBLE || layoutMpesa.getVisibility() == View.VISIBLE;
        if (c) {
            layoutOptions.setVisibility(View.VISIBLE);
            layoutOptions.startAnimation(animations.fadeIn);
            layoutCard.setVisibility(View.GONE);
            layoutMpesa.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_water_tokens);
        animations = new Animations(this);
        layoutCard = findViewById(R.id.ABWT_layoutCard);
        layoutMpesa = findViewById(R.id.ABWT_layoutMpesa);
        layoutOptions = findViewById(R.id.ABWT_layoutOptions);
        findViewById(R.id.ABWT_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.ABWT_btnCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCard.setVisibility(View.VISIBLE);
                layoutMpesa.setVisibility(View.GONE);
                layoutOptions.setVisibility(View.GONE);
                layoutCard.startAnimation(animations.fadeIn);
            }
        });
        findViewById(R.id.ABWT_btnMpesa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCard.setVisibility(View.GONE);
                layoutOptions.setVisibility(View.GONE);
                layoutMpesa.setVisibility(View.VISIBLE);
                layoutMpesa.startAnimation(animations.fadeIn);
            }
        });
        findViewById(R.id.ABWT_btnPayNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationDialog dialog = new NotificationDialog(BuyWaterTokens.this, R.style.DialogTheme);
                dialog.setTitle("Your payment has been\nreceived");
                dialog.setListener(new NotificationDialog.DialogListener() {
                    @Override
                    public void onDismiss() {
                        finish();
                    }
                });
            }
        });
    }
}
