package com.prueba.serti.entities;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "pokemon")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
