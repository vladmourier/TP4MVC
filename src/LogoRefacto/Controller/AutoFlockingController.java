/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.MovePatterns.FlockingMove;
import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;

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
        if(this.thread==null || !this.thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
        
    }
    
    

    @Override
    public void autoAction() {
         //Create new population to avoid multiple access from other threads
        PopulationTortue tortues = new PopulationTortue(getPopulation());
       
        for (Tortue t : tortues)
        {
            TortueFlocking tF = (TortueFlocking) t;
            doPatternTortue(t, new FlockingMove(tF, peuple));

        }
        
    }
    
    
    
}
