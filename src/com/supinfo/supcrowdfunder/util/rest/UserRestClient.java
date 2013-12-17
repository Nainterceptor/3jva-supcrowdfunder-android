package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.Global;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Author: Gaël Demette
 * Date: 16/12/13
 * Time: 23:23
 */
public class UserRestClient extends AbstractLoggedRestClient {
    protected Map<String, Object> json;
    protected Map<String, Object> user;

    public UserRestClient(Context context) {
        super(context, "/" + Locale.getDefault().getLanguage() + "/user/checkuser");
        try {
            this.Execute(LoginRestClient.RequestMethod.POST);
            json = gson.fromJson(response, HashMap.class);
            if (json.get("error") == null) {
                user = (Map<String, Object>) json.get("user");
                this.success = true;
            } else {
                Toast.makeText(context, (String) json.get("error"), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
    }

    public Map<String, Object> getUser() {
        return user;
    }
}
