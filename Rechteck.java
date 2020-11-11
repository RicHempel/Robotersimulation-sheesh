
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
        position = new Punkt();
        breite = 0;
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
    
    public void setPosition ( int x, int y )
    {
        position.setBoth(x,y);
    }
    
    public Punkt getPosition()
    {
        return position;
    }
    
    public void setBreite( int breite )
    {
        this.breite = breite;
    }
    
    public int getBreite()
    {
        return breite;
    }
    
    public void setLaenge( int laenge)
    {
        this.laenge = laenge;
    }
    
    public int getLaenge()
    {
        return laenge;
    }
    
    public void setBezeichnung( String bezeichnung )
    {
        this.bezeichnung = bezeichnung;
    }
    
    public String getBezeichnung()
    {
        return bezeichnung;
    }
    
    public void setFarbe( Color farbe )
    {
        this.farbe = farbe;
        if ( farbe == java.awt.Color.white )
        { farbe = java.awt.Color.black;
            System.out.println ( "Das Rechteck darf nicht weiß sein. Bitte wählen Sie eine andere Farbe! ");
        }
    }
    
    public Color getFarbe()
    {
        return farbe;
    }
    
    public void bewegeUm( int dx, int dy ) // dx und dy müssen angegben werden
    {
        position.bewegeUm(dx,dy); // Punkt (x,y) deshalb beides gleichzeitig
    }
    
    public void bewegeUm( Punkt verschiebevektor )
    {
        int x1 = verschiebevektor.getX();
        int y1 = verschiebevektor.getY();
        position.bewegeUm(x1,y1); 
    }
    
    public void ausgabeAttribute()
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
    } 
    
}

