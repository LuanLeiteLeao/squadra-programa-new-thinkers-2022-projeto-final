package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.PessoaService;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.PessoaServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pessoa")
public class PessoasController {

    private PessoaServiceImp service;

    public PessoasController(PessoaServiceImp service) {
        this.service = service;
    }

    @GetMapping
    public List<Pessoa> listarTodos(){
//        a ser implementado
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PessoaDTO salvar(@RequestBody @Valid PessoaDTO pessoa){

        return service.salvar(pessoa);
    }
}
