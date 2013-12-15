package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.supinfo.supcrowdfunder.entity.Project;

import java.util.List;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class AllProjectsRestClient extends AbstractRestClient {
    private List<Project> projects;
    public AllProjectsRestClient(Context context) {
        super("/project/all");
        try {
            this.Execute(AllProjectsRestClient.RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        projects = gson.fromJson(response, new TypeToken<List<Project>>(){}.getType());
    }

    public List<Project> getProjects() {
        return projects;
    }
}
