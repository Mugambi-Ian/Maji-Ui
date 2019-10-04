package com.nenecorp.majiapp.Interface.MajiUi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.nenecorp.majiapp.DataModels.Account;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Adapters.AccountAdapter;
import com.nenecorp.majiapp.Utility.Dialogs.SelectListItemsDialog;

import java.util.ArrayList;


public class Accounts extends Fragment {
    private static final String D_WP = "Your water provider";
    View parentView;
    SelectListItemsDialog listItemsDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_accounts, container, false);
        final Home home = ((Home) getActivity());
        final ArrayList<String> wpList = home.wpList;
        final EditText actNo = parentView.findViewById(R.id.FAM_actNumber);
        final TextView wP = parentView.findViewById(R.id.FAM_waterProvider);
        final ArrayList<Account> accounts = home.accountsList;
        final ListView accountsListView = parentView.findViewById(R.id.FAM_lstAcct);
        final AccountAdapter adapter = new AccountAdapter(getContext(), R.layout.fragment_accounts, accounts);
        accountsListView.setAdapter(adapter);
        adapter.setListener(new AccountAdapter.OnClickListener() {
            @Override
            public void deleteItem(int position) {
                accounts.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        parentView.findViewById(R.id.FAM_btnAddAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actNo.getText().length() == 0) {
                    actNo.setError("Fill Value");
                } else if (wP.getText().toString().equals(D_WP)) {
                    Toast.makeText(getContext(), "Select your water provider", Toast.LENGTH_LONG).show();
                } else {
                    accounts.add(new Account(actNo.getText().toString(), wP.getText().toString()));
                    home.accountsList = accounts;
                    adapter.notifyDataSetChanged();
                    wP.setText(D_WP);
                    actNo.setText("");
                }
            }
        });
        parentView.findViewById(R.id.FAM_btnWP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemsDialog = new SelectListItemsDialog(getContext(), R.style.DialogTheme, wpList);
                listItemsDialog.setDialogResultListener(new SelectListItemsDialog.DialogResultListener() {
                    @Override
                    public void resultItemId(int id) {
                        if (id != -1) {
                            wP.setText(wpList.get(id));
                        }
                    }
                });
            }
        });
        parentView.findViewById(R.id.FA_homeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.goHome();
            }
        });
        return parentView;
    }

}
