package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.RestClient;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.ProjectTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class IndexActivity extends Activity {
    Gson gson = new Gson();
    RestClient client = null;
    List<Project> projectsList = null;
    ListView projectsName = null;
    AlertDialog.Builder alert = null;
    Resources res = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        res = getResources();
        alert = new AlertDialog.Builder(this);

        projectsName = (ListView) findViewById(R.id.projectsList);
        List<String> allProjects = new ArrayList<String>();

        client = new RestClient("http://192.168.1.21:8080/api/project/all");

        try {
            client.Execute(RestClient.RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Coucou");
        String response = client.getResponse();
        projectsList = gson.fromJson(response, new TypeToken<List<ProjectTest>>(){}.getType());
        for (Project project : projectsList)
            allProjects.add(project.getName());
        allProjects.add("Test 1");
        allProjects.add("Test 2");

        projectsName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allProjects));
//        projectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(IndexActivity.this, ProjectDetailsActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}