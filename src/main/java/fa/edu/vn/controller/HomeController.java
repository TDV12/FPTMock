package fa.edu.vn.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	System.out.println(currentPrincipalName);
    	model.addAttribute("userName", "Xin chào, " + currentPrincipalName);
        return "home";
    }

    @GetMapping("/dashboard")
    public String showDashBoard(){ return "dashBoard";}

}
