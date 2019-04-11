package org.sid.data;
import org.sid.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, String > {
	public Joueur findByUsername(String username);
}
