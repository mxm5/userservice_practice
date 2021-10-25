package ir.maktab.userservice.repositories;

import ir.maktab.userservice.domain.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip,Long> {
}
