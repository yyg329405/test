package com.mvc.service;

import java.util.List;

import net.sf.json.JSONObject;

public interface TCS316NService {
	
	/**
	 * finger register
	 */
	public Boolean register(String username, String identification,String fingerString);
	
	/**
	 * get all fingers by qyname
	 */
	public List<JSONObject> getFingersByQyname(String qyname);
	
	/**
	 * update person's finger
	 */
	public Boolean updateFinger(String userid, String fingerString);
	
}
