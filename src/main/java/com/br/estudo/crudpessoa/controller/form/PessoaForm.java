package com.br.estudo.crudpessoa.controller.form;

import com.br.estudo.crudpessoa.model.Pessoa;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PessoaForm {

    @NotNull @NotEmpty
    private String nome;

    @NotNull
    private Date dataNascimento;

    @NotNull @NotEmpty
    private String rg;

    @NotNull @NotEmpty @Size(min = 11, max = 11)
    private String cpf;

    public Pessoa converter(Long id) {
        return new Pessoa(id, nome, dataNascimento, rg, cpf);
    }
}
