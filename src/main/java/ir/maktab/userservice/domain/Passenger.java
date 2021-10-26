package ir.maktab.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Passenger {

//    id
//    firstName
//    lastName
//    gender
//    tickets


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    protected Gender gender;

    @OneToMany(mappedBy = "passenger", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    protected List<Ticket> tickets = new ArrayList<>();

    public Passenger(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public enum Gender {
        MALE, FEMALE
    }

    public void boughtOne(Ticket ticket) {
        this.tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
