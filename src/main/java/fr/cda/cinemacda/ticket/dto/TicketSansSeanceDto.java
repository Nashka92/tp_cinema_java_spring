package fr.cda.cinemacda.ticket.dto;


import lombok.Data;

@Data
public class TicketSansSeanceDto {
    private int id;
    private String nomClient;
    private int nombrePlaces;
}

