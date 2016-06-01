/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.Shapes;

import LogoRefacto.model.Tortue;
import java.util.Random;

/**
 *
 * @author Vlad
 */
public class RandomPattern extends MovePattern{

    int defaultDistance = 25;
    int angle = 100;
    
    @Override
    public int moveTurtle(Tortue t) {
        Random r = new Random();
        t.droite(r.nextInt(angle)-angle/2);
        t.avancer(defaultDistance);
        return defaultDistance;
    }

    public int moveTurtle(Tortue t, int d) {
        Random r = new Random();
        t.droite(r.nextInt(angle)-angle/2);
        t.avancer(d);
        return d;
    }
    
}
