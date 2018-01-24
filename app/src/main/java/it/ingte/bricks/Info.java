package it.ingte.bricks;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by marco on 15/01/18.
 */

public class Info implements Parcelable{
    String localIdentifier;  // codice locale progetto
    String projectCode;      // codice unico progetto
    String beneficiarycode;  // codice fiscale beneficiario
    String beneficiaryName;
    String operationName;   // nome dell'operazione
    String operationSummary; //sintesi progetto
    String startOperation;  // data di inizio lavoro
    String endOpeation;   //data di fine lavoro
    float eligibleExpenditure;  // spesa ammissibile
    String taxFinanciate;  // tassa di finanziamento
    String cap;
    String town;
    String province;
    String country;
    String category;   //categoria dell'intervento
    String id;



    public Info(String localIdentifier, String projectCode, String beneficiarycode, String beneficiaryName, String operationName, String operationSummary, String startOperation, String endOpeation, float eligibleExpenditure, String taxFinanciate, String cap, String town, String province, String country, String category, String id) {
        this.localIdentifier = localIdentifier;
        this.projectCode = projectCode;
        this.beneficiarycode = beneficiarycode;
        this.beneficiaryName = beneficiaryName;
        this.operationName = operationName;
        this.operationSummary = operationSummary;
        this.startOperation = startOperation;
        this.endOpeation = endOpeation;
        this.eligibleExpenditure = eligibleExpenditure;
        this.taxFinanciate = taxFinanciate;
        this.cap=cap;
        this.town = town;
        this.province = province;
        this.country = country;
        this.category = category;
        this.id = id;
    }

    public String getLocalIdentifier() {
        return localIdentifier;
    }

    public void setLocalIdentifier(String localIdentifier) { this.localIdentifier = localIdentifier; }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBeneficiarycode() {
        return beneficiarycode;
    }

    public void setBeneficiarycode(String beneficiarycode) { this.beneficiarycode = beneficiarycode; }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) { this.beneficiaryName = beneficiaryName; }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationSummary() {
        return operationSummary;
    }

    public void setOperationSummary(String operationSummary) { this.operationSummary = operationSummary; }

    public String getStartOperation() {
        return startOperation;
    }

    public void setStartOperation(String startOperation) {
        this.startOperation = startOperation;
    }

    public String getEndOpeation() {
        return endOpeation;
    }

    public void setEndOpeation(String endOpeation) {
        this.endOpeation = endOpeation;
    }

    public float getEligibleExpenditure() {
        return eligibleExpenditure;
    }

    public void setEligibleExpenditure(float eligibleExpenditure) { this.eligibleExpenditure = eligibleExpenditure;}

    public String getTaxFinanciate() {
        return taxFinanciate;
    }

    public void setTaxFinanciate(String taxFinanciate) {
        this.taxFinanciate = taxFinanciate;
    }

    public String  getCap() {
        return cap;
    }

    public void setCap(String  cap) {
        this.cap = cap;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return localIdentifier+ " "+ projectCode + " "+ beneficiarycode + " "+beneficiaryName+""
                +operationName+ " "+operationSummary+" "+startOperation+" "+endOpeation+""
                +eligibleExpenditure+" "+taxFinanciate+" "+cap+" "+town+" "+province+" "+ country+" "+category;


    }

    public Info(Parcel in){
        String [] data = new String[15];
        in.readFloat();
        in.readStringArray(data);

        this.localIdentifier = data[0];
        this.projectCode = data[1];
        this.beneficiarycode = data[2];
        this.beneficiaryName = data[3];
        this.operationName = data[4];
        this.operationSummary = data[5];
        this.startOperation = data[6];
        this.endOpeation = data[7];
      //  this.eligibleExpenditure = data[8];
        this.taxFinanciate = data[9];
        this.cap = data[10];
        this.town = data[11];
        this.province = data[12];
        this.country = data[13];
        this.category = data[14];
        this.id = data[15];


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeStringArray(new String []{
                this.localIdentifier,
                this.projectCode,
                this.beneficiarycode,
                this.beneficiaryName,
                this.operationName,
                this.operationSummary,
                this.startOperation,
                this.endOpeation,
          //      this.eligibleExpenditure,
                this.taxFinanciate,
                this.cap,
                this.town,
                this.province,
                this.country,
                this.category,
                this.id
        });
        parcel.writeFloat(this.eligibleExpenditure);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Info createFromParcel(Parcel parcel) {
            return new Info(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Info[i];
        }
    };
}
