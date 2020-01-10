package com.br.estudo.crudpessoa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor 
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotNull
    private String rg;

    @NotNull
    private String cpf;

    public Pessoa(String nome, Date dataNascimento, String rg, String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.cpf = cpf;
    }
}
