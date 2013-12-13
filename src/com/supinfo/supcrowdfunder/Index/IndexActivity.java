package com.supinfo.supcrowdfunder.Index;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.*;
import com.supinfo.supcrowdfunder.R;

import java.util.ArrayList;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class IndexActivity extends Activity {
    ListView projectsList =null;
    AlertDialog.Builder alert = null;
    Resources res = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        res = getResources();
        alert = new AlertDialog.Builder(this);

        projectsList = (ListView) findViewById(R.id.projectsList);

        ArrayList<String> projects = new ArrayList<String>();
        projects.add("test");
        projectsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projects));
    }
}