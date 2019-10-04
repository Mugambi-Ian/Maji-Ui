package com.nenecorp.majiapp.Utility.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nenecorp.majiapp.DataModels.Account;
import com.nenecorp.majiapp.R;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account> {
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void deleteItem(int position);
    }

    public AccountAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Account> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View parentView = convertView;
        if (parentView == null) {
            parentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_result, parent, false);
        }
        Account account = getItem(position);
        ((TextView) parentView.findViewById(R.id.LIR_acctNo)).setText(account.getAccountNumber());
        ((TextView) parentView.findViewById(R.id.LIR_wp)).setText(account.getWaterProvider());
        parentView.findViewById(R.id.LIR_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.deleteItem(position);
                }
            }
        });
        return parentView;
    }
}
