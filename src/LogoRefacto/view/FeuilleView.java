/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Tortue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author Vlad
 */
public class FeuilleView extends JPanel implements Iterator<TortueView> {

    private ArrayList<TortueView> tortues; // la liste des tortues enregistrees
    private TortueView courante;
    
    public FeuilleView() {
        this.tortues = new ArrayList<>();
    }

    public FeuilleView(ArrayList<TortueView> tortues) {
        this.tortues = tortues;
        courante = hasNext() ? this.next() : null;
    }

    public void addTortueView(Tortue t) {
        addTortueView(new TortueView(t));
    }

    public void addTortueView(TortueView t) {
        tortues.add(t);
        courante = courante == null ? t : courante;
    }

    public void reset() {
        for (TortueView t : tortues) {
            t.reset();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (Iterator it = tortues.iterator(); it.hasNext();) {
            TortueView t = (TortueView) it.next();
            t.drawTurtle(g);
        }
        repaint();
    }

    @Override
    public boolean hasNext() {
        return tortues.iterator().hasNext();
    }

    @Override
    public TortueView next() {
        return tortues.iterator().next();
    }

    public TortueView getCourante() {
        return courante;
    }
    
    
}
