package controller;
import entities.AuctionException;
import entities.Core;
import entities.LoginUser;
import entities.RegistrationUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    private Core core;
    public MyController(){
        System.out.println(" ===============================================================================================\n"+
                           "|  ______             _          __             _                      _    _                   |\n"+
                           "| |_   _ `.          / |_       [  |           / \\                    / |_ (_)                  |\n"+
                           "|   | | `. \\ __   _ `| |-'.---.  | |--.       / _ \\    __   _   .---.`| |-'__   .--.   _ .--.   |\n"+
                           "|   | |  | |[  | | | | | / /'`\\] | .-. |     / ___ \\  [  | | | / /'`\\]| | [  |/ .'`\\ \\[ `.-. |  |\n"+
                           "|  _| |_.' / | \\_/ |,| |,| \\__.  | | | |   _/ /   \\ \\_ | \\_/ |,| \\__. | |, | || \\__. | | | | |  |\n"+
                           "| |______.'  '.__.'_/\\__/'.___.'[___]|__] |____| |____|'.__.'_/'.___.'\\__/[___]'.__.' [___||__] |\n"+
                           " ===============================================================================================");
        System.out.println("Initializing...");
        System.out.println("Controller: OK");
        core = Core.Initialize();
        System.out.println("Core: OK");
    }
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
        try {
            model.addAttribute("user",core.login(lu.getEmail(),lu.getPassword()));
            return "home.html";
        } catch (AuctionException e) {
            if(e.getCode()==11 || e.getCode()==12){
                model.addAttribute("loginMessage","Wrong email or password. Try again");
            }else model.addAttribute("loginMessage","Internal error. Sorry about that. Try again later");
        }
        return "login.html";
    }
    @RequestMapping("/registration")
    public String Registration(Model model){
        if(!model.containsAttribute("regUser")) {
            RegistrationUser ru = new RegistrationUser();
            model.addAttribute("regUser", ru);
        }
        model.addAttribute("registrationMessage", "Please enter all fields correctly");
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
            try {
                model.addAttribute("user",core.registration(ru.getEmail(),ru.getPassword(),ru.getUsername()));
                return "home.html";
            } catch (AuctionException e) {
                if(e.getCode()==13)model.addAttribute("registrationMessage", "This email is already using!");
                if(e.getCode()==14)model.addAttribute("registrationMessage", "This nickname is already using. Please create another one");
            }
        }
        else model.addAttribute("registrationMessage", "Password and confirm not match.");
        return "registration.html";
    }
}