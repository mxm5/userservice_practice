package ir.maktab.userservice.services.impl;

import ir.maktab.userservice.Utils.TimeUtils;
import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.repositories.TripRepository;
import ir.maktab.userservice.services.TripServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
@Service
public class TripService implements TripServiceApi<Trip, Long> {

    @Autowired
    TripRepository tripRepository;

    @Override
    public List<Trip> searchWithData(String origin, String destination, String date) throws ParseException {
        Date formattedDate = TimeUtils.dateOf(date);
        return tripRepository.findByOriginAndDestinationAndMovingDateOrderByMovingTimeAsc(origin,destination,formattedDate);
    }
}
