package fr.cda.cinemacda.salle;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalleService {
    private final SalleRepository salleRepository;

    //constructor
    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }


    //GET toute les salles
    public List<Salle> findAllSalle() {
        return salleRepository.findAll();
    }

    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    //GET by ID
    public Salle findSalleById(Integer id) {
        return salleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Salle non trouvée"
                )
        );
    }

    //DELETE la salle par l'id
    public void deleteSalleById(Integer id) {
        Salle salle = this.findSalleById(id);
        salleRepository.delete(salle);
    }

    //PUT (update) la salle
    public Salle updateSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    //GET par nom de la salle
    public Salle findByNomSalle(String nom) {
        return salleRepository.findByNomSalle(nom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucunes salles trouvé avec pour nom " + nom
                )
        );
    }
}
