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
                // 1. O que fazemos aqui para abrir caminho e avançar para a direita?
            }
        }

        // 2. Fora do for, já chegamos na posição final. Como salvamos o 'caractere' no nó 'atual'?
    }
}