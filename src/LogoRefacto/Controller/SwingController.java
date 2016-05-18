/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Vlad
 */
public class SwingController implements ActionListener {

    private MainController mc;

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals(MainView.CMD_BTN_AVANCER)) {
        //TODO
        } else if (c.equals(MainView.CMD_BTN_DROITE)) {
            //TODO
        } else if (c.equals(MainView.CMD_BTN_GAUCHE)) { 
            //TODO
        } else if (c.equals(MainView.CMD_BTN_LEVER)) {
            //TODO
        } else if (c.equals(MainView.CMD_BTN_BAISSER)) {
            //TODO
        } else if (c.equals(MainView.CMD_BTN_EFFACER)) {
            //TODO
        } else if (c.equals(MainView.CMD_BTN_QUITTER)) {
            //TODO
        } // actions des boutons du bas
        else if (c.equals("Proc1")) {
        } else if (c.equals("Proc2")) {
        } else if (c.equals("Proc3")) {

        }
    }

    }
