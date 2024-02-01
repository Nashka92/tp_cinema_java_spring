package fr.cda.cinemacda.film;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.cda.cinemacda.acteur.Acteur;
import fr.cda.cinemacda.realisateur.Realisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "film")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Film {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private LocalDate dateSortie;

    @Column(nullable = false)
    private int duree;

    @Column(length = 500)
    private String synopsis;


    @ManyToOne // Many films to One Realisateur (one realisateur to many films)
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;

    @ManyToMany // many acteurs to many films
    //jointure des deux tables, celle de l'entité acteur et celle de l'entité film
    @JoinTable(
            name = "acteur_film",
            // on lui precise le nom de la colonne de la jointure
            joinColumns = @JoinColumn(name = "film_id"),
            // la même mais concernant l'entité acteur
            inverseJoinColumns = @JoinColumn(name = "acteur_id")
    )
    private List<Acteur> acteurs = new ArrayList<>();
}
