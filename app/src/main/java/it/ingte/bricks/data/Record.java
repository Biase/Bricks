package it.ingte.bricks.data;

import android.provider.ContactsContract;

/**
 * Created by paolo on 21/12/17.
 */

public class Record {

    //Dati che corrispondo alle colonne
    private String identifier;
    private String localCode;
    private String fondo;
    private String descIntervBreve;
    private String descInterv;
    private String startData;
    private String endData;
    private String budget;
    private String tassoConfinUE;
    private String cap;
    private String comune;
    private String provincia;
    private String regione;
    private String categoria;
    private String beneficiario;
    private String codFiscaleBeneficiario;
    private String stato;
    private String dataAgg;

    private int nelem = 18;

    /**
     * Assegno secondo l'ordine di ingresso, che corrisponde all'ordine di lettura il valore all'elemento
     * @param split lista di valori da assegnare al nostro record
     * @throws Exception eccezione che viene lanciata nel caso il numero di elementi letti non corrisponde al numero di colonne del record
     */
    public Record(String ... split) throws Exception {
        if(split.length != nelem) throw new Exception("Bad elements number");
        else {
            fondo = split[0];
            localCode = split[1];
            identifier = split[2];
            codFiscaleBeneficiario= split[3];
            beneficiario = split[4];
            descIntervBreve = split[5];
            descInterv = split[6];
            startData = split[7];
            endData = split[8];
            budget = split[9];
            tassoConfinUE = split[10];
            cap = split[11];
            comune = split[12];
            provincia = split[13];
            regione = split[14];
            stato = split[15];
            categoria = split[16];
            dataAgg = split[17];


        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getDescIntervBreve() {
        return descIntervBreve;
    }

    public void setDescIntervBreve(String descIntervBreve) {
        this.descIntervBreve = descIntervBreve;
    }

    public String getDescInterv() {
        return descInterv;
    }

    public void setDescInterv(String descInterv) {
        this.descInterv = descInterv;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getTassoConfinUE() {
        return tassoConfinUE;
    }

    public void setTassoConfinUE(String tassoConfinUE) {
        this.tassoConfinUE = tassoConfinUE;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getCodFiscaleBeneficiario() {
        return codFiscaleBeneficiario;
    }

    public void setCodFiscaleBeneficiario(String codFiscaleBeneficiario) {
        this.codFiscaleBeneficiario = codFiscaleBeneficiario;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDataAgg() {
        return dataAgg;
    }

    public void setDataAgg(String dataAgg) {
        this.dataAgg = dataAgg;
    }

    public int getNelem() {
        return nelem;
    }

    public void setNelem(int nelem) {
        this.nelem = nelem;
    }

    @Override
    public String toString() {
        return identifier + " - " + descIntervBreve;
    }
}
