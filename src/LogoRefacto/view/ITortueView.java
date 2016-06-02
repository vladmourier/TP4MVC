/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Tortue;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Classe responsable du dessin des tortues et de leurs traces
 *
 * @author Vlad & Hassane
 */
public abstract class ITortueView {

    Color c = Color.BLACK;
    protected ArrayList<Segment> listSegments; // Trace de la tortue
    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche
    protected ITurtleShape shape;
    
    static int indexColor = 0;
    static final Color[] defaultsColors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.ORANGE, Color.ORANGE, Color.PINK,Color.RED };

    public ITortueView() {
        listSegments = new ArrayList<>();
        c = defaultsColors[indexColor++%defaultsColors.length];
    }

    public abstract void drawTurtle(Graphics graph);

    public abstract void updatePosition(int newX, int newY, int dir);

    public void reset() {
        listSegments.clear();
    }

    public void setColor(Color c) {
        this.c = c;
    }

    abstract void updateTortue(Tortue t);

    abstract public Tortue getTortue();
}
