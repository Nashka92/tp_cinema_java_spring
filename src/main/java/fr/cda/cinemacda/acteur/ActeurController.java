package fr.cda.cinemacda.acteur;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {

    //injection des dépendances
    private final ActeurService acteurService;

    public ActeurController(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    //méthode GET pour obtenir tout les acteurs
    @GetMapping
    public List<Acteur> findAll() {
        return acteurService.findAll();
    }

    //méthode POST avec en param le requestBody
    @PostMapping
    public Acteur save(@RequestBody Acteur acteur) {
        return acteurService.save(acteur);
    }

    // méthode GET pour obtenir l'acteur par ID
    @GetMapping("/{id}") // Path = url ressemblerait à ceci : acteurs/5
    public Acteur findById(@PathVariable Integer id) {
        return acteurService.findById(id);
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

