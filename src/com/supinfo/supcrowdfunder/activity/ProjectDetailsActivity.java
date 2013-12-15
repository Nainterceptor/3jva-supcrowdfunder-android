package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import com.supinfo.supcrowdfunder.R;
import org.w3c.dom.Text;

/**
 * Created by Fireaxe on 13/12/13.
 */
public class ProjectDetailsActivity extends Activity {

    Resources res = null;
    TextView projectName = null;
    TextView needCredits = null;
    TextView actualCredits = null;
    TextView percentage = null;
    TextView createdAt = null;
    TextView term = null;
    TextView creator = null;
    TextView description = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details_activity);

        res = getResources();
        projectName = (TextView) findViewById(R.id.projectName);
        needCredits = (TextView) findViewById(R.id.projectNeedCredits);
        actualCredits = (TextView) findViewById(R.id.projectActualCredits);
        percentage = (TextView) findViewById(R.id.projectPercentage);
        createdAt = (TextView) findViewById(R.id.projectCreatedAt);
        term = (TextView) findViewById(R.id.projectTerm);
        creator = (TextView) findViewById(R.id.projectCreator);
        description = (TextView) findViewById(R.id.projectDescription);

        projectName.setText("project.name");
        needCredits.setText("project.needCredits");
        description.setText("Test écrite" +
                "alors voyons voir jsqou on peut aller \n\n" +
                "je suis toujours en train décrire un text pour voir si le scroll marche bien" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj");
    }
}