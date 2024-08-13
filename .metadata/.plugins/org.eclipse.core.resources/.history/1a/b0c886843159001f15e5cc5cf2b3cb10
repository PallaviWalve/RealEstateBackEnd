package com.example.realestate.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="entity")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amenityName;

    @ManyToMany(mappedBy = "amenities")
    private List<Property> properties;

	public Amenity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Amenity(Long id, String amenityName, List<Property> properties) {
		super();
		this.id = id;
		this.amenityName = amenityName;
		this.properties = properties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Amenity [id=" + id + ", amenityName=" + amenityName + ", properties=" + properties + "]";
	}

   
}
