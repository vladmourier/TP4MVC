/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.Shapes.Carre;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.Polygone;
import LogoRefacto.model.Shapes.RandomPattern;
import LogoRefacto.model.Shapes.Spiral;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.util.Random;

/**
 *
 * @author Vlad
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
                    doPatternTortue(t, new RandomPattern());
                
                 Thread.sleep(200);
            } catch (Exception ex) {
                ok = false;
            }
        }
    }

}
