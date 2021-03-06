package th.ac.kmtil.atm.controller;
//Chutima Wongsasao 6105025

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.kmtil.atm.model.Customer;
import th.ac.kmtil.atm.service.CustomerService;

@Controller
@RequestMapping("/login")
public class LoginController {
    private CustomerService customerService;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public  String getLoginPage(){
        return  "login";
    }

    @PostMapping
    public  String login(@ModelAttribute Customer customer, Model model){
        // 1.check to see if id end pin matched customer info
        Customer matchingCustomer = customerService.checkPin(customer);
        // 2.if match, welcome customer
        if (matchingCustomer != null){
            model.addAttribute("greeting","Welcome, "+matchingCustomer.getName());
        } else {
            // 3.not match, display that customer info is incorrection
            model.addAttribute("greeting","Can't find customer ");
        }
        return  "home";
    }
}
