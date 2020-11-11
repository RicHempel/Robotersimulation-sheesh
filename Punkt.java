
import static java.lang.Math.sqrt; //Paket für Berechnung Quadratwurzel importieren
public class Punkt
{
    private int x;
    private int y;
    
    public Punkt()
    {
        // Instanzvariable initialisieren
        
    }
    
    public int getX()
    {
        return x; // Ausgabe von x
    }
    
    public int getY()
    {
        return y; // Ausgabe von y
    }
    
    public void setX ( int x )  //Computer fragt nach Eingabewert
    {
      this.x=x; //x muss definiert werden  
    }
    
    public void setY ( int y )
    {
        this.y=y; //y muss definiert werden
    }
    
    public void setBoth ( int x, int y ) // um beide Variablen auf einmal setzen zu können
    {
        this.x = x;
        this.y = y;
    }
    
    public void ausgabeAttribute()
    {
        System.out.print ("X = " + x + "Y = " + y);
    }
    
    public void bewegeUm ( int dx, int dy )
    {
        x = x + dx; // Überschreiben der x/y-Werte
        y = y + dy;
    }
    
    public double gibAbstand ( int x2, int y2 )
    {
        double d = sqrt(((x2-x)*(x2-x))+((y2-y)*(y2-y))); // Satz des Phytagoras
        return d; // Abstand ausgeben
    }
    
    public Punkt ( int x, int y ) // 2. Blatt - 1. Aufgabe
    {
        this.x = x;
        this.y = y;
    }
}
