package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.classes.Paciente;


@Repository
public interface RepositorioPaciente extends CrudRepository<Paciente, Long> {
	List<Paciente> findByNomeContaining(String nome);
}
