package fr.cda.cinemacda.acteur;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda.acteur.dto.ActeurAvecFilmDto;
import fr.cda.cinemacda.acteur.dto.ActeurSansFilmDto;
import fr.cda.cinemacda.film.dto.FilmSansActeurDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {

    //injection des dépendances
    private final ActeurService acteurService;
    private final ObjectMapper objectMapper;

    public ActeurController(ActeurService acteurService, ObjectMapper objectMapper) {
        this.acteurService = acteurService;
        this.objectMapper = objectMapper;
    }

    //méthode GET pour obtenir tout les acteurs
    @GetMapping
    public List<ActeurSansFilmDto> findAll() {
        return acteurService.findAll().stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
        ).toList();
    }

    //méthode POST avec en param le requestBody
    @PostMapping
    public Acteur save(@RequestBody Acteur acteur) {
        return acteurService.save(acteur);
    }

    // méthode GET pour obtenir l'acteur par ID
    @GetMapping("/{id}")
    public ActeurAvecFilmDto findById(@PathVariable Integer id) {
        Acteur acteur = acteurService.findById(id);

        ActeurAvecFilmDto acteurAvecFilmDto = new ActeurAvecFilmDto();

        acteurAvecFilmDto.setId(acteur.getId());
        acteurAvecFilmDto.setNom(acteur.getNom());
        acteurAvecFilmDto.setPrenom(acteur.getPrenom());

        acteurAvecFilmDto.setFilms(
                acteur.getFilms().stream().map(
                        film -> objectMapper.convertValue(film, FilmSansActeurDto.class)
                ).toList()
        );
        return acteurAvecFilmDto;
    }

    //Méthode DELETE par id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        acteurService.deleteById(id);
    }

    //Méthode PUT pour mettre à jour l'acteur
    @PutMapping
    public Acteur update(@RequestBody Acteur acteur) {
        return acteurService.update(acteur);
    }

    //Pour trouver acteur par son nom
    @GetMapping("/search")
    public Acteur findByNom(@RequestParam String nom) {
        return acteurService.findByNom(nom);
    }
}

