import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Leinwand.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Leinwand
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    public JFrame fenster = new JFrame();
    public Zeichenflaeche zeichenflaeche;
    int laenge,breite;
    /**
     * Konstruktor für Objekte der Klasse Leinwand
     */
    public Leinwand(int laenge, int breite)
    {
        this.laenge = laenge;
        this.breite = breite;
        this.zeichenflaeche = new Zeichenflaeche();
    }

    public void warten(int ms){
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    public void zeichnen(ArrayList<Rechteck> hindernisse){
        if(zeichenflaeche == null){
            zeichenflaeche = new Zeichenflaeche(hindernisse);
            zeichenflaeche.repaintFiguren(hindernisse);
        }
        else{
            zeichenflaeche.repaintFiguren(hindernisse);
        }
    }
}

class Zeichenflaeche extends JPanel{
    public ArrayList<Rechteck> rechteckliste;
    public Zeichenflaeche(ArrayList<Rechteck> r)
    {
        this.rechteckliste = r;
    }
    
    public Zeichenflaeche(){
    
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<rechteckliste.size();i++){
            Rechteck r = rechteckliste.get(i);
            g.setColor(r.getFarbe());
            g.drawRect(r.getPosition().getX(),r.getPosition().getY(),r.getBreite(),r.getLaenge());
        }
    }
    
    public void repaintFiguren(ArrayList<Rechteck> figuren){
        this.rechteckliste = figuren;
        super.repaint();
    }
}
