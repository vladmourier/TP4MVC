/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.MovePatterns.MovePattern;
import LogoRefacto.model.Tortue;
import java.util.Observable;

/**
 *
 * @author Vlad & Hassane
 */
public abstract class AbstractController extends Observable {

    /**
     * Intitialise la population de tortue du controleur
     */
    public abstract void initializePopulation();

    /**
     * Crée une tortue pour la population de tortues courantes
     *
     * @param t
     */
    public abstract void addTortue(Tortue t);

    /**
     * Supprime une tortue de la population courante
     *
     * @param t
     */
    public abstract void removeTortue(Tortue t);

    /**
     * Fait avancer la tortue donnée de la distance donnée, dans la direction de
     * la tortue
     *
     * @param t
     * @param v
     */
    public abstract void avancerTortue(Tortue t, int v);

    /**
     * Pivote la tortue vers la droite de v degrés
     *
     * @param t
     * @param v
     */
    public abstract void droiteTortue(Tortue t, int v);

    /**
     * Pivote la tortue donnée vers la gauche de v degrés
     *
     * @param t
     * @param v
     */
    public abstract void gaucheTortue(Tortue t, int v);

    /**
     * Ordone à la tortue donnée d'exécuter le MovePattern passé en paramètre
     *
     * @param t
     * @param mp
     * @throws Exception
     */
    public abstract void doPatternTortue(Tortue t, MovePattern mp) throws Exception;

    /**
     * Retourne la tortue courante de la population
     *
     * @return
     */
    public abstract Tortue getTortueCourante();

    /**
     * Retourne la prochaine tortue de la population
     *
     * @return
     */
    public abstract Tortue nextTortue();

    /**
     * Renvoie la population de tortues du contrôleur
     *
     * @return
     */
    public abstract PopulationTortue getPopulation();

    /**
     * Renvoie la largeur du monde : zone dans laquelle la population de tortues
     * se déplace
     *
     * @return
     */
    public abstract int getWorldWidth();

    /**
     * Renvoie la hauteur du monde
     *
     * @return
     */
    public abstract int getWorldHeight();
}
