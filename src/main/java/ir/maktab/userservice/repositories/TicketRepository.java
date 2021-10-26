package ir.maktab.userservice.repositories;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByPassenger(Passenger passenger);
}
