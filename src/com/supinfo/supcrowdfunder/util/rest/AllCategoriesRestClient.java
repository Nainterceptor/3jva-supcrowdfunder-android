package com.supinfo.supcrowdfunder.util.rest;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.supinfo.supcrowdfunder.entity.Category;

import java.util.List;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class AllCategoriesRestClient extends AbstractRestClient {
    private List<Category> categories;

    public AllCategoriesRestClient(Context context) {
        super("/category/all");
        try {
            this.Execute(RequestMethod.GET);
            categories = gson.fromJson(response, new TypeToken<List<Category>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }
}
