package it.ingte.bricks;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Domenico on 06/03/2018.
 */

public class MyCustomAdapterBen extends ArrayAdapter<Info> {

    private  static final String TAG = "MyCustomAdapterBen";
    private Context ct;
    private int textViewResourceId;
    private List<Info> info;
    private int pos;
    private int cont;

    public MyCustomAdapterBen(Context ct, int textViewResourceId, List<Info> info) {
        super(ct, textViewResourceId, info);
        this.ct = ct;
        this.textViewResourceId=textViewResourceId;

    }

    private class ViewHolder {
        TextView beneficiary;
        TextView town;
        TextView proj;
        TextView price;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        pos = position;
        cont = 0;
        String beneficiary = (getItem(position).getBeneficiaryName());
        String town = (getItem(position).getTown());
        double prezzoM = arrotonda(prezzoMedio(),2);
        String price = "" + prezzoM;
        String proj = "Progetti: " + cont;


        MyCustomAdapterBen.ViewHolder holder;
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            convertView = inflater.inflate(textViewResourceId, parent , false);

            holder = new MyCustomAdapterBen.ViewHolder();
            holder.beneficiary = (TextView) convertView.findViewById(R.id.textView1);
            holder.town = (TextView) convertView.findViewById(R.id.textView2);
            holder.proj = (TextView) convertView.findViewById(R.id.textView3);
            holder.proj.setGravity(Gravity.RIGHT);
            holder.price = (TextView) convertView.findViewById(R.id.textView);


            convertView.setTag(holder);

        } else {
            holder = (MyCustomAdapterBen.ViewHolder) convertView.getTag();
        }

        result = convertView;

        holder.beneficiary.setText(beneficiary);
        holder.town.setText(town);
        holder.proj.setText(proj);
        String imp = aggiustaStr(price);
        holder.price.setText(imp+"€");
        if(prezzoM < TabBeneficiary.max / 3) { // less than 95 green
            holder.price.setTextColor(0xBB00BB00);
        }
        else if(prezzoM >= TabBeneficiary.max / 3 && prezzoM < (TabBeneficiary.max / 3)*2) { // less than 100 orange
            holder.price.setTextColor(0xEEDDDD11);
        }
        else { // greater or equal than 100 red
            holder.price.setTextColor(0xBBBB0000);
        }
        return convertView;

    }

    public double prezzoMedio(){
        double media = 0;
        for (int i = 0; i < MainActivity.info.size(); i++){
            if(getItem(pos).getBeneficiaryName().equalsIgnoreCase(MainActivity.info.get(i).getBeneficiaryName()) && getItem(pos).getTown().equalsIgnoreCase(MainActivity.info.get(i).getTown())){
             media += MainActivity.info.get(i).getEligibleExpenditure();
             cont++;
            }
        }
        return media/cont;
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

    double arrotonda(double d, int p){
        return Math.rint(d*Math.pow(10,p))/Math.pow(10,p);
    }
}
