package fr.cda.cinemacda.film;

import fr.cda.cinemacda.acteur.Acteur;
import fr.cda.cinemacda.acteur.ActeurService;
import fr.cda.cinemacda.film.exception.BadRequestException;
import fr.cda.cinemacda.film.exception.FilmNotFoundException;
import fr.cda.cinemacda.realisateur.Realisateur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class FilmService {
    //injection de dépendance
    private final FilmRepository filmRepository;
    private final ActeurService acteurService;

    public FilmService(FilmRepository filmRepository, ActeurService acteurService) {
        this.filmRepository = filmRepository;
        this.acteurService = acteurService;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) {

        verifyFilm(film);

        return filmRepository.save(film);
    }

    private static void verifyFilm(Film film) {
        List<String> erreurs = new ArrayList<>();

        if (film.getTitre() == null) {
            erreurs.add("Le titre est obligatoire");
        }

        if (film.getDateSortie() == null) {
            erreurs.add("La date de sortie est obligatoire");
        }

        if (film.getRealisateur() == null) {
            erreurs.add("Le réalisateur est obligatoire");
        }

        if (!erreurs.isEmpty()) {
            throw new BadRequestException(erreurs);
        }
    }

    public Film findById(Integer id) {
        return filmRepository.findById(id)
                .orElseThrow(
                        () -> new FilmNotFoundException((id))
                );
    }

    public void deleteById(Integer id) {
        Film film = this.findById(id);
        filmRepository.delete(film);
    }

    public Film update(Film film) {
        return filmRepository.save(film);
    }

    public Film findByTitre(String titre) {
        return filmRepository.findByTitre(titre)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Aucun titre trouvé avec : " + titre
                        )
                );

    }


    public List<Film> findAllByRealisateurId(Integer id) {
        return filmRepository.findAllByRealisateurId(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun film ayant ce réalisateur"
                )
        );
    }

    // méthode qui retourne les acteurs d'un film
    public List<Acteur> findActeursByFilmId(Integer id) {
        Film film = this.findById(id);
        return film.getActeurs();
    }

    public Realisateur findRealisateurByFilmId(Integer id) {
        Film film = this.findById(id);
        return film.getRealisateur();
    }

    // ici appeler la méthode findById de ActeurService

    public Film addActeurToFilm(Integer id, Acteur acteur) {
        Film film = this.findById(id);
        acteur = acteurService.findById(acteur.getId());

        // Ajoute l'acteur à la liste des acteurs du film
        film.getActeurs().add(acteur);

        // En retour il sauvegarde le film avec l'acteur ajouté
        return this.save(film);
    }


}