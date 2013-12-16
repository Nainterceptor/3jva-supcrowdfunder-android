package com.supinfo.supcrowdfunder.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.util.SuperActivity;
import com.supinfo.supcrowdfunder.util.rest.ContributeRestClient;

/**
 * Created by Robin on 12/12/13.
 */
public class ContributeActivity extends SuperActivity {
    Resources res = null;
    Button contributeButton = null;
    TextView body = null;
    EditText amount = null;
    AlertDialog.Builder alert = null;
    Project project = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contribute_activity);

        Intent i = getIntent();

        project = i.getParcelableExtra("com.supinfo.supcrowdfunder.activity.PROJECT");

        res = getResources();
        body = (TextView) findViewById(R.id.contributeThanks);
        amount = (EditText) findViewById(R.id.contributeAmount);
        contributeButton = (Button) findViewById(R.id.contributeButton);
        alert = new AlertDialog.Builder(this);

        body.setText(res.getString(R.string.contributeThanks1) + " " + project.getName() + ". \n\n" +
                project.getUser().getFirstname() + " " + project.getUser().getLastname() +
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
    //TODO attention, le contribuant n'est pas le bon (actuellement le user projet) et il faut changer l'id projet
    private DialogInterface.OnClickListener alertValidateListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ContributeRestClient client = new ContributeRestClient(
                    ContributeActivity.this,
                    String.valueOf(project.getId()),
                    amount.getText().toString()
            );
            if (client.isSuccess()) {
                amount.getText().clear();
            }
        }
    };
    private DialogInterface.OnClickListener alertCancelListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastCancel),Toast.LENGTH_SHORT).show();
        }
    };
}