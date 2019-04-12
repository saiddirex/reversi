package org.sid.controller;

import java.util.List;

import org.sid.business.MoteurJeuInterface;
import org.sid.data.JoueurRepository;
import org.sid.entities.Joueur;
import org.sid.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoueurController {
	
	@Autowired
	private MoteurJeuInterface MoteurJeuInt;
	@Autowired
	private JoueurRepository joueurRepository;
	
	@RequestMapping ("/joueurs")
	public String getAllJoueurs(Model model) {
		List<Joueur> listeJoueurs=MoteurJeuInt.getAllJoueur();
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		Joueur user=joueurRepository.findByUsername(username);
		model.addAttribute("username",user.getUsername());
		model.addAttribute("listeJoueurs",listeJoueurs);
		return "listeJoueurs";
	}

	
	@RequestMapping ("/addJoueur")
	public String addJoueur(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		Joueur user=joueurRepository.findByUsername(username);
		model.addAttribute("username",user.getUsername());
		return "addJoueur";
	}
	
	
	
	@RequestMapping (value="/saveJoueur" , method=RequestMethod.POST)
	public String saveJoueur(Model model,String nom,String prenom,String username,String password) {
	
		try {
			MoteurJeuInt.addJoueur(nom, prenom, username, password);
		}catch(Exception e) {
			
			model.addAttribute("error", e);
			//pour afficher le message d'erreur
			return "redirect:/addJoueur?error="+e.getMessage();
		}
	
		return "redirect:/joueurs";
	}
	
	
	
	@RequestMapping ("/nouvellePartie")
	public String newPartie( ) {
		
		return "redirect:/main";
	}


}
