package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Grille implements Serializable{
	 @Id @GeneratedValue
	 public long id_grille;
	 @OneToMany(mappedBy="grille",fetch=FetchType.LAZY)
	 public Collection<Partie> parties;
	 @Column(length=65535 )
	 public int[][] matrice=new int[8][8];
	 public int tour;
	 public Grille(){
	        tour = 4;
	        int i, j;
	        for(i=0; i<matrice.length; i++) {
	            for(j=0; j<matrice[i].length; j++) {
	                matrice[i][j]=0;
	            }
	        }
	        matrice[3][3]=1;
	        matrice[4][4]=1;
	        matrice[4][3]=2;
	        matrice[3][4]=2;

	    }
	 

	public Collection<Partie> getParties() {
		return parties;
	}


	public void setParties(Collection<Partie> parties) {
		this.parties = parties;
	}


	public int getTour() {
		return tour;
	}


	public void setTour(int tour) {
		this.tour = tour;
	}


	public int[][] getMatrice() {
		return matrice;
	}
	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}

	 
}
