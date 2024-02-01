package fr.cda.cinemacda.film.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSansActeurNiRealisateurDto {
    private String titre;
    private int duree;
    private LocalDate dateSortie;
}
