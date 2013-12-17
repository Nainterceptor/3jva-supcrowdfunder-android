package com.supinfo.supcrowdfunder.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.RegistrationRestClient;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class RegistrationActivity extends SuperActivity {
    Button registrationButton = null;
    EditText email = null;
    EditText password = null;
    EditText passwordConfirm = null;
    EditText firstname = null;
    EditText lastname = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        email = (EditText) findViewById(R.id.registrationEmail);
        password = (EditText) findViewById(R.id.registrationPassword);
        passwordConfirm = (EditText) findViewById(R.id.registrationPasswordConfirm);
        firstname = (EditText) findViewById(R.id.registrationFirstname);
        lastname = (EditText) findViewById(R.id.registrationLastname);
        registrationButton = (Button) findViewById(R.id.registrationSubmit);
        registrationButton.setOnClickListener(registrationListener);
    }

    private View.OnClickListener registrationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new RegistrationRestClient(
                    RegistrationActivity.this,
                    email.getText().toString(),
                    password.getText().toString(),
                    passwordConfirm.getText().toString(),
                    firstname.getText().toString(),
                    lastname.getText().toString()
            );
        }
    };
}