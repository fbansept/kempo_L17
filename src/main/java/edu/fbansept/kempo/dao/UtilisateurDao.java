package edu.fbansept.kempo.dao;

import edu.fbansept.kempo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer>{

    Optional<Utilisateur> findByEmail(String email);
}