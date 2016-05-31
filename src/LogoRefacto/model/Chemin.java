/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

/**
 *
 * @author Vlad & Hassane
 */
public class Chemin {

    Position origine;
    Position destination;

    public Chemin(Position origine, Position destination) {
        this.origine = origine;
        this.destination = destination;
    }

    public Position getOrigine() {
        return origine;
    }

    public void setOrigine(Position origine) {
        this.origine = origine;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

}
