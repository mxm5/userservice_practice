package ir.maktab.userservice.services.impl;

import ir.maktab.userservice.Utils.SessionData;
import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.domain.User;
import ir.maktab.userservice.exceptions.*;
import ir.maktab.userservice.repositories.PassengerRepository;
import ir.maktab.userservice.repositories.TicketRepository;
import ir.maktab.userservice.repositories.TripRepository;
import ir.maktab.userservice.repositories.UserRepository;
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
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionData sessionData;

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
        else if (gender.equalsIgnoreCase("female")) passengerGender = Passenger.Gender.FEMALE;
        else throw new GenderNotProvided(" gender not provided");
        // check if passenger exists
        Passenger p = passengerRepository.findByFirstNameAndLastNameAndGender(firstName, lastName, passengerGender);
        if (p == null) p = userRepository.findByFirstNameAndLastNameAndGender(firstName,lastName,passengerGender);
        if (p == null) p = new Passenger(firstName, lastName, passengerGender);
        Long id = Long.parseLong(tripId);
        Optional<Trip> byId = tripRepository.findById(id);
        if (byId.isEmpty()) throw new TripNotFound("tripNotFound");
        Trip trip = byId.get();
        Ticket ticket = new Ticket();
        Long buyerId = sessionData.getCurrentUser().getId();
        Optional<User> buyerById = userRepository.findById(buyerId);
        User buyer =null;
        if(buyerById.isPresent()) buyer =buyerById.get();
        else throw new NoUserFound("no user logged in ");
        ticket.setBuyer(buyer);
        ticket.setTrip(trip);
        ticket.setPassenger(p);
        return ticketRepository.save(ticket);

    }
}
