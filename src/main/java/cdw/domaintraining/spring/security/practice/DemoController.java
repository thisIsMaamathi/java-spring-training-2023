package cdw.domaintraining.spring.security.practice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);





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
