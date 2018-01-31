package it.ingte.bricks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ProvaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prova);

        TextView name= (TextView) findViewById(R.id.TextViewTitolo);
        name.setText(getIntent().getStringExtra("name"));
        ListView mListView = (ListView) findViewById(R.id.listViewM);
        final ArrayList<Person> peopleList = new ArrayList<>();
        int control = getIntent().getIntExtra("control", 0);
        int nproject = getIntent().getIntExtra("nproject", 0);
        ArrayList<Info> info = MainActivity.manager.getDbhelper().getData();
        int project = 0;
        for(Info i: info) {
            LatLng temp = (LatLng) getIntent().getParcelableExtra("position");
            if(control == 0) {
                if (temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),0);
                    peopleList.add(p);
                    project++;
                }
            }
            if(control == 1) {
                if (i.getEligibleExpenditure() >= 0 && i.getEligibleExpenditure()<= 25000 && temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),1);
                    peopleList.add(p);
                    project++;
                }
            }
            if(control == 2) {
                if (i.getEligibleExpenditure() > 25000 && i.getEligibleExpenditure()<= 50000 && temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),1);
                    peopleList.add(p);
                    project++;
                }
            }
            if(control == 3) {
                if (i.getEligibleExpenditure() > 50000 && i.getEligibleExpenditure()<= 75000 && temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),1);
                    peopleList.add(p);
                    project++;
                }
            }
            if(control == 4) {
                if (i.getEligibleExpenditure() > 75000 && i.getEligibleExpenditure()<= 100000 && temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),1);
                    peopleList.add(p);
                    project++;
                }
            }
            if(control == 5) {
                if (i.getEligibleExpenditure() > 100000 && temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),1);
                    peopleList.add(p);
                    project++;
                }
            }
        }
        TextView filtro= (TextView) findViewById(R.id.TextViewFiltro);
        if(control == 0) {
            filtro.setText("Numero progetti: "+nproject+"/"+nproject);
        }
        if(control == 1) {
            filtro.setText("[FILTRO ATTIVO 0-25000€]\nNumero progetti: "+project+"/"+nproject);
        }
        if(control == 2) {
            filtro.setText("[FILTRO ATTIVO 25000-50000€]\nNumero progetti: "+project+"/"+nproject);
        }
        if(control == 3) {
            filtro.setText("[FILTRO ATTIVO 50000-75000€]\nNumero progetti: "+project+"/"+nproject);
        }
        if(control == 4) {
            filtro.setText("[FILTRO ATTIVO 75000-100000€]\nNumero progetti: "+project+"/"+nproject);
        }
        if(control == 5) {
            filtro.setText("[FILTRO ATTIVO > 100000€]\nNumero progetti: "+project+"/"+nproject);
        }

        PersonListAdapter adapter = new PersonListAdapter(this, R.layout.adapter_view_layout, peopleList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("boh", ""+i);
                Info a = new Info("", "", "", peopleList.get(i).getBeneficiaryName(), peopleList.get(i).getOperation(), peopleList.get(i).getSummary(), peopleList.get(i).getDateStart(),
                        peopleList.get(i).getDateFinish(), peopleList.get(i).getPrice(), "", peopleList.get(i).getCap(), peopleList.get(i).getTown(), peopleList.get(i).getProvincia(), "", "", "", "",
                        (peopleList.get(i).getPosition()).latitude, (peopleList.get(i).getPosition()).longitude);
                Intent intent=new Intent(ProvaActivity.this,itemClick.class);
                intent.putExtra("myInfo", a);
                startActivity(intent);
            }
        });

    }
}