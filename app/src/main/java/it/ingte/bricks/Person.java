package it.ingte.bricks;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Domenico on 09/01/2018.
 */

public class Person implements ClusterItem {

    LatLng mPosition;
    String beneficiaryName;
    String twitterHandle;
    String operation;
    String town;
    double price;
    String dateStart;
    String dateFinish;
    String cap;
    String summary;
    String provincia;
    int control;

    public Person(double lat, double lng, String beneficiaryName, String twitterHandle, double price, String operation, String summary, String town, String dateStart, String dateFinish, String cap, String provincia, int control) {
        this.beneficiaryName = beneficiaryName;
        this.twitterHandle = twitterHandle;
        mPosition = new LatLng(lat, lng);
        this.operation = operation;
        this.town = town;
        this.price = price;
        this.dateFinish = dateFinish;
        this.dateStart = dateStart;
        this.cap = cap;
        this.summary = summary;
        this.provincia = provincia;
        this.control = control;
    }

    public Person(String beneficiaryName, String operation, String town){
        this.beneficiaryName = beneficiaryName;
        this.operation = operation;
        this.town = town;
    }

    public Person(String beneficiaryName, String operation, String town, double price){
        this.beneficiaryName = beneficiaryName;
        this.operation = operation;
        this.town = town;
        this.price = price;
    }

    public Person(double lat, double lng){
        mPosition = new LatLng(lat, lng);
    }

    public String getBeneficiaryName(){
        return beneficiaryName;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return beneficiaryName;
    }

    @Override
    public String getSnippet() {
        return twitterHandle;
    }

    public String getOperation(){ return operation;  }

    public String getTown(){ return town;  }

    public double getPrice(){ return price;  }

    public String getDateStart(){ return dateStart; }

    public String getDateFinish(){ return dateFinish; }

    public String getCap(){ return cap; }

    public String getSummary(){ return summary; }

    public String getProvincia() { return provincia; }

    public int getControl() { return control; }
}