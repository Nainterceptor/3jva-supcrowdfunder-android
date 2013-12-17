package com.supinfo.supcrowdfunder.util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.activity.*;

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
        Global.setAPIUrl("http://10.1.18.219:8080/api");
        Global.setRes(res);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuIndex:
                startActivity(new Intent(this, IndexActivity.class));
                break;
            case R.id.menuLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuLogout:
                startActivity(new Intent(this, LogoutActivity.class));
                break;
            case R.id.menuRegister:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.menuViewProfile:
                startActivity(new Intent(this, ViewUserActivity.class));
                break;
            case R.id.menuEditProfile:
                startActivity(new Intent(this, EditUserActivity.class));
                break;
        }
        return true;
    }
}
