package com.company;

public class Auto {

    // Klassenvariable
    private static int autoIdCounter = -49;

    // Eigenschaften
    final private int autoId;
    private String hersteller, modell, farbe, kraftstoff;
    private double preis, ps, verbrauch;

    /**
     *
     * @param hersteller    Angabe des Herstellers
     * @param modell        Angabe des Modells
     * @param farbe         Angabe der Farbe des Autos
     * @param kraftstoff    Angabe des Kraftstofftyps
     * @param preis         Preis des Autos in €
     * @param ps            Angabe der PS
     * @param verbrauch     Angabe des Verbrauchs in l/h
     */
    Auto(String hersteller, String modell, String farbe, String kraftstoff, double preis, double ps, double verbrauch) {
        this.autoId = autoIdCounter;
        autoIdCounter++;
        this.hersteller = hersteller;
        this.modell = modell;
        this.farbe = farbe;
        this.kraftstoff = kraftstoff;
        this.preis = preis;
        this.ps = ps;
        this.verbrauch = verbrauch;
    }

    // Getter und Setter
    public int  getAutoId(){
        return autoId;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public String getKraftstoff() {
        return kraftstoff;
    }

    public void setKraftstoff(String kraftstoff) {
        this.kraftstoff = kraftstoff;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getPs() {
        return ps;
    }

    public void setPs(double ps) {
        this.ps = ps;
    }

    public double getVerbrauch() {
        return verbrauch;
    }

    public void setVerbrauch(double verbrauch) {
        this.verbrauch = verbrauch;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

}
