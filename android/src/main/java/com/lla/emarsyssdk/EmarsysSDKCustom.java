package com.lla.emarsyssdk;

import android.util.Log;

public class EmarsysSDKCustom {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public String initializeEmarsys(String value) {
        Log.i("initializeEmarsys", value);
        return value;
    }

    public String setPushTokenFirebase(String value) {
        Log.i("initializeEmarsys", value);
        return value;
    }

    public void setUser(String value) {
        Log.i("setUser ", value);
    }

    public void clearUser(String value) {
        Log.i("clearUser ", value);
    }
}
