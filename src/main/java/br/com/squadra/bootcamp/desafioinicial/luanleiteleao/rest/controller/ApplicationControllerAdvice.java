package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import static org.springframework.http.HttpStatus.*;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.exception.JaExisteUmRegistroSalvoException;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.ApiErros;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComExecaoMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> erros = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErros(erros);
    }

    @ExceptionHandler(JaExisteUmRegistroSalvoException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComExecaoJaExisteUmRegistroSalvoException( JaExisteUmRegistroSalvoException ex){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErros lidarComResponseStatusException(ResponseStatusException ex){
        return new ApiErros(ex.getReason());
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComMethodArgumentTypeMismatchException( MethodArgumentTypeMismatchException ex){
        return new ApiErros("incompatibilidade de valor para parametro "+ ex.getName());
    }



}
