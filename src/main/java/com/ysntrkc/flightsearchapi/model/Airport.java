package com.ysntrkc.flightsearchapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "\"Airports\"", uniqueConstraints = { @UniqueConstraint(columnNames = { "city" }) })
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "city")
	@NotBlank(message = "City is mandatory")
	private String city;

	public Airport() {
	}

	public Airport(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Airport [id=" + id + ", city=" + city + "]";
	}

}
