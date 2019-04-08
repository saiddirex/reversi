package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Joueur implements Serializable {


	@Id
	private String username;
	private String password;
	private String nom;
	private String prenom;
	boolean etat;
	
	boolean actif;
	@OneToMany(mappedBy="joueur1",fetch=FetchType.LAZY)
	private Collection<Partie> Parties;
	@OneToMany(mappedBy="joueur",fetch=FetchType.LAZY)
    private Collection<Relation> relations;
	public Joueur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Joueur(String nom, String prenom, String username, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.actif=true;
		this.etat=false;
	}
	
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public Collection<Relation> getRelations() {
		return relations;
	}
	public void setRelations(Collection<Relation> relations) {
		this.relations = relations;
	}

	public Collection<Partie> getParties() {
		return Parties;
	}
	public void setParties(Collection<Partie> parties) {
		Parties = parties;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
