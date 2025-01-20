package edu.fbansept.kempo.controller;

import edu.fbansept.kempo.dao.UtilisateurDao;
import edu.fbansept.kempo.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UtilisateurControlleur {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @GetMapping("/utilisateur/liste")
    public List<Utilisateur> getAll() {

        return utilisateurDao.findAll();
    }
}
