import javax.sound.sampled.*;

public class SintetizadorMorse {
    public void tocarBipe(int duracaoMs) {
        try {
            float taxaAmostragem = 8000f;
            AudioFormat formato = new AudioFormat(taxaAmostragem, 8, 1, true, false);
            SourceDataLine linhaAudio = AudioSystem.getSourceDataLine(formato);
            linhaAudio.open(formato);
            linhaAudio.start();

            byte[] buffer = new byte[1];
            int totalAmostras = (int) (duracaoMs * (taxaAmostragem / 1000));

            for (int i = 0; i < totalAmostras; i++) {
                double angulo = i / (taxaAmostragem / 800.0) * 2.0 * Math.PI;
                buffer[0] = (byte) (Math.sin(angulo) * 100.0);
                linhaAudio.write(buffer, 0, 1);
            }

            linhaAudio.drain();
            linhaAudio.close();
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Erro ao reproduzir som: " + e.getMessage());
        }
    }

    public void reproduzirMorse(String codigoMorse){
        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);

            if (simbolo == '.') {
                tocarBipe(150);
            } else if (simbolo == '-') {
                tocarBipe(400);
            } else if (simbolo == ' ') {
                try {
                    Thread.sleep(300);
                } catch (Exception e) {}
            }
        }
    }
}
