public class Excecao extends Exception {

    public Excecao() {
        super();
    }

    public Excecao(String mensagem) {
        super(mensagem);
    }

    public Excecao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public Excecao(Throwable causa) {
        super(causa);
    }
}