package com.prueba.serti.entities;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pokemon")
@Data
public class pokemon implements Serializable{
	
	@Id
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "habitat_name")
	private String habitat_name;
	@Column(name = "capture_rate")
	private int capture_rate;
	@Column(name = "evolution_chain_url")
	private String evolution_chain_url;
}
