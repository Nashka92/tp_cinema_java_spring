package fr.cda.cinemacda.acteur.dto;

import fr.cda.cinemacda.film.dto.FilmReduitDto;
import fr.cda.cinemacda.film.dto.FilmSansActeurDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActeurAvecFilmDto {
    private Integer id;
    private String nom;
    private String prenom;
    private List<FilmSansActeurDto> films = new ArrayList<>();
}
