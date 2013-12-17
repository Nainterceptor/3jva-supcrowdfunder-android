package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.util.Global;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fireaxe
 * Date: 17/12/13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class CategoryProjectsRestClient extends AbstractRestClient{
    private List<Project> projects;
    public CategoryProjectsRestClient(Context context, String id)
    {
        super("/project/category/" + id);
        try {
            this.Execute(ContributionsRestClient.RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Global.getRes().getString(R.string.restError), Toast.LENGTH_LONG).show();
        }
        response = response.substring(0, response.indexOf("\n"));
        projects = gson.fromJson(response, new TypeToken<List<Project>>(){}.getType());
    }

    public List<Project> getProjects() {
        return projects;
    }
}
