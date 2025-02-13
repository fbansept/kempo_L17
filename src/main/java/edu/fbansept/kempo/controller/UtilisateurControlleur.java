package edu.fbansept.kempo.controller;

import edu.fbansept.kempo.dao.UtilisateurDao;
import edu.fbansept.kempo.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurControlleur {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @GetMapping("/utilisateur/liste")
    public List<Utilisateur> getAll() {

        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> addUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable int id) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        utilisateurDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable int id,
            @RequestBody Utilisateur utilisateur) {

        utilisateur.setId(id);

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
