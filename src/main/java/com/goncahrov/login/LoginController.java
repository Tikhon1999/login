package com.goncahrov.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goncahrov.service.LoginService;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public ModelAndView checkUser(@RequestParam("uname") String uname,
			@RequestParam("pass") String pass,
			HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		
//		String uname = request.getParameter("uname");
//		String pass = request.getParameter("pass");
		String firstname;
		LoginService service =  new LoginService();
		
		if(service.check(uname, pass)) {
			firstname = service.getUserFirstName(uname);
			mv.setViewName("welcome");
			mv.addObject("firstname", firstname);
		}else {
			mv.setViewName("index");
		}
		
		
		return mv;
	}
}
