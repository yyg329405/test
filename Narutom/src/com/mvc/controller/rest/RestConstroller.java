package com.mvc.controller.rest;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestConstroller {
	public RestConstroller() {
	}

	@RequestMapping(value = "/login/{user}", method = RequestMethod.GET)
	public ModelAndView myMethod(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("user") String user,
			ModelMap modelMap) throws Exception {
		modelMap.put("loginUser", user);
		return new ModelAndView("/hello", modelMap);
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String registPost() {
		return "/welcome";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirectWelcome() {
		return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String testParam(PrintWriter out, @RequestParam("username") String username) {
		//visit url: http://localhost:8080/Narutom/login2?username=ppp
		out.println(username);
		return null;
	}
	
	
}
