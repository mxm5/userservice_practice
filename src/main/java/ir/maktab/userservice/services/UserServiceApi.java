package ir.maktab.userservice.services;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceApi <Passenger,Long>{

    Optional<User> login(String userName, String password);


}
