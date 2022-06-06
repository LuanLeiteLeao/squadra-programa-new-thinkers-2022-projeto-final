package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation;


import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.constraintvalidation.NotEmptySetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptySetValidator.class )
public @interface NotEmptyList {

    String message() default  "A Lista não pode ser vazia";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
