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

    private int  n0, k, a;
    private static int n=-1;

    public Spiral(int n, int k, int a) {
        if(this.n==-1) this.n = n;
        this.n0 = n;
        this.k = k;
        this.a = a;
    }

    @Override
    public void moveTurtle(Tortue t) {
        t.avancer(n);
        t.droite(360/a);
        n = n + 1;   
    }

    public void reset()
    {
        this.n= n0;
    }
            
}
