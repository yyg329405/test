package com.mvc.controller.smallfun;

import java.io.PrintWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SmallFunctionConstroller {
	public SmallFunctionConstroller() {
	}

	@RequestMapping(value = "/autoComplete", method = RequestMethod.POST)
	public void registPost(
			@RequestParam(value = "featureClass", required = true) String featureClass,
			PrintWriter out) {
		
		System.out.println(featureClass);
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		
		JSONObject json = new JSONObject();
		json.put("AA", "AA");
		json.put("bB", "bb");
		array.add(json);

		json = new JSONObject();
		json.put("cc", "cc");
		json.put("d3", "dd3");
		array.add(json);

		json = new JSONObject();
		json.put("ea", "ex");
		json.put("f", "f");
		array.add(json);
		
		result.put("geonames",array);
		
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
