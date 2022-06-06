package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.constraintvalidation;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;

//constraintvalidation
public class NotEmptySetValidator implements ConstraintValidator<NotEmptyList, List> {
    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}
