//package ir.maktab.userservice.practices.controller;
//
//import ir.maktab.userservice.practices.domain.User;
//import ir.maktab.userservice.practices.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/signup")
//    public String signup() {
//        return "signup-user";
//
//    }
//
//    @PostMapping(value = "/add-user")
//    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            return "add-user";
//        }
//        userService.createUser(user);
//        return "redirect:/index";
//    }
//
//
//}
