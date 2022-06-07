package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.EnderecoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
//    public List<EnderecoDTO> listarEnderecosPorCodigoPessoa(Long codigoPessoa) {
    @Query(" select en from Endereco en where en.codigoPessoa = :codigoPessoa")
    List<Endereco> procuraPorCodigoPessoaTodosEnderecos(@Param("codigoPessoa")Pessoa pessoa);
}
