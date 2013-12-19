package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.SuperLoggedActivity;
import com.supinfo.supcrowdfunder.util.rest.EditUserRestClient;
import com.supinfo.supcrowdfunder.util.rest.UserRestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class EditUserActivity extends SuperLoggedActivity {
    EditText email = null;
    EditText firstname = null;
    EditText lastname = null;
    EditText newPassword = null;
    EditText newPasswordConfirm = null;
    EditText address = null;
    EditText zipCode = null;
    EditText city = null;
    Spinner sex = null;
    UserRestClient client = null;
    Button editButton = null;
    String projectSex = null;
    List<String> sexChoice = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edituser_layout);
        email = (EditText) findViewById(R.id.edituserEmail);
        firstname = (EditText) findViewById(R.id.edituserFirstname);
        lastname = (EditText) findViewById(R.id.edituserLastname);
        newPassword = (EditText) findViewById(R.id.edituserPassword);
        newPasswordConfirm = (EditText) findViewById(R.id.edituserPasswordConfirm);
        address = (EditText) findViewById(R.id.edituserAddress);
        zipCode = (EditText) findViewById(R.id.edituserZipCode);
        city = (EditText) findViewById(R.id.edituserCity);
        sex = (Spinner) findViewById(R.id.edituserSex);

        sexChoice = new ArrayList<String>();
        sexChoice.add("Homme");
        sexChoice.add("Femme");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexChoice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter);

        sex.setOnItemSelectedListener(new CustomOnItemSListener());
    }

    public void onResume() {
        super.onResume();
        client = new UserRestClient(this);
        Map<String, Object> user = client.getUser();
        if (client.isSuccess()) {
            email.setText((String) user.get("email"));
            firstname.setText((String) user.get("firstname"));
            lastname.setText((String) user.get("lastname"));
            address.setText((String) user.get("address"));
            zipCode.setText((String) user.get("zipCode"));
            city.setText((String) user.get("city"));
        }
        editButton = (Button) findViewById(R.id.edituserSubmit);
        editButton.setOnClickListener(editListener);
    }

    public class CustomOnItemSListener extends Activity implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {
            projectSex = sexChoice.get(pos);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
        }
    }

    private View.OnClickListener editListener = new View.OnClickListener() {
        public void onClick(View v) {
            new EditUserRestClient(
                    EditUserActivity.this,
                    email.getText().toString(),
                    newPassword.getText().toString(),
                    newPasswordConfirm.getText().toString(),
                    firstname.getText().toString(),
                    lastname.getText().toString(),
                    address.getText().toString(),
                    zipCode.getText().toString(),
                    city.getText().toString(),
                    projectSex

            );
        }
    };
}