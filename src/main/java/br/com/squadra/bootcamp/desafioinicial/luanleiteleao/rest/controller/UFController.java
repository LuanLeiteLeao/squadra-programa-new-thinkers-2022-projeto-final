package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.UFServideImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/uf")
public class UFController {

    private UFServideImp service;

    public UFController(UFServideImp service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UF salvar(@RequestBody @Valid UFDTO ufDTO){
        return service.salvar(ufDTO);
    }

    @DeleteMapping
    public List<UF> deletar(Long codigoUf){
        return service.deletar(codigoUf);
    }

    @PutMapping
    public UF atualizar(@RequestBody  UF uf){
       return service.atualizar(uf);
    }


}


