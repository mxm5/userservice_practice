package ir.maktab.userservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @PostMapping("/login")
    public String logUserInSystem() {
    return null;
    }

    @GetMapping("/history")
    String getUserTicketHistory() {
    return null;
    }


    @GetMapping("/delete")
    String deleteTickets() {
    return null;
    }
}
