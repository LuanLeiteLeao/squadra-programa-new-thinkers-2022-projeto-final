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

    @GetMapping
    public List<BairroDTO> listarTodos(){
        return service.listarTodos();
    }

    @GetMapping(params = "codigoBairro")
    public BairroDTO consultarPorCodigoBairro(@RequestParam Long codigoBairro){
//        CONSULTANDO POR codigoBairro, TRAGA APENAS UM REGISTRO
        return service.consultarPorCodigoBairro(codigoBairro);
    }

    @GetMapping(params = "codigoMunicipio" )
    public  List<BairroDTO> listarPorCodigoMunicipio(@RequestParam Long codigoMunicipio){
        // LISTANDO POR codigoMunicipio, TRAGA TODOS OS BAIRROS DO MUNICÍPIO RECEBIDO COMO PARÂMETRO.
        return service.listarPorCodigoMunicipio(codigoMunicipio);
    }
}
