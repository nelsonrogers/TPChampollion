package champollion;

import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
        Salle B101;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");	
                B101 = new Salle("B101", 50);
                
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
                assertEquals(0, untel.heuresPlanifiees(),
                        "Un nouvel enseignant doit avoir 0 heures planifiées");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");
                
                untel.ajouteEnseignement(java, 0, 10, 0);
                
                assertEquals(10 + 10, untel.heuresPrevues(),
                        "L'enseignant doit maintenant avoir 20 heures de prévues");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}
        
        
        @Test
        public void testHeuresPlanifiees() {
                Date premierSept = new Date();
                int duree = 2;
                int duree2 = 4;
                        
                Intervention intervention1 = new Intervention(B101, java, untel, premierSept, duree);
                Intervention intervention2 = new Intervention(B101, java, untel, premierSept, duree2);
                untel.ajouteIntervention(intervention1);
                untel.ajouteIntervention(intervention2);
                
                assertEquals(2 + 4, untel.heuresPlanifiees());
        }
        
        @Test
        public void testAjouterIntervention() {
                Date premierSept = new Date();
                int duree = 2;
                int duree2 = 4;
                        
                Intervention intervention1 = new Intervention(B101, java, untel, premierSept, duree);
                Intervention intervention2 = new Intervention(B101, java, untel, premierSept, duree2);
                untel.ajouteIntervention(intervention1);
                untel.ajouteIntervention(intervention2);
                
                assertEquals(untel.getMyInterventions().size(), 2);
        }
        
        @Test
        public void testAjouterService() {
                untel.ajouteEnseignement(java, 1, 2, 4);
                assertEquals("heures de TP : 4, heures de TD : 2, heures de CM : 1", untel.getMyService().toString());
        }
	
        
        @Test
        public void testEnSousServiceV1() {
            
                Date premierSept = new Date();
                Date deuxSept = new Date();

                Intervention intervention1 = new Intervention(B101, java, untel, premierSept, 2);
                Intervention intervention2 = new Intervention(B101, java, untel, deuxSept, 4);
                untel.ajouteIntervention(intervention1);
                untel.ajouteIntervention(intervention2);
                
                untel.ajouteEnseignement(uml, 0, 2, 0);
                untel.ajouteEnseignement(java, 0, 3, 0);
                
                assertTrue(untel.enSousService());
            
        }
        
        
        @Test
        public void testEnSousServiceV2() {
            
                Date premierSept = new Date();
                Date deuxSept = new Date();
                        
                Intervention intervention1 = new Intervention(B101, java, untel, premierSept, 2);
                Intervention intervention2 = new Intervention(B101, java, untel, deuxSept, 4);
                untel.ajouteIntervention(intervention1);
                untel.ajouteIntervention(intervention2);
                
                untel.ajouteEnseignement(java, 0, 6, 0);
                
                
                assertEquals(untel.heuresPrevues(), untel.heuresPlanifiees());
                assertFalse(untel.enSousService());
            
        }

        
        
        
}
