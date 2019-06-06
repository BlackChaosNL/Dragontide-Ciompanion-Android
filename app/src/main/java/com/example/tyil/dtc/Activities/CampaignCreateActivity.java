package com.example.tyil.dtc.Activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyil.dtc.API.ApiCallback;
import com.example.tyil.dtc.API.CampaignCreate;
import com.example.tyil.dtc.Data.CampaignCreateData;
import com.example.tyil.dtc.R;
import com.example.tyil.dtc.databinding.ActivityCampaignCreateBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class CampaignCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCampaignCreateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_campaign);
        binding.setActivity(this);
        binding.setCampaign(new CampaignCreateData());
    }

    public void handleCreate(CampaignCreateData data) {
        if (data.getName().equals("")) {
            return;
        }

        CampaignCreate.handle(
                data.getName(),
                data.getDescription(),
                data.getPassword(),
                this,
                new ApiCallback() {
                    @Override
                    public void call(JSONObject data, Activity activity) {
                        try {
                            if (!data.getBoolean("ok")) {
                                Toast.makeText(
                                        activity.getApplicationContext(),
                                        "Creating campaign failed: " + data.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();

                                return;
                            }

                            JSONObject campaign = data.getJSONObject("campaign");

                            Intent i = new Intent(activity, CampaignInfoActivity.class);
                            i.putExtra("CAMPAIGN_ID", campaign.getString("_id"));
                            startActivity(i);
                        } catch (JSONException e) {
                            Log.e("CampaignCreateActivity:handleCreate", e.toString());
                        }
                    }
                }
        );
    }
}
