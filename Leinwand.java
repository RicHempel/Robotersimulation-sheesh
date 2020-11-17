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
    private JFrame fenster = new JFrame();
    private Zeichenflaeche zeichenflaeche;
    int laenge,breite;
    /**
     * Konstruktor für Objekte der Klasse Leinwand
     */
    public Leinwand(int laenge, int breite)
    {
        this.laenge = laenge;
        this.breite = breite;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
}

class Zeichenflaeche extends JPanel{
    ArrayList<Rechteck> rechteckliste;
    public Zeichenflaeche(ArrayList<Rechteck> r)
    {
        this.rechteckliste = r;
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
