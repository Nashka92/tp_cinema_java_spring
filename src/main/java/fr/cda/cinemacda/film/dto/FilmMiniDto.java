package fr.cda.cinemacda.film.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmMiniDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
}
