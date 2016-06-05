/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import LogoRefacto.model.MovePatterns.MovePattern;

/**
 * Un monde à la géométrie toroïdale
 *
 * @author Hassane
 */
public class ScrappingWorld extends World {

    public ScrappingWorld(PopulationTortue p, int width, int height) {
        super(p, width, height);
    }

    public ScrappingWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public void avancerTortue(Tortue t, int dist) {
        int[] result = null;
        int x = t.getX(), y = t.getY();
        Tortue habitant = habitants.getTortue(t);
        habitant.avancer(dist);
        correctPosition(x, y, habitant, dist);
    }

    @Override
    public void drawPatternTortue(Tortue t, MovePattern mp) {
        Tortue habitant = habitants.getTortue(t);
        int x = t.getX(), y = t.getY();
        int dist = habitant.drawPattern(mp);
        correctPosition(x, y, habitant, dist);

    }

    private void correctPosition(int oldX, int oldY, Tortue habitant, int lastDist) {
        int newX = habitant.getX();
        int newY = habitant.getY();

        if (newX > worldWidth) {
            habitant.setPosition(oldX - worldWidth, oldY);
            habitant.avancer(lastDist);

        } else if (newX < 0) {
            habitant.setPosition(oldX + worldWidth, oldY);
            habitant.avancer(lastDist);
        }
        if (newY > worldHeight) {
            habitant.setPosition(oldX, oldY - worldHeight);
            habitant.avancer(lastDist);
        } else if (newY < 0) {
            habitant.setPosition(oldX, oldY + worldHeight);
            habitant.avancer(lastDist);
        }

    }
}
