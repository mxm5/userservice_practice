package ir.maktab.userservice.services.impl;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.exceptions.EmptyFieldException;
import ir.maktab.userservice.exceptions.FailedBuyingTicket;
import ir.maktab.userservice.exceptions.GenderNotProvided;
import ir.maktab.userservice.exceptions.TripNotFound;
import ir.maktab.userservice.repositories.TicketRepository;
import ir.maktab.userservice.repositories.TripRepository;
import ir.maktab.userservice.services.TicketServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements TicketServiceApi<Ticket, Long> {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TripRepository tripRepository;

    @Override
    public Ticket buyTicket(Trip trip, Passenger passenger) {
        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrip(trip);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket buyTicketWithInfo(String firstName, String lastName, String gender, String tripId) {
        if (
                firstName == null || firstName.equals("") ||
                        lastName == null || lastName.equals("") ||
                        gender == null || gender.equals("") ||
                        tripId == null || tripId.equals("")
        ) throw new EmptyFieldException("fields should not be empty");
        Passenger.Gender passengerGender;
        if (gender.equalsIgnoreCase("male")) passengerGender = Passenger.Gender.MALE;
        else if(gender.equalsIgnoreCase("female")) passengerGender = Passenger.Gender.FEMALE;
        else throw new GenderNotProvided(" gender not provided");
        Passenger passenger = new Passenger(firstName, lastName, passengerGender);
        Long id = Long.parseLong(tripId);
        Optional<Trip> byId = tripRepository.findById(id);
        if(byId.isEmpty()) throw new TripNotFound("tripNotFound");
        Trip trip = byId.get();
        Ticket ticket = new Ticket();
        ticket.setTrip(trip);
        ticket.setPassenger(passenger);
        return ticketRepository.save(ticket);

    }
}
