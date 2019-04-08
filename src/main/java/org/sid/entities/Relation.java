package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Relation {
	@Id @GeneratedValue
    private long id_relation;
	 @ManyToOne
	 @JoinColumn(name="USERNAME")
	private Joueur joueur;
	 @ManyToOne
	 @JoinColumn(name="ROLE")
	private Role role;
	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Relation(Joueur joueur, Role role) {
		super();
		this.joueur = joueur;
		this.role = role;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	 
	
}
