/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Tortue;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Vlad
 */
public interface ITurtleShape {

    public void drawTurtleBody(Graphics graph, Color c, Tortue t);
}
