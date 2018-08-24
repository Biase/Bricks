package it.ingte.bricks;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Domenico on 23/08/2018.
 */

public class MyCustomAdapterSpeBen extends ArrayAdapter<Info> {

        private  static final String TAG = "MyCustomAdapter";
        private Context ct;
        private int textViewResourceId;
        private List<Info> info;

        public MyCustomAdapterSpeBen(Context ct, int textViewResourceId, List<Info> info) {
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

            String beneficiary = (getItem(position).getOperationName().toLowerCase());
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

            if(getItem(position).getEligibleExpenditure() < 500000) { // less than 95 green
                holder.price.setTextColor(0xBB00BB00);
            }
            else if(getItem(position).getEligibleExpenditure() >= 500000 && getItem(position).getEligibleExpenditure() < 1000000) { // less than 100 orange
                holder.price.setTextColor(0xBBFFDD11);
            }
            else { // greater or equal than 100 red
                holder.price.setTextColor(0xBBBB0000);
            }

            if(getItem(position).getStartOperation().substring(6,8).equals("15")) { // less than 95 green
                Log.i("color",""+getItem(position).getStartOperation().substring(6,8).equals("15"));
                holder.data.setTextColor(0xBB00008B);
            }
            else if(getItem(position).getStartOperation().substring(6,8).equals("16")) { // less than 100 orange
                Log.i("color2",""+getItem(position).getStartOperation().substring(6,8));

                holder.data.setTextColor(0xBB7B68EE);
            }
            else { // greater or equal than 100 red
                Log.i("color3",""+getItem(position).getStartOperation().substring(6,8));

                holder.data.setTextColor(0x9900BEEE);
            }

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

