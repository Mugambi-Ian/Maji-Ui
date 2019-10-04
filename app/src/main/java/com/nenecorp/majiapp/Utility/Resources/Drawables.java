package com.nenecorp.majiapp.Utility.Resources;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.nenecorp.majiapp.R;


public class Drawables {
    public Drawable showPassword;
    public Drawable hidePassword;
    public Drawable emptyBg, overlay;


    public Drawables(Context context) {
        showPassword = ContextCompat.getDrawable(context, R.drawable.show_password);
        hidePassword = ContextCompat.getDrawable(context, R.drawable.hide_password);
        emptyBg = ContextCompat.getDrawable(context, R.drawable.empty);
        overlay = ContextCompat.getDrawable(context, R.drawable.overlay);
    }
}
