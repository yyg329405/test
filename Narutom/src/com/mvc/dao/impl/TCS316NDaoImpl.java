package com.mvc.dao.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

@Repository(value="tsc316ndao")
public class TCS316NDaoImpl {
	
	public TCS316NDaoImpl(){}

	/**
	 * add finger
	 */
	public Boolean addFinger(String username, String identification,String fingerString){
		System.out.println("addFinger");
		return  null;
	}
	
	/**
	 * get fingers by qyname
	 */
	public List<JSONObject> getFingersByQyname(String qyname){
		System.out.println("getFingersByQyname");
		return  null;
	}
	
	/**
	 * update person's finger
	 */
	public Boolean updateFinger(String userid, String fingerString){
		System.out.println("updateFinger");
		return  null;
	}
	
	
}
