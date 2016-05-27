/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.Shapes;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.Tortue;

/**
 *
 * @author Vlad
 */
public abstract class MovePattern {
    public static final String CARRE = "CARRE";
    public static final String POLYGONE = "POLYGONE";
    public static final String SPIRALE = "SPIRALE";
    
    protected AbstractController abstractController;
    
    public void moveTurtle(Tortue t) throws Exception{
        if(abstractController == null) throw new Exception("Erreur, aucun contrôleur pour cette action");
        move(t);
    }
    
    protected abstract void move(Tortue t);

    public void setAbstractController(AbstractController abstractController) {
        this.abstractController = abstractController;
    }
}
