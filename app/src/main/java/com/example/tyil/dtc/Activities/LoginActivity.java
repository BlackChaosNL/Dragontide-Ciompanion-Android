package com.example.tyil.dtc.Activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tyil.dtc.API.ApiCallback;
import com.example.tyil.dtc.API.UserAuth;
import com.example.tyil.dtc.Data.LoginData;
import com.example.tyil.dtc.Helpers.GlobalSharedPreferences;
import com.example.tyil.dtc.R;
import com.example.tyil.dtc.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private LoginData data = new LoginData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setUser(this.data);
        binding.setActivity(this);
    }

    public void handleLogin(LoginData loginData) {
        if(loginData.getEmail().isEmpty() || loginData.getPassword().isEmpty()) {
            return;
        }

        UserAuth.handle(
                loginData.getEmail(),
                loginData.getPassword(),
                this,
                new ApiCallback() {
                    @Override
                    public void call(JSONObject data, Activity activity) {
                        try {
                            String apiToken = data.getString("message");

                            Log.i("LoginActivity", "Login successful (" + apiToken + ")");

                            GlobalSharedPreferences.setString(
                                    activity.getApplicationContext(),
                                    "apiToken",
                                    apiToken
                            );

                            activity.startActivity(new Intent(activity, CampaignActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}