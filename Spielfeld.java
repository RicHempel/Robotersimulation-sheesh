import java.util.Scanner;
import java.util.*;
import java.awt.Color;

 public class Spielfeld
 {
     private int breite = 1000;
     private int laenge = 1000; // Länge und Breite des Spielfeldes definiert
     private int a; // Anzahl der Punkte
     private Punkt[] punkte;
     private static Random zufallsgenerator;
    
     public Spielfeld()
     {
        
     }

    
     public Punkt[] punkteEingeben() //Koordinaten müssen einzelnd eingegeben werden
     {
       System.out.println("Wie viele Punkte möchten Sie haben?");
       Scanner sc = new Scanner(System.in); // System.in liest Daten aus dem Kontroler
       int a = sc.nextInt(); //java wartet auf Antwort 
       int n = 1; // n steht für Nummer des Punktes startend mit 1
       System.out.println("Wie lauten die Koordinaten der " + a + " Punkte?");
       punkte = new Punkt[a+1];
       punkte[0] = new Punkt(0,0);
        while ( a>0)
        {
          Scanner sc1 = new Scanner(System.in);
          int x = sc1.nextInt();
          while (x<= 0)
          {
           System.out.println( " X muss sich zwischen 0 und 1000 befinden! Bitte wählen Sie erneut!");
           Scanner sc2 = new Scanner(System.in);
           x = sc2.nextInt();
          }
          while ( x>=1000)
          {
              System.out.println( " X muss sich zwischen 0 udn 1000 befinden! Bitte wählen Sie erneut!");
              Scanner sc2 = new Scanner(System.in);
              x = sc2.nextInt();
          }
          Scanner sc3 = new Scanner(System.in);
          int y = sc3.nextInt();
          while ( y<=0)
          {
             System.out.println( " Y muss sich zwischen 0 udn 1000 befinden! Bitte wählen Sie erneut!");
             Scanner sc4 = new Scanner(System.in);
             y = sc4.nextInt();
          }
          while ( y>=1000)
          {
             System.out.println( " Y muss sich zwischen 0 udn 1000 befinden! Bitte wählen Sie erneut!");
             Scanner sc4 = new Scanner(System.in);
             y = sc4.nextInt();
          }
         punkte [a] = new Punkt(x,y);
         System.out.println( "Die Koordinaten des " + n + " . Punktes lauten ( " + x + " / " + y + ")");
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
         int f=0;
         do{             
           beginn:
           for (int j=0; j<hindernisListe.size();j++) //
           {
               Rechteck jetzt = zufallsRechteck(hindernisListe.size()+1); //Zufallshindernisse werden erzeugt
               if (jetzt.ueberlappt(hindernisListe.get(j)))               
               {
                   f++;
                   if (f>50)
                   {
                       System.out.println (" Es gab zu viele Überschneidungen bei Rechtecken.");
                       return null;//komplette Funktion wird abgebrochen
                    }
                }
                else{
                    hindernisListe.add(jetzt); // wenn das Rechteck nicht überlappt wird es der Array-Liste hinzugefügt
                    f=0;
                }
            }
            
        }while(hindernisListe.size()<hindernisAnzahl); //so lange bis gewünschte Anzahl an Hindernissen erreicht ist
        return hindernisListe;
    }
      
    private Rechteck zufallsRechteck(int i)
      {
          int laenge = zufallszahl(1,100);
          int breite = zufallszahl(1,100);
          int positionX = zufallszahl(0,1000-breite);
          int positionY = zufallszahl(0,1000-laenge);
          String bezeichnung = "Rechteck " + i ;
          Punkt position = new Punkt(positionX, positionY);
          Rechteck jetzt = new Rechteck(position, breite, laenge, bezeichnung, zufallsfarbe());
          return jetzt;
        }
        
        private int zufallszahl( int von, int bis )
      {
         int range = bis - von;
         int zufallszahl = zufallsgenerator.nextInt(range) + von;
         return zufallszahl;
      }
    
      private Color zufallsfarbe()
      {
        Color zufallsfarbe  = new Color(zufallszahl(0,256),zufallszahl(0,256),zufallszahl(0,256));
        System.out.println( " Die zufällig generierte Farbe ist: " + zufallsfarbe.toString());
        return zufallsfarbe;
      }
}


