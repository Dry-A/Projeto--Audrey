package com.desafio.apiattornatus.Repository;

import com.desafio.apiattornatus.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {

    public List <Endereco> findAllByLogradouroContainingIgnoreCase(@Param("logradouro") String logradouro );
}
