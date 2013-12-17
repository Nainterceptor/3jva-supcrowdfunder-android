package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.AllCategoriesRestClient;
import com.supinfo.supcrowdfunder.util.rest.AllProjectsRestClient;
import com.supinfo.supcrowdfunder.util.rest.ContributionsRestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class IndexActivity extends SuperActivity {
    AllProjectsRestClient client = null;
    ContributionsRestClient client2 = null;
    AllCategoriesRestClient client3 = null;
    List<Project> projectsList = null;
    ListView projectsName = null;
    List<Category> categoriesList = null;
    Spinner categoriesSpinner = null;
    Button indexAddProject = null;
    boolean onLoad;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);
    }

    public void onResume() {
        super.onResume();

        onLoad = true;
        List<HashMap<String, String>> allProjects = new ArrayList<HashMap<String, String>>();
        List<String> allCategories = new ArrayList<String>();

        indexAddProject = (Button) findViewById(R.id.indexAddProject);
        projectsName = (ListView) findViewById(R.id.projectsList);
        categoriesSpinner = (Spinner) findViewById(R.id.indexCategoriesSpinner);

        client = new AllProjectsRestClient(IndexActivity.this);
        client3 = new AllCategoriesRestClient(IndexActivity.this);

        categoriesList = client3.getCategories();
        allCategories.add("Catégories");
        for (Category category : categoriesList){
            allCategories.add(category.getName());
        }

        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCategories);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapterCat);
        categoriesSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        projectsList = client.getProjects();
        for (Project project : projectsList) {
            client2 = new ContributionsRestClient(IndexActivity.this, project.getId().toString());
            String response = client2.getResponse();
            Long resp = Long.parseLong(response);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", project.getName());
            map.put("details", project.shortDescribe());
            map.put("percent", String.valueOf(project.percentToEnd(resp) + "%"));
            allProjects.add(map);
        }

        ListAdapter adapter = new SimpleAdapter(this, allProjects, R.layout.simple_list_item_3,
                new String[] {"name", "details", "percent"},
                new int[] {R.id.nameBodyList, R.id.detailsBodyList, R.id.percentBodyList});
        projectsName.setAdapter(adapter);
        projectsName.setOnItemClickListener(new CustomOnItemClickListener());
        indexAddProject.setOnClickListener(new CustomOnClickListenerAddProject());
    }

    public class CustomOnItemClickListener extends Activity implements
            AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
            Project projectIntent = projectsList.get(i);
            Intent intent = new Intent(IndexActivity.this, ProjectDetailsActivity.class);
            intent.putExtra("com.supinfo.supcrowdfunder.activity.PROJECTINTENT", projectIntent);
            IndexActivity.this.startActivity(intent);
        }
    }

    public class CustomOnClickListenerAddProject extends Activity implements
            AdapterView.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(IndexActivity.this, AddProjectActivity.class);
            IndexActivity.this.startActivity(intent);
        }
    }

    public class CustomOnItemSelectedListener extends Activity implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {
            if(!onLoad) {
                Category categoryIntent = categoriesList.get(pos - 1);
                Intent intent = new Intent(IndexActivity.this, CategoryActivity.class);
                intent.putExtra("com.supinfo.supcrowdfunder.activity.CATEGORYINTENT", categoryIntent);
                IndexActivity.this.startActivity(intent);
            } onLoad = false;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {}
    }
}