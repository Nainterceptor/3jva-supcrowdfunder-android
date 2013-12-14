package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import com.supinfo.supcrowdfunder.R;
import org.w3c.dom.Text;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class ProjectDetailsActivity extends Activity {

    Resources res = null;
    TextView projectName = null;
    TextView needCredits = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details_activity);

        res = getResources();
        projectName = (TextView) findViewById(R.id.projectName);
        needCredits = (TextView) findViewById(R.id.projectNeedCredits);

        projectName.setText("project.name");
        needCredits.setText("project.needCredits");
    }
}