package com.example.realestate.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="builder")
	public class Builder {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String companyName;
	    private String contactInfo;
	    private String email;
	    private String password;

	    @OneToMany(mappedBy = "builder")
	    private List<Property> properties;

		public Builder() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Builder(Long id, String name, String companyName, String contactInfo, String email, String password,
				List<Property> properties) {
			super();
			this.id = id;
			this.name = name;
			this.companyName = companyName;
			this.contactInfo = contactInfo;
			this.email = email;
			this.password = password;
			this.properties = properties;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getContactInfo() {
			return contactInfo;
		}

		public void setContactInfo(String contactInfo) {
			this.contactInfo = contactInfo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Property> getProperties() {
			return properties;
		}

		public void setProperties(List<Property> properties) {
			this.properties = properties;
		}

		@Override
		public String toString() {
			return "Builder [id=" + id + ", name=" + name + ", companyName=" + companyName + ", contactInfo="
					+ contactInfo + ", email=" + email + ", password=" + password + ", properties=" + properties + "]";
		}

	    
	}


