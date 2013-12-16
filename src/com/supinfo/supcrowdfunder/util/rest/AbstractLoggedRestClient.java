package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.supinfo.supcrowdfunder.util.SuperActivity;

/**
 * Author: Gaël Demette
 * Date: 16/12/13
 * Time: 17:46
 */
public abstract class AbstractLoggedRestClient extends AbstractRestClient {
    public AbstractLoggedRestClient(Context context, String url)
    {
        super(url);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this
            .addParam("email", preferences.getString("email", "no-mail"))
            .addParam("password", preferences.getString("password", "no-password"));
    }
}
