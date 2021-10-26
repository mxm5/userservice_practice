package ir.maktab.userservice.services.impl;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.repositories.TicketRepository;
import ir.maktab.userservice.services.TicketServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements TicketServiceApi<Ticket,Long> {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket buyTicket(Trip trip, Passenger passenger) {
        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrip(trip);
        return ticketRepository.save(ticket);
    }
}
