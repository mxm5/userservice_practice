package ir.maktab.userservice.practices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
//
//@Embeddable
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class TicketId implements Serializable {
//    @Column(name = "passenger_id", nullable = false, unique = true)
//    private Long passengerId;
//
//    @Column(name = "trip_id",nullable = false, unique = true)
//    private Long tripId;
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        TicketId ticketId = (TicketId) o;
//        return passengerId != null && Objects.equals(passengerId, ticketId.passengerId)
//                && tripId != null && Objects.equals(tripId, ticketId.tripId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(passengerId, tripId);
//    }
//}