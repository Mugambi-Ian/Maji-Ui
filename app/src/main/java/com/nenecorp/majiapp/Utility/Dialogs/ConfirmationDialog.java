package com.nenecorp.majiapp.Utility.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.nenecorp.majiapp.R;

public class ConfirmationDialog extends Dialog {
    public interface DialogListener {
        void onDismiss(boolean state);
    }


    private DialogListener listener;

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    public ConfirmationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_confirm);
        show();
        findViewById(R.id.DC_btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDismiss(false);
                }
                dismiss();
            }
        });
        findViewById(R.id.DC_btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDismiss(true);
                }
                dismiss();
            }
        });
    }
}
