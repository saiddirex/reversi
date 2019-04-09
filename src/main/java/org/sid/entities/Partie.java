package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Partie implements Serializable {
    
	 @Id @GeneratedValue
	private long id_partie;
	 @ManyToOne
	 @JoinColumn(name="USERNAME1")
	private Joueur joueur1;
	 @ManyToOne
	 @JoinColumn(name="USERNAME2")
	private Joueur joueur2;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_GRILLE")
	private Grille grille;
	public Partie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Partie(Joueur joueur1, Joueur joueur2, Grille grille) {
		super();
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.grille = grille;
	}
	public long getId_partie() {
		return id_partie;
	}
	public void setId_partie(long id_partie) {
		this.id_partie = id_partie;
	}
	public Joueur getJoueur1() {
		return joueur1;
	}
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}
	public Joueur getJoueur2() {
		return joueur2;
	}
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}
	public Grille getGrille() {
		return grille;
	}
	public void setGrille(Grille grille) {
		this.grille = grille;
	}
	
	
	
}
