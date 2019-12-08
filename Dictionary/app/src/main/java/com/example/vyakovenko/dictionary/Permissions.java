package com.example.vyakovenko.dictionary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
/*test22*/

public class Permissions {
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public void checkPermissions(Context context, Activity activity) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                Log.d("myAppName", "permission:WRITE_EXTERNAL_STORAGE: NOT granted!");
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            }
        }
    }
}
