package com.example.tyil.dtc.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.tyil.dtc.API.ApiCallback;
import com.example.tyil.dtc.API.CampaignInfo;
import com.example.tyil.dtc.Data.CampaignData;
import com.example.tyil.dtc.Helpers.QrCodeAsync;
import com.example.tyil.dtc.R;
import com.example.tyil.dtc.databinding.ActivityCampaignInfoBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Formatter;
import java.util.Locale;

public class CampaignInfoActivity extends AppCompatActivity {
    private CampaignData data = new CampaignData();
    private static final int PICK_CONTACT = 719;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCampaignInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_campaign_info);
        binding.setActivity(this);
        binding.setCampaign(this.data);

        final CampaignInfoActivity that = this;

        CampaignInfo.handle(
                this.getIntent().getStringExtra("CAMPAIGN_ID"),
                this,
                new ApiCallback() {
                    @Override
                    public void call(JSONObject data, Activity activity) {
                        try {
                            that.data.setName(data.getString("title"));
                            that.data.setDescription(data.getString("description"));

                            // Update the QR code asynchronously, because generating it is
                            // very slow.
                            new QrCodeAsync(
                                    that.getApplicationContext(),
                                    (ImageView) findViewById(R.id.qrCode),
                                    data.getString("_id")
                            ).execute();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    /**
     * Handle sending of an SMS with campaign data.
     *
     * @todo  Select a phone number to send the SMS to. This is required on Android, or
     *        you'll get an Exception when trying to start the Activity.
     * @param data
     */
    public void handleSendSms(CampaignData data) {
        Log.d("CampaignInfoActivity:handleSendSms","Handling...");

        Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactIntent, PICK_CONTACT);
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        switch (reqCode) {
            case(PICK_CONTACT):
                if (data == null) { return; }

                Uri contactData = data.getData();
                if (contactData == null) { return; }

                Cursor c = null;

                try {
                    c = getContentResolver().query(contactData, new String[]{
                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                    }, null, null, null);

                    if (c == null) { return; }

                    c.moveToFirst();

                    String number = c.getString(0);

                    // Create an intent towards a SMS URI, which will make Android go to it's
                    // text messaging activity.
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: " + number));
                    StringBuilder sb = new StringBuilder();
                    Formatter f = new Formatter(sb, Locale.US);

                    // Add a default text body to the SMS message
                    intent.putExtra("sms_body", f.format(
                            this.getResources().getString(R.string.body_invite_sms),
                            this.getIntent().getStringExtra("CAMPAIGN_ID"),
                            this.data.getName()
                    ).toString());

                    // And start the Activity
                    startActivity(intent);
                } finally {
                    // Ensure the cursor gets closed properly
                    if (c != null) {
                        c.close();
                    }
                }

                break;
        }
    }
}
