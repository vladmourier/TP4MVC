/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Vlad
 */
public class Segment {

    private Point ptStart, ptEnd;
    private Color color = Color.BLACK;

    public Segment() {
        ptStart = new Point(0, 0);
        ptEnd = new Point(0, 0);
    }

    public Segment(Point ptStart, Point ptEnd) {
        this.ptStart = ptStart;
        this.ptEnd = ptEnd;
    }

    public Point getPtStart() {
        return ptStart;
    }

    public void setPtStart(Point ptStart) {
        this.ptStart = ptStart;
    }

    public Point getPtEnd() {
        return ptEnd;
    }

    public void setPtEnd(Point ptEnd) {
        this.ptEnd = ptEnd;
    }
    
    

    public void drawSegment(Graphics graph) {
        if (graph == null) {
            return;
        }

        graph.setColor(color);
        graph.drawLine(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
