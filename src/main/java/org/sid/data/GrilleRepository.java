package org.sid.data;


import org.sid.entities.Grille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface GrilleRepository extends JpaRepository<Grille, Long >{

}
