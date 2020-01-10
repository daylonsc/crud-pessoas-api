package com.br.estudo.crudpessoa.controller;

import com.br.estudo.crudpessoa.controller.dto.PessoaDTO;
import com.br.estudo.crudpessoa.controller.form.PessoaForm;
import com.br.estudo.crudpessoa.model.Pessoa;
import com.br.estudo.crudpessoa.repository.PessoaRepository;
import com.br.estudo.crudpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaDTO> buscarTodos() {
        return PessoaDTO.converter(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPeloCodigo(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(value -> ResponseEntity.ok(new PessoaDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaDTO> criar(@Valid @RequestBody PessoaForm form) {
        Pessoa pessoa = form.converter(null);
        pessoa = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaDTO(pessoa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaForm form) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = form.converter(id);
            pessoa = pessoaService.editarPessoa(pessoaOptional.get(), pessoa);
            return ResponseEntity.ok(new PessoaDTO(pessoa));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Pessoa> ooptionalPessoa = pessoaRepository.findById(id);

        if (ooptionalPessoa.isPresent()) {
            pessoaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
