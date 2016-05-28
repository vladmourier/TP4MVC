/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Tortue;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author Vlad
 */
public abstract class AbstractPopulationController extends AbstractController {

    protected PopulationTortue populationTortue;
    protected Iterator<Tortue> itTortue;
    protected Tortue tortueCourante;

    public AbstractPopulationController() {
        populationTortue = new PopulationTortue();
        itTortue = populationTortue.iterator();
    }

    public Tortue getTortue(Tortue t) {
        return populationTortue.getTortue(t);
    }

    @Override
    public void addTortue(Tortue t) {
        populationTortue.addTortue(t);
        itTortue = populationTortue.iterator();
        notifyView();
    }

    @Override
    public void removeTortue(Tortue t) {
        populationTortue.removeTortue(t);
        itTortue = populationTortue.iterator();
        notifyView();
    }

    @Override
    public void avancerTortue(Tortue t, int dist) {
        getTortue(t).avancer(dist);
        notifyView();
    }

    @Override
    public void droiteTortue(Tortue t, int dist) {
        getTortue(t).droite(dist);
        notifyView();
    }

    @Override
    public void gaucheTortue(Tortue t, int dist) {
        getTortue(t).gauche(dist);
        notifyView();
    }

    @Override
    public void doPatternTortue(Tortue t, MovePattern mp) throws Exception {
        getTortue(t).drawPattern(mp);
        notifyView();
    }

    @Override
    public Tortue nextTortue() {
        if (itTortue.hasNext()) {
            tortueCourante = itTortue.next();
        } else if (populationTortue.size() > 0) {
            itTortue = populationTortue.iterator();
            tortueCourante = itTortue.next();
        } else {
            tortueCourante = null;
        }
        return tortueCourante;
    }

    @Override
    public Tortue getTortueCourante() {
        return tortueCourante;

    }

    protected void notifyView() {
        setChanged();
        notifyObservers();
    }

    @Override
    public void initializePopulation() {
        populationTortue.clear();
        tortueCourante = new Tortue();
        tortueCourante.setPosition(500 / 2, 400 / 2);
        populationTortue.addTortue(tortueCourante);
        notifyView();
    }

    @Override
    public PopulationTortue getPopulation() {
        return populationTortue;
    }
}
