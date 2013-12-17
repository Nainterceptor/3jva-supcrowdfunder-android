package com.supinfo.supcrowdfunder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.supinfo.supcrowdfunder.util.SuperActivity;

/**
 * Author: Gaël Demette
 * Date: 16/12/13
 * Time: 09:58
 */
public class LogoutActivity extends SuperActivity {
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LogoutActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        // todo : review this if we add more than logged shared preference
        editor.commit();
        startActivity(new Intent(this, IndexActivity.class));
    }
}