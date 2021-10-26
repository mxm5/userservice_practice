package ir.maktab.userservice.controllers;

import ir.maktab.userservice.Utils.SessionData;
import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;
    final
    SessionData sessionData;


    @Autowired
    public UserController(SessionData sessionData) {
        this.sessionData = sessionData;
    }


    @GetMapping({"","/"})
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String logUserInSystem(
            @RequestParam("user-name") String userName,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Optional<Passenger> login = userService.login(userName, password);
            if (login.isPresent()) {
                sessionData.setCurrentUser(login.get());
                System.out.println(sessionData.getCurrentUser());
                return "redirect: /users/dashboard";
            } else{
                redirectAttributes.addFlashAttribute("error","invalid information");
                return "redirect:/users";}
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error","invalid information "+e.getMessage());
            return "redirect:/users";
        }


    }

    @GetMapping("/dashboard")
    String getUserTicketHistory() {
        return null;
    }


}
