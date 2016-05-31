/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.Shapes.Carre;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.Polygone;
import LogoRefacto.model.Shapes.Spiral;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad & Hassane
 */
public class ProcedureBarListener implements ActionListener {

    AbstractController mainController;

    public ProcedureBarListener(AbstractController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        Field[] forName = MovePattern.class.getDeclaredFields();
        ArrayList<String> s = new ArrayList<>();
        for (Field f : forName) {
            s.add(f.getName());
        }
        if (s.contains(c)) {
            try {
                mainController.doPatternTortue(mainController.getTortueCourante(), getPatternMove(c));

            } catch (Exception ex) {
                Logger.getLogger(MainListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private MovePattern getPatternMove(String pattern) throws Exception {
        MovePattern mp = null;
        switch (pattern) {
            case Carre.CARRE:
                mp = new Carre();
                break;
            case Carre.POLYGONE:
                mp = new Polygone(60, 8);
                break;
            case Carre.SPIRALE:
                mp = new Spiral(50, 40, 6);
                break;
        }
        return mp;
    }
}
