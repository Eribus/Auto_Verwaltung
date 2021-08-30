package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.System;

import static java.lang.Integer.parseInt;

public class Datenbank {
    private final Auto[] datenArray;                        // Klassenvariable
    private int autoCounter;                                // Zählervariable
    static final private Comparator<Auto> ascHersteller;    // Comparator declaration
    static Scanner sc = new Scanner(System.in);             // Scanner initialisierung


    // Comparator anlegen
    static {
        ascHersteller = Comparator.comparing(Auto::getHersteller);
    }

    public Datenbank() {
        int arraySize = 50;                             // Arraygröße
        this.datenArray = new Auto[arraySize];

        for (int i=0; i< datenArray.length;i++) {       // Jeden Index initialisieren mit default Werten
            datenArray[i] = new Auto(null,null,null,null,0,0,0);
        }
    }

    // #### METHODEN ####

    // Methode für die Datenausgabe aller Daten
    public void output() {
        String anzeige;
        System.out.println("╔════════╦════════════════╦════════════════════╦════════════════╦════════════════════╦══════════════╦══════════╦════════════════╗");
        System.out.println("║   ID   ║     Modell     ║     Hersteller     ║     Farbe      ║     Kraftstoff     ║     Preis    ║    PS    ║    Verbrauch   ║");
        System.out.println("╠════════╬════════════════╬════════════════════╬════════════════╬════════════════════╬══════════════╬══════════╬════════════════╣");
        for (Auto a : datenArray) {
            if(a.getModell() != null ) {
                System.out.print("║");
                outputCell(String.valueOf(a.getAutoId()),8,3);      // Ausrichten der Strings in der Tabelle
                outputCell(a.getModell(),16,5);
                outputCell(a.getHersteller(),20,6);
                outputCell(a.getFarbe(),16,5);
                outputCell(a.getKraftstoff(),20,5);
                anzeige = a.getPreis() + "0 €";                                 // int zu String casten und "0 €" anhängen
                outputCell(anzeige,14,1);
                anzeige=String.valueOf(a.getPs());                              // int zu String casten
                anzeige=anzeige.substring(0,anzeige.length()-2);                // letzten beiden Zeichen abschneiden
                outputCell(anzeige,10,4);
                anzeige= a.getVerbrauch() + " l";                               // int zu String casten und l(Liter) anhängen
                outputCell(anzeige,16,5);
                System.out.println();
            }else {
                break;
            }
        }
        System.out.println("╚════════╩════════════════╩════════════════════╩════════════════╩════════════════════╩══════════════╩══════════╩════════════════╝");
    }

    // Methode für die Datenausgabe einzelner Daten
    public void output(int pos) {
        String anzeige;
        if(datenArray[pos].getModell() != null ) {
            System.out.print("║");
            outputCell(String.valueOf(datenArray[pos].getAutoId()),8,3);    // Ausrichten der Strings in der Tabelle
            outputCell(datenArray[pos].getModell(),16,5);
            outputCell(datenArray[pos].getHersteller(),20,6);
            outputCell(datenArray[pos].getFarbe(),16,5);
            outputCell(datenArray[pos].getKraftstoff(),20,5);
            anzeige = datenArray[pos].getPreis() + " €";                                // int zu String casten und " €" anhängen
            outputCell(anzeige,14,1);
            anzeige=String.valueOf(datenArray[pos].getPs());                            // int zu String casten
            outputCell(anzeige,10,4);
            anzeige= datenArray[pos].getVerbrauch() + " l";                             // int zu String casten und l(Liter) anhängen
            outputCell(anzeige,16,5);
            System.out.println();
        }
    }

    // Methode Tabellezelle ausgeben (String)
    private void outputCell(String attribut,int signs,int space) {
        int lenght = attribut.length();                                                 // länge des Strings speichern
        for(int i=0; i<(signs-lenght-space); i++) { System.out.print(" "); }            // Leerzeichen vor dem String
        System.out.print(attribut);                                                     // String ausgeben
        for(int i=0; i<space; i++) { System.out.print(" "); }                           // Leerzeichen nach dem String
        System.out.print("║");
    }

