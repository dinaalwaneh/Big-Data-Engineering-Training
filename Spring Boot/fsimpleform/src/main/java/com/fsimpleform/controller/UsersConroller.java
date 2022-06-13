package com.fsimpleform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsimpleform.model.UserModel;
import com.fsimpleform.repository.UsersRepository;
import com.fsimpleform.service.UsersService;

@Controller
public class UsersConroller {

 @Autowired
	private  UsersService usersService ;
	
    //@RequestMapping(value={"/register"} , method = RequestMethod.GET)
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
    	model.addAttribute("registerRequest", new UserModel());
		return "register_page";
	}
    //@RequestMapping(value={"/login"} , method = RequestMethod.GET)
	@GetMapping("/login")
	public String getLoginPage(Model model) {
    	model.addAttribute("loginRequest", new UserModel());
		return "login_page";
	}
    
    //@RequestMapping(value={"/register"} , method = RequestMethod.POST)
	@PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
    	System.out.println("register request: " + userModel);
		UserModel registeredUser = usersService.registerUser(userModel.getLogin(),userModel.getPassword(),userModel.getEmail());
		return registeredUser == null? "error_page":"redirect:/login";
	}
    
    //@RequestMapping(value={"/login"} , method = RequestMethod.POST)
	@PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
    	System.out.println("login request: " + userModel);
		UserModel authenticated = usersService.authenticate(userModel.getLogin(),userModel.getPassword());
		if(authenticated!=null) {
			
			model.addAttribute("userLogin", model.addAttribute(authenticated.getLogin()));
			return "personal_page";
		}else {
			return "personal_page";
		}
		
	}
    
}
