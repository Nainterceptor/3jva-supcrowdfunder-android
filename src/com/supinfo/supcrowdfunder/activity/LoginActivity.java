package com.supinfo.supcrowdfunder.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.LoginRestClient;

/**
 * Author: Gaël Demette
 * Date: 16/12/13
 * Time: 09:58
 */
public class LoginActivity extends SuperActivity {
    Button loginButton = null;
    EditText email = null;
    EditText password = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginSubmit);
        loginButton.setOnClickListener(loginListener);
    }
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoginRestClient client = new LoginRestClient(
                    LoginActivity.this,
                    email.getText().toString(),
                    password.getText().toString()
            );
            if (client.isSuccess()) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                preferences.edit().putString("email", email.getText().toString());
                preferences.edit().putString("password", password.getText().toString());
                preferences.edit().putBoolean("connected", true);

            }
        }
    };
}