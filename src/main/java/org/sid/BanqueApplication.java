package org.sid;

import java.util.Date;
import java.util.Optional;

import org.sid.data.GrilleRepository;
import org.sid.data.JoueurRepository;
import org.sid.data.PartieRepository;
import org.sid.data.RelationRepository;
import org.sid.data.RoleRepository;
import org.sid.entities.Grille;
import org.sid.entities.Joueur;
import org.sid.entities.Partie;
import org.sid.entities.Relation;
import org.sid.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan("org.sid")
@EntityScan("org.sid")
public class BanqueApplication implements CommandLineRunner{
	
	@Autowired
	private JoueurRepository joueurRepository;
	@Autowired
	private GrilleRepository grilleRepository;
	@Autowired
	private PartieRepository partieRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RelationRepository relationRepository;
	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Joueur j1=joueurRepository.save(new Joueur("el farkh","said","admin","$2a$10$/tEXFDmfefHgi7RX6vO30.BOrx2XmnwkPJaWd7LLJGbA222Vbb52W"));
		Joueur j2=joueurRepository.save(new Joueur("bourges","clement","user","$2a$10$/tEXFDmfefHgi7RX6vO30.BOrx2XmnwkPJaWd7LLJGbA222Vbb52W"));
		Joueur j3=joueurRepository.save(new Joueur("varane","rafael","varane","$2a$10$/tEXFDmfefHgi7RX6vO30.BOrx2XmnwkPJaWd7LLJGbA222Vbb52W"));
		Joueur j4=joueurRepository.save(new Joueur("ronaldo","christiano","ronaldo","$2a$10$/tEXFDmfefHgi7RX6vO30.BOrx2XmnwkPJaWd7LLJGbA222Vbb52W"));
		
		
		
		Grille g1=grilleRepository.save(new Grille());
		

		Partie p1=partieRepository.save(new Partie(j1,j2,g1));
		Partie p2=partieRepository.save(new Partie(j1,j3,g1));
		Partie p3=partieRepository.save(new Partie(j1,j4,g1));
		Partie p4=partieRepository.save(new Partie(j2,j3,g1));
		Partie p5=partieRepository.save(new Partie(j2,j4,g1));
		Partie p6=partieRepository.save(new Partie(j3,j4,g1));
		
		Role admin=roleRepository.save(new Role("ADMIN"));
		Role user=roleRepository.save(new Role("USER"));
		
		Relation rel1=relationRepository.save(new Relation(j1,admin));
		Relation rel2=relationRepository.save(new Relation(j1,user));
		
		Relation rel3=relationRepository.save(new Relation(j2,admin));
		Relation rel4=relationRepository.save(new Relation(j2,user));
		
		Relation rel6=relationRepository.save(new Relation(j4,admin));
		Relation rel5=relationRepository.save(new Relation(j4,user));

		Relation rel7=relationRepository.save(new Relation(j3,user));
		
		
		
		/*
		banqueMetier.verser("aa", 2000);
		banqueMetier.retirer("bb", 1000);
	  
		*/
		/*String hash = BCrypt.hashpw("1234", BCrypt.gensalt()); 
		System.out.println("le hash =     "+hash);*/
			
	}

}
