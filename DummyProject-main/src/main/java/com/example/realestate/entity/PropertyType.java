package com.example.realestate.entity;

import java.util.List;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="propertytype")
public class PropertyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeName;

    @OneToMany(mappedBy = "propertyType")
    private List<Property> properties;
    
    @ManyToOne
    @JoinColumn(name = "builder_id")
    @Cascade(CascadeType.ALL)
    private Builder builderRef;

	public PropertyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyType(Long id, String typeName, List<Property> properties,Builder builderRef) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.properties = properties;
		this.builderRef = builderRef;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	public Builder getBuilderRef() {
		return builderRef;
	}



	public void setBuilderRef(Builder builderRef) {
		this.builderRef = builderRef;
	}
	

	@Override
	public String toString() {
		return "PropertyType [id=" + id + ", typeName=" + typeName + ", properties=" + properties + "]";
	}

   
}
