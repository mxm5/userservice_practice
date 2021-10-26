package ir.maktab.userservice.repositories;

import ir.maktab.userservice.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
