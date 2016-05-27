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
public class Spiral extends MovePattern {

    private int n, k, a;

    public Spiral(int n, int k, int a) {
        this.n = n;
        this.k = k;
        this.a = a;
    }

    @Override
    protected void move(Tortue t) {
        for (int i = 0; i < k; i++) {
            abstractController.avancerTortue(t, n);
            abstractController.droiteTortue(t, 360/a);
            n = n + 1;
        }
    }

}