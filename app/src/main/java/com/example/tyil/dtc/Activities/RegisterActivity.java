package com.example.tyil.dtc.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyil.dtc.API.ApiCallback;
import com.example.tyil.dtc.API.UserRegister;
import com.example.tyil.dtc.Data.RegisterData;
import com.example.tyil.dtc.Helpers.PermissionHelper;
import com.example.tyil.dtc.R;
import com.example.tyil.dtc.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setActivity(this);
        binding.setUser(new RegisterData());

        PermissionHelper helper = new PermissionHelper(this);
        if (!helper.CheckPermission(Manifest.permission.GET_ACCOUNTS)) {
            helper.RequestPermission(Manifest.permission.GET_ACCOUNTS, getString(R.string.permission_rationale));
        }
    }

    public void handleRegister(RegisterData registerData) {
        // region Data validation
        if (registerData.getEmail().equals("")) {
            return;
        }

        if (registerData.getUsername().equals("")) {
            return;
        }

        if (!registerData.getPassword().equals(registerData.getPassword2())) {
            return;
        }
        // endregion

        UserRegister.handle(
                registerData.getEmail(),
                registerData.getUsername(),
                registerData.getPassword(),
                this,
                new ApiCallback() {
                    @Override
                    public void call(JSONObject data, Activity activity) {
                        try {
                            if (!data.getBoolean("ok")) {
                                Toast.makeText(
                                        activity.getApplicationContext(),
                                        "Registration failed: " + data.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();

                                return;
                            }

                            Toast.makeText(
                                    activity.getApplicationContext(),
                                    "Registration succeeded!",
                                    Toast.LENGTH_LONG
                            ).show();

                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        } catch (JSONException e) {
                            Log.e("RegisterActivity", e.toString());
                        }
                    }
                }
        );
    }
}
