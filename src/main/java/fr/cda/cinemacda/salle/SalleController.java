package fr.cda.cinemacda.salle;

import fr.cda.cinemacda.acteur.Acteur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }
    //private final OjectMapper objectMapper;


    //GET toute les salles
    @GetMapping
    public List<Salle> findAllSalle() {
        return salleService.findAllSalle();
    }

    //GET By id la salle
    @GetMapping("/{id}")
    public Salle findSalleById(@PathVariable Integer id) {
        return salleService.findSalleById(id);
    }

    //DELETE by id la salle
    @DeleteMapping("/{id}")
    public void deleteSalleById(@PathVariable Integer id) {
        salleService.deleteSalleById(id);
    }

    //PUT (update) la salle
    @PutMapping
    public Salle updateSalle(@RequestBody Salle salle) {
        return salleService.updateSalle(salle);
    }

    //GET par nom de la salle
    @GetMapping("/search")
    public Salle findByNomSalle(@RequestParam String nom) {
        return salleService.findByNomSalle(nom);
    }

    // POST
    @PostMapping
    public Salle saveSalle(@RequestBody Salle salle) {
        return salleService.saveSalle(salle);
    }
}
