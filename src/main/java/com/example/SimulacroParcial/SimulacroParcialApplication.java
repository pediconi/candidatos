package com.example.SimulacroParcial;

import com.example.SimulacroParcial.models.Candidato;
import com.example.SimulacroParcial.repositories.CandidatoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.rowset.CachedRowSet;

@SpringBootApplication
@EnableScheduling
public class SimulacroParcialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulacroParcialApplication.class, args);

		Candidato candidato;

		CandidatoRepository candidatoRepository;
	}

}
