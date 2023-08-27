package fa.edu.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome(){
        return "home";
    }

    @GetMapping("/dashboard")
    public String showDashBoard(){ return "dashBoard";}

}
