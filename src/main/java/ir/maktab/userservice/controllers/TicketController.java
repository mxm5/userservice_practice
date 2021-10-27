package ir.maktab.userservice.controllers;

import ir.maktab.userservice.Utils.SessionData;
import ir.maktab.userservice.domain.Passenger;
import ir.maktab.userservice.domain.Ticket;
import ir.maktab.userservice.domain.Trip;
import ir.maktab.userservice.exceptions.FailedBuyingTicket;
import ir.maktab.userservice.exceptions.NoTicketsFound;
import ir.maktab.userservice.repositories.TicketRepository;
import ir.maktab.userservice.services.impl.TicketService;
import ir.maktab.userservice.services.impl.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticket")
public class TicketController {


    @GetMapping("/delete/{id}")
    public String deleteTicket(
            @PathVariable("id") String ticketId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            ticketService.removeTicket(ticketId);
            redirectAttributes.addFlashAttribute("result", "successfully deleted ticket with id " + ticketId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "could not delete ticket with id " + ticketId);
        }

        return "redirect:/users/dashboard";
    }

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

        try {

            List<Trip> trips = tripService.searchWithData(origin, destination, movingDate);
            if (trips.size() == 0) throw new NoTicketsFound("no tickets found");
            model.addAttribute("listTrip", trips);

            return "search";
        } catch (Exception e) {
            model.addAttribute("error", "something went wrong " + e.getMessage());
            return "search";
        }


    }

    @Autowired
    TicketService ticketService;

    @Autowired
    SessionData sessionData;

    @GetMapping("/buy/{id}")
    public String getBuyTicket(@PathVariable("id") String tripId, Model model
            , RedirectAttributes redirectAttributes
    ) {
        if (sessionData.getCurrentUser() == null) {
            redirectAttributes.addFlashAttribute("error", "for buying tickets you must login");
            return "redirect:/users";
        }

        try {
            Optional<Trip> trip = tripService.getById(tripId);
            if (trip.isPresent()) {
                Trip tripFound = trip.get();
                if (tripFound.getTickets().size() != tripFound.getTotalSeats()) {
                    model.addAttribute("trip", tripFound);
                    return "buy";
                } else {
                    model.addAttribute("error", "sold out");

                }
            }
        } catch (Exception e) {
            model.addAttribute("error", "something went wrong " + e.getMessage());
        }
        return "search";
    }


    @PostMapping("/buying")
    public String buyingTicket(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName,
            @RequestParam("gender") String gender,
            @RequestParam("trip-id") String tripId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (sessionData.getCurrentUser() == null) {
            redirectAttributes.addFlashAttribute("error", "for buying tickets you must login");
            return "redirect:/users";
        }

        try {
            Ticket ticket = ticketService.buyTicketWithInfo(
                    firstName,
                    lastName,
                    gender,
                    tripId
            );
            if (ticket == null) throw new FailedBuyingTicket("failed buying ticket");
            String title = "";
            if (gender.equalsIgnoreCase("male")) title = "Mr";
            model.addAttribute(
                    "result",
                    "buying ticket was done successfully for "
                            + title + " " + firstName + " " + lastName);
            return "buysuccess";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "something went wrong " + e.getMessage());
            redirectAttributes.addFlashAttribute("trip-id", tripId);
        }
        return "redirect:/ticket/buy/" + tripId;
    }


    @GetMapping("/show/{id}")
    public String showTicketDetail(@PathVariable("id") String ticketId, Model model
            , RedirectAttributes redirectAttributes
    ) {
        if (sessionData.getCurrentUser() == null) {
            redirectAttributes.addFlashAttribute("error", "for buying tickets you must login");
            return "redirect:/users";
        }

        try {
            Optional<Ticket> ticketById = ticketService.findTicketById(ticketId);
            if (ticketById.isPresent()) {
                Ticket ticket = ticketById.get();
                model.addAttribute("ticket", ticket);
                return "ticketDetail";
            }
        } catch (Exception e) {
            model.addAttribute("error", "something went wrong " + e.getMessage());
        }
        return "redirect:/users";

    }


}
