package ir.maktab.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Passenger{
    public User( String firstName, String lastName, Gender gender, String userName, String password) {
        super( firstName, lastName, gender);
        this.userName = userName;
        this.password = password;
    }

//      user.setFirstName("ali");
    //        user.setLastName("hasani");
    //        user.setPassword("1234");
    //        user.setUserName("alix");
    //        user.setGender(Passenger.Gender.MALE);

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

}
