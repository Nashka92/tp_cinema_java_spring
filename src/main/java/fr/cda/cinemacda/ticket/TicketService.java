package fr.cda.cinemacda.ticket;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // GET tout les tickets
    public List<Ticket> findAllTicket() {
        return ticketRepository.findAll();
    }

    //SAVE
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    //GET ticket par id
    public Ticket findTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ticket non trouvé"
                )
        );
    }

    //DELETE ticket
    public void deleteTicket(Integer id) {
        Ticket ticket = this.findTicketById(id);
        ticketRepository.delete(ticket);
    }

    //UPDATE ticket
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
