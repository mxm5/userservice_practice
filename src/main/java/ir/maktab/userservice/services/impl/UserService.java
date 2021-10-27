package ir.maktab.userservice.services.impl;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.User;
import ir.maktab.userservice.repositories.UserRepository;
import ir.maktab.userservice.services.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements UserServiceApi<Passenger,Long> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> login( String userName, String password) {
        User user = userRepository.findByUserNameAndPassword(userName, password);
        return Optional.ofNullable(user);
    }



}
