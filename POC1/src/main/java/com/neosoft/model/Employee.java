package com.neosoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 65)
	private String firstname;
	@NotNull
	@Size(max = 65)
	private String surname;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	private Date dob;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="doj")
	private Date doj;
	
	
	@NotNull
	@Size(max = 265)
	private String Address;
	
	@NotNull
	@Size(max = 10)
	private String pincode;
	
	@Column(name="deleted")
	private int deleted;
}
