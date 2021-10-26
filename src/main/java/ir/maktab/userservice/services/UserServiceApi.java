package ir.maktab.userservice.services;

import ir.maktab.userservice.domain.Passenger;

import java.util.Optional;

public interface UserServiceApi <Passenger,Long>{

    Optional<ir.maktab.userservice.domain.Passenger> login(String userName, String password);
}
