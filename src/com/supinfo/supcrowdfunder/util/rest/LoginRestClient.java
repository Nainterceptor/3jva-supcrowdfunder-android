package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.Global;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class LoginRestClient extends AbstractRestClient {
    protected Map<String, Object> json;
    public LoginRestClient(Context context, String email, String password)
    {
        super("/" + Locale.getDefault().getLanguage() + "/user/checkuser");
        this
            .addParam("email", email)
            .addParam("password", password);
        try {
            this.Execute(LoginRestClient.RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
        json = gson.fromJson(response, HashMap.class);
        if (json.get("error") == null) {
            Toast.makeText(context, Global.getRes().getString(R.string.loginSuccess), Toast.LENGTH_LONG).show();
            this.success = true;
        } else {
            Toast.makeText(context, (String) json.get("error"), Toast.LENGTH_LONG).show();
        }
    }
}