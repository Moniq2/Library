package br.com.biblioteca.exception;

public class UsuarioException extends RuntimeException{
    public UsuarioException(String mensagem){
        super(mensagem);
    }
}
