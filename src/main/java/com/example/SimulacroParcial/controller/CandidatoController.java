package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.models.Candidato;
import com.example.SimulacroParcial.models.Vote;
import com.example.SimulacroParcial.repositories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/candidato")  // le indico que es una controladora osea cuando ponga localhost/persona viene a esta clase
@RestController
public class CandidatoController {

    private static final String PERSON_NOT_FOUND = "No existe el candidato con el id: %s";

    @Autowired
    private CandidatoRepository candidatoRepository;
    private static Calendar tmax = Calendar.getInstance();

    @PostMapping("")
    public void addCandidato(@RequestBody Candidato c) {   //convierte el JSON entrante a un objeto Candidato
        candidatoRepository.save(c);
    }

    @GetMapping("")   // peticiones get(desde postman) envia una peticion y llama a este metodo
    public List<Candidato> getAll(){
        return candidatoRepository.findAll();   //retorno la lista
    }

    @GetMapping("/{id}")
    public Optional<Candidato> getCandidatoById(@PathVariable Integer id){
        return candidatoRepository.findById(id);
    }

    @Scheduled(fixedRate = 1000)
    public void eliminaVotos() {
    List<Candidato> candidatos = candidatoRepository.findAll();
        for(Candidato c : candidatos){

            c.getVotos().removeIf(x -> (x.getFecha().plusMinutes(1).isBefore(LocalDateTime.now())));
            candidatoRepository.saveAndFlush(c);
        }
     }

    @PostMapping("/vote/{id}")   // para votar le paso en la url el id del candidato q quiero votar y debo escribir en json el voto (igual no esta incluido)
    public void votar(@PathVariable final Integer id, @RequestBody final Vote vote) {    // le paso en el body el voto
        Candidato c = candidatoRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND,id)));
        vote.setCandidato(c);
        c.getVotos().add(vote);
        candidatoRepository.save(c);

    }
}
