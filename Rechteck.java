
import java.awt.Color;// muss für die Farben importiert werden

public class Rechteck
{
   
    private Punkt position;//"Punkt", weil x+y enthalten sind //links-oben
    private int breite; //x-Richtung
    private int laenge; //y-Richtung
    private String bezeichnung; //"string" weil es ein Word sein soll
    private Color farbe; // Farbe ( Color.red, Color.yellow ) 
    
    
        public Rechteck() // Anfangswerte definieren
        {
            position = new Punkt(); // die Klasse "Punkt" wird als Objekt erzeugt
            breite = 0; //alle Variabeln werden definiert
            laenge = 0;
            bezeichnung = "";  
            farbe = java.awt.Color.green;
        }

        public Rechteck ( Punkt position, int breite, int laenge, String bezeichnung, Color farbe )
        { // Benutzer soll Rechteck-Werte eingeben
            this.position = position;
            this.breite = breite;
            this.laenge = laenge;
            this. bezeichnung = "bezeichnung";
            this.farbe = farbe;
        }   
    
        public void setPosition ( int x, int y ) // die Werte des linken oberen Punktes des Rechtecks werden verändert
        {
            position.setBoth(x,y); // die Methode "setboth(x,y)" aus der Klasse Punkt wird verwendet
        }
    
        public Punkt getPosition() // Zugriff auf die Koordinaten 
        {
            return position;
        }
    
        public void setBreite( int breite ) // Veränderung der momentanen Breite des Rechtecks
        {
            this.breite = breite;
        }
    
        public int getBreite() // Zugriff auf die Breite
        {
            return breite;
        }
    
        public void setLaenge( int laenge) // die Länge des Rechtecks wird verändert
        {
            this.laenge = laenge;
        }
    
        public int getLaenge() // Zugriff auf die Länge
        {
            return laenge;
        }
    
        public void setBezeichnung( String bezeichnung ) // die Bezeichnung des Rechtecks wird verändert 
        {
            this.bezeichnung = bezeichnung;
        }
    
        public String getBezeichnung() // Zugriff auf die Bezeichnung. "String", da die Bezeichnung ein Wort ist
        {
            return bezeichnung;
        }
    
        public void setFarbe( Color farbe ) // die Farbe des Rechtecks wird verändert
        {
            if ( farbe == java.awt.Color.white ) // falls der Nutzer das Rechteck weiß haben will, soll die Konsole Folgendes ausgeben:
            { 
                System.out.println ( "Das Rechteck darf nicht weiß sein. Bitte wählen Sie eine andere Farbe! ");
                return;
            }
                this.farbe = farbe;
        }
    
        public Color getFarbe() // Zugriff auf die Farbe
        {
            return farbe;
        }
    
        public void bewegeUm( int dx, int dy ) // diese Methode verschiebt die aktuellen Koordinaten des Rechtecks um dx/dy Pixel
        {
            position.bewegeUm(dx,dy); // Punkt (x,y)... deshalb beides gleichzeitig
        }
    
        public void bewegeUm( Punkt verschiebevektor ) 
        {
            int x1 = verschiebevektor.getX();
            int y1 = verschiebevektor.getY();
            position.bewegeUm(x1,y1); 
        }
    
        public void ausgabeAttribute() // Die Methode soll alle Attribute des Rechtecks ausgeben
        {
            System.out.println ( "Position = " + position );
            System.out.println ( "Breite = " + breite );
            System.out.println ( "Laenge = " + laenge );
            System.out.println ( "Bezeichnung = " + bezeichnung );
            System.out.println ( "Farbe = " + farbe );
        }
    
        public boolean ueberlappt ( Rechteck r)
        {        
            return position.getX() < r.position.getX() + r.breite && position.getX() + breite > r.position.getX() && position.getY() < r.position.getY() + r.laenge && position.getY() + laenge > r.position.getY();
        } // 
    
}

