/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.AbstractPopulationController;
import LogoRefacto.Controller.MainController;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.awt.event.ActionEvent;

/**
 *
 * @author Vlad
 */
public class MainListener extends CommandListener {

    AbstractPopulationController populationController;

    ActionEvent current;

    public MainListener(MainController mc, MainView mainV) {
        super(mc, mainV);
        String mode = selectMode();
        mainController.setMode(mode);
        if (mode.equals(MainController.MODE_AUTO)) {
            mainV.lockToolbar(true);
        } else {
            mainV.lockToolbar(false);
        }
        populationController = mainController.getCurrentController();
    }

    @Override
    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        current = e;
        super.actionPerformed(e);
    }

    public void createTortue() {
        // Creation de la tortue
        Tortue tortue = new Tortue();
        tortue.setPosition(500 / 2, 400 / 2);

        // Deplacement de la tortue au centre de la feuille
        populationController.addTortue(tortue);

    }

    public String selectMode() {
        return mainView.showChooseBox();
    }
}
