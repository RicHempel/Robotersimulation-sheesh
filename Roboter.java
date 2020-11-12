import java.lang.Enum;
import java.util.Scanner;

public class Roboter
{

    private int x;
    int spracherkennung;
    public enum Stichwort //vlt.static
    {
        NAME, ALTER, HERSTELLER, GESCHLECHT, ENDE
    }
    /**
     * Konstruktor für Objekte der Klasse Roboter
     */
    public Roboter()
    {
        // Instanzvariable initialisieren
        x = 0;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void spracherkennung()
    {
   
        System.out.println ("Stellen Sie eine Frage");
        Scanner sc = new Scanner(System.in);
        Stichwort eingabe = Stichwort.valueOf(sc.next().toUpperCase());
        
       switch(eingabe)
       {
           case NAME:
           System.out.println(" Mein Name ist Bond, James Bond.!) ");
           break;
           
           case ALTER: 
           System.out.println(" Ich bin erst heute geupdatet worden.");
           break;
           
           case HERSTELLER:
           System.out.println(" Ich wurde von der PR GmbH & Co programmiert.");
           break;
           
           case GESCHLECHT:
           System.out.println(" Ich bin EIN Roboter. Also scheine ich ein Männlicher zu sein.");
           break;
           
           case ENDE:
           return;
        }
        
       
       
      
        
    }
}
