package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.nenecorp.majiapp.DataModels.Account;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Adapters.AccountAdapter;

import java.util.ArrayList;


public class Profile extends Fragment {
    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_profile, container, false);
        final Home home = ((Home) getActivity());
        final ListView listView = parentView.findViewById(R.id.FP_lstAcct);
        final ArrayList<Account>[] accounts = new ArrayList[]{home.accountsList};
        final AccountAdapter adapter = new AccountAdapter(getContext(), R.layout.fragment_profile, accounts[0]);
        listView.setAdapter(adapter);
        adapter.setListener(new AccountAdapter.OnClickListener() {
            @Override
            public void deleteItem(int position) {
                home.accountsList.remove(position);
                accounts[0] = home.accountsList;
                adapter.notifyDataSetChanged();
            }
        });
        parentView.findViewById(R.id.FP_btnAddAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.bottomNavigationView.setCurrentItem(1);
            }
        });
        return parentView;
    }

}
