package cdw.domaintraining.spring.security.practice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {
        return "home";

    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "login";
    }


    @GetMapping("/leaders")
    public String showLeadersPage() {
        return "leaders";

    }

    @GetMapping("/systems")
    public String showsystemsPage() {
        return "systems";

    }

    @GetMapping("/access-denied")
    public String showaccessDeniedPage() {
        return "denied";

    }

}
