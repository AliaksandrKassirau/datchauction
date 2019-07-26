package controller;
import entities.LoginUser;
import entities.RegistrationUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Message from controller");

        return "index.html";
    }
    @GetMapping("/login")
    public String login(Model model) {
        if(!model.containsAttribute("loginMessage")) {
            LoginUser lu = new LoginUser();
            model.addAttribute("loginMessage", "");
            model.addAttribute("loginUser", lu);
        }
        return "login.html";
    }
    @GetMapping("/confirmLogin")
    public String confirmLogin(@ModelAttribute("loginUser") LoginUser lu,Model model) {
        //TODO check in base
        if(lu.getEmail().equals("default@gmail.com") && lu.getPassword().equals("default")) {
            model.addAttribute("username", "DefaultUser");
            return "home.html";
        }else{
            model.addAttribute("loginMessage","Wrong email or password. Try again");
            return "login.html";
        }
    }
    @RequestMapping("/registration")
    public String Registration(Model model){
        RegistrationUser ru = new RegistrationUser();
        model.addAttribute("regUser",ru);
        return "registration.html";
    }

    private boolean isRegistrationValid(RegistrationUser ru){
        if(!ru.getPassword().equals(ru.getConfirmPassword()))return false;
        //TODO check nickname,email,passwordDifficult...
        return true;
    }

    @RequestMapping("/confirmRegistration")
    public String confirmRegistration(@ModelAttribute("regUser")RegistrationUser ru,Model model){
        //TODO request registration
        if(isRegistrationValid(ru)) {
            model.addAttribute("username",ru.getUsername());
            return "home.html";
        }
        return "registration.html";
    }
}