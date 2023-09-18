package fa.edu.vn.controller;

import fa.edu.vn.dto.LoginDto;
import fa.edu.vn.dto.RegisterDto;
import fa.edu.vn.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	
	@Autowired
	IAccountService iAccountService;

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }
    
    @PostMapping("/login")
    public String register(Model model, RegisterDto registerDto){
    	model.addAttribute("loginDto", new LoginDto());
    	if (iAccountService.createAccount(registerDto)) {
    		 return "login";
		}
    	else {
    		model.addAttribute("message", "Username is present");
    		return null;
    	}
        
        
    }
}
