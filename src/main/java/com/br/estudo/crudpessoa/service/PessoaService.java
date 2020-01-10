package com.br.estudo.crudpessoa.service;

import com.br.estudo.crudpessoa.config.CpfDuplicadoException;
import com.br.estudo.crudpessoa.model.Pessoa;
import com.br.estudo.crudpessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {

        if (existeCpf(pessoa.getCpf())) throw new CpfDuplicadoException();

        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Pessoa pessoaBeforeSave, Pessoa pessoa) {

        if(!Objects.equals(pessoaBeforeSave.getCpf(), pessoa.getCpf())){
            if (existeCpf(pessoa.getCpf())) throw new CpfDuplicadoException();
        }

        return pessoaRepository.save(pessoa);
    }

    private boolean existeCpf(String cpf) {
        return pessoaRepository.existsByCpf(cpf);
    }

}
