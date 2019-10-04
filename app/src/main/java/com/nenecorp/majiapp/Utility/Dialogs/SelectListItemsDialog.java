package com.nenecorp.majiapp.Utility.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nenecorp.majiapp.Interface.AccountWizard.AccountMissing;
import com.nenecorp.majiapp.R;
import com.nenecorp.majiapp.Utility.Resources.Animations;

import java.util.ArrayList;
import java.util.Collections;

public class SelectListItemsDialog extends Dialog {
    private ArrayList<String> arrayList;
    private ArrayList<ListItems> listItems;
    private Animations animations;
    private ListView listView, rListView;
    private SearchAdapter searchAdapter;
    private DialogResultListener dialogResultListener;

    public void setDialogResultListener(DialogResultListener dialogResultListener) {
        this.dialogResultListener = dialogResultListener;
    }

    private ArrayList<String> sortAscending() {
        Collections.sort(arrayList);
        return arrayList;
    }

    private ArrayList<ListItems> getListItems() {
        ArrayList<ListItems> listItems = new ArrayList<>();
        int i = 0;
        for (String x : arrayList) {
            char t = x.charAt(0);
            if (listItems.size() == 0) {
                String string = t + ".";
                listItems.add(new ListItems(true, string.toUpperCase(), -1));
            } else {
                if (t == arrayList.get(i - 1).charAt(0)) {
                    listItems.add(new ListItems(false, x, i));
                } else {
                    String string = t + ".";
                    listItems.add(new ListItems(true, string.toUpperCase(), -1));
                }
            }
            i++;
        }
        return listItems;
    }

    public SelectListItemsDialog(@NonNull Context context, int themeResId, ArrayList<String> items) {
        super(context, themeResId);
        arrayList = items;
        arrayList = sortAscending();
        listItems = getListItems();
        animations = new Animations(context);
        setContentView(R.layout.dialog_select_list_items);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        show();
        initDialog();
    }

    private View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void initDialog() {
        listView = findViewById(R.id.DSLI_list);
        rListView = findViewById(R.id.DSLI_rList);
        ListAdapter adapter = new ListAdapter(getContext(), R.layout.dialog_select_list_items, listItems);
        listView.setAdapter(adapter);
        listView.requestFocus();
        searchAdapter = new SearchAdapter(listView, rListView);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(ListItems item, int id) {
                for (ListItems x : listItems) {
                    if (id == listItems.indexOf(x)) {
                        getViewByPosition(id, listView).findViewById(R.id.LIS_in).setVisibility(View.VISIBLE);
                    } else {
                        getViewByPosition(listItems.indexOf(x), listView).findViewById(R.id.LIS_in).setVisibility(View.GONE);
                    }
                }
            }
        });
        ((EditText) findViewById(R.id.DLSI_searchText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    searchAdapter.closeSearch();
                } else {
                    searchAdapter.searchFor(listItems, s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        findViewById(R.id.DSLI_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogResultListener != null) {
                    dialogResultListener.resultItemId(-1);
                }
                dismiss();
            }
        });
        findViewById(R.id.DSLi_btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        findViewById(R.id.DSLI_btnMissing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), AccountMissing.class));
                dismiss();
            }
        });
    }

    private class ListAdapter extends ArrayAdapter<ListItems> {
        OnItemClickListener onItemClickListener;

        void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListItems> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View parentView = convertView;
            if (parentView == null) {
                parentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_string, parent, false);
            }
            final ListItems item = getItem(position);
            TextView title = parentView.findViewById(R.id.LIS_title);
            RelativeLayout btnItem = parentView.findViewById(R.id.LIS_btnItem);
            if (item.isTitle()) {
                btnItem.setVisibility(View.GONE);
                title.setVisibility(View.VISIBLE);
                title.setText(item.text);
            } else {
                btnItem.setVisibility(View.VISIBLE);
                title.setVisibility(View.GONE);
                parentView.findViewById(R.id.LIS_in).setVisibility(View.GONE);
                ((TextView) parentView.findViewById(R.id.LIS_text)).setText(item.getText());
                btnItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClicked(item, position);
                        }
                        if (dialogResultListener != null) {
                            dialogResultListener.resultItemId(item.getId());
                        }
                    }
                });
            }
            return parentView;
        }
    }

    private interface OnItemClickListener {
        void onItemClicked(ListItems item, int id);
    }

    private class ListItems {
        private boolean title;
        private String text;
        private int id;

        int getId() {
            return id;
        }

        void setId(int id) {
            this.id = id;
        }

        boolean isTitle() {
            return title;
        }

        String getText() {
            return text;
        }

        ListItems(boolean title, String text, int id) {
            this.title = title;
            this.text = text;
            this.id = id;
        }
    }

    public class SearchAdapter {
        private ListView listView;
        private ListView rListView;
        private ArrayList<ListItems> listItems;

        SearchAdapter(ListView listView, ListView rListView) {
            this.listView = listView;
            this.rListView = rListView;
        }

        void searchFor(ArrayList<ListItems> list, String text) {
            listItems = getItems(list, text);
            listView.setVisibility(View.GONE);
            ListAdapter adapter = new ListAdapter(getContext(), R.layout.dialog_select_list_items, listItems);
            rListView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClicked(ListItems item, int id) {
                    for (ListItems x : listItems) {
                        if (id == listItems.indexOf(x)) {
                            getViewByPosition(id, listView).findViewById(R.id.LIS_in).setVisibility(View.VISIBLE);
                        } else {
                            getViewByPosition(id, listView).findViewById(R.id.LIS_in).setVisibility(View.GONE);
                        }
                    }
                }
            });
            if (rListView.getVisibility() != View.VISIBLE) {
                rListView.setVisibility(View.VISIBLE);
                rListView.startAnimation(animations.fadeIn);
            }
        }

        void closeSearch() {
            if (listView.getVisibility() != View.VISIBLE) {
                listView.setVisibility(View.VISIBLE);
                listView.startAnimation(animations.fadeIn);
                rListView.setVisibility(View.GONE);
            }

        }
    }

    public interface DialogResultListener {
        void resultItemId(int id);
    }

    private ArrayList<ListItems> getItems(ArrayList<ListItems> list, String text) {
        ArrayList<ListItems> listItems = new ArrayList<>();
        for (ListItems items : list) {
            if (items.getText().contains(text)) {
                listItems.add(items);
            }
        }
        return listItems;
    }
}
