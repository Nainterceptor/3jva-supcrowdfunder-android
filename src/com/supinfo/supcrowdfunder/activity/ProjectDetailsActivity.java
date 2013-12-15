package com.supinfo.supcrowdfunder.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.RestClient;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;
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
    RestClient client = null;
    Button projectButton = null;

    //ce qui suit est pour tester
    Category category = new Category()
            .setName("Catégorie 1")
            .setId(1L);
    User user = new User()
            .setId(7L)
            .setAdmin(1)
            .setEmail("foo@bar.com")
            .setFirstname("Foo")
            .setLastname("Bar")
            .setPassword("foobar");
    Project project = new Project()
            .setId(1L)
            .setCreatedAt("05-12-2013")
            .setDetails("Test écrite" +
                    "alors voyons voir jsqou on peut aller \n\n" +
                    "je suis toujours en train décrire un text pour voir si le scroll marche bien" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj" +
                    "ljfbgd ihdfigndfg podfpjgd fpojdfg pojdfgpjdfg phidfg pojdfgopjdfg opjdfpgjojpjdfgpj ojdfpgoj opjdfgpj")
            .setName("Test 1")
            .setNeedCredits(850L)
            .setTerm("03-02-2014")
            .setCategory(category)
            .setUser(user);

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

        client = new RestClient(res.getString(R.string.URL)+"/project/"+project.getId().toString()+"/contributions");
        try {
            client.Execute(RestClient.RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String response = client.getResponse();
        response = response.substring(0, response.indexOf("\n"));
        Long respLong = Long.parseLong(response);

        projectButton = (Button) findViewById(R.id.projectButton);

        projectName.setText(project.getName());
        needCredits.setText(project.getNeedCredits().toString()+"€");
        actualCredits.setText(response + "€");
        percentage.setText(String.valueOf(respLong*100L/project.getNeedCredits())+"%");
        createdAt.setText(project.getCreatedAt());
        term.setText(project.getTerm());
        creator.setText(project.getUser().getFirstname()+" "+project.getUser().getLastname());
        description.setText(project.getDetails());

        projectButton.setOnClickListener(projectListener);
    }
    private View.OnClickListener projectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(ProjectDetailsActivity.this, ContributeActivity.class);
            i.putExtra("com.supinfo.supcrowdfunder.activity.PROJECT", project);
            startActivity(i);
        }
    };
}