/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;

/**
 *
 * @author Hassane
 */
public interface IView {
    
    public void clearViewx();
    public void closeWindow();
    public void displayPopulation(PopulationTortue t);
    public void updatePopulation(PopulationTortue t);
    public void updateTortue(Tortue t);
    public void addTortue(Tortue t);
    
            
}
