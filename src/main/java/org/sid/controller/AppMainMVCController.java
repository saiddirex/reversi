package org.sid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.sid.business.MoteurJeuInterface;
import org.sid.controller.GrilleController;


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
