package fr.cda.cinemacda.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda.acteur.Acteur;
import fr.cda.cinemacda.acteur.dto.ActeurSansFilmDto;
import fr.cda.cinemacda.film.dto.FilmCompletDto;
import fr.cda.cinemacda.film.dto.FilmReduitDto;
import fr.cda.cinemacda.realisateur.Realisateur;
import fr.cda.cinemacda.realisateur.dto.RealisateurSansFilmDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    //transformer une classe en json
    private final ObjectMapper objectMapper;

    public FilmController(FilmService filmService, ObjectMapper objectMapper) {
        this.filmService = filmService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<FilmReduitDto> findAll() {
        return filmService.findAll().stream().map(
                film -> objectMapper.convertValue(film, FilmReduitDto.class)
        ).toList();
    }

    @PostMapping
    public Film save(@RequestBody Film film) {
        return filmService.save(film);
    }


    @GetMapping("/{id}") //films/1
    public FilmCompletDto findById(@PathVariable Integer id) {
        Film film = filmService.findById(id);

        FilmCompletDto filmCompletDto = new FilmCompletDto();
        filmCompletDto.setId(film.getId());
        filmCompletDto.setDuree(film.getDuree());
        filmCompletDto.setSynopsis(film.getSynopsis());
        filmCompletDto.setRealisateur(film.getRealisateur());
        filmCompletDto.setDateSortie(film.getDateSortie());
        filmCompletDto.setActeurs(
                film.getActeurs().stream().map(
                        acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
                ).toList()
        );
        return filmCompletDto;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        filmService.deleteById(id);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    @GetMapping("/search")
    public Film findByTitre(@RequestParam String titre) {
        return filmService.findByTitre(titre);
    }

    @GetMapping("/{id}/acteurs")
    public List<ActeurSansFilmDto> findActeursByFilmId(@PathVariable Integer id) {
        List<Acteur> acteurs = filmService.findActeursByFilmId(id);
        return acteurs.stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
        ).toList();
    }

    @GetMapping("/{id}/realisateurs")
    public Realisateur findRealisateursByFilm(@PathVariable Integer id) {
        return filmService.findById(id).getRealisateur();
    }

    @PostMapping("/{id}/acteurs")
    // il retourne un 201 lorsque il a été bien créer
    @ResponseStatus(HttpStatus.CREATED)
    public FilmCompletDto addActeurToFilm(@PathVariable Integer id, @RequestBody Acteur acteur) {
        Film film = filmService.addActeurToFilm(id, acteur);

        FilmCompletDto filmCompletDto = new FilmCompletDto();

        filmCompletDto.setId(film.getId());
        filmCompletDto.setDuree(film.getDuree());
        filmCompletDto.setRealisateur(film.getRealisateur());
        filmCompletDto.setDateSortie(film.getDateSortie());
        filmCompletDto.setSynopsis(film.getSynopsis());
        filmCompletDto.setActeurs(
                film.getActeurs().stream().map(
                        unmappedActor -> objectMapper.convertValue(
                                unmappedActor,
                                ActeurSansFilmDto.class

                        )
                ).toList()
        );
        return filmCompletDto;
    }


}