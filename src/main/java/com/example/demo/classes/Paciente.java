package com.example.demo.classes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PACIENTE")
@Data
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "DOCUMENTO")
	private String documento;
}
