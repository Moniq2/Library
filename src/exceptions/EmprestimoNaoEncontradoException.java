package exceptions;

public class EmprestimoNaoEncontradoException extends Exception{
    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}