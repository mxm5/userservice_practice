package ir.maktab.userservice.repositories;

import ir.maktab.userservice.domain.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<Passenger,Long> {
}
