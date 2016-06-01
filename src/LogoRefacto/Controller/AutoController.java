/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.MovePatterns.Carre;
import LogoRefacto.model.MovePatterns.MovePattern;
import LogoRefacto.model.MovePatterns.Polygone;
import LogoRefacto.model.MovePatterns.RandomPattern;
import LogoRefacto.model.MovePatterns.Spiral;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.util.Random;

/**
 * Controleur gérant les tortues auto (bougeant aléatoirement)
 * @author Vlad & Hassane
 */
public class AutoController extends AbstractPopulationController implements Runnable {

    Thread thread;
    
    public AutoController(int width, int height) {
        super(width, height);
        thread = new Thread(this);
       
    }

    @Override
    public void initializePopulation() {
        getPopulation().clear();
        Tortue t = new Tortue();
        t.setPosition(500 / 2, 400 / 2);
        getPopulation().addTortue(t);
        this.thread.start();
        notifyView();
    }


    @Override
    public void addTortue(Tortue t) {
        getPopulation().addTortue(t);
        notifyView();
    }

    @Override
    public void run() {
        boolean ok = true;
        while (ok) {
            try {
                for (Tortue t : getPopulation())
                {
                    doPatternTortue(t, new RandomPattern());
                    
                }
                
                 Thread.sleep(1000); //200
            } catch (Exception ex) {
                System.err.println(ex.getLocalizedMessage());
                ok = false;
            }
        }
    }

}
