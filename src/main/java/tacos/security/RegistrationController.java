package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.UserRepository;
import tacos.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository repos;
    private final PasswordEncoder encoder;

    public RegistrationController(UserRepository repos, PasswordEncoder encoder) {
        this.repos = repos;
        this.encoder = encoder;
    }


    @ModelAttribute
    public User user() {
        return new User();
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }


    @PostMapping
    public String processRegistration(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return "registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repos.save(user);
        return "redirect:/login";
    }


}
