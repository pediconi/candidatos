package com.example.SimulacroParcial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_voto;
    private LocalDateTime fecha;

    @JsonIgnore     // cuando quiera construir un json de este objeto, no tendra en cuenta el atributo candidato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id", referencedColumnName = "id" )   // foreign key indicando name: tablaReferenciada (el nombre que tendra el campo en ESTA tabla) + "_id"
    private Candidato candidato;                                        // y el campo referenciado de la otra tabla (referencedColumnName)

    @PrePersist // se ejecuta antes del save
    public void checkFecha(){
        if(this.fecha == null){
            this.fecha = LocalDateTime.now();
        }
    }
}

/*  @Entity
public class Office {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private Address address;
}
The above code example will create a foreign key linking the Office entity with the primary key from the Address entity.
The name of the foreign key column in the Office entity is specified by name property.*/
