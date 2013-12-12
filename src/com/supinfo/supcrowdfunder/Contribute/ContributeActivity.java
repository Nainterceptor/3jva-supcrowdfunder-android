package com.supinfo.supcrowdfunder.Contribute;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import com.supinfo.supcrowdfunder.R;

/**
 * Created by Robin on 12/12/13.
 */
public class ContributeActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contribute_activity);

        Resources res = getResources();
        TextView title = (TextView) findViewById(R.id.thanks);

        title.setText(res.getString(R.string.contributeThanks1)+" "+"project.name"+". \n\n"+"project.user.firstname"+" "+"project.user.lastname"+res.getString(R.string.contributeThanks2));
    }
}