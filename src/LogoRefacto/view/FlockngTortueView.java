/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;
import static LogoRefacto.view.ITortueView.rb;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Iterator;

/**
 *
 * @author Vlad
 */
public class FlockngTortueView extends ITortueView{

    TortueFlocking t;

    public FlockngTortueView(Tortue t){
        this.t = new TortueFlocking();
        this.t.setDir(t.getDir());
        this.t.setPosition(t.getX(), t.getY());
    }
    public FlockngTortueView(TortueFlocking t) {
        this.t = t;
    }

    @Override
    public void drawTurtle(Graphics graph) {
        if (graph == null) {
            return;
        }

        //Crée les segments
        for (Tortue.Chemin chemin : t.getTrace()) {

            Segment seg = new Segment(
                    new Point(chemin.getOrigine().getX(), chemin.getOrigine().getY()),
                    new Point(chemin.getDestination().getX(), chemin.getDestination().getY()));
            seg.setColor(c);
            listSegments.add(seg);
        }
        // Dessine les segments
        for (Iterator it = listSegments.iterator(); it.hasNext();) {
            Segment seg = (Segment) it.next();
            seg.drawSegment(graph);
        }

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(t.getX(), t.getY());
        Polygon champVision = new Polygon();
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = Tortue.ratioDegRad * (-t.getDir());
        //bord supérieur champ de vision
        double vision1 = Tortue.ratioDegRad * (-((t.getDir()-(t.getAngle_vision()/2))));
        //bord inférieur champ de vision
        double vision2 = Tortue.ratioDegRad * (-((t.getDir()+(t.getAngle_vision()/2))));
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
        graph.setColor(c);
        graph.fillPolygon(arrow);
        
        champVision.addPoint(p.x, p.y);
        //bord supérieur
        Point p3 = new Point((int) Math.round(p.x + t.getDistance_vision() * Math.cos(vision1)), (int) Math.round(p.y - t.getDistance_vision() * Math.sin(vision1)));
        Point p4 = new Point((int) Math.round(p.x + t.getDistance_vision() * Math.cos(vision2)), (int) Math.round(p.y - t.getDistance_vision() * Math.sin(vision2)));
        champVision.addPoint(rp, rp);
        champVision.addPoint(p3.x, p3.y);
        champVision.addPoint(p4.x, p4.y);
        graph.setColor(Color.MAGENTA);
        graph.fillPolygon(champVision);
        
    }

    @Override
    public void updatePosition(int newX, int newY, int dir) {
        t.setDir(dir);
        t.setPosition(newX, newY);
    }

    @Override
    void updateTortue(Tortue t) {
        this.t.setDir(t.getDir());
        this.t.setPosition(t.getX(), t.getY());
    }

    @Override
    public Tortue getTortue() {
        return t;
    }
    
}
