
import static java.lang.Math.sqrt; //Paket für Berechnung Quadratwurzel importieren
public class Punkt
{
        private int x; //
        private int y;
    
        public Punkt()
        {
        
        }
    
        public int getX() // Zugriff auf den Wert von x
        {   
            return x; // Ausgabe von x
        }
    
        public int getY() // Zugriff auf den Wert von x
        {
            return y; // Ausgabe von y
        }
    
        public void setX ( int x )  // ändert den Wert von x
        {
            this.x=x; //x muss definiert werden  
        }
    
        public void setY ( int y ) // ändert den Wert von x
        {
            this.y=y; //y muss definiert werden
        }
    
        public void setBoth ( int x, int y ) // beide Variablen werden verändert
        {
            this.x = x;
            this.y = y;
        }
    
        public void ausgabeAttribute() // gibt die Koordinaten des aktuellen Punktes an
        {
            System.out.print ("X = " + x + "Y = " + y);//auf der Konsole wird ausgegeben: "X = (aktueller Wert von x)" und " Y = (aktueller Wert vom y)
        }
    
        public void bewegeUm ( int dx, int dy ) // verändert den wert von x und y um dx und dy Pixel
        {
            x = x + dx; // das aktuelle x wird überschrieben
            y = y + dy; // das aktuelle y wird überschrieben
        }
    
        public double gibAbstand ( int x2, int y2 ) // die Methode soll den Abstand zwischen dem ertsen und zweiten berechnen 
        {
        double d = sqrt(((x2-x)*(x2-x))+((y2-y)*(y2-y))); // Die Hypotenuse wird berechnet ( Abstand zwischen 2 Punkten )
        return d; // Abstand wird ausgeben
        }
    
        public Punkt ( int x, int y ) // ermöglicht die Bildung von Objekten der Klasse "Punkt"
        {
            this.x = x;
            this.y = y;
        }
}
