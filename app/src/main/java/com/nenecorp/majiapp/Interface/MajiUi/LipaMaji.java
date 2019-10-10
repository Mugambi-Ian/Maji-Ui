package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Resources.Animations;

public class LipaMaji extends AppCompatActivity {
    Animations animations;
    View currentView, layoutMpesa, layoutCard, layoutBank;

    @Override
    public void onBackPressed() {
        boolean c = currentView == layoutCard || currentView == layoutBank;
        if (c) {
            layoutMpesa.setVisibility(View.VISIBLE);
            layoutBank.setVisibility(View.GONE);
            layoutCard.setVisibility(View.GONE);
            currentView = layoutMpesa;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipa_maji);
        layoutMpesa = findViewById(R.id.ALM_layoutMpesa);
        layoutCard = findViewById(R.id.ALM_layoutCard);
        layoutBank = findViewById(R.id.ALM_layoutBank);
        currentView = layoutMpesa;
        animations = new Animations(this);
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
        findViewById(R.id.ALM_btnCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBank.setVisibility(View.GONE);
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
                layoutMpesa.setVisibility(View.GONE);
                layoutCard.setVisibility(View.GONE);
                layoutBank.startAnimation(animations.fadeIn);
                currentView = layoutBank;
            }
        });
    }
}
