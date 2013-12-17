package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.util.rest.CategoryProjectsRestClient;
import com.supinfo.supcrowdfunder.util.rest.ContributionsRestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Romain-x1300
 * Date: 12/12/13
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
public class CategoryActivity extends Activity {
    CategoryProjectsRestClient client = null;
    ContributionsRestClient client2 = null;
    List<Project> projectsList = null;
    Category category = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
    }

    public void onResume() {
        super.onResume();

        List<HashMap<String, String>> allProjects = new ArrayList<HashMap<String, String>>();

        Intent i = getIntent();
        category = i.getParcelableExtra("com.supinfo.supcrowdfunder.activity.CATEGORYINTENT");

        ListView categoriesList = (ListView) findViewById(R.id.listCategory);
        client = new CategoryProjectsRestClient(CategoryActivity.this, category.getId().toString());

        projectsList = client.getProjects();
        for (Project project : projectsList) {
            client2 = new ContributionsRestClient(CategoryActivity.this, project.getId().toString());
            String response = client2.getResponse();
            Long resp = Long.parseLong(response);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", project.getName());
            map.put("details", project.shortDescribe());
            map.put("percent", String.valueOf(project.percentToEnd(resp) + "%"));
            allProjects.add(map);
        }

        ListAdapter adapter = new SimpleAdapter(this, allProjects, R.layout.simple_list_item_3,
                new String[]{"name", "details", "percent"},
                new int[]{R.id.nameBodyList, R.id.detailsBodyList, R.id.percentBodyList});
        categoriesList.setAdapter(adapter);
        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Project projectIntent = projectsList.get(i);
                Intent intent = new Intent(CategoryActivity.this, ProjectDetailsActivity.class);
                intent.putExtra("com.supinfo.supcrowdfunder.activity.PROJECTINTENT", projectIntent);
                startActivity(intent);
            }
        });
    }
}