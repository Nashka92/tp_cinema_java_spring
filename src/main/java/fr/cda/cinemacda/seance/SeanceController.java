package fr.cda.cinemacda.seance;

import fr.cda.cinemacda.ticket.dto.TicketSansSeanceDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {

    //Injection dépendance
    private final SeanceService seanceService;

    // Constructor
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    //GET toute les séances
    @GetMapping
    public List<Seance> findAllSeance() {
        return seanceService.findAllSeance();
    }

    //GET séance by id
    @GetMapping("/{id}")
    public Seance findSeanceById(@PathVariable Integer id) {
        return seanceService.findSeanceById(id);
    }

    //DELETE seance
    @DeleteMapping("/{id}")
    public void deleteSeanceById(@PathVariable Integer id) {
        seanceService.deleteSeanceById(id);
    }

    //UPDATE séance
    @PutMapping
    public Seance updateSeance(@RequestBody Seance seance) {
        return seanceService.updateSeance(seance);
    }

    //POST séance
    @PostMapping
    public Seance saveSeance(@RequestBody Seance seance) {
        return seanceService.saveSeance(seance);
    }

    //Créer une route permettant de récupérer les tickets réservés pour une séance
    @GetMapping("/{id}/tickets")
    public List<TicketSansSeanceDto> findTicketsBySeanceId(@PathVariable Integer id) {
        return seanceService.findTicketsBySeanceId(id);
    }

}
