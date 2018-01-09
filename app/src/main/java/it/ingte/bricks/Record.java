package it.ingte.bricks;

/**
 * Created by paolo on 26/12/17.
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

    public Record(String... split) throws Exception {
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

    public String getLocalCode() {
        return localCode;
    }

    public String getFondo() {
        return fondo;
    }

    public String getDescIntervBreve() {
        return descIntervBreve;
    }

    public String getDescInterv() {
        return descInterv;
    }

    public String getStartData() {
        return startData;
    }

    public String getEndData() {
        return endData;
    }

    public String getBudget() {
        return budget;
    }

    public String getTassoConfinUE() {
        return tassoConfinUE;
    }

    public String getCap() {
        return cap;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getRegione() {
        return regione;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public String getCodFiscaleBeneficiario() {
        return codFiscaleBeneficiario;
    }

    public String getStato() {
        return stato;
    }

    public String getDataAgg() {
        return dataAgg;
    }

    public int getNelem() {
        return nelem;
    }

    @Override
    public String toString() {
        return identifier + " " + comune;
    }
}
