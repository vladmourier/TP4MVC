/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Tortue;
import java.awt.Graphics;

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

    @Override
    protected void drawMore(Graphics graph) {
    }

}
