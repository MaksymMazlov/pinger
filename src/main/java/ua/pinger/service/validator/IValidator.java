package ua.pinger.service.validator;

public interface IValidator<T> {
    boolean isValid(T t);
}
