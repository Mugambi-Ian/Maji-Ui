package com.nenecorp.majiapp.Utility.NeneViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nenecorp.majiapp.R;

public class QuantityBar extends LinearLayout {
    private int qty;
    private TextView qtyTextView;
    private OutPutListener outPutListener;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setOutPutListener(OutPutListener outPutListener) {
        this.outPutListener = outPutListener;
    }

    public interface OutPutListener {
        void currentQuantity(int q);
    }

    public QuantityBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        qty = 1;
        LayoutInflater.from(context).inflate(R.layout.view_quantity_bar, this);
        qtyTextView = findViewById(R.id.VQB_q);
        findViewById(R.id.VQB_btnM).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty != 1) {
                    qty--;
                }
                qtyTextView.setText("" + qty);
                if (outPutListener != null) {
                    outPutListener.currentQuantity(qty);
                }
            }
        });
        findViewById(R.id.VQB_btnA).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                qtyTextView.setText("" + qty);
                if (outPutListener != null) {
                    outPutListener.currentQuantity(qty);
                }
            }
        });
    }
}
