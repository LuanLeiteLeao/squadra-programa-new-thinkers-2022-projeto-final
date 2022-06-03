package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.exception;

public class JaExisteUmRegistroSalvoException  extends  RuntimeException{
    public JaExisteUmRegistroSalvoException(String nomeDoCampo,String informacaoDoCampo ) {
        super(String.format( "Ja existe um Registro %s em %s",informacaoDoCampo,nomeDoCampo));
    }
}
