package fr.cda.cinemacda.ticket;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //GET tout les tickets
    @GetMapping
    public List<Ticket> findAllTicket() {
        return ticketService.findAllTicket();
    }

    //GET by id ticket
    @GetMapping("/{id}")
    public Ticket findTicketById(@PathVariable Integer id) {
        return ticketService.findTicketById(id);
    }

    //DELETE ticket
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }

    //PUT (update) ticket
    @PutMapping
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    //POST ticket
    @PostMapping
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }
}
