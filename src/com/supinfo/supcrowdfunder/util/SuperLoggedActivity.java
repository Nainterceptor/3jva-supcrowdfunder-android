package com.supinfo.supcrowdfunder.util;

import android.content.Intent;
import android.preference.PreferenceManager;
import com.supinfo.supcrowdfunder.activity.LoginActivity;

/**
 * Created by nainterceptor on 15/12/13.
 */
public abstract class SuperLoggedActivity extends SuperActivity {
    protected void onResume() {
        super.onResume();
        if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean("logged", false)) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
