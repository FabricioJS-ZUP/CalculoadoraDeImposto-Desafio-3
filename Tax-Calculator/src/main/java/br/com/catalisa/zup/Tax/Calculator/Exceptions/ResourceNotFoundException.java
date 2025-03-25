package br.com.catalisa.zup.Tax.Calculator.Exceptions;
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}