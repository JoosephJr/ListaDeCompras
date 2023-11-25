package br.senac.sp.epictask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// ORM
@Entity //converte do db para o java e do java para db
@Data
public class Task {

    //boilerplate - métodos que são gerados automaticamente, códigos clichês
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "O valor não pode estar em branco!")
    private String title;

    @Size(min = 5, message = "Digite no mínimo 5 caracteres")
    private String description;
    
    @Min(value = 1, message = "O valor deve ser maior ou igual a 1")
    private Integer score;



}