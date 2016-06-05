/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vlad
 */
public class WorldTest {

    World w;

    public WorldTest() {
    }

    @Test
    public void testavancerTortue() {
        w = new World(10, 10);
        Tortue t1 = new Tortue(5, 5, 0), t2 = new Tortue(0, 0, 0);
        w.habitants.addTortue(t1);
        w.habitants.addTortue(t2);

        w.avancerTortue(t1, 1);
        w.avancerTortue(t2, 5);

        assertTrue(t1.getPosition().equals(new Position(6, 5)));
        assertTrue(t2.getPosition().equals(new Position(5, 0)));
    }

    @Test
    public void testDroiteTortue() {
        w = new World(10, 10);
        Tortue t1 = new Tortue(5, 5, 0), t2 = new Tortue(0, 0, 0), t3 = new Tortue(0, 0, 0);
        w.habitants.addTortue(t1);
        w.habitants.addTortue(t2);
        w.habitants.addTortue(t3);

        w.droiteTortue(t1, 0);
        w.droiteTortue(t2, 90);
        w.droiteTortue(t3, -30);

        assertEquals(t2.getDir(), 90);
        assertEquals(t1.getDir(), 0);
        assertEquals(t3.getDir(), 330);
        assertTrue(t1.getPosition().equals(new Position(5, 5)));
        assertTrue(t2.getPosition().equals(new Position(0, 0)));
    }

    @Test(expected = NullPointerException.class)
    public void testOperationTortueAbsente() {
        w = new World(10, 10);
        Tortue t1 = new Tortue(5, 5, 0);
        w.avancerTortue(t1, 5);
    }

}
