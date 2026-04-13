package Selection;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SelectionSortVisualizer extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 400;
    private final int BAR_WIDTH = 40;
    private int[] array;
    private int currentIdx = -1;
    private int checkingIdx = -1;
    private int minIdx = -1;

    public SelectionSortVisualizer() {
        // Кездейсоқ массив дайындау
        array = new int[WIDTH / BAR_WIDTH];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(HEIGHT - 50) + 10;
        }

        // Сұрыптауды бөлек ағында бастау
        new Thread(() -> {
            try {
                selectionSort();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void selectionSort() throws InterruptedException {
        for (int i = 0; i < array.length; i++) {
            minIdx = i;
            currentIdx = i;

            for (int j = i + 1; j < array.length; j++) {
                checkingIdx = j; // Қазір қай элементті тексеріп жатырмыз
                repaint();
                Thread.sleep(100); // Анимация жылдамдығы

                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap (Орын ауыстыру)
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;

            repaint();
            Thread.sleep(200);
        }
        // Аяқталған соң индекстерді тазалау
        currentIdx = -1;
        checkingIdx = -1;
        minIdx = -1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            // Түстермен белгілеу
            if (i == currentIdx) {
                g.setColor(Color.RED); // Қазіргі бастапқы орын
            } else if (i == checkingIdx) {
                g.setColor(Color.YELLOW); // Іздеу үстінде
            } else if (i == minIdx) {
                g.setColor(Color.GREEN); // Табылған ең кіші элемент
            } else {
                g.setColor(Color.GRAY);
            }

            // Бағандарды салу
            int x = i * BAR_WIDTH;
            int y = HEIGHT - array[i];
            g.fillRect(x, y, BAR_WIDTH - 2, array[i]);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), x + 10, y - 5);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Selection Sort Visualizer");
        SelectionSortVisualizer visualizer = new SelectionSortVisualizer();
        frame.add(visualizer);
        frame.setSize(815, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
