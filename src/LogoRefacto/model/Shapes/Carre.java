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
public class Carre extends MovePattern {

    @Override
    public void move(Tortue t) {
        for (int i = 0; i < 4; i++) {
            t.avancer(100);
            t.droite(90);
        }
    }

}
