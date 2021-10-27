package ir.maktab.userservice.controllers;

import ir.maktab.userservice.Utils.SessionData;
import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.User;
import ir.maktab.userservice.repositories.UserRepository;
import ir.maktab.userservice.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    final UserService userService;
    final SessionData sessionData;
    final UserRepository userRepository;

    @Autowired
    public UserController(SessionData sessionData, UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.sessionData = sessionData;

    }


    @GetMapping({"", "/"})
    public String getLoginPage() {
        if (sessionData.getCurrentUser() != null) return "redirect:/users/dashboard";
        return "login";
    }

    @PostMapping("/login")
    public String logUserInSystem(
            @RequestParam("user-name") String userName,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes
    ) {
        if (sessionData.getCurrentUser() != null) return "redirect:/users/dashboard";


        try {
            Optional<User> login = userService.login(userName, password);
            if (login.isPresent()) {
                sessionData.setCurrentUser(login.get());
                System.out.println(sessionData.getCurrentUser());
                return "redirect:/users/dashboard";
            } else {
                redirectAttributes.addFlashAttribute("error", "invalid information");
                return "redirect:/users";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "invalid information " + e.getMessage());
            return "redirect:/users";
        }


    }

    @GetMapping("/dashboard")
    String getUserTicketHistory(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        // chek login
        if (sessionData.getCurrentUser() == null) {
            redirectAttributes.addFlashAttribute("error", "you must login to see dashboard");
            return "redirect:/users";
        }
        Long id = sessionData.getCurrentUser().getId();
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();
        List<Ticket> boughtTickets = user.getBoughtTickets();
        model.addAttribute("bought", boughtTickets);
        return "dashboard";
    }


}
