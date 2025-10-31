package com.jsp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String indutry;
	private String location;
	private String website;
	private String email;
	private long contactNumber;
	private int establishedYear;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndutry() {
		return indutry;
	}
	public void setIndutry(String indutry) {
		this.indutry = indutry;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getEstablishedYear() {
		return establishedYear;
	}
	public void setEstablishedYear(int establishedYear) {
		this.establishedYear = establishedYear;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", indutry=" + indutry + ", location=" + location + ", website="
				+ website + ", email=" + email + ", contactNumber=" + contactNumber + ", establishedYear="
				+ establishedYear + "]";
	}
	
	
	
}
