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
public class RegistrationRestClient extends AbstractRestClient {
    protected Map<String, Object> json;

    public RegistrationRestClient(Context context, String email, String password, String passwordConfirm, String firstname, String lastname) {
        super("/" + Locale.getDefault().getLanguage() + "/user/register");
        this
                .addParam("email", email)
                .addParam("password", password)
                .addParam("confirmPassword", passwordConfirm)
                .addParam("firstname", firstname)
                .addParam("lastname", lastname);
        try {
            this.Execute(RegistrationRestClient.RequestMethod.POST);
            json = gson.fromJson(response, HashMap.class);
            if (json.get("error").getClass().getName().equals("java.lang.Boolean") && json.get("error").equals(false)) {
                Toast.makeText(context, Global.getRes().getString(R.string.registrationSuccess), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, (String) json.get("error"), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
    }
}
