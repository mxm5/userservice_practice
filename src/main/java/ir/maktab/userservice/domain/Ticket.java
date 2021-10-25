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




    public Ticket(Trip trip, Passenger passenger) throws Exception {

        this.trip = trip;
        this.passenger = passenger;

        passenger.boughtOne(this);
        trip.reservedOne(this);
        this.seatNumber = trip.getTickets().size();

    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "trip_id", nullable = false,insertable = false,updatable = false)
    private Trip trip;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "passenger_id", nullable = false,insertable = false,updatable = false)
    private Passenger passenger;

    @EmbeddedId
    private TicketId ticketId;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "buying_time", nullable = false)
    private LocalDateTime buyingTime = LocalDateTime.now();

    @PreRemove
    public void preRemove() {
        passenger.getTickets().remove(this);
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TicketId implements Serializable {
        @Column(name = "passenger_id", nullable = false, unique = true)
        private Long passengerId;

        @Column(name = "trip_id", nullable = false, unique = true)
        private Long tripId;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
            TicketId ticketId = (TicketId) o;
            return passengerId != null && Objects.equals(passengerId, ticketId.passengerId)
                    && tripId != null && Objects.equals(tripId, ticketId.tripId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(passengerId, tripId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return ticketId != null && Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }
}
