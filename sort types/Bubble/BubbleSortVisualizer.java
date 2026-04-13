package Bubble;

import javax.swing.*;
import java.awt.*;

public class BubbleSortVisualizer extends JPanel {
    private int[] array = {12, 6, 4, 1, 15, 10, 8, 3}; // Сұрыпталатын массив
    private int currentJ = -1; // Қазіргі салыстырылып жатқан элемент
    private int currentI = -1;

    public void startSort() {
        new Thread(() -> {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                currentI = i;
                for (int j = 0; j < n - i - 1; j++) {
                    currentJ = j;

                    // Элементтерді салыстыру және ауыстыру
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }

                    // Экранды жаңарту және күте тұру (баяулату)
                    repaint();
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                }
            }
            currentJ = -1; // Сұрыптау аяқталды
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / array.length; // Бағана ені

        for (int i = 0; i < array.length; i++) {
            int height = array[i] * 20; // Биіктігін масштабтау

            // Түстермен белгілеу
            if (i == currentJ || i == currentJ + 1) {
                g.setColor(Color.RED); // Салыстырылып жатқандар - қызыл
            } else {
                g.setColor(Color.BLUE); // Қалғандары - көк
            }

            g.fillRect(i * width, getHeight() - height, width - 5, height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), i * width + (width / 4), getHeight() - height - 5);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bubble Sort Visualizer");
        BubbleSortVisualizer visualizer = new BubbleSortVisualizer();
        frame.add(visualizer);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        visualizer.startSort(); // Сұрыптауды бастау
    }
}

