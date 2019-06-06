package com.example.tyil.dtc.Helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import static com.example.tyil.dtc.Helpers.StaticAppConfig.PERMISSION_REQUEST_CODE;

public class PermissionHelper {
    private Activity activity;

    public PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    public boolean CheckPermission(String permission){
        if (Build.VERSION.SDK_INT < 27) {
            return true;
        }

        int result = ContextCompat.checkSelfPermission(activity, permission);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    public void RequestPermission(String permission, String rationale){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
            Toast.makeText(activity, rationale, Toast.LENGTH_LONG).show();
        }

        ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSION_REQUEST_CODE);
    }
}
