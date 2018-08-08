package it.ingte.bricks;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 15/01/18.
 */

public class MyCustomAdapter extends ArrayAdapter<Info> {

    private  static final String TAG = "MyCustomAdapter";
    private Context ct;
    private int textViewResourceId;
    private List<Info> info;

    public MyCustomAdapter(Context ct, int textViewResourceId, List<Info> info) {
        super(ct, textViewResourceId, info);
        this.ct = ct;
        this.textViewResourceId=textViewResourceId;

    }

    private class ViewHolder {
        TextView beneficiary;
        TextView town;
        TextView data;
        TextView price;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String beneficiary = (getItem(position).getBeneficiaryName());
        String town = (getItem(position).getTown());
        String data = "inizio: " + (getItem(position).getStartOperation()) + "\nfine: " + (getItem(position).getEndOpeation());
        String price = getItem(position).getEligibleExpenditure()+"";


        ViewHolder holder;
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            convertView = inflater.inflate(textViewResourceId, parent , false);

            holder = new ViewHolder();
            holder.beneficiary = (TextView) convertView.findViewById(R.id.textView1);
            holder.town = (TextView) convertView.findViewById(R.id.textView2);
            holder.data = (TextView) convertView.findViewById(R.id.textView3);
            holder.price = (TextView) convertView.findViewById(R.id.textView);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        result = convertView;

        holder.beneficiary.setText(beneficiary);
        holder.town.setText(town);
        holder.data.setText(data);
        String imp = aggiustaStr(price);
        holder.price.setText(imp+" €");
        return convertView;


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
