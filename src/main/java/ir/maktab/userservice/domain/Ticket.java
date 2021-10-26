package ir.maktab.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

//    trip
//    passenger
//    ticketId
//    seatNumber
//    buyingTime


    public void setTrip(Trip trip) {
        trip.preservedOne(this);
        this.trip = trip;
        this.seatNumber = trip.getTickets().size();
    }

    public void setPassenger(Passenger passenger) {
        passenger.boughtOne(this);
        this.passenger = passenger;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "trip_id", nullable = false )
    private Trip trip;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "passenger_id", nullable = false )
    private Passenger passenger;


    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "buying_time", nullable = false)
    private LocalDateTime buyingTime = LocalDateTime.now();

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @PreRemove
    public void preRemove() {
        passenger.getTickets().remove(this);
    }

}
