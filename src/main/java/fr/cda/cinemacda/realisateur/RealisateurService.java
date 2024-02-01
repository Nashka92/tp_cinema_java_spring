package fr.cda.cinemacda.realisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda.film.Film;
import fr.cda.cinemacda.film.FilmRepository;
import fr.cda.cinemacda.film.FilmService;
import fr.cda.cinemacda.film.dto.FilmMiniDto;
import fr.cda.cinemacda.realisateur.dto.RealisateurAvecFilmDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RealisateurService {
    //injection de dépendance
    private final RealisateurRepository realisateurRepository;
    private final FilmService filmService;
    private final ObjectMapper objectMapper;

    public RealisateurService(RealisateurRepository realisateurRepository, FilmService filmService, ObjectMapper objectMapper) {
        this.realisateurRepository = realisateurRepository;
        this.filmService = filmService;
        this.objectMapper = objectMapper;
    }

    public List<Realisateur> findAll() {
        return realisateurRepository.findAll();
    }

    public Realisateur save(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    public Realisateur findById(Integer id) {
        return realisateurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Réalisateur non trouvé"
                )
        );
    }

    public void deleteById(Integer id) {
        this.findById(id);

        List<Film> filmsAvecCeRealisateur = filmService.findAllByRealisateurId(id);

        filmsAvecCeRealisateur.forEach(
                film -> {
                    film.setRealisateur(null);
                    filmService.save(film);
                }
        );
    }

    public Realisateur update(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    public Realisateur findByNom(String nom) {
        return realisateurRepository.findByNom(nom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun réalisateur trouve avec: " + nom
                )
        );
    }

    public RealisateurAvecFilmDto findRealisateurWithFilm(Integer id) {
        Realisateur realisateur = this.findById(id);
        List<Film> filmsDuRealisateur = filmService.findAllByRealisateurId(id);
        RealisateurAvecFilmDto realisateurAvecFilmDto = new RealisateurAvecFilmDto();

        realisateurAvecFilmDto.setId(realisateur.getId());
        realisateurAvecFilmDto.setNom(realisateur.getNom());
        realisateurAvecFilmDto.setPrenom(realisateur.getPrenom());

        realisateurAvecFilmDto.setFilms(
                // On convertir la liste de film en notre DTO FilmMini
                // pour ne pas avoir d'erreur de type
                filmsDuRealisateur.stream().map(
                        film -> objectMapper.convertValue(film, FilmMiniDto.class)
                ).toList()
        );
        return realisateurAvecFilmDto;
    }

    public List<Film> findFilmsByRealisateurId(Integer id) {
        return filmService.findAllByRealisateurId(id);
    }
}
