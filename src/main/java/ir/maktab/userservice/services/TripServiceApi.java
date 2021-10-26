package ir.maktab.userservice.services;

import ir.maktab.userservice.domain.Trip;

import java.text.ParseException;
import java.util.List;

public interface TripServiceApi <Trip,Long>{
    List<ir.maktab.userservice.domain.Trip> searchWithData(
            String origin,
            String destination,
            String date
            ) throws ParseException;

    Trip getById(String tripId);
}
