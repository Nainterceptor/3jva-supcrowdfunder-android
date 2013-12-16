package com.supinfo.supcrowdfunder.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.AllProjectsRestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class IndexActivity extends SuperActivity {
    AllProjectsRestClient client = null;
    List<Project> projectsList = null;
    ListView projectsName = null;
    AlertDialog.Builder alert = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        alert = new AlertDialog.Builder(this);

        projectsName = (ListView) findViewById(R.id.projectsList);
        List<String> allProjects = new ArrayList<String>();

        client = new AllProjectsRestClient(IndexActivity.this);

        projectsList = client.getProjects();
        for (Project project : projectsList)
            allProjects.add(project.getName());

        projectsName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allProjects));
        projectsName.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Project projectIntent = projectsList.get(i);
               Intent intent = new Intent(IndexActivity.this, ProjectDetailsActivity.class);
               intent.putExtra("com.supinfo.supcrowdfunder.activity.PROJECTINTENT",projectIntent);
               startActivity(intent);
           }
        });
    }
}