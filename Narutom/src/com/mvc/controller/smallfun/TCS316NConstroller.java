package com.mvc.controller.smallfun;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.service.TCS316NService;

@Controller
public class TCS316NConstroller {
	
	public TCS316NConstroller() {
	}

	@Autowired
	@Qualifier("tsc316nservice")
	private TCS316NService tsc316nservice;
	

	@RequestMapping(value = "/regfinger", method = RequestMethod.GET)
	public void registPost(
			@RequestParam(value = "fingervalue", required = true) String fingervalue,
			PrintWriter out) {
		
		System.out.println(fingervalue);
		
		String username = "";
		String identification = "";
		String fingerString = fingervalue;
//		tsc316nservice.register(username, identification, fingerString);
		
		out.write(fingervalue);
		out.flush();
		out.close();
	}
}
