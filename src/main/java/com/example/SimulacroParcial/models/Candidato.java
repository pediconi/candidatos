package com.example.SimulacroParcial.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // indica que va a ser una tabla

public class Candidato {

    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private String apellido;
      //               LAZY: para traer mas rapido (nombre, ap, y un proxy de los votos) || cascade: estrategia para borrar
    @ToString.Exclude // omite este campo en una salida toString para evitar que se muestre la lista cada vez q se quiera mostrar el candidato
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "candidato")    // muchos votos a uno
    private List<Vote> votos;
}
