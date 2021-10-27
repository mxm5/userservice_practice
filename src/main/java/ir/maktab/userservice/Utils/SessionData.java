package ir.maktab.userservice.Utils;

import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Getter
@Setter
@NoArgsConstructor
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value= WebApplicationContext.SCOPE_SESSION)
public class SessionData {
    private User currentUser;

}