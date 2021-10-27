package ir.maktab.userservice.services;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Trip;

import java.util.Optional;

public interface TicketServiceApi<Ticket,Long>{
    Ticket buyTicket(Trip trip, Passenger passenger);

    Ticket buyTicketWithInfo(String firstName, String lastName, String gender, String tripId);

}
