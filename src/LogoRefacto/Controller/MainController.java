/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.MovePatterns.MovePattern;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;
import java.util.HashMap;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contrôleur principal de l'application
 * @author Vlad & Hassane
 */
public class MainController extends AbstractController {

    public static final String MODE_MANUEL = "MANUEL";
    public static final String MODE_AUTO = "AUTO";
    public static final String MODE_FLOCKING = "FLOCKING";

    private final int worldWidth;
    private final int worldHeight;

    private HashMap<String, AbstractPopulationController> controllers;
    private AbstractPopulationController currentController;

    public MainController(int width, int height) {
        controllers = new HashMap<>();
        this.worldHeight = height;
        this.worldWidth = width;
        //Creating two differents mode : auto and manual
        ManualController manC = new ManualController(width, height);
        AutoController autoC = new AutoController(width, height);
        AutoFlockingController autoFC = new AutoFlockingController(width, height);

        addController(MainController.MODE_AUTO, autoC);
        addController(MainController.MODE_MANUEL, manC);
        addController(MainController.MODE_FLOCKING, autoFC);
    }

    public void setMode(String mode) {
        for (String key : controllers.keySet()) {
            if (key == mode) {
                if (currentController != null) {
                    currentController.clearPopulation();
                }
                currentController = controllers.get(key);
                currentController.initializePopulation();
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

    public void addTortue(int x, int y, int dir) {
        Tortue t;
        if (currentController instanceof AutoFlockingController) {
            t = new TortueFlocking(x, y, dir);
        } else {
            t = new Tortue(x, y, dir);
        }
        addTortue(t);
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
        try {
            currentController.doPatternTortue(t, mp);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public int getWorldWidth() {
        return worldWidth;
    }

    @Override
    public int getWorldHeight() {
        return worldHeight;
    }
}
