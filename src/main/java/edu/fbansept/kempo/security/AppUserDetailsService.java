package edu.fbansept.kempo.security;

import edu.fbansept.kempo.dao.UtilisateurDao;
import edu.fbansept.kempo.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {


    @Autowired
    private UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByEmail(email);

            //si l'utilisateur n'est ni employe, ni client, c'est qu'il n'existe pas
            if(optionalUtilisateur.isEmpty()) {
                throw new UsernameNotFoundException("email introuvable");
            }

            return new AppUserDetails(optionalUtilisateur.get());

    }
}
