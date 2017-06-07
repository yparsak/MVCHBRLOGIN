package com.sample.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.model.User;
import com.sample.service.UserService;

@Controller
public class WebController {
	
	final private String project_name = "MVCHBRLOGIN";
	final private String default_value = "";
	final private String user_cookie_name = "user";
	final private int max_login_time = 7200; // 2 hours
	
	private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="UserService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	// Default View
	@RequestMapping({"/", "index"})
	public String index(Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = default_value) String userid) {
		
		if (! userid.equals(default_value)) {
			int id = Integer.parseInt(userid);
			User u = this.userService.getUserById(id);
			
			if (u != null) {
				model.addAttribute("id", userid);
				model.addAttribute("name", u.getName());
				model.addAttribute("lastname", u.getLastname());
				model.addAttribute("email", u.getEmail());				
			}
		}
		return "index";
	}

	@RequestMapping(value="login")
	public String login(Model model, @ModelAttribute(project_name)User u,HttpServletResponse response) {
		if (u != null) {
			User user = this.userService.getUser(u.getName(), u.getPassword());
			if (user != null) {
				Cookie cookie = new Cookie(user_cookie_name, String.valueOf(user.getId()));
				cookie.setMaxAge(max_login_time);
				response.addCookie(cookie);
			}			
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie(user_cookie_name,default_value);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	@RequestMapping(value="register")
	public String register(Model model, @ModelAttribute(project_name)User u) {
		this.userService.addUser(u);
		return "redirect:/";
	}
}
