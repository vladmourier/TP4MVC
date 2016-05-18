/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Vlad
 */
public class MainController implements ActionListener {

    PopulationTortue fcourante;
    MainView mainV;

    public MainController(PopulationTortue fcourante, MainView mainV) {
        this.fcourante = fcourante;
        this.mainV = mainV;
    }
    
    

    @Override
    /** la gestion des actions des boutons */
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(mainV.getInputValue());
                fcourante.getCourante().avancer(v);
                mainV.getFeuille().getCourante().avancer(fcourante.getCourante().getX(), fcourante.getCourante().getY());
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainV.getInputValue());
            }

        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(mainV.getInputValue());
                fcourante.getCourante().droite(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainV.getInputValue());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(mainV.getInputValue());
                fcourante.getCourante().gauche(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainV.getInputValue());
            }
//        } else if (c.equals("Lever")) {
//            fcourante.getCourante().leverCrayon();
//        } else if (c.equals("Baisser")) {
//            fcourante.getCourante().baisserCrayon();
        } // actions des boutons du bas
        else if (c.equals("Proc1")) {
            proc1();
        } else if (c.equals("Proc2")) {
            proc2();
        } else if (c.equals("Proc3")) {
            proc3();
        } else if (c.equals("Effacer")) {
            effacer();
        } else if (c.equals("Quitter")) {
            quitter();
        }
        mainV.repaint();
    }

    /** les procedures Logo qui combine plusieurs commandes.. */
    public void proc1() {
        fcourante.getCourante().carre();
    }

    public void proc2() {
        fcourante.getCourante().poly(60, 8);
    }

    public void proc3() {
        fcourante.getCourante().spiral(50, 40, 6);
    }

    public void createTortue() {
        // Creation de la tortue
        Tortue tortue = new Tortue();

        // Deplacement de la tortue au centre de la feuille
        tortue.setPosition(500 / 2, 400 / 2);
        fcourante.addTortue(tortue);
        mainV.getFeuille().addTortueView(tortue);
    }

    // efface tout et reinitialise la feuille
    public void effacer() {
        mainV.reset();
        mainV.repaint();

        // Replace la tortue au centre
        Dimension size = mainV.getSize();
        fcourante.getCourante().setPosition(size.width / 2, size.height / 2);
    }

    private void quitter() {
        System.exit(0);
    }
}
