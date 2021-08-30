package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *      Klasse zur Steuerung des Programms
 */
public class UserInterface {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Datenbank d1 = new Datenbank();
        Menue(d1);
        System.exit(0);
    }

    // Hauptmenü und Mainschleife
    private static void Menue(Datenbank d1) {
        boolean isRunning = true;
        int menuAntwort;

        while(isRunning) {
            System.out.println("\t\t╔══════════════════════════════════════════╗");
            System.out.println("\t\t║      Auto Verwaltungs Programm v0.4      ║");
            System.out.println("\t\t╠══════════════════════════════════════════╣");
            System.out.println("\t\t║   1 - Neues Auto hinzufügen              ║");
            System.out.println("\t\t║   2 - Auto löschen                       ║");
            System.out.println("\t\t║   3 - Alle Autos anzeigen                ║");
            System.out.println("\t\t║   4 - Autos sortieren                    ║");
            System.out.println("\t\t║   5 - Autos anzeigen nach Eigenschaften  ║");
            System.out.println("\t\t║   0 - Programm beenden                   ║");
            System.out.println("\t\t╚══════════════════════════════════════════╝");
            try {
                menuAntwort = sc.nextInt();
                sc.nextLine();                  // Enter abfangen
            } catch (InputMismatchException e) {
                sc.nextLine();                  // Enter abfangen
                failInput();
                Menue(d1);
                return;
            }

            if(menuAntwort == 1) {
                clearScreen();
                menueAutoNeu(d1);               // Neues Auto hinzufügen
            }else if (menuAntwort == 2) {
                clearScreen();                  // Auto lösschen
                menueAutoDel(d1);
            }else if (menuAntwort == 3) {
                clearScreen();                  // Alle Autos anzeigen
                d1.output();
            }else if (menuAntwort == 4) {
                d1.sortHersteller();            // Autos sortieren nach Hersteller
            }else if (menuAntwort == 5) {
                menueOutputByAttribut(d1);      // Autos anzeigen nach Eigenschaften
            }else if (menuAntwort == 0) {
                clearScreen();                  // Programm beenden
                System.out.println("\t\t╔═════════════════════════════════════╗");
                System.out.println("\t\t║       Programm wurde beendet.       ║");
                System.out.println("\t\t╚═════════════════════════════════════╝");
                isRunning = false;
            }else{
                failInput();
            }
        }
    }

    // Methode zum leeren der Console
    public static void clearScreen() {
        for (int i=0; i<20; i++) {
            System.out.println();
        }
    }

    // Menü "Neues Auto hinzufügen"
    private static void menueAutoNeu(Datenbank d1) {
        String again;

        d1.input();
        System.out.println("\t\t ╔═══════════════════════════════════════╗");
        System.out.println("\t\t ║ Ein weiteres Auto speichern (Y/N)?    ║");
        System.out.println("\t\t ╚═══════════════════════════════════════╝");
        try {
            again = sc.next();
        } catch (InputMismatchException e) {
            failInput();
            return;
        }

        again = again.toLowerCase();
        if (again.equals("y")) {
            menueAutoNeu(d1);
        }else if (again.equals("n")) {
            clearScreen();
        }else {
            failInput();
        }
    }

    // Ausgabe bei Fehlerhafter Eingabe
    public static void failInput() {
        clearScreen();
        System.out.println("\n\t\t╔═══════════════════════════════════════════════════════╗");
        System.out.println("\t\t║    ### Fehlerhafte Eingabe! Bitte wiederholen. ###    ║");
        System.out.println("\t\t╚═══════════════════════════════════════════════════════╝\n");
    }

    // Menü "Auto löschen über ID"
    private static void menueAutoDel(Datenbank d1) {
        int delID;

        clearScreen();
        System.out.println("\n\t\t╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("\t\t║    Bitte geben Sie die ID des Autos ein, das Sie löschen wollen.  ║");
        System.out.println("\t\t║    ID?                                                            ║");
        System.out.println("\t\t╚═══════════════════════════════════════════════════════════════════╝\n");

        try {
            delID = sc.nextInt();
            sc.nextLine();                      // Enter abfangen nach nextInt
        }catch (InputMismatchException e) {
            failInput();
            menueAutoDel(d1);
            return;
        }

        d1.del(delID);                      // Aufruf der Lösch-Methode mit Übergabe der zu löschen Auto-ID
        menueAutoDelAgain(d1);
    }

    // Menü zur abfrage ob ein weiteres Auto gelöscht werden soll
    private static void menueAutoDelAgain(Datenbank d1) {
        String again;

        System.out.println("\t\t╔════════════════════════════════════════════╗");
        System.out.println("\t\t║     Ein weiteres Auto löschen (Y/N)?       ║");
        System.out.println("\t\t╚════════════════════════════════════════════╝\n");
        try {
            again = sc.next();
        } catch (InputMismatchException e) {
            failInput();
            menueAutoDelAgain(d1);
            return;
        }

        again = again.toLowerCase();
        if (again.equals("y")) {
            menueAutoDel(d1);
        } else if (again.equals("n")) {
            clearScreen();
        } else {
            failInput();
            menueAutoDelAgain(d1);
        }
    }

    // Menü zur anzeige nach Eigenschaften
    private static void menueOutputByAttribut(Datenbank d1) {
        boolean isRunning = true;
        int menuAntwort;

        while(isRunning) {
            System.out.println("\t\t╔═════════════════════════════════════════════════════╗");
            System.out.println("\t\t║      Bitte wählen Sie eine Auto Eigenschaft aus!    ║");
            System.out.println("\t\t╠═════════════════════════════════════════════════════╣");
            System.out.println("\t\t║       1 - Hersteller                                ║");
            System.out.println("\t\t║       2 - Modell                                    ║");
            System.out.println("\t\t║       3 - Farbe                                     ║");
            System.out.println("\t\t║       4 - Kraftstoff                                ║");
            System.out.println("\t\t║       5 - Preis                                     ║");
            System.out.println("\t\t║       6 - PS                                        ║");
            System.out.println("\t\t║       7 - Verbrauch                                 ║");
            System.out.println("\t\t║       0 - Zurück zum Hauptmenü                      ║");
            System.out.println("\t\t╚═════════════════════════════════════════════════════╝");
            try {
                menuAntwort = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                failInput();
                menueOutputByAttribut(d1);
                return;
            }

            clearScreen();
            try {
                if (menuAntwort == 1) {
                    System.out.println("Bitte Hersteller eingeben: ");
                    String hersteller = sc.nextLine();
                    d1.outputByAttribut("Hersteller", hersteller);
                } else if (menuAntwort == 2) {
                    System.out.println("Bitte Modell eingeben: ");
                    String modell = sc.nextLine();
                    d1.outputByAttribut("Modell", modell);
                } else if (menuAntwort == 3) {
                    System.out.println("Bitte Farbe eingeben: ");
                    String farbe = sc.next();
                    d1.outputByAttribut("Farbe", farbe);
                } else if (menuAntwort == 4) {
                    System.out.println("Bitte Kraftstofftyp eingeben: ");
                    String kraftstoff = sc.nextLine();
                    d1.outputByAttribut("Kraftstoff", kraftstoff);
                } else if (menuAntwort == 5) {
                    System.out.println("Bitte Preis eingeben: ");
                    int preis = sc.nextInt();
                    d1.outputByAttribut("Preis", String.valueOf(preis));
                } else if (menuAntwort == 6) {
                    System.out.println("Bitte PS eingeben: ");
                    int ps = sc.nextInt();
                    d1.outputByAttribut("PS", String.valueOf(ps));
                } else if (menuAntwort == 7) {
                    System.out.println("Bitte Verbrauch eingeben: ");
                    int verbrauch = sc.nextInt();
                    d1.outputByAttribut("Verbrauch", String.valueOf(verbrauch));
                } else if (menuAntwort == 0) {
                    isRunning = false;
                } else {
                    failInput();
                }
            } catch (InputMismatchException e) {
                failInput();
            }
        }
    }
}