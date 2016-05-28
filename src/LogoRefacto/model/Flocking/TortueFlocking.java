/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.Flocking;

import LogoRefacto.model.Tortue;

/**
 *
 * @author Vlad
 */
public class TortueFlocking extends Tortue {
    private final int distance_vision = 100; // distance en pixel jusqu'où la tortue peut voir
    private final int angle_vision = 180; // en degrés
    private final double vitesse=  0;
    
    public boolean canSee (Tortue t){
        //TODO
        return false;
    }
    
    
}
