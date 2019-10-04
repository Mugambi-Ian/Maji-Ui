package com.nenecorp.majiapp.Utility.NeneViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.nenecorp.majiapp.R;

public class CheckBox extends RelativeLayout {
    boolean checked = false;

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_check_box, this);
        findViewById(R.id.VCB_).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View b = findViewById(R.id.VCB_checked);
                if (b.getVisibility() == View.VISIBLE) {
                    checked = false;
                    b.setVisibility(GONE);
                } else {
                    checked = true;
                    b.setVisibility(VISIBLE);
                }
            }
        });
    }
}
