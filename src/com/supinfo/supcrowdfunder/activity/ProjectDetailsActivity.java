package com.supinfo.supcrowdfunder.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.ContributionsRestClient;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class ProjectDetailsActivity extends SuperActivity {

    Resources res = null;
    TextView projectName = null;
    TextView needCredits = null;
    TextView actualCredits = null;
    TextView percentage = null;
    TextView createdAt = null;
    TextView term = null;
    TextView creator = null;
    TextView description = null;
    ContributionsRestClient client = null;
    Button projectButton = null;
    Project project = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details_activity);

        Intent i = getIntent();

        project = i.getParcelableExtra("com.supinfo.supcrowdfunder.activity.PROJECTINTENT");

        res = getResources();
        projectName = (TextView) findViewById(R.id.projectName);
        needCredits = (TextView) findViewById(R.id.projectNeedCredits);
        createdAt = (TextView) findViewById(R.id.projectCreatedAt);
        term = (TextView) findViewById(R.id.projectTerm);
        creator = (TextView) findViewById(R.id.projectCreator);
        description = (TextView) findViewById(R.id.projectDescription);

        projectButton = (Button) findViewById(R.id.projectButton);

        projectName.setText(project.getName());
        needCredits.setText(project.getNeedCredits().toString() + "€");
        createdAt.setText(project.getCreatedAt());
        term.setText(project.getTerm());
        creator.setText(project.getUser().getFirstname() + " " + project.getUser().getLastname());
        description.setText(project.getDetails());
    }

    public void onResume() {
        super.onResume();
        actualCredits = (TextView) findViewById(R.id.projectActualCredits);
        percentage = (TextView) findViewById(R.id.projectPercentage);
        client = new ContributionsRestClient(ProjectDetailsActivity.this, project.getId().toString());
        String response = client.getResponse();
        Long respLong = Long.parseLong(response);

        actualCredits.setText(response + "€");
        percentage.setText(String.valueOf(respLong * 100L / project.getNeedCredits()) + "%");

        projectButton.setOnClickListener(projectListener);
    }

    private View.OnClickListener projectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(ProjectDetailsActivity.this, ContributeActivity.class);
            i.putExtra("com.supinfo.supcrowdfunder.activity.PROJECT", project);
            startActivity(i);
        }
    };
}