    // Methode zum Anlegen eines neuen Datensatzes
    public void input() {
        String hersteller,modell,farbe,kraftstoff;
        double preis,ps,verbrauch;

        try {
            System.out.println("Eingabe der Daten für das neue Auto.\n");
            System.out.print("Hersteller: ");
            hersteller = sc.nextLine();              // einlesen Hersteller
            System.out.print("Modell: ");
            modell = sc.nextLine();                  // einlesen Modell
            System.out.print("Farbe: ");
            farbe = sc.nextLine();                   // einlesen Farbe
            System.out.print("Kraftstoff: ");
            kraftstoff = sc.nextLine();              // einlesen Kraftstoff
            System.out.print("Preis: ");
            preis = stringToDouble(sc.nextLine());
            System.out.print("PS: ");
            ps = stringToDouble(sc.nextLine());
            System.out.print("Verbrauch: ");
            verbrauch = stringToDouble(sc.nextLine());
        } catch (InputMismatchException | NumberFormatException e) {
            UserInterface.failInput();
            input();
            return;
        }

        for (int i = 0; i < datenArray.length; i++) {
            if(datenArray[i].getModell()==null) {             // Checken ob Datensatz leer ist und gefüllt werden kann
                datenArray[i] = new Auto(hersteller, modell, farbe, kraftstoff, preis, ps, verbrauch);
                autoCounter++;
                System.out.println("\n\t\t╔══════════════════════════════════════════╗");
                System.out.println("\t\t║   ### Datensatz wurde aufgenommen! ###   ║");
                System.out.println("\t\t╚══════════════════════════════════════════╝\n");
                return;
            }else if (i==datenArray.length-1) {                  // Checken ob Datenbank(Array) voll ist
                System.out.println("\t╔════════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("\t║      ### Datenbank ist voll, Eintrag konnte leider nicht vorgenommen werden! ####      ║");
                System.out.println("\t╚════════════════════════════════════════════════════════════════════════════════════════╝\n");
            }
        }
    }

    // Methode zum löschen eines Datensatzes
    public void del(int delID) {
        boolean isVorhanden=false;

        // Durchlaufen des Arrays und vergleichen der autoId mit der delID
        for (int i=0; i<datenArray.length; i++) {
            if (datenArray[i].getAutoId() == delID && datenArray[i].getModell()!=null) {           // ID wurde gefunden "isVorhanden auf true setzten"
                isVorhanden=true;
                autoCounter--;                                                                     // Datensatz anzahl -1
                System.out.println("\t╔════════════════════════════════════════════════════════╗");
                if(delID<10 && delID>0) { System.out.println("\t║     Das Auto mit der ID: " + delID + " wurde gelöscht!             ║");}
                if(delID<100 && delID>9) { System.out.println("\t║     Das Auto mit der ID: " + delID + " wurde gelöscht!            ║");}
                if(delID<1000 && delID>99) { System.out.println("\t║     Das Auto mit der ID: " + delID + " wurde gelöscht!           ║");}
                System.out.println("\t╚════════════════════════════════════════════════════════╝\n");
            }

            if(isVorhanden && i+1<datenArray.length) {          // Datensätze werden verschoben und das letzte gelöscht
                datenArray[i] = datenArray[i+1];
            } else if(isVorhanden && i+1==datenArray.length) {
                datenArray[i] = new Auto(null, null, null, null, 0, 0, 0);
            }
        }

        if(!isVorhanden) {
            System.out.println("\t╔═════════════════════════════════════════════════════╗");
            System.out.println("\t║     Eingegebene ID konnte nicht gefunden werden!    ║");
            System.out.println("\t╚═════════════════════════════════════════════════════╝\n");
        }
    }

    // Methode zum Sortieren der Datensätze
    public void sortHersteller() {
        Auto[] tempArray = new Auto[autoCounter];

        try {
            System.arraycopy(datenArray, 0, tempArray, 0, autoCounter);         // kopiere daten von datenArray zu tempArray
            Arrays.sort(tempArray, ascHersteller);                                            // sortiere tempArray
            System.arraycopy(tempArray, 0, datenArray, 0, autoCounter);         // kopiere daten zurück in datenArray
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        UserInterface.clearScreen();
        System.out.println("\t╔═════════════════════════════════════════════════════╗");
        System.out.println("\t║        Datenbank wurde erfolgreich sortiert!        ║");
        System.out.println("\t╚═════════════════════════════════════════════════════╝\n");
        output();                                                                             // Ausgabe der Tabelle
    }

    // Methode Komma und Punkt double
    public double stringToDouble(String value) {
        boolean isNoKomma = true;                                                            // flag nur 1mal komma oder punkt
        StringBuilder newValue = new StringBuilder();
        for (int i=0; i < value.length(); i++) {                                            // durch den String gehen
            if(Character.isDigit(value.charAt(i))) {                                        // ist der aktuelle char eine Zahl?
                newValue.append(value.charAt(i));                                           // wenn ja, an String anhängen
            }else {
                if((value.charAt(i) == '.' || value.charAt(i) == ',') && isNoKomma) {        // ist der char Punkt oder Komma?
                    newValue.append(".");                                                   // Punkt an den String anhängen
                    isNoKomma = false;
                }else {
                    break;                                                                  // Wenn alles nicht zutrifft "abbrechen"
                }
            }
        }
        return Double.parseDouble(newValue.toString());                                     // String als double zurückgeben
    }

    // Methode zum anzeigen nach Eigenschaften
    public void outputByAttribut(String attribut, String value) {
        System.out.println("╔════════╦════════════════╦════════════════════╦════════════════╦════════════════════╦══════════════╦══════════╦════════════════╗");
        System.out.println("║   ID   ║     Modell     ║     Hersteller     ║     Farbe      ║     Kraftstoff     ║     Preis    ║    PS    ║    Verbrauch   ║");
        System.out.println("╠════════╬════════════════╬════════════════════╬════════════════╬════════════════════╬══════════════╬══════════╬════════════════╣");
        for (int i=0; i< datenArray.length; i++) {
            // Hersteller
            if (datenArray[i].getHersteller()!=null) {
                if (attribut.equals("Hersteller")) {
                    if (datenArray[i].getHersteller().equals(value)) {
                        output(i);
                    }
                }
                // Modell
                if (attribut.equals("Modell")) {
                    if (datenArray[i].getModell().equals(value)) {
                        output(i);
                    }
                }
                // Farbe
                if (attribut.equals("Farbe")) {
                    if (datenArray[i].getFarbe().equals(value)) {
                        output(i);
                    }
                }
                // Kraftstoff
                if (attribut.equals("Kraftstoff")) {
                    if (datenArray[i].getKraftstoff().equals(value)) {
                        output(i);
                    }
                }
                if(attribut.equals("PS")) {
                    if(datenArray[i].getPs()== parseInt(value)) {
                        output(i);
                    }
                }
                // Preis
                if(attribut.equals("Preis")) {
                    if(datenArray[i].getPreis()== parseInt(value)) {
                        output(i);
                    }
                }
                // Verbrauch
                if(attribut.equals("Verbrauch")) {
                    if(datenArray[i].getVerbrauch()== parseInt(value)) {
                        output(i);
                    }
                }
            }
        }
        System.out.println("╚════════╩════════════════╩════════════════════╩════════════════╩════════════════════╩══════════════╩══════════╩════════════════╝");
    }
}
