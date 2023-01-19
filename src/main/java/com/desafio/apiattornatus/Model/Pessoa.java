package com.desafio.apiattornatus.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity // identifica a classe como entidade e será mapeada no bco de dados.
@Getter // anotação do lombok que possibilita métodos getters sem escrevê-los
@Setter
@NoArgsConstructor // essa anotação do lombok é responsável por gerar um construtor sem parâmetros.
@Table(name="tb_pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa") /// Esse Join column faz referencia ao id dessa propria classe Pessoa.
    private List<Endereco> endereco;

}
