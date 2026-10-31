public class ArvoreMorse {
    private No raiz;

    public ArvoreMorse() {
        this.raiz = new No("");
    }

    public void inserir(String caractere, String codigoMorse) {
        No atual = this.raiz;

        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);

            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new No("");
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    atual.direita = new No("");
                }
                atual = atual.direita;
            }
        }
        atual.caractere = caractere;
    }

    public String decodificarLetra(String codigoMorse) {
        No atual = raiz;
        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);

            if (simbolo == '.') {
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                atual = atual.direita;
            } else {
                return "";
            }
        }
        return atual.caractere;
    }
}