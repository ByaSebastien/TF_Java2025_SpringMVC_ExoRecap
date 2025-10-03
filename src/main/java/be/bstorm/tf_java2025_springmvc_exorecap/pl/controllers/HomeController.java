package be.bstorm.tf_java2025_springmvc_exorecap.pl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(
            @RequestParam(value = "logged", required = false) String logged,
            Model model
    ) {

        if (logged != null) {
            model.addAttribute("toastMessage", "Connexion réussie !");
            model.addAttribute("toastType", "success");
        }

        return "home/home";
    }

    @GetMapping("/about")
    public String about() {
        return "home/about";
    }

}