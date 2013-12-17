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
public class EditUserRestClient extends AbstractLoggedRestClient {
    protected Map<String, Object> json;

    public EditUserRestClient(Context context,
                              String email,
                              String password,
                              String passwordConfirm,
                              String firstname,
                              String lastname,
                              String address,
                              String zipCode,
                              String city,
                              String sex
    ) {
        super(context, "/" + Locale.getDefault().getLanguage() + "/user/edit");
        this
                .addParam("newEmail", email)
                .addParam("newPassword", password)
                .addParam("passwordConfirm", passwordConfirm)
                .addParam("firstname", firstname)
                .addParam("lastname", lastname)
                .addParam("address", address)
                .addParam("zipCode", zipCode)
                .addParam("city", city)
                .addParam("sex", sex)
        ;
        try {
            this.Execute(RequestMethod.POST);
            json = gson.fromJson(response, HashMap.class);
            if (json.get("error").getClass().getName().equals("java.lang.Boolean") && json.get("error").equals(false)) {
                Toast.makeText(context, Global.getRes().getString(R.string.edituserSuccess), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, (String) json.get("error"), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
    }
}
