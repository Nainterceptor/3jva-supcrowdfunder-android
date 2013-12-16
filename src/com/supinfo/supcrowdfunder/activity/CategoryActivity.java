package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.supinfo.supcrowdfunder.R;

/**
 * Created with IntelliJ IDEA.
 * User: Romain-x1300
 * Date: 12/12/13
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
public class CategoryActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView categories = (ListView) findViewById(R.id.listCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, laListeDesTrucsQueTaAMettre);

        categories.setAdapter(adapter);
    }
}