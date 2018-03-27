package it.ingte.bricks;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class itemClick extends AppCompatActivity {
    List<String> list = new ArrayList<>();
    String classe = new String("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        ListView listView = (ListView) findViewById(R.id.myList);
        Info info = (Info) getIntent().getParcelableExtra("myInfo");
        classe = getIntent().getStringExtra("classe");
        list.add("Beneficiario : " + info.getBeneficiaryName());
        String imp = aggiustaStr(""+info.getEligibleExpenditure());
        list.add("Importo : " +imp +" €");
        list.add("Data di inizio : " + info.getStartOperation());
        list.add("Data di fine : " + info.getEndOpeation());
        list.add("Città : " + info.getTown());
        //list.add("CAP :" + info.getCap());
        list.add("Provincia : "+ info.getProvince());
        list.add("Sommario : " + info.getOperationSummary());
        list.add("Breve descrizione : " + info.getOperationName());
        list.add("Categoria : "+ info.getCategory());
        //list.add("longitudine :"+ info.getLng());
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, list) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setTypeface(null, Typeface.BOLD);

                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(list.get(position).substring(0, list.get(position).indexOf(":")));

                text2.setText(list.get(position).substring(list.get(position).indexOf(":") + 1, list.get(position).length()));

                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    if(classe.equalsIgnoreCase("TabList")) {
                        Toast.makeText(getBaseContext(), "" + classe, Toast.LENGTH_SHORT).show();
                    }
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }


    public String aggiustaStr(String s){
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '.'){
                if(s.length()-i == 2){
                    s += "0";
                }
                String t = s.substring(0,i);
                t += ",";
                t += s.substring(i+1, s.length());
                s = t;
                int j = i-4;
                while(j >= 0){
                    String x = s.substring(0,j+1);
                    x += ".";
                    x += s.substring(j+1, s.length());
                    s = x;
                    j-=3;
                }
            }
        }
        return s;
    }
}












