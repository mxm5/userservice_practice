package ir.maktab.userservice.repositories;

import ir.maktab.userservice.domain.Trip;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long> {
    Iterable<Trip> findByOriginIsAndDestinationIsAndMovingDateIs(
            String origin, String destination, Date movingDate
    );

    List<Trip> findByOriginAndDestinationAndMovingDate(
            String origin, String destination, Date movingDate

    );


    List<Trip> findByOriginAndDestinationAndMovingDateOrderByMovingTimeAsc(
            String origin, String destination, Date movingDate
    );

    List<Trip> findByOriginAndDestinationAndMovingDateAndAvailableOrderByMovingTimeAsc(
            String origin, String destination, Date movingDate,Boolean available
    );

//    id
//    origin
//    destination
//    totalSeats
//    movingDate
//    movingTime
//    tickets
}
