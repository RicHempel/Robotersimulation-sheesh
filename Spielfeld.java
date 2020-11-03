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
           System.out.println( " X muss sich zwischen 0 udn 1000 befinden! Bitte wählen Sie erneut!");
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
    
     public ArrayList<Rechteck> hindernislisteErzeugen()
     {
        ArrayList<Rechteck> hindernisse = new ArrayList<Rechteck>();
        System.out.println( " Wie viele Hindernisse sollen sich auf dem Spielfeld befinden? ");
        Scanner scH = new Scanner(System.in);
        int aH = scH.nextInt(); // ah = Anzahl der Hindernisse
        System.out.println( "Auf dem Spielfeld sollen sich " + aH + " Hindernisse befinden.");
        zufallsgenerator = new Random();
        int i = 1; // i = das wievielte Rechteck wurde erzeugt
        int nxtFarbe;
        int zufallszahl; 
        int falsch = 0; //bisher überlappen sich 0 Rechtecke
        
         while( hindernisse.size() < aH ) // wenn die gesamte Fläche der Hindernisse < Anzahl der Hindernisse ist auf jeden Fall noch Platz
         {
             int x = zufallszahl(1000);
             int y = zufallszahl(1000);
             Punkt position1 = new Punkt ( x, y );
             int breite1 = zufallszahl(1000);
             int laenge1 = zufallszahl(1000);
             Hindernisse size1 = new Punkt ( breite , laenge );
             String bezeichung1 = "Rechteck" + i ;
             Color farbe = zufallsfarbe();
             Rechtech r = new Rechteck ( position1, breite1, laenge1, bezeichnung1, farbe1);
             
             if( position1.getX() + r.getBreite() < 1000 && position.getY() + r.getLaenge() < 1000)
              {
                 int a = 0;
                 bolean b = false;
                 int wahr = 0;
                 
                 if( 0 < hindernisse.size() )
                  {
                      while( a < hindernisse.size() )
                       {
                           b = r.ueberlappt(hindernisse.get(a));
                            if ( b == true)
                             {
                                 wahr++;
                                 if( wahr == hindernisse.size() )
                                  {
                                      hindernise.add(r);
                                      falsch = 0; // der Zähler für " wie viele falsche Hindernisse hintereinander " wird 0 gesetzt
                                      i++;
                                      break;
                                    }
                                }
                           a++;
                           
                            if ( b == false )
                             {
                                 falsch ++;
                                 break;
                                }
                        }
                 }        
            }
         }
         return hindernisse;
      }
      
      private int zufallszahl( int von, int bis )
      {
         int range = bis - von;
         int zufallszahl = zufallsgenerator.nextInt(range) + von;
         return zufallszahl;
      }
    
      private Color zufallsfarbe()
      {
        Random ranFarbe = new Random();
        Color zufallsfarbe  = ranFarbe.nextInt();
        System.out.println( " Die zufällig generierte Farbe ist: " );
        return zufallsfarbe;
      }
}


