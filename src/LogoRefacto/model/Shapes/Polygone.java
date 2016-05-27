/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.Shapes;

import LogoRefacto.model.Tortue;

/**
 *
 * @author Vlad
 */
public class Polygone extends MovePattern{

    private int n, a;

    public Polygone(int n, int a) {
        this.n = n;
        this.a = a;
    }
    
    @Override
    protected void move(Tortue t) {
        for (int j = 0; j < a; j++) {
            abstractController.avancerTortue(t, n);
            abstractController.droiteTortue(t, 360/a);
        }    }
    
}
