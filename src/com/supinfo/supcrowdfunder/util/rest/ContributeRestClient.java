package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.Global;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class ContributeRestClient extends AbstractRestClient {
    protected Map<String, Object> json;
    public ContributeRestClient(Context context, String id, String email, String password, String amount) {
        super("/project/" + id + "/contribute");
        this.addParam("email", email);
        this.addParam("password", password);
        this.addParam("amount", amount);
        try {
            this.Execute(ContributeRestClient.RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
        json = gson.fromJson(response, HashMap.class);
        if (json.get("error").getClass().getName().equals("java.lang.Boolean") && json.get("error").equals(false)) {
            Toast.makeText(context, Global.getRes().getString(R.string.contributeToastValidate),Toast.LENGTH_LONG).show();
            this.success = true;
        } else {
            Toast.makeText(context, (String) json.get("error"),Toast.LENGTH_LONG).show();
        }
    }
}
