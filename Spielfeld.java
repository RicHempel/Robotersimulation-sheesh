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
     Roboter roboter = new Roboter(); // die Klasse Roboter wird als Objekt erzeugt
     
        public static void main(String[] args){
            Spielfeld aktuellesSpielfeld = new Spielfeld(); // ein neues Objekt der Klasse Spielfeld wird erzeugt
            Scanner scanner = new Scanner(System.in);
            System.out.println("Funktion auswählen:");
            System.out.println("1: Punkte abfahren lassen");
            System.out.println("2: Gespräch führen");
            String in = scanner.next(); 
                switch(in){
                    case "1": aktuellesSpielfeld.poiAbfahren();
                    case "2": aktuellesSpielfeld.poiSortieren(aktuellesSpielfeld.punkte);
                    case "3": aktuellesSpielfeld.hindernisListeErzeugen();
                    case "ENDE": System.exit(0);
                    default: System.out.println("Befehl nicht erkannt");
                }
        }
        
        public Spielfeld()
        {
            Roboter roboter = new Roboter();
        }
     
        public Punkt[] punkteEingeben() //Koordinaten müssen einzelnd eingegeben werden
        {
            System.out.println("Wie viele Punkte möchten Sie haben?");
            Scanner sc = new Scanner(System.in); // Nutzer gibt an wie viele Punkte er haben will
            int a = sc.nextInt(); // a ist die Anzahl der gewünschten Punkte. Am Ende der Methode wird von a runtergezählt um bei der Wiederholung den nächsten Punkt zu bearbeiten
            int n = 1; // n steht für Nummer des Punktes startend mit 1
            punkte = new Punkt[a]; // es werden "a" Punkte erzeugt?
            punkte[0] = new Punkt(0,0);
            System.out.println("Wie lauten die Koordinaten der " + a + " Punkte?");
                while (a>1) // für den Fall, dass noch Punkte übrig sind, deren Koordinaten angegeben werden müssen...
                {
                    int x = sc.nextInt(); // X-Koordinate des Punktes wird angegeben
                    while (x<=0 | x>=1000) // falls der Nutzer eine x-Koordinate angibt, die außerhalb des Spielfeldes liegt...
                    {
                         System.out.println("X muss sich zwischen 0 und 1000 befinden! Bitte wählen Sie erneut!");
                         x = sc.nextInt(); // Eingabe wird wiederholt
                    }
          
                    int y = sc.nextInt(); // Y-Koordinate des Punktes wird angegeben
                    while (y<=0 | y>=1000) // falls der Nutzer eine y-Koordinate angibt, die außerhalb des Spielfeldes liegt...
                    {
                        System.out.println("Y muss sich zwischen 0 und 1000 befinden! Bitte wählen Sie erneut!");
                        y = sc.nextInt(); // Eingabe wird wiederholt
                    }
                    punkte [a-1] = new Punkt(x,y); //  "[a-1], da das Programm bei "0" zu zählen anfängt, wir aber bei "1"
                    System.out.println("Die Koordinaten des " + n + ". Punktes lauten (" + x + "/" + y +")");
                    a--; // Anzahl der noch zu bearbeitenden Punkte wird mit "1" subtrahiert
                    n++; // die Nummer des nächsten zu bearbeitenden Punktes wird mit "1" addiert
                }      
            return punkte;
        }
    
        public void poiSortieren(Punkt[] poi)
        {
            int x;
            int y;
            int a = poi.length; //a = anzahl der Punkte
            double abstand;  
            int punktespeicher = 0; // die Variable "punktespeicher"
            int nächsterpunktspeicher = 1;
                for( int start = 0; start < a - 1; start++) // 
                {
                    double strecke = 1000000;
                    x = poi[start].getX();
                    y = poi[start].getY();
           
                    for( int nächster = start + 1; nächster < a; nächster++)
                    {
                        abstand = poi[nächster].gibAbstand(x, y);  // extrahiert Koordinaten vom "nächsten" Punkt und errechnet den Abstand                      
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
            ArrayList<Rechteck> hindernisListe = new ArrayList<Rechteck>(); // es wird eine neue Array-Liste erzeugt
            System.out.println( "Wie viele Hindernisse sollen sich auf dem Spielfeld befinden? ");
            Scanner sc1 = new Scanner(System.in); // Nutzer gibt die gewünschte Anzahl an Hidernissen ein
            int hindernisAnzahl = sc1.nextInt();
            System.out.println( "Es werden " + hindernisAnzahl + " Hindernisse zufällig generiert.");
            int fehler=0; // Variable "fehler" wird eingeführt um die Anzahl der aufeinander folgenden Überlappungen der Rechtecke zu zählen. startend bei 0
            for(int i=1; i<hindernisAnzahl+1; i++){ // das Programm fängt bei 1 an zu zählen. solange noch nicht die gewünschte Anzahl an Rechtecken in der ArrayList gespeichert ist wird i bei jeder Wiederholung um 1 erhöht
                Rechteck rechteck = zufallsRechteck(i); // ein zufälliges Rechteck wird erzeugt
                    if(!ueberlappt_test(rechteck, hindernisListe)){ // falls sich das neue Rechteck nicht mit Einem aus der ArrayList überlappt wird es der ArrayList hinzugeführt
                        hindernisListe.add(rechteck);
                        fehler = 0; // da nur aufeinanderfolgende Überlappungen gezählt werden sollen muss der Fehler-Counter wieder auf 0 gesetzt werden
                    }
                    else{ // falls sich das neue Rechteck mit einem der Anderen überlappt...
                        fehler++; // ... wird der Fehler-Counter um 1 erhöht...
                        i--; // ... und der Counter für die Rechtecke wird um 1 verkleinert um die gewünschte Anzahl der Rechtecke trotzdem zu erreichen
                        if(fehler>=50){ // falls sich 50 mal hintereinander das nue Rechteck mit einem Anderen überlappt... 
                            System.out.println("50 Überlappungen in Folge, Abbruch");
                            return null;// ...wird die Methode abgebrochen
                        }
                    }
            }
            return hindernisListe;
        }
      
        private Rechteck zufallsRechteck(int i)
        {
            int laenge = zufallszahl(1,100); // die zufällige Länge des i-ten Rechtecks im Bereich von 1-100 Pixel wird erzeugt 
            int breite = zufallszahl(1,100); // die zufällige Breite des i-ten Rechtecks im Bereich von 1-100 Pixel wird erzeugt
            int positionX = zufallszahl(0,1000-breite); // die x-Koordinate des linken oberen Punktes des Rechtecks wird zufällig erzeugt wobei beachtet werden muss, dass rechts neben dem Punkt noch genug Platz für die Breite des Rechtecks ist
            int positionY = zufallszahl(0,1000-laenge); // die y-Koordinate des linken oberen Punktes des Rechtecks wird zufällig erzeugt wobei beachtet werden muss, dass unter dem Punkt noch genug Platz für die Länge des Rechtecks ist
            String bezeichnung = "Rechteck" + i ; // i ist die Nummer des Rechtecks
            Punkt position = new Punkt(positionX, positionY);
            Rechteck jetzt = new Rechteck(position, breite, laenge, bezeichnung, zufallsfarbe());
            return jetzt;
        }
        
        private int zufallszahl( int von, int bis ) //Methode um zufällig Zahlen zu generieren
        {
            int zufallszahl = ThreadLocalRandom.current().nextInt(von, bis);  
            return zufallszahl;
        }
    
        private Color zufallsfarbe()
        {
            Color zufallsfarbe  = new Color(zufallszahl(0,256),zufallszahl(0,256),zufallszahl(0,256)); // Farben werden in Java mit RGB-Werten angegeben. Deshakb werden 3 Zahlen zufällig erzeugt
            System.out.println( " Die zufällig generierte Farbe ist: " + zufallsfarbe.toString()); // der RGB-Wert wird in den Namen der zugehörigen Farbe umgewandelt und auf der Konsole ausgegeben
            return zufallsfarbe;
        }
      
        private boolean ueberlappt_test(Rechteck r, ArrayList<Rechteck> hindernisListe){
            if(hindernisListe.size() < 1){return false;}
            for(int i=0; i<hindernisListe.size();i++){
                if(r.ueberlappt(hindernisListe.get(i))){ // das Programm kontrolliert ob sich das Rechteck r mit Einem aus der ArrayList überlappt
                    return true;
                }
            }
            return false;
        }
}


