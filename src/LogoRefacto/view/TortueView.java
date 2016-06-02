/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Chemin;
import LogoRefacto.model.Tortue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Vlad & Hassane
 */
public class TortueView extends ITortueView {

    Tortue t;

    public TortueView() {
        super();
        this.t = new Tortue();
        shape = new TriangleShape();
    }

    public TortueView(Tortue t) {
        super();
        this.t = new Tortue(t);
        shape = new TriangleShape();
    }
    
    
    @Override
    public void reset() {
        super.reset();
        t.reset();
    }

    @Override
    public void drawTurtle(Graphics graph) {
        if (graph == null) {
            return;
        }
        List<Chemin> chemins = new ArrayList<>(t.getTrace());
        //Crée les segments
        for (Chemin chemin : chemins) {

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

        shape.drawTurtleBody(graph, c, t);
    }

    @Override
    public void updatePosition(int newX, int newY, int dir) {
        t.setDir(dir);
        t.setPosition(newX, newY);
    }

    @Override
    public Tortue getTortue() {
        return t;
    }

    @Override
    void updateTortue(Tortue t) {
        this.t = t;
    }

}
