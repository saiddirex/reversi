package org.sid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import org.sid.business.MoteurJeuInterface;
import org.sid.controller.GrilleController;
import org.sid.entities.Grille;
import org.sid.entities.Joueur;


@Controller
@RequestMapping("/main")
public class AppMainMVCController {


	 private GrilleController grilleController;


	    @Autowired
	    public AppMainMVCController(GrilleController grilleController) {
	        this.grilleController = grilleController;
	    }
	    
	    
	    
	 /*   @Autowired
	    private MoteurJeuInterface moteurJeuInt;*/

	    @GetMapping
	    public ModelAndView main() {
	   
	        return this.grilleController.getGrille("apps-grille");
	      
	    }

}
