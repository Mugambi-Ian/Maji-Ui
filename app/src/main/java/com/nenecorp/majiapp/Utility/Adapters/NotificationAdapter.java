package com.nenecorp.majiapp.Utility.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.nenecorp.majiapp.DataModels.Notification;
import com.nenecorp.majiapp.R;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    public NotificationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notification> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View parentView = convertView;
        if (parentView == null) {
            parentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_notification, parent, false);
        }
        Notification x = getItem(position);
        switch (x.getType()) {
            case Notification.P:
                parentView.findViewById(R.id.LIN_type).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightBlue));
                break;
            case Notification.O:
                parentView.findViewById(R.id.LIN_type).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow));
                break;
            case Notification.BnS:
                parentView.findViewById(R.id.LIN_type).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
                break;
        }
        ((TextView) parentView.findViewById(R.id.LIN_textView)).setText(x.getText());
        ((TextView) parentView.findViewById(R.id.LIN_date)).setText(x.getDate());
        if (x.getList() != null) {
            if (x.getList().size() != 0) {
                parentView.findViewById(R.id.LIN_listView).setVisibility(View.VISIBLE);
                ((ListView) parentView.findViewById(R.id.LIN_listView)).setAdapter(new ListAdapter(getContext(), R.layout.list_item_notification, x.getList()));
            }
        } else {
            parentView.findViewById(R.id.LIN_listView).setVisibility(View.GONE);
        }
        return parentView;
    }

    private class ListAdapter extends ArrayAdapter<String> {

        public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View parentView = convertView;
            if (parentView == null) {
                parentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_n_string, parent, false);
            }
            ((TextView) parentView.findViewById(R.id.LINS_text)).setText(getItem(position));
            return parentView;
        }
    }
}
