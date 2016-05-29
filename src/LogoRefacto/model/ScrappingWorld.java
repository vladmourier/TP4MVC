/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import LogoRefacto.model.Shapes.MovePattern;

/**
 *
 * @author Hassane
 */
public class ScrappingWorld extends World{

    public ScrappingWorld(PopulationTortue p, int width, int height) {
        super(p, width, height);
    }
    
    public ScrappingWorld(int width, int height) {
        super( width, height);
    }
    
    @Override
    public void avancerTortue(Tortue t, int dist ){
        System.out.println("Avancement scrappisé");
        Tortue habitant = habitants.getTortue(t);
        habitant.avancer(dist);
        int newX = habitant.getX();
        int newY = habitant.getY();
        habitant.setPosition(newX%worldWidth, newY%worldHeight);
    }
    
    @Override
    public void drawPatternTortue(Tortue t, MovePattern mp){
        Tortue habitant = habitants.getTortue(t);
        habitant.drawPattern(mp);
        int newX = habitant.getX();
        int newY = habitant.getY();
        habitant.setPosition(newX%worldWidth, newY%worldHeight);
    }
}
