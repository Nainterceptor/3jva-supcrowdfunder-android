package com.supinfo.supcrowdfunder.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.supinfo.supcrowdfunder.R;

/**
 * Created by nainterceptor on 15/12/13.
 */
public abstract class SuperActivity extends Activity {
    protected Resources res = null;
    protected SharedPreferences preferences;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getResources();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Global.setAPIUrl(res.getString(R.string.URL));
        Global.setRes(res);
    }
}
