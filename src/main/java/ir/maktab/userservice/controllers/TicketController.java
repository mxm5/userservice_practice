package ir.maktab.userservice.controllers;

import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.repositories.TicketRepository;
import ir.maktab.userservice.services.impl.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {



    @GetMapping({"/", ""})
    public String getSearchTickets() {
        return "search";
    }

@Autowired
    TripService tripService;

    @PostMapping("/search")
    public String sendSearchResults(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam("moving-date") String movingDate,
            Model model
            ) {
        System.out.println("=======================================");
        System.out.println(origin);
        System.out.println(destination);
        System.out.println(movingDate);
        System.out.println("=======================================");

        try {

            List<Trip> trips = tripService.searchWithData(origin, destination, movingDate);
            model.addAttribute("listTrip", trips);

            return "search";
        } catch (Exception e) {
            model.addAttribute("error", "something went wrong " + e.getMessage());
            return "search";
        }

    }
}
