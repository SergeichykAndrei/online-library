package by.andreisergeichyk.validator;

import org.springframework.core.GenericTypeResolver;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface CustomeValidator<T> extends Validator {

    @Override
    default boolean supports(Class<?> clazz) {
        Class<?> currentClass = GenericTypeResolver.resolveTypeArgument(getClass(), CustomeValidator.class);

        return currentClass.isAssignableFrom(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    default void validate(Object target, Errors errors) {
        check((T) target, errors);
    }

    void check(T target, Errors errors);
}
