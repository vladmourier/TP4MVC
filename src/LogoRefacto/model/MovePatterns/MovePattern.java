/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.MovePatterns;

import LogoRefacto.model.Tortue;

/**
 *
 * @author Vlad & Hassane
 */
public abstract class MovePattern {
    public static final String CARRE = "CARRE";
    public static final String POLYGONE = "POLYGONE";
    public static final String SPIRALE = "SPIRALE";
    public static final String FLOCKING = "FLOCKING";
    
    
    
    
    public abstract int moveTurtle(Tortue t);

    
}
