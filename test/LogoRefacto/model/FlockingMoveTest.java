/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import LogoRefacto.model.MovePatterns.FlockingMove;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vlad
 */
public class FlockingMoveTest {

    FlockingMove move;
    World map;
    TortueFlocking t;

    public FlockingMoveTest() {
        t = new TortueFlocking(5, 5, 0);
        map = new World(500,900);
        map.addTortue(t);
        move = new FlockingMove(map);
    }

    @Test
    public void testcanSee() {
        TortueFlocking t2 = new TortueFlocking(4, 5, 0);
        map.addTortue(t2);

        assertTrue(move.canSee(t2, t));
        assertFalse(move.canSee(t, t2));
    }

    @Test
    public void testcanSeeLimit() {
        TortueFlocking t2 = new TortueFlocking(205, 5, 0);
        map.addTortue(t2);

        assertTrue(move.canSee(t2, t));
    }
    
}
