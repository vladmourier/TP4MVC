package LogoRefacto.model;

// package logo;

import java.util.*;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin implements Iterator<Tortue> {
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
    private Tortue courante;
    
    public FeuilleDessin() {
        tortues = new ArrayList<>();
    }
    
    public void addTortue(Tortue o) {
        tortues.add(o);
        if(courante == null) courante = o;
    }
    
    public void reset() {
        for (Iterator it = tortues.iterator();it.hasNext();) {
            Tortue t = (Tortue) it.next();
            t.reset();
        }
    }

    @Override
    public boolean hasNext() {
        return tortues.iterator().hasNext();
    }

    @Override
    public Tortue next() {
        Tortue t = tortues.iterator().next();
        courante = t;
        return courante;
    }

    public Tortue getCourante() {
        return courante;
    }
    
    
}
