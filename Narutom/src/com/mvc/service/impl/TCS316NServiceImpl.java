package com.mvc.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mvc.dao.TCS316NDao;

@Repository(value="tsc316nservice")
public class TCS316NServiceImpl {
	
	public TCS316NServiceImpl(){}
	
	@Autowired
	@Qualifier("tsc316ndao")
	private TCS316NDao tsc316ndao;
	
	/**
	 * finger register
	 */
	public Boolean register(String username, String identification,String fingerString){
		return tsc316ndao.addFinger(username, identification,fingerString);
	}
	
	/**
	 * get all fingers by qyname
	 */
	public List<JSONObject> getFingersByQyname(String qyname){
		return tsc316ndao.getFingersByQyname(qyname);
	}
	
	
	/**
	 * update person's finger
	 */
	public Boolean updateFinger(String userid, String fingerString){
		return tsc316ndao.updateFinger(userid, fingerString);
	}
	
}
