package com.example.tyil.dtc.Activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tyil.dtc.API.ApiCallback;
import com.example.tyil.dtc.API.CampaignIndex;
import com.example.tyil.dtc.Helpers.StableArrayAdapter;
import com.example.tyil.dtc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CampaignActivity extends AppCompatActivity {
    private ArrayList<JSONObject> campaignJsonList;
    private ArrayList<String> list;
    private StableArrayAdapter stableArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        

        setContentView(R.layout.activity_campaign);

        ListView campaignList = findViewById(R.id.campaignList);
        this.campaignJsonList = new ArrayList<>();
        this.list = new ArrayList<>();

        final CampaignActivity that = this;

        stableArrayAdapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        campaignList.setAdapter(stableArrayAdapter);
        campaignList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    that.navigateToCampaignInfo(that.campaignJsonList.get(i).getString("_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        CampaignIndex.handle(
                this,
                new ApiCallback() {
                    @Override
                    public void call(JSONObject data, Activity activity) {
                        try {
                            if (!data.getBoolean("ok")) {
                                return;
                            }

                            JSONArray campaigns = data.getJSONArray("campaigns");

                            for (int i = 0; i < campaigns.length(); i++) {
                                JSONObject campaign = campaigns.getJSONObject(i);

                                that.campaignJsonList.add(campaign);
                                that.list.add(campaign.getString("title"));
                            }

                            that.stableArrayAdapter.notifyDataSetChanged();

                            Log.d("CampaignActivity", "Updated Campaign list via cb");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void navigateToCampaignInfo(String campaignId) {
        Intent i = new Intent(this, CampaignInfoActivity.class);
        i.putExtra("CAMPAIGN_ID", campaignId);
        startActivity(i);
    }
}
