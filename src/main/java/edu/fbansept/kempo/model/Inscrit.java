package edu.fbansept.kempo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Inscrit extends Utilisateur {

    private LocalDate dateDeNaissance;

    private int poids;

    private Boolean homme;

    @Column(columnDefinition = "ENUM('BLANCHE','JAUNE','ORANGE','VERT','BLEU','MARRON','NOIR')")
    @Enumerated(EnumType.STRING)
    private Ceinture ceinture;

}
