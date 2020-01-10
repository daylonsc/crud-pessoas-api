package com.br.estudo.crudpessoa.controller.dto;

import com.br.estudo.crudpessoa.model.Pessoa;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
public class PessoaDTO {

    private Long id;
    private String nome;
    private Date dataNascimento;
    private String rg;
    private String cpf;

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.rg = pessoa.getRg();
        this.cpf = pessoa.getCpf();
    }

    public static List<PessoaDTO> converter(List<Pessoa> list) {
        return list.stream().map(PessoaDTO::new).collect(toList());
    }
}
