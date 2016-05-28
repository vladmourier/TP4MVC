/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.Shapes.Carre;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.Polygone;
import LogoRefacto.model.Shapes.Spiral;
import LogoRefacto.model.Tortue;
import java.util.Random;

/**
 *
 * @author Vlad
 */
public class AutoController extends AbstractPopulationController {

    private MovePattern chooseRandomPattern() {
        MovePattern mp;
        Random r = new Random();
        switch (r.nextInt(3)) {
            case 0:
                mp = new Carre();
                break;
            case 1:
                mp = new Polygone(30, 8);
                break;
            case 2:
                mp = new Spiral(3, 10, 4);
                break;
                default:mp = new Spiral(1, 1, 1);
        }
        return mp;
    }

    @Override
    public void addTortue(Tortue t) {
        populationTortue.addTortue(t);
        itTortue = populationTortue.iterator();
        autoMoveTortue(t, chooseRandomPattern());
        notifyView();
    }

}
