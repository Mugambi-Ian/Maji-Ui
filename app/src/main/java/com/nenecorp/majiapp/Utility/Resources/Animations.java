package com.nenecorp.majiapp.Utility.Resources;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nenecorp.majiapp.R;

public class Animations {
    public Animation fadeIn, fadeOut, slideLeft, slideRight, slideFromLeft, slideFromRight, slideFromBottom, slideFromTop, slideDown, slideUp, shake;
    public Context context;

    public Animations(Context context) {
        this.context = context;
        fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        slideLeft = AnimationUtils.loadAnimation(context, R.anim.slide_left);
        slideRight = AnimationUtils.loadAnimation(context, R.anim.slide_right);
        slideFromLeft = AnimationUtils.loadAnimation(context, R.anim.slide_from_left);
        slideFromRight = AnimationUtils.loadAnimation(context, R.anim.slide_from_right);
        slideFromBottom = AnimationUtils.loadAnimation(context, R.anim.slide_frombottom);
        slideFromTop = AnimationUtils.loadAnimation(context, R.anim.slide_fromtop);
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        shake = AnimationUtils.loadAnimation(context, R.anim.shake);
    }
}
