import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Scanner;

public class TreeVisualizer extends Application {

    // Calcula a altura da árvore a partir da raiz
    public int getHeight(No node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.esquerda), getHeight(node.direita));
    }

    public void drawTree(Canvas canvas, No raiz) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // Começa o desenho da árvore na raiz
        drawNode(gc, raiz, canvas.getWidth() / 2, 40, canvas.getWidth() / 4, 1);
    }

    private void drawNode(GraphicsContext gc, No node, double x, double y, double xOffset, int level) {
        if (node == null) {
            return;
        }

        // Desenha um círculo ao redor do nó
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        // Desenha a letra dentro do círculo
        gc.strokeText(node.caractere, x - 5, y + 5);

        // Desenho das linhas para os nós filhos
        if (node.esquerda != null) {
            double newX = x - xOffset;
            double newY = y + 120;
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.esquerda, newX, newY, xOffset / 2, level + 1);
        }

        if (node.direita != null) {
            double newX = x + xOffset;
            double newY = y + 120;
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.direita, newX, newY, xOffset / 2, level + 1);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Árvore Binária");

        ArvoreMorse arvore = new ArvoreMorse();
        SintetizadorMorse sintetizador = new SintetizadorMorse();

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite uma palavra: ");
        String palavra = sc.nextLine();
        String morse = arvore.codificarPalavra(palavra);
        System.out.println("A palavra " + palavra + " codificada, fica: " + morse);
        sintetizador.reproduzirMorse(morse);

        System.out.println("Digite uma palavra em morse: ");
        String palavraEmMorse = sc.nextLine();
        String palavraTrduzida = arvore.decodificarPalavra(palavraEmMorse);
        System.out.println("A palavra em morse " + palavraEmMorse + " traduzida, fica: " + palavraTrduzida);

        try {
            Thread.sleep(200);
        } catch (Exception e) {}

        // Inicialização da Janela
        int height = getHeight(arvore.raiz);
        int canvasHeight = 100 + height * 100;
        int canvasWidth = (int) Math.pow(2, height) * 40;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        drawTree(canvas, arvore.raiz);

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}