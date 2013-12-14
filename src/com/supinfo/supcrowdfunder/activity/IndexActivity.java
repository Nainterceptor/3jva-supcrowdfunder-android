package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.supinfo.supcrowdfunder.R;

import java.util.ArrayList;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class IndexActivity extends Activity {
    ListView projectsList = null;
    AlertDialog.Builder alert = null;
    Resources res = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        res = getResources();
        alert = new AlertDialog.Builder(this);

        projectsList = (ListView) findViewById(R.id.projectsList);

        ArrayList<String> projects = new ArrayList<String>();
        projects.add("project_name");
        projectsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projects));
        projectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(IndexActivity.this, ProjectDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}