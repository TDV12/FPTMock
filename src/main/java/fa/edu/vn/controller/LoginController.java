package fa.edu.vn.controller;

import fa.edu.vn.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }
}
