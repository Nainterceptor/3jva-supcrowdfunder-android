package com.supinfo.supcrowdfunder.Contribute;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.RestClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 12/12/13.
 */
public class ContributeActivity extends Activity {
    Resources res = null;
    Button contributeButton = null;
    TextView body = null;
    EditText amount = null;
    AlertDialog.Builder alert = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contribute_activity);

        res = getResources();
        body = (TextView) findViewById(R.id.contributeThanks);
        amount = (EditText) findViewById(R.id.contributeAmount);
        contributeButton = (Button) findViewById(R.id.contributeButton);
        alert = new AlertDialog.Builder(this);

        body.setText(res.getString(R.string.contributeThanks1) + " " + "project.name" + ". \n\n" +
                "project.user.firstname" + " " + "project.user.lastname" +
                res.getString(R.string.contributeThanks2));
        contributeButton.setOnClickListener(contributeListener);
    }
    private View.OnClickListener contributeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (amount.getText().length() == 0){
                Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastEmpty),Toast.LENGTH_SHORT).show();
            }
            else if (Long.parseLong(amount.getText().toString()) <= 0L){
                Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastInvalid),Toast.LENGTH_SHORT).show();
                amount.getText().clear();
            }
            else{
                alert.setMessage(res.getString(R.string.contributeAlertContent)+" "+amount.getText().toString()+"€");
                alert.setTitle(res.getString(R.string.contributeAlertTitle));
                alert.setPositiveButton(res.getString(R.string.validate), alertValidateListener);
                alert.setNegativeButton(res.getString(R.string.cancel), alertCancelListener);
                alert.show();
            }
        }
    };

    private DialogInterface.OnClickListener alertValidateListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            RestClient client = new RestClient("http://192.168.1.21:8080/api/project/1/contribute");
            client.AddParam("email", "foo@bar.com");
            client.AddParam("password", "foobar");
            client.AddParam("amount", amount.getText().toString());
            client.AddHeader("Accept", "*/*");
            client.AddHeader("Cache-Control", "no-cache");

            try {
                client.Execute(RestClient.RequestMethod.POST);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String response = client.getResponse();
            Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastValidate)+response,Toast.LENGTH_LONG).show();
        }
    };
    private DialogInterface.OnClickListener alertCancelListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastCancel),Toast.LENGTH_SHORT).show();
        }
    };


}