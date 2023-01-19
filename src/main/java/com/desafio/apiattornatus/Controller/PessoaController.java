package com.desafio.apiattornatus.Controller;

import com.desafio.apiattornatus.Model.Pessoa;
import com.desafio.apiattornatus.Repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {

        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable Long id){
        return pessoaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pessoa>> getByName(@PathVariable String nome) {

        return ResponseEntity.ok(pessoaRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Pessoa> post(@Valid @RequestBody Pessoa pessoa){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pessoaRepository.save(pessoa));
    }

    @PutMapping
    public ResponseEntity<Pessoa> put(@Valid @RequestBody Pessoa pessoa){
        return pessoaRepository.findById(pessoa.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(pessoaRepository.save(pessoa)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        pessoaRepository.deleteById(id);
    }

}



