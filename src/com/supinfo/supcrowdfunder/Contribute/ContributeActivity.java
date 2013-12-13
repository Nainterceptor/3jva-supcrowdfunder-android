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
        body = (TextView) findViewById(R.id.thanks);
        amount = (EditText) findViewById(R.id.amount);
        contributeButton = (Button) findViewById(R.id.button);
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
            Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastValidate),Toast.LENGTH_SHORT).show();
        }
    };
    private DialogInterface.OnClickListener alertCancelListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(ContributeActivity.this,res.getString(R.string.contributeToastCancel),Toast.LENGTH_SHORT).show();
        }
    };


}