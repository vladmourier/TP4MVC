/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Tortue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Vlad
 */
public class TortueView extends JPanel implements Observer {

    Tortue t;
    protected ArrayList<Segment> listSegments; // Trace de la tortue

    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche

    public TortueView() {
        listSegments = new ArrayList<>();
    }

    public TortueView(Tortue t) {
        this();
        this.t = new Tortue(t);
        
    }

    public TortueView(ArrayList<Segment> listSegments) {
        this.listSegments = listSegments;
    }

    @Override
    public void update(Observable o, Object arg) {
        int x = t.getX();
        int y = t.getY();
        t = new Tortue((Tortue) o);
        Segment s = new Segment(new Point(x, y), new Point(t.getX(), t.getY()));
        listSegments.add(s);
        drawTurtle(getGraphics());
    }

    public void reset() {
        listSegments.clear();
    }

    public void drawTurtle(Graphics graph) {
        if (graph == null) {
            return;
        }

        // Dessine les segments
        for (Iterator it = listSegments.iterator(); it.hasNext();) {
            Segment seg = (Segment) it.next();
            seg.drawSegment(graph);
        }

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(t.getX(), t.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = Tortue.ratioDegRad * (-t.getDir());
        //Demi angle au sommet du triangle
        double alpha = Math.atan((float) rb / (float) rp);
        //Rayon de la fleche
        double r = Math.sqrt(rp * rp + rb * rb);
        //Sens de la fleche

        //Pointe
        Point p2 = new Point((int) Math.round(p.x + r * Math.cos(theta)),
                (int) Math.round(p.y - r * Math.sin(theta)));
        arrow.addPoint(p2.x, p2.y);
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(theta + alpha)),
                (int) Math.round(p2.y + r * Math.sin(theta + alpha)));

        //Base2
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(theta - alpha)),
                (int) Math.round(p2.y + r * Math.sin(theta - alpha)));

        arrow.addPoint(p2.x, p2.y);
        graph.setColor(Color.green);
        graph.fillPolygon(arrow);
        repaint();
    }

    public void avancer(int newX, int newY) {
//        if (t.isCrayon()==true) {
        Segment seg = new Segment();

        seg.ptStart.x = t.getX();
        seg.ptStart.y = t.getY();
        seg.ptEnd.x = newX;
        seg.ptEnd.y = newY;
//            seg.color = decodeColor(t.getCoul());

        listSegments.add(seg);
//        }
    }

    protected Color decodeColor(int c) {
        switch (c) {
            case 0:
                return (Color.black);
            case 1:
                return (Color.blue);
            case 2:
                return (Color.cyan);
            case 3:
                return (Color.darkGray);
            case 4:
                return (Color.red);
            case 5:
                return (Color.green);
            case 6:
                return (Color.lightGray);
            case 7:
                return (Color.magenta);
            case 8:
                return (Color.orange);
            case 9:
                return (Color.gray);
            case 10:
                return (Color.pink);
            case 11:
                return (Color.yellow);
            default:
                return (Color.black);
        }
    }
}
