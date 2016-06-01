/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.MovePatterns.FlockingMove;
import LogoRefacto.model.MovePatterns.MovePattern;
import LogoRefacto.model.MovePatterns.RandomPattern;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Vlad
 */
public class AutoFlockingController extends AutoController {

    public AutoFlockingController(int width, int height) {
        super(width, height);
    }

    @Override
    public void initializePopulation() {
        getPopulation().clear();
        TortueFlocking t = new TortueFlocking();
        t.setPosition(500 / 2, 400 / 2);
        getPopulation().addTortue(t);
        this.thread.start();
        notifyView();
    }
    
    @Override
    public void run() {
        boolean ok = true;
        while (ok) {
            try {
                for (Tortue t : getPopulation())
                {
                    doPatternTortue(t, new FlockingMove(peuple.getPopulation()));
                    
                }
                
                 Thread.sleep(1000); //200
            } catch (Exception ex) {
                System.err.println(ex.getLocalizedMessage());
                ok = false;
            }
        }
    }
}
