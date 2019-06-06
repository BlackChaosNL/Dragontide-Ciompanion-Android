package com.example.tyil.dtc.Helpers;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String> {
    HashMap<String, Integer> mIdMap = new HashMap<>();

    public StableArrayAdapter(Context ctx, int resourceId, List<String> data) {
        super(ctx, resourceId, data);

        for (int i = 0; i < data.size(); i++) {
            this.mIdMap.put(data.get(i), i);
        }
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
