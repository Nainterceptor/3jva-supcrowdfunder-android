package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.Global;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class ContributionsRestClient extends AbstractRestClient {
    public ContributionsRestClient(Context context, String id)
    {
        super("/project/" + id + "/contributions");
        try {
            this.Execute(ContributionsRestClient.RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
        response = response.substring(0, response.indexOf("\n"));
    }
}
