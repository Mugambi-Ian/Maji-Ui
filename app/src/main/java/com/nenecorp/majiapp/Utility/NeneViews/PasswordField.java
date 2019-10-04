package com.nenecorp.majiapp.Utility.NeneViews;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Resources.Drawables;

public class PasswordField extends LinearLayout {
    EditText field;
    LinearLayout btnSwitch;
    ImageView indicator;
    Drawables drawables;
    int state = 1;

    public PasswordField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_password_field, this);
        field = findViewById(R.id.VPF_field);
        field.setHint("Pin ****");
        btnSwitch = findViewById(R.id.VPF_switch);
        indicator = findViewById(R.id.VPF_indicator);
        drawables = new Drawables(context);
        field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        btnSwitch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 1) {
                    state = 0;
                    field.setInputType(InputType.TYPE_CLASS_TEXT);
                    indicator.setBackground(drawables.hidePassword);
                } else {
                    state = 1;
                    field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    indicator.setBackground(drawables.showPassword);
                }
            }
        });
    }

    public String getPassword() {
        return field.getText().toString();
    }

    public void setError(String error) {
        field.setError(error);
    }

    public void clear() {
        field.setText("");
    }
}
