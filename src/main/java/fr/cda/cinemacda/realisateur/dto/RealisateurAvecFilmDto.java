package fr.cda.cinemacda.realisateur.dto;

import fr.cda.cinemacda.film.dto.FilmMiniDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RealisateurAvecFilmDto {
    private Integer id;
    private String nom;
    private String prenom;
    private List<FilmMiniDto> films = new ArrayList<>();
}
