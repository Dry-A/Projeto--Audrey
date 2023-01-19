package com.desafio.apiattornatus.Repository;

import com.desafio.apiattornatus.Model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);

}
