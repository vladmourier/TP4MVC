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
public class TortueFlockingTest {

    TortueFlocking t;

    public TortueFlockingTest() {
    }

    @Test
    public void testUpdateVitesse() {
        t = new TortueFlocking(0, 0, 0);
        t.avancer(5);
        assertEquals(t.getVitesse(), 5);
    }
}
