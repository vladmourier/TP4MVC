/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.RandomPattern;
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
        autoMoveTortue(t, new RandomPattern());
        notifyView();
    }
}
