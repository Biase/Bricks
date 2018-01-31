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

        ArrayList<Info> info = MainActivity.manager.getDbhelper().getData();
        for(Info i: info) {
            LatLng temp = (LatLng) getIntent().getParcelableExtra("position");
            if(temp.latitude == i.getLat() && temp.longitude == i.getLng() && i.getBeneficiaryName().equalsIgnoreCase(getIntent().getStringExtra("name"))) {
                Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince());
                peopleList.add(p);
            }
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
                //ArrayList<Info> inf = new ArrayList<>();
                //inf.add(a);
                intent.putExtra("myInfo", a);
                startActivity(intent);
            }
        });

    }
}