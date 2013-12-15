package com.supinfo.supcrowdfunder.util;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import com.supinfo.supcrowdfunder.R;

/**
 * Created by nainterceptor on 15/12/13.
 */
public abstract class SuperActivity extends Activity {
    protected Resources res = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getResources();
        Global.setAPIUrl(res.getString(R.string.URL));
        Global.setRes(res);
    }
}
