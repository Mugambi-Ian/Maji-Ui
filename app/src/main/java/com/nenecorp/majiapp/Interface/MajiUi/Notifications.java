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
    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Home x = ((Home) getActivity());
        parentView = inflater.inflate(R.layout.fragment_notifications, container, false);
        parentView.findViewById(R.id.FN_homeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.goHome();
            }
        });
        ArrayList<Notification> notifications = x.notificationsList;
        NotificationAdapter adapter = new NotificationAdapter(getContext(), R.layout.list_item_notification, notifications);
        ListView listView = parentView.findViewById(R.id.FN_listN);
        listView.setAdapter(adapter);
        return parentView;
    }

}
