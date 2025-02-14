package edu.fbansept.kempo.controller;

import edu.fbansept.kempo.dao.InscritDao;
import edu.fbansept.kempo.model.Inscrit;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class InscritControlleur {

    @Autowired
    private InscritDao inscritDao;

    @GetMapping("/inscrit/liste")
    public List<Inscrit> getAll() {

        return inscritDao.findAll();
    }

    @GetMapping("/inscrit/{id}")
    public ResponseEntity<Inscrit> getInscritById(@PathVariable int id) {
        Optional<Inscrit> optionalInscrit = inscritDao.findById(id);

        if (optionalInscrit.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalInscrit.get(), HttpStatus.OK);
    }

    @PostMapping("/inscrit")
    public ResponseEntity<Inscrit> addInscrit(
            @RequestBody @Valid Inscrit inscrit) {
        inscritDao.save(inscrit);

        return new ResponseEntity<>(inscrit, HttpStatus.CREATED);
    }

    @DeleteMapping("/inscrit/{id}")
    public ResponseEntity<Inscrit> deleteInscrit(
            @PathVariable int id) {

        Optional<Inscrit> optionalInscrit = inscritDao.findById(id);

        if (optionalInscrit.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        inscritDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/inscrit/{id}")
    public ResponseEntity<Inscrit> updateInscrit(
            @PathVariable int id,
            @RequestBody @Valid Inscrit inscrit) {

        inscrit.setId(id);

        Optional<Inscrit> optionalInscrit = inscritDao.findById(id);

        if (optionalInscrit.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        inscritDao.save(inscrit);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
