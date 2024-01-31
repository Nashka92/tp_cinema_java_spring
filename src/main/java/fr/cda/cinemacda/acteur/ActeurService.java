package fr.cda.cinemacda.acteur;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActeurService {

    //injection des dépendances
    private final ActeurRepository acteurRepository;

    public ActeurService(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    public List<Acteur> findAll() {
        return acteurRepository.findAll();
    }

    public Acteur save(Acteur acteur) {
        return acteurRepository.save(acteur);
    }

    public Acteur findById(Integer id) {
        return acteurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "acteur non trouvé"
                )
        );
    }

    public void deleteById(Integer id) {
        Acteur acteur = this.findById(id);
        acteurRepository.delete(acteur);
    }

    public Acteur update(Acteur acteur) {
        return acteurRepository.save(acteur);
    }

    public Acteur findByNom(String nom) {
        return acteurRepository.findByNom(nom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucuns acteur trouvé avec: " + nom
                )
        );
    }

}
