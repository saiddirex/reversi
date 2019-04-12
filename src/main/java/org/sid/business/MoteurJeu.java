package org.sid.business;

import java.util.List;

import org.sid.data.GrilleRepository;
import org.sid.data.JoueurRepository;
import org.sid.data.PartieRepository;
import org.sid.entities.Grille;
import org.sid.entities.Joueur;
import org.sid.entities.Partie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class MoteurJeu implements MoteurJeuInterface{

	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	} 
	
	@Autowired 
	PasswordEncoder passwordEncoder; 
	@Autowired
	 private JoueurRepository joueurRepository;

	@Autowired
	 private PartieRepository partieRepository;

	@Autowired
	 private GrilleRepository grilleRepository;
	
	 public int tour= 4;

     
	 
	 
	 @Override
	 public List<Joueur> getAllJoueur(){
		 List<Joueur> listJoueurs;
		 listJoueurs=joueurRepository.findAll();
		 return listJoueurs;
	 }
	 
	 @Override
	 public Grille saveGrille(Grille grille){
		 Grille maGrille;
		 maGrille=grilleRepository.saveAndFlush(grille);
		 return maGrille;
	 }
	 
	 @Override
	 public Partie savePartie(Partie partie){
		 Partie maPartie;
		 maPartie=partieRepository.saveAndFlush(partie);
		 return maPartie;
	 }
	 
	 
	 @Override
	 public Joueur addJoueur(String nom,String prenom, String username, String password){
         Joueur user=new Joueur(nom, prenom, username, password);
         String encryptedPassword = passwordEncoder.encode(password); 
         user.setPassword(encryptedPassword); 
         boolean j=joueurRepository.existsById(username);
         if(j) throw new RuntimeException("username deja utilise");
		 joueurRepository.saveAndFlush(user);
		 return user;
	 }
	 
	 
	 /* On part du principe qu'au tour 5 c'est les noir qui commencent 
	 Les pions blanc sont representé par un 1 dans la matrice 
	 Les pions noir sont representé par un 2 dans la matrice .
	 3 - tour_joueur : permet de calculer le pion adverse
	 */ 
	 
	 
     @Override
	 public boolean permetPrise(Grille grille, int tour_joueur, int ligne, int colonne,int sens_i, int sens_j){
		 boolean valide=false;
		 int i = ligne;
		 int j = colonne;
		 do {
			 i += sens_i;
			 j += sens_j;
		 } while ((dansGrille(i,j)) && (grille.matrice[i][j] == 3 - tour_joueur));
		 if ((dansGrille(i, j)) && (grille.matrice[i][j] == tour_joueur)) {
			 valide = true;
		 }
		 return valide;
	 }

     
     
     /*Verifie qu'un coup est valide 
     Le joueur est le joueur qui veut poser un pion(soit 1 ou 2}
     x,y est la position ou joueur veut placer un pion
     une position est valide si
      -contient un O
      -adjascente a un pion adverse dans les huit direction
      -permet de reprendre au moins un pion adverse
      
      */
     
	 @Override
	public boolean isValide2(Grille grille,int joueur, int i, int j){
		boolean valide = false;
		if (grille.matrice[i][j]==0) {
			int coupPossible[] = {-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};
			for (int k = 0; k < 16; k += 2) {
				if (dansGrille(i + coupPossible[k], j + coupPossible[k + 1]) && (grille.matrice[i + coupPossible[k]][j + coupPossible[k + 1]] == 3 - joueur)) {
					if (permetPrise(grille, joueur, i, j, coupPossible[k], coupPossible[k + 1])) {
						valide = true;
					}
				}
			}
		}
		return valide;
	}


	 
	 
	 
	 //calculer aui gagne
	 @Override
	 public int comptePoint(Grille grille) {
		 int somme_j1 = 0;
		 for (int i = 0; i < grille.matrice.length; i++) {
			 for (int j = 0; j < grille.matrice.length; j++) {
				 if (grille.matrice[i][j] == 1) {
					 somme_j1 += 1;
				 }
			 }
		 }
		 return somme_j1;
	 }

	 
	 
	 
	 //changer la couleur entre deux pions
	 @Override
	 public  void changerCouleur(int case_, int case_cible, int ligne, int colonne, int tour_joueur,Grille grille) {
		 System.out.println("fonction changer couleurs");   
		 int pas;
	        if (ligne*colonne==1){
	            pas =9;
	        } else if (ligne*colonne==-1) {
	                pas = 7;
	        } else if (ligne ==0){
	            pas = 1;
	        } else {
	            pas = 8;
	        }
	        for (int i=Math.min(case_,case_cible);i<Math.max(case_,case_cible)&&i<64;i+=pas){
	            System.out.println(Math.min(case_,case_cible));
	            System.out.println(Math.max(case_,case_cible));
	            System.out.println("je change de couleurs.........");
	            if (dansGrille(i/8,i%8)) {
	                grille.matrice[i / 8][i % 8] = tour_joueur;
	            }
	        }

	    }
	 
	 
	 
	 //verifier qu'un coup est dans la grille
	 @Override
	 public  boolean dansGrille(int ligne,int colonne){
		 return !((ligne < 0) || (ligne > 7) || (colonne < 0) || (colonne > 7));
	 }

	    
	 
	 //parcourir la grille et changer la couleurs des pions 
	 @Override
	 public void parcoursGrille(Grille grille,int tour_joueur,int ligne,int colonne,int sens_i,int sens_j) {
	        int i=ligne;
	        int j=colonne;
	       do{
	            i += sens_i;
	            j += sens_j;
	        }  while ((dansGrille(i, j)) && (grille.matrice[i][j] == 3 - tour_joueur));
	        if ((dansGrille(i, j))&&(grille.matrice[i][j] == tour_joueur)) {
	            System.out.println("bas gauche, maintenant je vais changer de couleurs");
	            changerCouleur(i * 8 + j, ligne * 8 + colonne, sens_i, sens_j, tour_joueur, grille);
	     
	        }
	    }
	    
	    
	 //effectuer un coup
	 @Override
	 public void effectuerCoup(Grille grille,int tour_joueur,int ligne,int colonne){
	        int sens[] = {0,1,-1};
	        for (int i=0;i<3;i++){
	            for (int j=0;j<3;j++){
	                parcoursGrille(grille,tour_joueur,ligne,colonne,sens[i],sens[j]);
	            }
	        }
	    }
	 
	 
		/* @Override
	    public boolean isValide(Grille grille,int joueur,int x, int y) {
	        boolean valide = false;
	        int i=x;
	        int j=y;
	        int pion_adverse = 3 - joueur;  //Permet d'obtenir la valeur du pion adverse
	        if(grille.matrice[i][j]==1 || grille.matrice[i][j]==2) {
	        	throw new RuntimeException("case Invalide");
	        	valide=false;
	        }else if(i>=1 && i<=6 && j>=1 && j<=6) {
	        	 if(grille.matrice[i-1][j-1]==pion_adverse || grille.matrice[i+1][j+1]==pion_adverse 
	             		|| grille.matrice[i][j-1]==pion_adverse || grille.matrice[i][j+1]==pion_adverse
	             		|| grille.matrice[i-1][j]==pion_adverse || grille.matrice[i+1][j]==pion_adverse
	             		|| grille.matrice[i-1][j+1]==pion_adverse || grille.matrice[i+1][j-1]==pion_adverse)
	             {
	             	valide = true;
	             }
	        }else if(i==0 && j==0 ) {
	        	if( grille.matrice[i+1][j+1]==pion_adverse 
	             		|| grille.matrice[i][j+1]==pion_adverse || grille.matrice[i+1][j]==pion_adverse )
	             		
	             {
	             	valide = true;
	             }
	        }else if(i==0 && j==7) {
	        	if( grille.matrice[i+1][j-1]==pion_adverse 
	             		|| grille.matrice[i+1][j]==pion_adverse || grille.matrice[i][j-1]==pion_adverse )
	             		
	             {
	             	valide = true;
	             }
	        }else if(i==7 && j==7) {
	        	if( grille.matrice[i][j-1]==pion_adverse 
	             		|| grille.matrice[i-1][j-1]==pion_adverse || grille.matrice[i-1][j]==pion_adverse )
	             		
	             {
	             	valide = true;
	             }
	        }else if(i==7 && j==0) {
	        	if( grille.matrice[i-1][j]==pion_adverse 
	             		|| grille.matrice[i][j+1]==pion_adverse || grille.matrice[i-1][j+1]==pion_adverse )
	             		
	             {
	             	valide = true;
	             }
	        }else if(j==0 && i<=6 && i>=1) {
	        	if( grille.matrice[i-1][j+1]==pion_adverse 
	             		|| grille.matrice[i][j+1]==pion_adverse || grille.matrice[i+1][j+1]==pion_adverse 
	             		|| grille.matrice[i-1][j]==pion_adverse || grille.matrice[i+1][j]==pion_adverse)
	             		
	             {
	             	valide = true;
	             }
	        }else if(i==0 && j<=6 && j>=1) {
	        	if( grille.matrice[i+1][j-1]==pion_adverse 
	             		|| grille.matrice[i+1][j]==pion_adverse || grille.matrice[i+1][j+1]==pion_adverse
	             		|| grille.matrice[i][j+1]==pion_adverse || grille.matrice[i][j-1]==pion_adverse)
	             		
	             {
	             	valide = true;
	             }
	        }else if(j==7 && i<=6 && i>=1) {
	        	if( grille.matrice[i-1][j-1]==pion_adverse 
	             		|| grille.matrice[i][j-1]==pion_adverse || grille.matrice[i+1][j-1]==pion_adverse
	             		|| grille.matrice[i-1][j]==pion_adverse || grille.matrice[i+1][j]==pion_adverse)
	             		
	             {
	             	valide = true;
	             }
	        }else if(i==7 && j<=6 && j>=1) {
	        	if( grille.matrice[i-1][j-1]==pion_adverse 
	             		|| grille.matrice[i-1][j]==pion_adverse || grille.matrice[i-1][j+1]==pion_adverse
	             		|| grille.matrice[i][j+1]==pion_adverse || grille.matrice[i][j-1]==pion_adverse)
	             		
	             {
	             	valide = true;
	             }
	        }
	       
	        	
	        	return valide;
	        
	    }
		 */
}
	 
	 

