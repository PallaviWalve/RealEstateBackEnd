package com.example.realestate.entity;

import java.sql.Date;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_date")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    
    private String status;  // e.g., Pending, Confirmed, Completed, Canceled

    private Date scheduledTime;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Cascade(CascadeType.ALL)
    private User user;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    @Cascade(CascadeType.ALL)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    @Cascade(CascadeType.ALL)
    private Flat flat;

    @OneToOne(mappedBy = "appointment")
    private Feedback feedback;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(Long id, Date appointmentDate, String status, Date scheduledTime, User user,
			Agent agent, Flat flat, Feedback feedback) {
		super();
		this.id = id;
		this.appointmentDate = appointmentDate;
		this.status = status;
		this.scheduledTime = scheduledTime;
		this.user = user;
		this.agent = agent;
		this.flat = flat;
		this.feedback = feedback;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", appointmentDate=" + appointmentDate + ", status=" + status
				+ ", scheduledTime=" + scheduledTime + ", user=" + user + ", agent=" + agent + ", flat=" + flat
				+ ", feedback=" + feedback + "]";
	}

	
	

    
}
