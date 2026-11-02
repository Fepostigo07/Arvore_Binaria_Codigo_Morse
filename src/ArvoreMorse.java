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

    public String decodificarPalavra(String morsePalavra){
        String[] codigos = morsePalavra.split(" ");
        String palavraFinal = "";
        for (int i = 0; i < codigos.length;i++){
            palavraFinal += decodificarLetra(codigos[i]);
        }

        return palavraFinal;
    }

    private String buscarCodigo(No atual, String letraProcurada, String caminhoAtual){
        if(atual == null) return null;
        if (atual.caractere.equalsIgnoreCase(letraProcurada)) return caminhoAtual;
        String caminhoEsquerda = buscarCodigo(atual.esquerda, letraProcurada,caminhoAtual + ".");
        if (caminhoEsquerda != null) return caminhoEsquerda;
        String caminhoDireita = buscarCodigo(atual.direita, letraProcurada, caminhoAtual + "-");
        return caminhoDireita;
    }

    public String codificarPalavra(String palavra){
        String morseFinal = "";
        for (int i = 0; i < palavra.length(); i++){
            String letra = String.valueOf(palavra.charAt(i));
            morseFinal += buscarCodigo(this.raiz,letra,"") + " ";
        }
        return morseFinal.trim();
    }
}