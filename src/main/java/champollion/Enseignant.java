package champollion;

import static java.lang.Math.round;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Enseignant extends Personne {
    

    // hashmap avec l'UE et le nombre d'heures associées
    private final HashMap<UE, HashMap<TypeIntervention, Integer>> hashmap = new HashMap<>();
    private final Set<Intervention> myInterventions = new HashSet<>();;
    private final ServicePrevu myService = new ServicePrevu(0, 0, 0);

    public Enseignant(String nom, String email) {
        
        super(nom, email);
        
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        
        int nb_heures_totales = 0;
        nb_heures_totales = 
                hashmap.keySet().stream().map(ue -> heuresPrevuesPourUE(ue)).map(nb_heures -> nb_heures).reduce(nb_heures_totales, Integer::sum);
        
        return nb_heures_totales;
        
    }

    public Set<Intervention> getMyInterventions() {
        return myInterventions;
    }

    public ServicePrevu getMyService() {
        return myService;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        
            HashMap<TypeIntervention, Integer> myHours = hashmap.get(ue);
            float heuresCM = myHours.get(TypeIntervention.CM) * 1.5f;
            int heuresTD = myHours.get(TypeIntervention.TD);
            float heuresTP = myHours.get(TypeIntervention.TP) * 0.75f;
            int nb_heures = round(heuresCM + heuresTD + heuresTP);
            
            return nb_heures;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        
            myService.setVolumeTP(myService.getVolumeTP() + volumeTP);
            myService.setVolumeTD(myService.getVolumeTD() + volumeTD);
            myService.setVolumeCM(myService.getVolumeCM() + volumeCM);

            if (hashmap.get(ue) == null) {
                HashMap<TypeIntervention, Integer> myHours = new HashMap<>();
                myHours.put(TypeIntervention.TP, volumeTP);
                myHours.put(TypeIntervention.TD, volumeTD);
                myHours.put(TypeIntervention.CM, volumeCM);
                hashmap.put(ue, myHours);
            }
            else {
                HashMap<TypeIntervention, Integer> myHours = hashmap.get(ue);
                myHours.put(TypeIntervention.CM, myHours.get(TypeIntervention.CM) + volumeCM);
                myHours.put(TypeIntervention.TD, myHours.get(TypeIntervention.TD) + volumeTD);
                myHours.put(TypeIntervention.TP, myHours.get(TypeIntervention.TP) + volumeTP);
                hashmap.put(ue, myHours);
            }
            
    }
    
    
    public void ajouteIntervention(Intervention e) {
            myInterventions.add(e);
    }
    
    public int heuresPlanifiees() {
            int heuresPlanifiees = 0;
            for (Intervention intervention : myInterventions) {
                heuresPlanifiees += intervention.getDuree();
            }
            return heuresPlanifiees;
        
    }
    
    public boolean enSousService() {
        return heuresPrevues() < heuresPlanifiees();
    }
    
}
