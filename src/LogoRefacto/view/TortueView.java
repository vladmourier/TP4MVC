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

/**
 *
 * @author Vlad
 */
public class TortueView {

    Tortue t;
    protected ArrayList<Segment> listSegments; // Trace de la tortue
    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche

    public TortueView() {
        listSegments = new ArrayList<>();
        this.t = new Tortue();
    }

    public TortueView(Tortue t) {
        listSegments = new ArrayList<>();
        this.t = new Tortue(t);
    }

    public void reset() {
        listSegments.clear();
        t.reset();
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
    }

    public void updatePosition(int newX, int newY, int dir, Color c) {
        c = new Color(c.getRed(), c.getGreen(), c.getBlue());
        Segment seg = new Segment();
        seg.getPtStart().x = t.getX();
        seg.getPtStart().y = t.getY();
        seg.getPtEnd().x = newX;
        seg.getPtEnd().y = newY;
        seg.setColor(c);

        listSegments.add(seg);

        t.setPosition(newX, newY);
        t.setDir(dir);
    }

    public Tortue getTortue() {
        return t;
    }

}
