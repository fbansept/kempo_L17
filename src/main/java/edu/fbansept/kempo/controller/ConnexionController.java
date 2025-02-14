package edu.fbansept.kempo.controller;

import edu.fbansept.kempo.dao.UtilisateurDao;
import edu.fbansept.kempo.model.Utilisateur;
import edu.fbansept.kempo.security.AppUserDetails;
import edu.fbansept.kempo.security.IsAdmin;
import edu.fbansept.kempo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnexionController {

    private UtilisateurDao utilisateurDao;
    private PasswordEncoder encoder;
    private AuthenticationProvider authenticationProvider;
    private JwtUtils jwtUtils;

    @Autowired
    public ConnexionController(
            UtilisateurDao utilisateurDao,
            PasswordEncoder encoder,
            AuthenticationProvider authenticationProvider,
            JwtUtils jwtUtils
    ) {
        this.utilisateurDao = utilisateurDao;
        this.encoder = encoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscription (@RequestBody Utilisateur utilisateur) {

        //TODO verifier les infos client

        //On hash le mot de passe du client
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Utilisateur utilisateur) {


        try {

            AppUserDetails userDetails = (AppUserDetails) authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(
                                    utilisateur.getEmail(),
                                    utilisateur.getPassword()))
                    .getPrincipal();

             return ResponseEntity.ok(jwtUtils.generateToken(userDetails));

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
