/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Tortue;
import java.util.HashMap;
import java.util.Observer;

/**
 *
 * @author Vlad
 */
public class MainController extends AbstractController {

    public static final String MODE_MANUEL = "MANUEL";
    public static final String MODE_AUTO = "AUTO";

    private HashMap<String, AbstractPopulationController> controllers;
    private AbstractPopulationController currentController;

    public MainController() {
        controllers = new HashMap<>();
    }

    
    public void setMode(String mode) {
        for (String key : controllers.keySet()) {
            if (key == mode) {
                currentController = controllers.get(key);
            }
        }
    }

    public void setObservers(Observer o) {
        for (AbstractPopulationController apc : controllers.values()) {
            apc.addObserver(o);
        }
    }

    public void initialisePopulations() {
        for (AbstractPopulationController apc : controllers.values()) {
            apc.initializePopulation();
        }
    }

    public void addController(String mode, AbstractPopulationController apc) {
        controllers.put(mode, apc);
    }

    public AbstractPopulationController getCurrentController() {
        return currentController;
    }

    public void closeApplication() {
        System.exit(0);
    }

    @Override
    public void initializePopulation() {
        currentController.initializePopulation();
    }

    @Override
    public void addTortue(Tortue t) {
        currentController.addTortue(t);
    }

    @Override
    public void removeTortue(Tortue t) {
        currentController.removeTortue(t);
    }

    @Override
    public void avancerTortue(Tortue t, int v) {
        currentController.avancerTortue(t, v);
    }

    @Override
    public void droiteTortue(Tortue t, int v) {
        currentController.droiteTortue(t, v);
    }

    @Override
    public void gaucheTortue(Tortue t, int v) {
        currentController.gaucheTortue(t, v);
    }

    @Override
    public void doPatternTortue(Tortue t, MovePattern mp) {
        currentController.doPatternTortue(t, mp);
    }

    @Override
    public Tortue getTortueCourante() {

        return currentController.getTortueCourante();
    }

    @Override
    public Tortue nextTortue() {
        return currentController.nextTortue();
    }

    @Override
    public PopulationTortue getPopulation() {
        return currentController.getPopulation();
    }
}
