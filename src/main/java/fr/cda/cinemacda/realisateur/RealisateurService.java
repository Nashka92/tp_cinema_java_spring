package fr.cda.cinemacda.realisateur;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RealisateurService {
    //injection de dépendance
    private final RealisateurRepository realisateurRepository;

    public RealisateurService(RealisateurRepository realisateurRepository) {
        this.realisateurRepository = realisateurRepository;
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
        Realisateur realisateur = this.findById(id);
        realisateurRepository.delete(realisateur);
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
}
