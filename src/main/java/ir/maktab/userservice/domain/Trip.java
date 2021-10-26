package ir.maktab.userservice.domain;

import ir.maktab.userservice.exceptions.TicketsSoldOut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

//    id
//    origin
//    destination
//    totalSeats
//    movingDate
//    movingTime
//    tickets
//    available



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats = 20;

    @Column(name = "moving_date", nullable = false)
    private Date movingDate;

    @Column(name = "moving_time", nullable = false)
    private Time movingTime;


    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @Column(name = "available", nullable = false)
    private Boolean available = true;

    public void preservedOne(Ticket ticket) {
        if (totalSeats > tickets.size()) {
            this.tickets.add(ticket);
        } else {
            throw new TicketsSoldOut(" all tickets sold out ");
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", totalSeats=" + totalSeats +
                ", movingDate=" + movingDate +
                ", movingTime=" + movingTime +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if(totalSeats==tickets.size()) available =false;
    }
}

//
//@Entity
//public class BookPublisher {
//    @Embeddable
//    public static class BookPublisherId implements Serializable {
//        @Column(name = "fk_book")
//        protected Long bookId;
//        @Column(name = "fk_publisher")
//        protected Long publisherId;
//
//        public BookPublisherId() {
//        }
//
//        public BookPublisherId(Long bookId, Long publisherId) {
//            this.bookId = bookId;
//            this.publisherId = publisherId;
//        }
//
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
//            result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
//            return result;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) return true;
//            if (obj == null) return false;
//            if (getClass() != obj.getClass()) return false;
//            BookPublisherId other = (BookPublisherId) obj;
//            if (bookId == null) {
//                if (other.bookId != null) return false;
//            } else if (!bookId.equals(other.bookId)) return false;
//            if (publisherId == null) {
//                if (other.publisherId != null) return false;
//            } else if (!publisherId.equals(other.publisherId)) return false;
//            return true;
//        }
//    }
//}