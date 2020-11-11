import java.util.Scanner;
import java.util.*;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

 public class Spielfeld
 {
     private int breite = 1000;
     private int laenge = 1000; // Länge und Breite des Spielfeldes definiert
     private int a; // Anzahl der Punkte
     private Punkt[] punkte;
    
     public Spielfeld()
     {
        
     }

    
     public Punkt[] punkteEingeben() //Koordinaten müssen einzelnd eingegeben werden
     {
       System.out.println("Wie viele Punkte möchten Sie haben?");
       Scanner sc = new Scanner(System.in); // System.in liest Daten aus dem Kontroler
       int a = sc.nextInt(); //java wartet auf Antwort 
       int n = 1; // n steht für Nummer des Punktes startend mit 1
       punkte = new Punkt[a];
       punkte[0] = new Punkt(0,0);
       System.out.println("Wie lauten die Koordinaten der " + a + " Punkte?");
        while (a>1)
        {
          int x = sc.nextInt();
          while (x<=0 | x>=1000)
          {
           System.out.println("X muss sich zwischen 0 und 1000 befinden! Bitte wählen Sie erneut!");
           x = sc.nextInt();
          }
          
          int y = sc.nextInt();
          while (y<=0 | y>=1000)
          {
             System.out.println("Y muss sich zwischen 0 und 1000 befinden! Bitte wählen Sie erneut!");
             Scanner sc4 = new Scanner(System.in);
             y = sc4.nextInt();
          }
         punkte [a-1] = new Punkt(x,y);
         System.out.println("Die Koordinaten des " + n + ". Punktes lauten (" + x + "/" + y +")");
         a--;
         n++; 
      }      
      return punkte;
     }
    
     public void poiSortieren(Punkt[] poi)
     {
         int x;
         int y;
         int a = poi.length; //anzahl der Punkte
         double abstand;  
         int punktespeicher = 0;
         int nächsterpunktspeicher = 1;
        for( int start = 0; start < a - 1; start++) // for( Startwert, Bedingung, Schrittweite)
        {
           double strecke = 1000000;
           x = poi[start].getX();
           y = poi[start].getY();
           
           for( int nächster = start + 1; nächster < a; nächster++)
            {
                abstand = poi[nächster].gibAbstand(x, y);  // extrahiert Koordinaten vom "nächsten" Punkt und errechent abstand direkt                     
                if( abstand < strecke)
                {
                    strecke = abstand; // überschreibt solange "Strecke" bis der kleinste Abstand feststeht. Dieser kann dann vielleicht als "bewegeUm"-Befehl genutzt werden 
                    punktespeicher = nächster; // es wird sich gemerkt zu welchem Punkt der bisher kleinste Abstand gehört
                }                  
        }

        System.out.println( " Die Koordinaten des " + nächsterpunktspeicher + " . Punktes lauten : " );
        poi[punktespeicher].ausgabeAttribute();
        tauschen(punktespeicher, nächsterpunktspeicher);
        nächsterpunktspeicher++;
        System.out.println( " . Der Abstand zu diesem Punkt beträgt " + strecke + " Pixel. ");
      }
     }

      private void tauschen( int index1, int index2) // verlangt nach 4 Punkten
     {
        Punkt temp = punkte[index1];
        this.punkte[index1] = punkte[index2];
        this.punkte[index2] = punkte[index1];
     }
    
     public void poiAbfahren()
     {
        punkteEingeben();
        poiSortieren(punkte);
     }
    
     public ArrayList<Rechteck> hindernisListeErzeugen()
     {
         ArrayList<Rechteck> hindernisListe = new ArrayList<Rechteck>();
         System.out.println( "Wie viele Hindernisse sollen sich auf dem Spielfeld befinden? ");
         Scanner sc1 = new Scanner(System.in);
         int hindernisAnzahl = sc1.nextInt();
         System.out.println( "Es werden " + hindernisAnzahl + " Hindernisse zufällig generiert.");
         int fehler=0;
         for(int i=1; i<hindernisAnzahl+1; i++){
            Rechteck rechteck = zufallsRechteck(i);
            if(!ueberlappt_test(rechteck, hindernisListe)){
                hindernisListe.add(rechteck);
                fehler = 0;
            }
            else{
                fehler++;
                i--;
                if(fehler>=50){
                System.out.println("50 Überlappungen in Folge, Abbruch");
                return null;
                }
            }
            }
         return hindernisListe;
    }
      
    private Rechteck zufallsRechteck(int i)
      {
          int laenge = zufallszahl(1,100);
          int breite = zufallszahl(1,100);
          int positionX = zufallszahl(0,1000-breite);
          int positionY = zufallszahl(0,1000-laenge);
          String bezeichnung = "Rechteck" + i ;
          Punkt position = new Punkt(positionX, positionY);
          Rechteck jetzt = new Rechteck(position, breite, laenge, bezeichnung, zufallsfarbe());
          return jetzt;
        }
        
        private int zufallszahl( int von, int bis )
      {
         int zufallszahl = ThreadLocalRandom.current().nextInt(von, bis);
         return zufallszahl;
      }
    
      private Color zufallsfarbe()
      {
        Color zufallsfarbe  = new Color(zufallszahl(0,256),zufallszahl(0,256),zufallszahl(0,256));
        System.out.println( " Die zufällig generierte Farbe ist: " + zufallsfarbe.toString());
        return zufallsfarbe;
      }
      
      private boolean ueberlappt_test(Rechteck r, ArrayList<Rechteck> hindernisListe){
        if(hindernisListe.size() < 1){return false;}
          for(int i=0; i<hindernisListe.size();i++){
            if(r.ueberlappt(hindernisListe.get(i))){
                return true;
            }
        }
        return false;
        }
}


