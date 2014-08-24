package com.mvc.dao;

import java.util.List;

import net.sf.json.JSONObject;

public interface TCS316NDao {

	/**
	 * add finger
	 */
	public Boolean addFinger(String username, String identification,String fingerString);
	
	/**
	 * get fingers by qyname
	 */
	public List<JSONObject> getFingersByQyname(String qyname);
	
	/**
	 * update person's finger
	 */
	public Boolean updateFinger(String userid, String fingerString);
	
}
