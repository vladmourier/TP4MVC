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
public class TortueTest {

    Tortue t;

    public TortueTest() {
    }

    @Test
    public void testConstructeurParRecopie() {
        t = new Tortue();
        t.setPosition(0, 0);
        t.setDir(0);

        Tortue t2 = new Tortue(t);
        assertEquals(t2.getDir(), t.getDir());
        assertEquals(t2.getX(), t.getX());
        assertEquals(t2.getY(), t.getY());
        assertEquals(t2.trace.size(), t.trace.size());
    }

    @Test
    public void testAvancer() {
        t = new Tortue(0, 0, 0);
        t.avancer(10);
        assertEquals(t.getX(), 10);
        assertEquals(t.getY(), 0);
        assertEquals(t.getTrace().size(), 1);
    }

    @Test
    public void testAvancerNeg() {
        t = new Tortue(0, 0, 0);
        t.avancer(-5);
        assertEquals(t.getX(), -5);
        assertEquals(t.getY(), 0);
        assertEquals(t.getTrace().size(), 1);
    }
}
