package org.sid.business;

import org.sid.entities.Grille;

public interface MoteurJeuInterface {

	 public boolean isValide(Grille grille,int joueur,int x, int y);
	public boolean permetPrise(Grille grille, int tour_joueur, int ligne, int colonne,int sens_i, int sens_j);
		public int comptePoint(Grille grille) ;
	 public  void changerCouleur(int case_, int case_cible, int ligne, int colonne, int tour_joueur,Grille grille);
	 public  boolean dansGrille(int ligne,int colonne);
	 public void parcoursGrille(Grille grille,int tour_joueur,int ligne,int colonne,int sens_i,int sens_j);
	 public void effectuerCoup(Grille grille,int tour_joueur,int ligne,int colonne);
	public boolean isValide2(Grille grille,int joueur, int i, int j);


	}
