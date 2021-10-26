package ir.maktab.userservice;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.domain.User;
import ir.maktab.userservice.repositories.PassengerRepository;
import ir.maktab.userservice.repositories.TicketRepository;
import ir.maktab.userservice.repositories.TripRepository;
import ir.maktab.userservice.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static ir.maktab.userservice.Utils.TimeUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testPassengerRepo() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("mohammad");
        passenger.setLastName("hasani");
        passenger.setGender(Passenger.Gender.MALE);
        Passenger saved = passengerRepository.save(passenger);
        assertNotNull(saved);
        assertNotNull(saved.getId());

    }

    @Test
    public void timeUtilTest() throws ParseException {
        Date date = dateOf("2020-11-11");
        System.out.println(date.getYear());

    }

    @Test
    public void hourTest() {
        Time time = Time.valueOf("10:20:00");
        System.out.println(time.getHours());

    }

    @Test
    public void createATrip() throws ParseException {
        Trip trip = new Trip();
        trip.setDestination("tehran");
        trip.setOrigin("mashad");
//        "yyyy-MM-dd"
        trip.setMovingDate(dateOf("2020-11-11"));
        trip.setMovingTime(timeOf("10:06:00"));
        trip.setTotalSeats(30);
        Trip saved = tripRepository.save(trip);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void superFind() throws ParseException {
        Iterable<Trip> list = tripRepository.findByOriginIsAndDestinationIsAndMovingDateIs(
                "mashad", "tehran", dateOf("2020-11-11")
        );
        List<Trip> byOriginAndDestinationAndMovingDate = tripRepository.findByOriginAndDestinationAndMovingDate(
                "mashad", "tehran", dateOf("2020-11-11")
        );
        byOriginAndDestinationAndMovingDate.forEach(System.out::println);
        System.out.println("========================");
        for (Trip trip : list) {
            System.out.println(trip);
        }
        List<Trip> byMovingTime = tripRepository.findByOriginAndDestinationAndMovingDateOrderByMovingTimeAsc(
                "mashad", "tehran", dateOf("2020-11-11")
        );
        System.out.println("11111111111111");
        byMovingTime.forEach(System.out::println);
        list.forEach(System.out::println);
        System.out.println("========================");
    }

    @Test
    public void testUserRepo() {
        User user = new User();
        user.setFirstName("ali");
        user.setLastName("hasani");
        user.setPassword("1234");
        user.setUserName("alix");
        User userb = new User("ali", "hasani", Passenger.Gender.MALE, "alixyz", "1234");
        user.setGender(Passenger.Gender.MALE);
        User saved = userRepository.save(user);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        User alix = userRepository.findByUserName("alix");
        assertNotNull(alix);
    }


    @Test
    public void orderTicket() throws Exception {
        Optional<Trip> tripById = tripRepository.findById(1L);
        Trip trip = tripById.get();
        Optional<Passenger> passengerById = passengerRepository.findById(3L);
        Passenger passenger = passengerById.get();
        Ticket ticket = new Ticket();
        ticket.setTrip(trip);
        ticket.setPassenger(passenger);
        ticketRepository.save(ticket);


    }

    @Test
    public void orderTicketUser() throws Exception {
        Optional<Trip> tripById = tripRepository.findById(1L);
        Trip trip = tripById.get();
        Optional<User> passengerById =  userRepository.findById(2L);
        Passenger passenger = passengerById.get();
        Ticket ticket = new Ticket();
        ticket.setTrip(trip);
        ticket.setPassenger(passenger);
        ticketRepository.save(ticket);


    }


    @Test
    public void removeTicket() {
        Optional<Ticket> byId = ticketRepository.findById(5L);
        Ticket ticket = byId.get();
        ticketRepository.delete(ticket);
    }

    @Test
    public void findTicketsForPassenger() {
        Optional<User> byId = userRepository.findById(2L);
        Passenger user = byId.get();
        List<Ticket> byPassenger = ticketRepository.findByPassenger(user);
        System.out.println("=============");
        byPassenger.forEach(System.out::println);
        Optional<Passenger> pById = passengerRepository.findById(3L);
        Passenger passenger = pById.get();
        System.out.println("=============");
        List<Ticket> byPassenger1 = ticketRepository.findByPassenger(passenger);
        byPassenger1.forEach(System.out::println);
    }

}

//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace .NONE)
//@Rollback(false)
//public class UserRepositoryTests {
//    @Autowired private UserRepository repo;
//    @Test
//    public void testAddNew() {
//        User user = new User();
