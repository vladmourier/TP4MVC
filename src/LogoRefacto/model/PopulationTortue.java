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

public class PopulationTortue implements Iterable<Tortue> {
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
    private Iterator it;
    private Tortue courante=null;
    
    public PopulationTortue() {
        tortues = new ArrayList<>();
        it = tortues.iterator();
    }
    
    public void addTortue(Tortue o) {
        tortues.add(o);
    }
    
    public void reset() {
        for (Iterator it = tortues.iterator();it.hasNext();) {
            Tortue t = (Tortue) it.next();
            t.reset();
        }
        it = tortues.iterator();
        courante = (Tortue) it.next();
    }

   

    public Tortue getCourante() {
       if(courante == null)
           courante = (Tortue) it.next();
       return courante;
    }

    @Override
    public Iterator<Tortue> iterator() {
        return tortues.iterator();
    }

    /**
     * Get a turtle (by his label)
     * @param t
     * @return 
     */
    public Tortue getTortue(Tortue t) {
        int index = tortues.indexOf(t);
        return index>-1 ? tortues.get(index) : null;
    }

    public int size() {
        return tortues.size();
    }

    public void clear() {
        tortues.clear();
        it=tortues.iterator();
        courante = null;
    }

    public boolean contains(Tortue t) {
        return tortues.contains(t);
    }
    
    
    
}
