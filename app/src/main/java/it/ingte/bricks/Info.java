package it.ingte.bricks;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by marco on 15/01/18.
 */

public class Info implements Serializable{
    String localIdentifier;  // codice locale progetto
    String projectCode;      // codice unico progetto
    String beneficiarycode;  // codice fiscale beneficiario
    String beneficiaryName;
    String operationName;   // nome dell'operazione
    String operationSummary; //sintesi progetto
    String startOperation;  // data di inizio lavoro
    String endOpeation;   //data di fine lavoro
    double eligibleExpenditure;  // spesa ammissibile
    double taxFinanciate;  // tassa di finanziamento
    String cap;
    String town;
    String province;
    String country;
    String category;   //categoria dell'intervento
    int id;

    public Info(String localIdentifier, String projectCode, String beneficiarycode, String beneficiaryName, String operationName, String operationSummary, String startOperation, String endOpeation, double eligibleExpenditure, double taxFinanciate, String cap, String town, String province, String country, String category, int id) {
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
        this.cap = cap;
        this.town = town;
        this.province = province;
        this.country = country;
        this.category = category;
        this.id = id;
    }

    public String getLocalIdentifier() {
        return localIdentifier;
    }

    public void setLocalIdentifier(String localIdentifier) {
        this.localIdentifier = localIdentifier;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBeneficiarycode() {
        return beneficiarycode;
    }

    public void setBeneficiarycode(String beneficiarycode) {
        this.beneficiarycode = beneficiarycode;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationSummary() {
        return operationSummary;
    }

    public void setOperationSummary(String operationSummary) {
        this.operationSummary = operationSummary;
    }

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

    public double getEligibleExpenditure() {
        return eligibleExpenditure;
    }

    public void setEligibleExpenditure(double eligibleExpenditure) {
        this.eligibleExpenditure = eligibleExpenditure;
    }

    public double getTaxFinanciate() {
        return taxFinanciate;
    }

    public void setTaxFinanciate(double taxFinanciate) {
        this.taxFinanciate = taxFinanciate;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

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
        return super.toString();

    }

}
