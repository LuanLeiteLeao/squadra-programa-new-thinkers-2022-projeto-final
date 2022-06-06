package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
