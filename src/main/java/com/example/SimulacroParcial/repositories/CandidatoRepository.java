package com.example.SimulacroParcial.repositories;

import com.example.SimulacroParcial.models.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
    //@Query(value = "delete from vot" ,nativeQuery = true)
    //void removeVotes(Integer idVote);
}
