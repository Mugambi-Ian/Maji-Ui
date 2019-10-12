package com.nenecorp.majiapp.Interface.MajiUi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nenecorp.majiapp.DataModels.Account;
import com.nenecorp.majiapp.DataModels.Notification;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.NeneViews.NavigationBar;
import com.nenecorp.majiapp.Utility.Resources.Animations;
import com.nenecorp.majiapp.Utility.Resources.DataChannel;
import com.nenecorp.majiapp.Utility.Resources.Drawables;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ArrayList<Account> accountsList;
    ArrayList<String> wpList;
    ArrayList<Notification> notificationsList;
    NavigationBar bottomNavigationView;
    RelativeLayout drawerLayout;
    Animations animations;
    Drawables drawables;
    LinearLayout drawer;
    Accounts accounts;
    Profile profile;
    Notifications notifications;
    FragmentManager fragmentManager;
    View homeView, containerView;
    NavigationBar.SelectedItemListener selectedItemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DataChannel.home = this;
        wpList = new ArrayList<>();
        accountsList = new ArrayList<>();
        notificationsList = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            notificationsList.add(new Notification(Notification.P, "Payment received from james " + x + x, "0" + "x" + "/11/12", null));
        }
        for (int x = 0; x < 3; x++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int z = 0; z < 3; z++) {
                strings.add("Test " + z + x + z);
            }
            notificationsList.add(new Notification(Notification.O, "Testing other n" + x + x, "10/11/12", strings));
        }
        for (int x = 0; x < 3; x++) {
            notificationsList.add(new Notification(Notification.BnS, "Paid by James " + x + x, "10/11/12", null));
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            String x = "" + ch;
            x = x.toUpperCase();
            wpList.add(x + " River Water");
            wpList.add(x + " Borehole Water");
            wpList.add(x + " Water Company");
        }
        bottomNavigationView = findViewById(R.id.AH_bnv);
        animations = new Animations(this);
        drawables = new Drawables(this);
        drawerLayout = findViewById(R.id.AH_drawer);
        drawer = findViewById(R.id.DH_drawer);
        fragmentManager = getSupportFragmentManager();
        homeView = findViewById(R.id.AH_layoutHome);
        containerView = findViewById(R.id.AH_container);
        findViewById(R.id.LH_btnDrawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.getVisibility() != View.VISIBLE) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.overlay);
                    drawerLayout.setVisibility(View.VISIBLE);
                    drawer.startAnimation(animations.slideFromRight);
                }
            }
        });
        findViewById(R.id.DH_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.getVisibility() == View.VISIBLE) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.startAnimation(animations.slideRight);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        });
        selectedItemListener = new NavigationBar.SelectedItemListener() {
            @Override
            public void itemPosition(int id) {
                switch (id) {
                    case 0:
                        goHome();
                        break;
                    case 1:
                        closeFragments();
                        accounts = new Accounts();
                        openFragment(accounts);
                        break;
                    case 2:
                        closeFragments();
                        notifications = new Notifications();
                        openFragment(notifications);
                        break;
                    case 3:
                        closeFragments();
                        profile = new Profile();
                        openFragment(profile);
                        break;
                }
            }

            private void closeFragments() {
                accounts = null;
                notifications = null;
                profile = null;
            }
        };
        bottomNavigationView.setSelectedItemListener(selectedItemListener);
        View.OnClickListener lipaMaji = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, LipaMaji.class));
                if (v.getId() == R.id.LDI_btnLipaMaji) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardLipaMaji).setOnClickListener(lipaMaji);
        findViewById(R.id.LH_btnLipaMaji).setOnClickListener(lipaMaji);
        findViewById(R.id.LDI_btnLipaMaji).setOnClickListener(lipaMaji);
        View.OnClickListener jisomeeMeter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, JisomeeMeter.class));
                if (v.getId() == R.id.LDI_btnJisomeeMeter) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_btnJisomeeMeter).setOnClickListener(jisomeeMeter);
        findViewById(R.id.LH_cardJisomeeMeter).setOnClickListener(jisomeeMeter);
        findViewById(R.id.LDI_btnJisomeeMeter).setOnClickListener(jisomeeMeter);
        View.OnClickListener buyWaterTokens = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, BuyWaterTokens.class));
                if (v.getId() == R.id.LDI_btnBuyWaterTokens) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardBuyWaterTokens).setOnClickListener(buyWaterTokens);
        findViewById(R.id.LDI_btnBuyWaterTokens).setOnClickListener(buyWaterTokens);
        View.OnClickListener bowserServices = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, BowserServices.class));
                if (v.getId() == R.id.LDI_btnBowserServices) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardBowserServices).setOnClickListener(bowserServices);
        findViewById(R.id.LDI_btnBowserServices).setOnClickListener(bowserServices);
        View.OnClickListener exhausterServices = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ExhausterServices.class));
                if (v.getId() == R.id.LDI_btnExhausterServices) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardExhausterServices).setOnClickListener(exhausterServices);
        findViewById(R.id.LDI_btnExhausterServices).setOnClickListener(exhausterServices);
        View.OnClickListener billStatement = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, BillStatement.class));
                if (v.getId() == R.id.LDI_btnBillStatement) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardBillStatement).setOnClickListener(billStatement);
        findViewById(R.id.LDI_btnBillStatement).setOnClickListener(billStatement);
        View.OnClickListener reportIncident = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ReportIncident.class));
                if (v.getId() == R.id.LDI_btnReportIncident) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardReportIncident).setOnClickListener(reportIncident);
        findViewById(R.id.LDI_btnReportIncident).setOnClickListener(reportIncident);
        View.OnClickListener waterUsage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, WaterUsage.class));
                if (v.getId() == R.id.LDI_btnWaterUsage) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardWaterUsage).setOnClickListener(waterUsage);
        findViewById(R.id.LDI_btnWaterUsage).setOnClickListener(waterUsage);
        View.OnClickListener otherServices = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, OtherServices.class));
                if (v.getId() == R.id.LDI_btnOtherServices) {
                    findViewById(R.id.DH_backGround).setBackground(drawables.emptyBg);
                    drawerLayout.setVisibility(View.GONE);
                }
            }
        };
        findViewById(R.id.LH_cardOtherServices).setOnClickListener(otherServices);
        findViewById(R.id.LDI_btnOtherServices).setOnClickListener(otherServices);
    }

    public void goHome() {
        if (homeView.getVisibility() != View.VISIBLE) {
            accounts = null;
            profile = null;
            notifications = null;
            homeView.setVisibility(View.VISIBLE);
            homeView.startAnimation(animations.fadeIn);
            containerView.setVisibility(View.GONE);
            bottomNavigationView.setCurrentItem(0);
        }
    }

    private void openFragment(Fragment x) {
        FragmentTransaction z = fragmentManager.beginTransaction();
        z.replace(R.id.AH_container, x);
        z.commitAllowingStateLoss();
        if (homeView.getVisibility() == View.VISIBLE) {
            homeView.setVisibility(View.GONE);
        }
        containerView.setVisibility(View.VISIBLE);
        containerView.startAnimation(animations.fadeIn);
    }

}
