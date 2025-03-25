package br.com.catalisa.zup.Tax.Calculator.Exceptions;
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}