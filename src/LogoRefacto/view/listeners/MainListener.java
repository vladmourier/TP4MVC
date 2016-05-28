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
import java.awt.event.ActionListener;

/**
 *
 * @author Vlad
 */
public class MainListener implements ActionListener {

    MainController mainController;
    AbstractPopulationController populationController;
    MainView mainView;

    ActionEvent current;

    public MainListener(MainController mc, MainView mainV) {
        mainController = mc;
        this.mainView = mainV;
        mainController.setMode(selectMode());
        populationController = mainController.getCurrentController();
    }

    public void update(Tortue tortueCourante) {
        String c = current.getActionCommand();
        if (c.equals(MainView.CMD_QUITTER)) {
            mainController.closeApplication();
        }
    }

    @Override
    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        current = e;
//        update(mainController.getTortueCourante());
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
