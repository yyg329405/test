package com.mvc.controller.testPart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AjaxIterationTest {

	public AjaxIterationTest() {
	}
	
	@RequestMapping(value="/ajaxTest", method=RequestMethod.GET)
	public void ajaxTest(String testStr){
		System.out.println("testStr = "+testStr);
	}

}
