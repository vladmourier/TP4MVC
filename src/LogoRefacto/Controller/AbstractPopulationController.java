/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.ScrappingWorld;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.World;
import java.util.Iterator;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad
 */
public abstract class AbstractPopulationController extends AbstractController {

    protected World peuple;

    public AbstractPopulationController(int width, int height) {
        peuple = new ScrappingWorld(width, height);
    }

    public Tortue getTortue(Tortue t) {
        return getPopulation().getTortue(t);
    }

    @Override
    public void addTortue(Tortue t) {
        getPopulation().addTortue(t);
        notifyView();
    }

    @Override
    public void removeTortue(Tortue t) {
        getPopulation().removeTortue(t);
        notifyView();
    }

    @Override
    public void avancerTortue(Tortue t, int dist) {
        System.out.println(t.getX() + " | " + t.getY());
        peuple.avancerTortue(t, dist);
        /*while (res != null) {
            notifyView();
            if (res[0] == 0) {
                t.setPosition(peuple.getWidth(), t.getY());
            }
            if (res[1] == 0) {
                t.setPosition(t.getX(), peuple.getHeight());
            }
            if (res[0] == peuple.getWidth()) {
                t.setPosition(0, t.getY());
            }
            if (res[1] == peuple.getHeight()) {
                t.setPosition(t.getX(), 0);
            }
            notifyView(false);
            res = peuple.avancerTortue(t, res[2]);
            notifyView();
        }*/
        notifyView();
    }

    @Override
    public void droiteTortue(Tortue t, int dist) {
        peuple.droiteTortue(t, dist);
        notifyView();
    }

    @Override
    public void gaucheTortue(Tortue t, int dist) {
        peuple.gaucheTortue(t, dist);
        notifyView();
    }

    @Override
    public void doPatternTortue(Tortue t, MovePattern mp) throws Exception {
        getTortue(t).drawPattern(mp);
        notifyView();
    }

    @Override
    public Tortue nextTortue() {

        return getPopulation().nextTortue();
    }

    @Override
    public Tortue getTortueCourante() {
        return getPopulation().getCourante();

    }

    protected void notifyView() {
        notifyView(null);
    }
    
    protected void notifyView(Object object){
        setChanged();
        notifyObservers(object);
    }

    @Override
    public void initializePopulation() {
        getPopulation().clear();
        Tortue t = new Tortue();
        t.setPosition(500 / 2, 400 / 2);
        getPopulation().addTortue(t);
        notifyView();
    }

    @Override
    public PopulationTortue getPopulation() {
        return peuple.getPopulation();
    }

    @Override
    public int getWorldWidth() {
        return peuple.getWidth();
    }

    public int getWorldHeight() {
        return peuple.getHeight();
    }
}
