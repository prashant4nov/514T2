package com.hmkcode.vo;
/**
 * author @ pku@iastate.edu
 *
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Content2 implements Serializable {

	
	
	private Map<String,String> registration;
	private Map<String,String> data;
	
	
	public void addRegId(String regId){
		if(registration == null)
			registration = new HashMap<String,String>();
		registration.put("registration_id", regId);
	}
	
	public void createData(String title, String message){
		if(data == null)
			data = new HashMap<String,String>();
	
		data.put("title", title);
		data.put("message", message);
	}
	
	
	public Map<String, String> getRegistration_ids() {
		return registration;
	}

	public void setRegistration_ids(Map<String, String> registration_ids) {
		this.registration = registration_ids;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
}
