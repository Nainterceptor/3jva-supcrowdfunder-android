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
public class ContributeRestClient extends AbstractLoggedRestClient {
    protected Map<String, Object> json;

    public ContributeRestClient(Context context, String id, String amount) {
        super(context, "/project/" + Locale.getDefault().getLanguage() + "/" + id + "/contribute");
        this.addParam("amount", amount);
        try {
            this.Execute(ContributeRestClient.RequestMethod.POST);
            json = gson.fromJson(response, HashMap.class);
            if (json.get("error") == null) {
                Toast.makeText(context, Global.getRes().getString(R.string.contributeToastValidate), Toast.LENGTH_LONG).show();
                this.success = true;
            } else {
                Toast.makeText(context, (String) json.get("error"), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
    }
}
