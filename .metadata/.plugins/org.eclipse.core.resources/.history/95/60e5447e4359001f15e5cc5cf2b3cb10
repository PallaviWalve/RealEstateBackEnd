package com.example.realestate.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;
    private String email;

    @OneToMany(mappedBy = "agent")
    private List<Appointment> appointments;

	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent(Long id, String name, String contactInfo, String email, List<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.contactInfo = contactInfo;
		this.email = email;
		this.appointments = appointments;
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

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + ", email=" + email
				+ ", appointments=" + appointments + "]";
	}

    
}
