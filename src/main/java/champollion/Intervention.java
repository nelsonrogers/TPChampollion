/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.Date;

/**
 *
 * @author nelsonrogers
 */
public class Intervention {
    
    private final Salle salle;
    private final UE ue;
    private final Enseignant enseignant;
    private final Date debut;
    private final int duree;

    public Intervention(Salle salle, UE ue, Enseignant enseignant, Date debut, int duree) {
        this.salle = salle;
        this.ue = ue;
        this.enseignant = enseignant;
        this.debut = debut;
        this.duree = duree;
    }

    public Salle getSalle() {
        return salle;
    }

    public UE getUE() {
        return ue;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }
    
    
    
    
    
}
