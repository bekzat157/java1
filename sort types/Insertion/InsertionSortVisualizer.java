package Insertion;

import javax.swing.*;
import java.awt.*;

public class InsertionSortVisualizer extends JPanel {
    private int[] array = {50, 20, 80, 10, 60, 30, 90, 40, 70, 5};
    private int currentI = -1; // Қазіргі өңделіп жатқан индекс
    private int currentJ = -1; // Салыстырылып жатқан индекс

    public InsertionSortVisualizer() {
        // Жеке ағында сұрыптауды бастау (интерфейс қатып қалмауы үшін)
        new Thread(() -> {
            try {
                insertionSort();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void insertionSort() throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            currentI = i;
            int swap = array[i];
            int j;

            for (j = i; j > 0 && swap < array[j - 1]; j--) {
                currentJ = j;
                array[j] = array[j - 1];

                // Экранды қайта сызу және азғантай кідіріс (анимация үшін)
                repaint();
                Thread.sleep(500);
            }
            array[j] = swap;
            currentJ = j;
            repaint();
            Thread.sleep(500);
        }
        currentI = -1; // Сұрыптау аяқталды
        currentJ = -1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / array.length;

        for (int i = 0; i < array.length; i++) {
            int height = array[i] * 3; // Биіктігін масштабтау

            // Түстерді анықтау
            if (i == currentI) {
                g.setColor(Color.RED); // Қазіргі таңдалған элемент
            } else if (i == currentJ) {
                g.setColor(Color.GREEN); // Салыстырылып жатқан орын
            } else {
                g.setColor(Color.BLUE); // Қалғандары
            }

            // Бағанды сызу
            g.fillRect(i * width, getHeight() - height, width - 5, height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), i * width + (width / 4), getHeight() - height - 5);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Insertion Sort Visualizer");
        InsertionSortVisualizer panel = new InsertionSortVisualizer();
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
