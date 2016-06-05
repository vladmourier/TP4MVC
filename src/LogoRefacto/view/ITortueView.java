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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsable du dessin des tortues et de leurs traces
 *
 * @author Vlad & Hassane
 */
public abstract class ITortueView {

    Color c = Color.BLACK;
    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche
    protected ITurtleShape shape;

    static int indexColor = 0;
    static final Color[] defaultsColors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.ORANGE, Color.ORANGE, Color.PINK, Color.RED};

    public ITortueView() {
        c = defaultsColors[indexColor++ % defaultsColors.length];
    }

    public void drawTurtle(Graphics graph) {
        if (graph == null) {
            return;
        }
        drawTurtleTrace(graph);
        shape.drawTurtleBody(graph, c, getTortue());
        drawMore(graph);
    }

    private void drawTurtleTrace(Graphics graph) {
        Tortue t = getTortue();
        List<Chemin> chemins = new ArrayList<>(t.getTrace());
        //Crée les segments
        for (Chemin chemin : chemins) {

            Segment seg = new Segment(
                    new Point(chemin.getOrigine().getX(), chemin.getOrigine().getY()),
                    new Point(chemin.getDestination().getX(), chemin.getDestination().getY()));
            seg.setColor(c);
            seg.drawSegment(graph);
        }
        
    }

    public abstract void updatePosition(int newX, int newY, int dir);

    public void reset() {
    }

    public void setColor(Color c) {
        this.c = c;
    }

    abstract void updateTortue(Tortue t);

    abstract public Tortue getTortue();

    protected abstract void drawMore(Graphics graph);
}
