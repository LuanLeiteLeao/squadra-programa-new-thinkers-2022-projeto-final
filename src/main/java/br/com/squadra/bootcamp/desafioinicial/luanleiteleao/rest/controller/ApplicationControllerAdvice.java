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
import org.springframework.validation.BindException;
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

        return new ApiErros(BAD_REQUEST,String.join(" , ",erros));
    }

    @ExceptionHandler(JaExisteUmRegistroSalvoException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComExecaoJaExisteUmRegistroSalvoException( JaExisteUmRegistroSalvoException ex){
        return new ApiErros(BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErros lidarComResponseStatusException(ResponseStatusException ex){
        return new ApiErros(NOT_FOUND,ex.getReason());
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex){
        return new ApiErros(BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComMethodArgumentTypeMismatchException( MethodArgumentTypeMismatchException ex){
        return new ApiErros(BAD_REQUEST,"incompatibilidade de valor para parametro "+ ex.getName());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros lidarComBindException( BindException ex){
        return new ApiErros(BAD_REQUEST,"incompatibilidade com tipo de dado informado ");
    }
//    BindException .NumberFormatException

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErros lidarComAmbiguidadeDeRota(IllegalStateException ex){
        return new ApiErros(NOT_FOUND,"URL inválida");
    }

}
