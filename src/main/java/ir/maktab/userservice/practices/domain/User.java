//package ir.maktab.userservice.practices.domain;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class User {
//    @Id
//    @Column(name = "user_id", nullable = false)
//    @GeneratedValue
//    private Long userId;
//    @NotBlank(message = "Name is mandatory!!!")
//    private String name;
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//}
