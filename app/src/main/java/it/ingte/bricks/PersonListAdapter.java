package it.ingte.bricks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Domenico on 24/01/2018.
 */

public class PersonListAdapter extends ArrayAdapter<Person> {

    private static final String TAG = "PersonListAdapter";

    private Context context;
    private int resource;

    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
    }
    /*

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String name = getItem(position).getName();
        String operation = getItem(position).getOperation();
        String town = getItem(position).getTown();

        Person person = new Person(name,operation,town);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView)convertView.findViewById(R.id.TextViewM1);
        TextView tvOPeration = (TextView)convertView.findViewById(R.id.TextViewM2);
        TextView tvTown = (TextView)convertView.findViewById(R.id.TextViewM3);

        tvName.setText(name);
        tvOPeration.setText(operation);
        tvTown.setText(town);
        return convertView;
    }
    */


    private class ViewHolder {
        //TextView beneficiary;
        TextView operation;
        TextView town;
        TextView price;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getBeneficiaryName();
        String operation = getItem(position).getOperation();
        String town = getItem(position).getTown();
        double price = getItem(position).getPrice();

        ViewHolder holder;
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();
            //holder.beneficiary = (TextView) convertView.findViewById(R.id.TextViewM1);
            holder.operation = (TextView) convertView.findViewById(R.id.TextViewOperation);
            holder.town = (TextView) convertView.findViewById(R.id.TextViewTown);
            holder.price= (TextView) convertView.findViewById(R.id.TextViewPrice);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        result = convertView;


        //holder.beneficiary.setText(name);
        holder.operation.setText(operation);
        holder.town.setText(town);
        holder.price.setText(""+price);

        return convertView;
    }
}