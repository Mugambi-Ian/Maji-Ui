package com.nenecorp.majiapp.Utility.Resources;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.nenecorp.majiapp.R;


public class Drawables {
    public Drawable showPassword;
    public Drawable hidePassword;
    public Drawable btnGrey, btnBlue, btnYellow;
    public Drawable emptyBg, overlay;


    public Drawables(Context context) {
        showPassword = ContextCompat.getDrawable(context, R.drawable.show_password);
        hidePassword = ContextCompat.getDrawable(context, R.drawable.hide_password);
        emptyBg = ContextCompat.getDrawable(context, R.drawable.empty);
        overlay = ContextCompat.getDrawable(context, R.drawable.overlay);
        btnGrey = ContextCompat.getDrawable(context, R.drawable.btn_grey_dark);
        btnBlue = ContextCompat.getDrawable(context, R.drawable.btn_blue_dark);
        btnYellow = ContextCompat.getDrawable(context,R.drawable.btn_yellow);
    }
}
