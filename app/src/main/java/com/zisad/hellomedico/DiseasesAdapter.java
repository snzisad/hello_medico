package com.zisad.hellomedico;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;

public class DiseasesAdapter extends BaseAdapter implements Filterable {

    /* renamed from: ID */
    ArrayList<Integer> f47ID;
    Context context;
    ArrayList<Integer> idfull;
    ArrayList<String> name;
    ArrayList<String> namefull;
    private Filter searchFilter = new Filter() {
        /* access modifiers changed from: protected */
        public FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<String> filtername = new ArrayList<>();
            ArrayList<Integer> filterid = new ArrayList<>();
            ArrayList<ArrayList> filterData = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtername.addAll(DiseasesAdapter.this.namefull);
                filterid.addAll(DiseasesAdapter.this.idfull);
            } else {
                String pattern = charSequence.toString().toLowerCase().trim();
                for (int i = 0; i < DiseasesAdapter.this.namefull.size(); i++) {
                    if (DiseasesAdapter.this.namefull.get(i).toLowerCase().contains(pattern)) {
                        filtername.add(DiseasesAdapter.this.namefull.get(i));
                        filterid.add(DiseasesAdapter.this.idfull.get(i));
                    }
                }
            }
            filterData.add(filtername);
            filterData.add(filterid);
            FilterResults results = new FilterResults();
            results.values = filterData;
            return results;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            DiseasesAdapter.this.name.clear();
            DiseasesAdapter.this.f47ID.clear();
            ArrayList<ArrayList> filterData = (ArrayList) filterResults.values;
            DiseasesAdapter.this.name.addAll(filterData.get(0));
            DiseasesAdapter.this.f47ID.addAll(filterData.get(1));
            DiseasesAdapter.this.notifyDataSetChanged();
        }
    };

    public DiseasesAdapter(Context context2, ArrayList<String> name2, ArrayList<Integer> ID) {
        this.context = context2;
        this.name = name2;
        this.f47ID = ID;
        this.namefull = new ArrayList<>(name2);
        this.idfull = new ArrayList<>(ID);
    }

    public int getCount() {
        return this.f47ID.size();
    }

    public Object getItem(int i) {
        return this.name.get(i);
    }

    public long getItemId(int i) {
        return (long) this.f47ID.get(i).intValue();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(this.context, R.layout.layout_diseases_list, (ViewGroup) null);
        ((TextView) v.findViewById(R.id.tvDiseasesName)).setText(this.name.get(i));
        return v;
    }

    public Filter getFilter() {
        return this.searchFilter;
    }
}
