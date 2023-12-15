package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegistrationController {

    // TODO: Task 6

    @GetMapping
    public String register(Model m) {
        RegistrationController regisControl = new RegistrationController(); 
        m. addAttribute("registration", regisControl);
        return "eventregister";
    }

    // TODO: Task 7

    @PostMapping("/postRegistration")
    public String processRegistration(@Valid @ModelAttribute RegistrationController regisControl, BindingResult result,
            Model m) {
        if (result.hasErrors()) {
            return "eventregister";
        }

        return "SuccessRegistration";

    }
}
