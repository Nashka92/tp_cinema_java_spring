package fr.cda.cinemacda.seance;

import fr.cda.cinemacda.salle.Salle;
import fr.cda.cinemacda.ticket.dto.TicketSansSeanceDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class SeanceService {
    // Injection de dépendances
    private final SeanceReposity seanceReposity;

    //Contructor
    public SeanceService(SeanceReposity seanceReposity) {
        this.seanceReposity = seanceReposity;
    }

    //GET toute les séances
    public List<Seance> findAllSeance() {
        return seanceReposity.findAll();
    }

    // Save seance pour post
    public Seance saveSeance(Seance seance) {
        return seanceReposity.save(seance);
    }

    //GET seance by Id
    public Seance findSeanceById(Integer id) {
        return seanceReposity.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Séance non trouvée"
                )
        );
    }

    // DELETE seance
    public void deleteSeanceById(Integer id) {
        Seance seance = this.findSeanceById(id);
        seanceReposity.delete(seance);
    }

    //UPDATE séance
    public Seance updateSeance(Seance seance) {
        return seanceReposity.save(seance);
    }

    // Créer une route permettant de récupérer les tickets réservés pour une séance
    public List<TicketSansSeanceDto> findTicketsBySeanceId(Integer id) {
        Seance seance = this.findSeanceById(id);
        return seance.getTickets().stream().map(ticket -> {
            TicketSansSeanceDto ticketSansSeanceDto = new TicketSansSeanceDto();
            ticketSansSeanceDto.setId(ticket.getId());
            ticketSansSeanceDto.setNomClient(ticket.getNomClient());
            ticketSansSeanceDto.setNombrePlaces(ticket.getNombrePlaces());
            return ticketSansSeanceDto;
        }).toList();
    }

}
