package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.util.DateTool;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.AddProjectRestClient;
import com.supinfo.supcrowdfunder.util.rest.AllCategoriesRestClient;
import com.supinfo.supcrowdfunder.util.rest.RegistrationRestClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Robin on 15/12/13.
 */
public class AddProjectActivity extends SuperActivity {
    Resources res = null;
    AllCategoriesRestClient client = null;
    EditText projectName = null;
    EditText projectNeedCredits = null;
    EditText projectDescription = null;
    DatePickerDialog datePicker = null;
    Calendar defaultDate = null;
    List<Category> categoriesList = null;
    Spinner categoriesSpinner = null;
    Button projectTerm = null;
    Button projectButton = null;
    String termDate = null;
    Category projectCategory = null;
    boolean onLoad;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project_activity);
        onLoad = true;

        res = getResources();
        projectName = (EditText) findViewById(R.id.addProjectName);
        projectNeedCredits = (EditText) findViewById(R.id.addProjectNeedCredits);
        projectDescription = (EditText) findViewById(R.id.addProjectDescription);
        categoriesSpinner = (Spinner) findViewById(R.id.addProjectCategory);
        projectTerm = (Button) findViewById(R.id.addProjectTerm);
        projectButton = (Button) findViewById(R.id.addProjectButton);

        defaultDate = Calendar.getInstance();

        List<String> allCategories = new ArrayList<String>();
        client = new AllCategoriesRestClient(AddProjectActivity.this);
        categoriesList = client.getCategories();
        for (Category category : categoriesList){
            allCategories.add(category.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);

        projectTerm.setText(DateTool.calendarString(defaultDate));
        projectTerm.setOnClickListener(termListener);
        categoriesSpinner.setOnItemSelectedListener(new categoriesListener());
        projectButton.setOnClickListener(projectAddListener);
    }

    private View.OnClickListener termListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            datePicker = new DatePickerDialog(AddProjectActivity.this, dateListener,
                    defaultDate.get(Calendar.YEAR),defaultDate.get(Calendar.MONTH),
                    defaultDate.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        }
    };

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            projectTerm.setText(DateTool.datePickerString(datePicker));
            termDate = DateTool.datePickerString(datePicker);
        }
    };

    public class categoriesListener extends Activity implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {
            if(!onLoad) {
                projectCategory = categoriesList.get(pos);
            } onLoad = false;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {}
    }

    private View.OnClickListener projectAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AddProjectRestClient(
                    AddProjectActivity.this,
                    projectName.getText().toString(),
                    projectDescription.getText().toString(),
                    projectCategory.getId().toString(),
                    projectNeedCredits.getText().toString(),
                    termDate
            );
        }
    };
}