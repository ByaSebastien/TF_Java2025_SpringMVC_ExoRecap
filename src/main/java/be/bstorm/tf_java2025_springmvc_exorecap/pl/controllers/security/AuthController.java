package be.bstorm.tf_java2025_springmvc_exorecap.pl.controllers.security;

import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.security.AuthService;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.user.RegisterForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register(
            Model model
    ){

        model.addAttribute("form", new RegisterForm());

        return "auth/register";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("form") RegisterForm form,
            BindingResult bindingResult,
            Model model
    ){

        if(bindingResult.hasErrors()){
            return "auth/register";
        }

        try{

        authService.register(form.ToUser());

        return "redirect:/login?registered";

        }catch(Exception e){

            model.addAttribute("toastMessage", e.getMessage());
            model.addAttribute("toastType", "error");
            return "auth/register";

        }
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(
            @RequestParam(value = "registered", required = false) String registered,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "error", required = false) String error,
            Model model
    ){

        if(registered != null){

            model.addAttribute("toastMessage", "Inscription réussie !");
            model.addAttribute("toastType", "success");

        }

        if(logout != null){

            model.addAttribute("toastMessage", "Déconnexion !");
            model.addAttribute("toastType", "success");

        }

        if(error != null){
            model.addAttribute("toastMessage", "Identifiants invalides !");
            model.addAttribute("toastType", "error");
        }

        return "auth/login";
    }
}
