package ir.maktab.userservice.repositories;

import ir.maktab.userservice.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
       User findByUserName(String userName);

       User findByUserNameAndPassword(String userName, String password);
}
