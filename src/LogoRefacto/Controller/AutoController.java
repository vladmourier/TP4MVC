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
public class AutoController extends AbstractPopulationController {

    private MovePattern chooseRandomPattern() {
        MovePattern mp;
        Random r = new Random();
        switch (r.nextInt(3)) {
            case 0:
                mp = new Carre();
                break;
            case 1:
                mp = new Polygone(r.nextInt(30), r.nextInt(30));
                break;
            case 2:
                mp = new Spiral(r.nextInt(30), r.nextInt(30), r.nextInt(30));
                break;
            default:
                mp = new Spiral(r.nextInt(30), r.nextInt(30), r.nextInt(30));
        }
        return mp;
    }

    @Override
    public void avancerTortue(Tortue t, int dist) {
        getTortue(t).avancer(dist);
        notifyView();
    }

    @Override
    public void initializePopulation() {
        populationTortue.clear();
        tortueCourante = new Tortue();
        tortueCourante.setPosition(500 / 2, 400 / 2);
        populationTortue.addTortue(tortueCourante);
        autoMoveTortue(tortueCourante, new RandomPattern());
        notifyView();
    }

    protected void autoMoveTortue(Tortue tortue, MovePattern mp) {
        Thread t = (new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ok = true;
                while (ok) {
                    try {
                        doPatternTortue(tortue, mp);
                        Thread.sleep(500);
                    } catch (Exception ex) {
                        ok = false;
                    }
                }

            }
        }));
        t.start();
    }

    @Override
    public void addTortue(Tortue t) {
        populationTortue.addTortue(t);
        itTortue = populationTortue.iterator();
        autoMoveTortue(t, new RandomPattern());
        notifyView();
    }

}
