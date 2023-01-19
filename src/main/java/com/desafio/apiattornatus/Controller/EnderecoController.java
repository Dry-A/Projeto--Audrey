package com.desafio.apiattornatus.Controller;

import com.desafio.apiattornatus.Model.Endereco;
import com.desafio.apiattornatus.Repository.EnderecoRepository;
import com.desafio.apiattornatus.Repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {

        return ResponseEntity.ok(enderecoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/logradouro/{logradouro}")
    public ResponseEntity<List<Endereco>> getByLogradouro(@PathVariable String logradouro) {

        return ResponseEntity.ok(enderecoRepository.findAllByLogradouroContainingIgnoreCase(logradouro));
    }

    @PostMapping("/adicionar") //metodo para salvar o endereço
    public Endereco adiciona(@RequestBody @Valid Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @PutMapping
    public ResponseEntity<Endereco> put(@Valid @RequestBody Endereco endereco) {
        return enderecoRepository.findById(endereco.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(enderecoRepository.save(endereco)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        enderecoRepository.deleteById(id);
    }
}
