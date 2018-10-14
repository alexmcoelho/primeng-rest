package com.primeng.dto;

import java.io.Serializable;
import java.util.Date;

public class CandidateNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date dob;
	private String gender;
	
	private String favkey;
	private String favValue;
	
	public CandidateNewDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFavkey() {
		return favkey;
	}

	public void setFavkey(String favkey) {
		this.favkey = favkey;
	}

	public String getFavValue() {
		return favValue;
	}

	public void setFavValue(String favValue) {
		this.favValue = favValue;
	}

}
