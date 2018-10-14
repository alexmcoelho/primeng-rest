package com.primeng.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Favourite implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String favkey;
	private String favValue;
	//não pode serializar o cliente
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	
	public Favourite() {
		super();
	}
	public Favourite(String favkey, String favValue, Candidate candidate) {
		super();
		this.favkey = favkey;
		this.favValue = favValue;
		this.candidate = candidate;
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
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((favkey == null) ? 0 : favkey.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favourite other = (Favourite) obj;
		if (favkey == null) {
			if (other.favkey != null)
				return false;
		} else if (!favkey.equals(other.favkey))
			return false;
		return true;
	}
	
}
