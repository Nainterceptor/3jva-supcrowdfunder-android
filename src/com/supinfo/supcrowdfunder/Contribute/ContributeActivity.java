package com.supinfo.supcrowdfunder.Contribute;

import android.app.Activity;
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

        TextView title = (TextView) findViewById(R.id.contributeTitle);

        title.setText("Contribuer au projet : project.name");
    }
}