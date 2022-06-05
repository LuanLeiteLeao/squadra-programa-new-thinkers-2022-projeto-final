package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.BairroServiceImp;
import lombok.Getter;
import static org.springframework.http.HttpStatus.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    private BairroServiceImp service;

    public BairroController(BairroServiceImp service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public BairroDTO salvar(@RequestBody @Valid BairroDTO bairroDTO){
        return service.salvar(bairroDTO);
    }

    @PutMapping
    public BairroDTO atualizar(@RequestBody @Valid BairroDTO bairroDTO){
        return service.atualizar(bairroDTO);
    }

    @DeleteMapping
    public List<BairroDTO> deletar(@RequestParam Long codigoBairro){
        return service.deletar(codigoBairro);
    }
}
