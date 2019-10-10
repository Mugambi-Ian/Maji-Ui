package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.nenecorp.majiapp.DataModels.Notification;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Adapters.NotificationAdapter;

import java.util.ArrayList;


public class Notifications extends Fragment {
    private ArrayList<Notification> notifications;
    private NotificationAdapter adapter;
    private ListView listView;
    private Home x;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        x = ((Home) getActivity());
        View parentView = inflater.inflate(R.layout.fragment_notifications, container, false);
        parentView.findViewById(R.id.FN_homeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.goHome();
            }
        });
        notifications = x.notificationsList;
        adapter = new NotificationAdapter(getContext(), R.layout.list_item_notification, notifications);
        listView = parentView.findViewById(R.id.FN_listN);
        listView.setAdapter(adapter);
        parentView.findViewById(R.id.FN_btnPayments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListView(Notification.P);
            }
        });
        parentView.findViewById(R.id.FN_btnOthers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListView(Notification.O);
            }
        });
        parentView.findViewById(R.id.FN_btnBnS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListView(Notification.BnS);
            }
        });
        return parentView;
    }

    private void sortListView(String category) {
        notifications = x.notificationsList;
        ArrayList<Notification> z = new ArrayList<>();
        for (Notification n : notifications) {
            if (n.getType().equals(category)) {
                z.add(n);
            }
        }
        notifications = z;
        adapter = new NotificationAdapter(getContext(), R.layout.fragment_notifications, notifications);
        listView.setAdapter(adapter);
    }

}
