/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Chemin;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;
import static LogoRefacto.view.ITortueView.rb;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Arc2D;
import java.util.Iterator;

/**
 *
 * @author Vlad & Hassane
 */
public class FlockngTortueView extends ITortueView {

    TortueFlocking t;

    public FlockngTortueView(Tortue t) {
        this.t = new TortueFlocking();
        this.t.setDir(t.getDir());
        this.t.setPosition(t.getX(), t.getY());
        shape = new TriangleShape();
    }

    public FlockngTortueView(TortueFlocking t) {
        this.t = t;
        shape = new TriangleShape();
    }

    @Override
    protected void drawMore(Graphics graph) {
        drawRadar((Graphics2D) graph);
    }

    public void drawRadar(Graphics2D g2d) {
        Arc2D arc = new Arc2D.Double(
                t.getX() - t.getDistance_vision(),
                t.getY() - t.getDistance_vision(),
                t.getDistance_vision() * 2,
                t.getDistance_vision() * 2,
                -t.getDir() - t.getAngle_vision() / 2,
                t.getAngle_vision(),
                Arc2D.Double.PIE
        );
        Color c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), 128);
        g2d.setColor(c2);

        g2d.fill(arc);
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
