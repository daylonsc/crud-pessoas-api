package com.br.estudo.crudpessoa.repository;

import com.br.estudo.crudpessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
