package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.Global;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Fireaxe
 * Date: 17/12/13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class AddProjectRestClient extends AbstractLoggedRestClient {
    protected Map<String, Object> json;

    public AddProjectRestClient(Context context, String projectName, String projectDetails,
                                String projectCategory, String projectNeedCredits, String termDate) {
        super(context, "/project/" + Locale.getDefault().getLanguage() + "/create");
        this
                .addParam("name", projectName)
                .addParam("details", projectDetails)
                .addParam("category", projectCategory)
                .addParam("needCredits", projectNeedCredits)
                .addParam("term", termDate);
        try {
            this.Execute(RegistrationRestClient.RequestMethod.POST);
            json = gson.fromJson(response, HashMap.class);
            if (json.get("error").getClass().getName().equals("java.lang.Boolean") && json.get("error").equals(false)) {
                Toast.makeText(context, Global.getRes().getString(R.string.addProjectSuccess), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, (String) json.get("error"), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
    }
}
