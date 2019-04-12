package org.sid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import org.sid.business.MoteurJeuInterface;
import org.sid.controller.GrilleController;
import org.sid.data.JoueurRepository;
import org.sid.entities.Grille;
import org.sid.entities.Joueur;


@Controller
@RequestMapping("/main")
public class AppMainMVCController {


	 private GrilleController grilleController;
	  @Autowired
     private JoueurRepository joueurRepository;

	    @Autowired
	    public AppMainMVCController(GrilleController grilleController) {
	        this.grilleController = grilleController;
	    }
	    
	   

	    @GetMapping
	    public ModelAndView main(Model model) {

	    	Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    	String username = loggedInUser.getName();
			Joueur user=joueurRepository.findByUsername(username);
	    	model.addAttribute("username",user.getUsername());
	        return this.grilleController.getGrille("apps-grille");
	      
	    }

}
