import java.lang.Enum;
import java.util.Scanner;

public class Roboter
{
    
    public enum Stichwort //vlt.static
    {
        NAME, ALTER, HERSTELLER, GESCHLECHT, ENDE, FEHLER 
    }
    /**
     * Konstruktor für Objekte der Klasse Roboter
     */
    public Roboter()
    {
        // Instanzvariable initialisieren
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void spracherkennung()
    {
        boolean ende = false; //falls "ende" geschrieben wird wird die Funktion beendet
        Scanner sc = new Scanner(System.in); 
        Stichwort eingabe;
        while(!ende){
            System.out.println ("Stellen Sie eine Frage");
            try{
                eingabe = Stichwort.valueOf(sc.next().toUpperCase()); //schreibt alle buchstaben groß
            } catch(IllegalArgumentException ex){
                eingabe = Stichwort.FEHLER;
           }
        
        
       switch(eingabe)
       {
           case NAME:
           System.out.println("Mein Name ist Bond, James Bond.!) ");
           break;
           
           case ALTER: 
           System.out.println("Ich bin erst heute geupdatet worden.");
           break;
           
           case HERSTELLER:
           System.out.println("Ich wurde von der PR GmbH & Co programmiert.");
           break;
           
           case GESCHLECHT:
           System.out.println("Ich bin EIN Roboter. Also scheine ich ein Männlicher zu sein.");
           break;
           
           case ENDE:
           ende = true;
           break;
           
           case FEHLER:
           System.out.println("Schlüsselwort nicht gefunden, bitte erneut versuchen.");
           break;
           
          
            
            
        }
      }   
    }}

