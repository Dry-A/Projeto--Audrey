package com.desafio.apiattornatus.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // identifica a classe como entidade e será mapeada no bco de dados.
@Getter // anotação do lombok que possibilita métodos getters sem escrevê-los
@Setter
@NoArgsConstructor // essa anotação do lombok é responsável por gerar um construtor sem parâmetros.
@Table(name="tb_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "atributo logradouro é obrigatório!")
    private String logradouro;
    @NotBlank(message = "atributo CEP é obrigatório!")
    private String cep;
    @NotBlank(message = "atributo número é obrigatório!")
    private String numero;
    @NotBlank(message = "atributo cidade é obrigatório!")
    private String cidade;
    private boolean enderecoPrincipal;
    @Column(name = "id_pessoa") // Coluna renomeada para fazer referencia na model de Pessoa
    private Long pessoa_id;

    }
