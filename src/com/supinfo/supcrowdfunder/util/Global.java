package com.supinfo.supcrowdfunder.util;

import android.content.res.Resources;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class Global {
    private static String APIUrl;
    private static Resources res;

    public static void setAPIUrl(String APIUrl) {
        Global.APIUrl = APIUrl;
    }
    public static String getAPIUrl() {
        return Global.APIUrl;
    }

    public static Resources getRes() {
        return res;
    }

    public static void setRes(Resources res) {
        Global.res = res;
    }
}
