/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.MovePatterns;

import LogoRefacto.model.Tortue;
import java.util.Random;

/**
 *
 * @author Vlad & Hassane
 */
public class RandomPattern extends MovePattern{

    @Override
    public void moveTurtle(Tortue t) {
        Random r = new Random();
        t.avancer(50);
        t.droite(r.nextInt(360));
    }
    
}
