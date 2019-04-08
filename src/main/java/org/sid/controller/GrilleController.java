package org.sid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.sid.business.MoteurJeuInterface;
import org.sid.entities.Grille;
import org.sid.entities.Joueur;

@RestController
@RequestMapping("/grille")
public class GrilleController {

	  
	    private Grille grille;

	    @Autowired
	    public GrilleController(Grille grille) {
	        this.grille = grille;
	    }
	 
	    @Autowired
	    private MoteurJeuInterface moteurJeuInt;

	
	  /*  public GrilleController(MoteurJeuInterface moteurJeuInt) {
	        this.moteurJeuInt=moteurJeuInt;
	    }*/

	    public GrilleController()
	    {
	        grille= new Grille();
	    }
	    
	    @GetMapping
	    public Grille grille() {
	    	return this.grille;
	    }
	    

	    @GetMapping("{id1}/{id2}")
	    public String displayQuote(@PathVariable(value = "id1") Integer id1,@PathVariable(value = "id2") Integer id2) {
	        //System.out.println(id1);
	       // System.out.println(id2);
	        
	        //Mécanique de jeu !!!
	        int joueur;
	        if(grille.tour<63) {
				if (grille.tour % 2 == 0) {//au debut tour==4
					joueur = 2;//noir
				} else {
					joueur = 1;//blanc
				}
				boolean valide = moteurJeuInt.isValide2(grille, joueur, id1, id2);
				if (valide) {
					if (grille.tour % 2 == 0) {
						grille.matrice[id1][id2] = 2;
						grille.tour++;

					} else {
						grille.matrice[id1][id2] = 1;
						grille.tour++;

					}

					moteurJeuInt.effectuerCoup(grille, joueur, id1, id2);
				}
			}
	        else
			{
				moteurJeuInt.comptePoint(grille);
			}
	       
	        //moteurJeuInt.changerCouleur(id1, id2, 3, 3, 2, grille);

	        return "<meta http-equiv='refresh' content='0; url=/main'>";
	    }
	    ModelAndView getGrille(String viewName) {
	        return new ModelAndView(viewName, "Grille", grille);
	    }
}
