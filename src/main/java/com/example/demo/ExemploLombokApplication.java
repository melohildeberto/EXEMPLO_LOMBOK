package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.classes.Paciente;
import com.example.demo.repositorios.RepositorioPaciente;

@SpringBootApplication
public class ExemploLombokApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploLombokApplication.class, args);
		Paciente p = new Paciente();
	}

//	@Bean
	public CommandLineRunner cadastrarPaciente(RepositorioPaciente repositorio) {
		return (args) -> {
			try {
				for (int i = 1; i <= 100; i++) {
					Paciente p = new Paciente();
					p.setNome("Paciente " + i);
					p.setDocumento("Doc Paciente " + i);
					repositorio.save(p);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}

//	@Bean
	public CommandLineRunner listarTodosOsPacientes(RepositorioPaciente repositorio) {
		return (args) -> {
			try {
				Iterable<Paciente> pacientes = repositorio.findAll();
				for (Paciente paciente : pacientes) {
					System.out.println(paciente.getNome());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}

//	@Bean
	public CommandLineRunner pegarUmPaciente(RepositorioPaciente repositorio) {
		return (args) -> {
			try {
				Optional<Paciente> objPesquisa = repositorio.findById(2l);
				if (objPesquisa.isPresent()) {
					Paciente p = objPesquisa.get();
					System.out.println(p);
				} else {
					System.out.println("Paciente não encontrado");
				}
			} catch (Exception e) {
				System.out.println("Exceção gerada: " + e.getMessage());
			}
		};
	}

//	@Primary
//	@Bean
	public CommandLineRunner removerUmPaciente(RepositorioPaciente repositorio) {
		return (args) -> {
			try {
				Optional<Paciente> objPesquisa = repositorio.findById(1l);
				if (objPesquisa.isPresent()) {
					Paciente p = objPesquisa.get();
					repositorio.delete(p);
					System.out.println("Paciente removido com sucesso");
				} else {
					System.out.println("Paciente não encontrado");
				}
			} catch (Exception e) {
				System.out.println("Exceção gerada: " + e.getMessage());
			}
		};
	}

	@Bean
	public CommandLineRunner pegarUmPacientePeloNome(RepositorioPaciente repositorio) {
		return (args) -> {
			try {
				ArrayList<Paciente> objPesquisa = (ArrayList<Paciente>) repositorio.findByNomeContaining("coisado");
				for (Paciente paciente : objPesquisa) {
					System.out.println(paciente);
				}
			} catch (Exception e) {
				System.out.println("Exceção gerada: " + e.getMessage());
			}
		};
	}

//	@Bean
	public CommandLineRunner alterarPaciente(RepositorioPaciente repositorio) {
		return (args) -> {
			try {
				if (repositorio.existsById(15l)) {
					Paciente p = new Paciente();
					p.setId(15l);
					p.setNome("Paciente melo coisado");
					p.setDocumento("Doc Paciente melo coisado demais");
					repositorio.save(p);
					System.out.println("Paciente alterado com sucesso");
				}else {
					System.out.println("O referido paciente não existe");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}
}
