package com.teamnexters.zipsa.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

/**
 * Created by Hwasoo.Sung on 2017-02-24.
 */

public class PermissionsChecker {
    private  final Context context;

    public PermissionsChecker(Context context) {
        this.context = context;
    }

    public  boolean lacksPermissions(String[] permissions) {
        for(String  permission : permissions) {
            if(lacksPermission(permission)) {
                return true;
            }
        }
        return  false;
    }

    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }
}
