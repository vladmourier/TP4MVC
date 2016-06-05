/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.MovePatterns.RandomPattern;
import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;

/**
 * Controleur gérant les tortues auto (bougeant aléatoirement)
 *
 * @author Vlad & Hassane
 */
public class AutoController extends AbstractPopulationController implements Runnable {

    Thread thread;

    public AutoController(int width, int height) {
        super(width, height);

    }

    @Override
    public void initializePopulation() {
        getPopulation().clear();
        Tortue t = new Tortue();
        t.setPosition(500 / 2, 400 / 2);
        getPopulation().addTortue(t);
        thread = new Thread(this);
        thread.start();
        notifyView();
    }

    @Override
    public void addTortue(Tortue t) {
        getPopulation().addTortue(t);
        notifyView();
    }

    public void autoAction() {
        PopulationTortue p = new PopulationTortue(getPopulation());

        for (Tortue t : p) {
            doPatternTortue(t, new RandomPattern());

        }
    }

    @Override
    public void run() {
        boolean ok = true;
        while (ok) {
            try {
                autoAction();
                Thread.sleep(200);
            } catch (Exception ex) {
                ex.printStackTrace();
                ok = false;
            }
        }
    }

}
