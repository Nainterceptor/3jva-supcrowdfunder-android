package com.supinfo.supcrowdfunder.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.util.SuperLoggedActivity;
import com.supinfo.supcrowdfunder.util.rest.UserRestClient;

import java.util.Map;

/**
 * Created by nainterceptor on 15/12/13.
 */
public class ViewUserActivity extends SuperLoggedActivity {
    TextView email = null;
    TextView firstname = null;
    TextView lastname = null;
    TextView address = null;
    TextView zipCode = null;
    TextView createdAt = null;
    TextView admin = null;
    TextView sex = null;
    TextView city = null;
    UserRestClient client = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewuser_layout);
        email = (TextView) findViewById(R.id.viewUserEmail);
        firstname = (TextView) findViewById(R.id.viewUserFirstname);
        lastname = (TextView) findViewById(R.id.viewUserLastname);
        address = (TextView) findViewById(R.id.viewUserAddress);
        zipCode = (TextView) findViewById(R.id.viewUserZipCode);
        createdAt = (TextView) findViewById(R.id.viewUserCreatedAt);
        admin = (TextView) findViewById(R.id.viewUserAdmin);
        sex = (TextView) findViewById(R.id.viewUserSex);
        city = (TextView) findViewById(R.id.viewUserCity);
    }

    public void onResume() {
        super.onResume();
        client = new UserRestClient(this);
        Map<String, Object> user = client.getUser();
        email.setText((String) user.get("email"));
        firstname.setText((String) user.get("firstname"));
        lastname.setText((String) user.get("lastname"));
        address.setText((String) user.get("address"));
        zipCode.setText((String) user.get("zipCode"));
        createdAt.setText((String) user.get("createdAt"));
        city.setText((String) user.get("city"));
        if ((Boolean) user.get("admin"))
            admin.setText(res.getString(R.string.yes));
        else
            admin.setText(res.getString(R.string.no));
        if ((Boolean) user.get("sex"))
            sex.setText(res.getString(R.string.viewuserSexMen));
        else
            sex.setText(res.getString(R.string.viewuserSexGirl));
    }
}