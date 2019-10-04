package com.nenecorp.majiapp.Utility.NeneViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.nenecorp.majiapp.R;

import java.util.ArrayList;

public class NavigationBar extends LinearLayout {
    private int current = 0;
    private SelectedItemListener selectedItemListener;
    ArrayList<BarItem> barItems = new ArrayList<>();

    public void setSelectedItemListener(SelectedItemListener selectedItemListener) {
        this.selectedItemListener = selectedItemListener;
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_navigation_bar, this);
        barItems.add(new BarItem(findViewById(R.id.NB_homeTxt), findViewById(R.id.NB_home)));
        barItems.add(new BarItem(findViewById(R.id.NB_accountsTxt), findViewById(R.id.NB_accounts)));
        barItems.add(new BarItem(findViewById(R.id.NB_notificationsTxt), findViewById(R.id.NB_notifications)));
        barItems.add(new BarItem(findViewById(R.id.NB_profileTxt), findViewById(R.id.NB_profile)));
        for (final BarItem x : barItems) {
            x.field.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (current != barItems.indexOf(x)) {
                        closeViews();
                        x.title.setVisibility(VISIBLE);
                        current = barItems.indexOf(x);
                        x.field.setEnabled(false);
                        if (selectedItemListener != null) {
                            selectedItemListener.itemPosition(barItems.indexOf(x));
                        }
                    }
                }
            });
        }
    }

    private void closeViews() {
        for (BarItem c : barItems) {
            c.title.setVisibility(GONE);
            c.field.setEnabled(true);
        }
    }

    public void setCurrentItem(int i) {
        BarItem x = barItems.get(i);
        closeViews();
        x.title.setVisibility(VISIBLE);
        current = barItems.indexOf(x);
        x.field.setEnabled(false);
        if (selectedItemListener != null) {
            selectedItemListener.itemPosition(barItems.indexOf(x));
        }
    }

    private class BarItem {
        View title;
        View field;

        BarItem(View title, View field) {
            this.title = title;
            this.field = field;
        }
    }

    public interface SelectedItemListener {
        void itemPosition(int id);
    }
}

