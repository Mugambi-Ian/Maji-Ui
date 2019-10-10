package com.nenecorp.majiapp.Utility.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nenecorp.majiapp.R;

public class NotificationDialog extends Dialog {
    public interface DialogListener {
        void onDismiss();
    }

    public void setTitle(String text) {
        ((TextView) findViewById(R.id.DD_text)).setText(text);
    }

    private DialogListener listener;

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    public NotificationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_done);
        show();
        findViewById(R.id.DD_btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDismiss();
                }
                dismiss();
            }
        });
    }
}
