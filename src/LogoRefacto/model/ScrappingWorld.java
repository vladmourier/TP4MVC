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
public class ScrappingWorld extends World {

    public ScrappingWorld(PopulationTortue p, int width, int height) {
        super(p, width, height);
    }

    public ScrappingWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public int[] avancerTortue(Tortue t, int dist) {
        int[] result = null;
        System.out.println("Avancement scrappisé");
        int x = t.getX(), y = t.getY();
        Tortue habitant = habitants.getTortue(t);
        habitant.avancer(dist);
        int newX = habitant.getX();
        int newY = habitant.getY();

        if (newX > worldWidth) {
            result = new int[3];
            habitant.setPosition(worldWidth, newY);
            result[0] = worldWidth;
            result[1] = newY;
            result[2] = dist - ((int) Position.getDistance(new Position(x, y), new Position(result[0], result[1])));
        } else if (newX < 0) {
            result = new int[3];
            habitant.setPosition(0, newY);
            result[0] = 0;
            result[1] = newY;
            result[2] = dist - ((int) Position.getDistance(new Position(x, y), new Position(result[0], result[1])));
        }
        if (newY > worldHeight) {
            result = new int[3];
            habitant.setPosition(newX, worldHeight);
            result[0] = newX;
            result[1] = worldHeight;
            result[2] = dist - ((int) Position.getDistance(new Position(x, y), new Position(result[0], result[1])));

        } else if (newY < 0) {
            result = new int[3];
            habitant.setPosition(newX, 0);
            result[0] = newX;
            result[1] = 0;
            result[2] = dist - ((int) Position.getDistance(new Position(x, y), new Position(result[0], result[1])));
        }

        if (result != null) {
            habitant.setPosition(result[0], result[1]);
        }

        return result;
    }

    @Override
    public void drawPatternTortue(Tortue t, MovePattern mp) {
        Tortue habitant = habitants.getTortue(t);
        habitant.drawPattern(mp);
        int newX = habitant.getX();
        int newY = habitant.getY();
        habitant.setPosition(newX % worldWidth, newY % worldHeight);
    }
}
