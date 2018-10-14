package com.primeng.dto;

import java.io.Serializable;
import java.util.Date;

import com.primeng.models.Candidate;

public class CandidateDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Date dob;
	private String gender;
	
	public CandidateDTO() {
		
	}
	
	public CandidateDTO(Candidate obj) {
		this.id     = obj.getId();
		this.name   = obj.getName();
		this.dob    = obj.getDob();
		this.gender = obj.getGender();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
