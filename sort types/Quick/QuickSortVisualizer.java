package Quick;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class QuickSortVisualizer extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 400;
    private final int BAR_WIDTH = 5;
    private final int ARRAY_SIZE = WIDTH / BAR_WIDTH;
    private int[] array = new int[ARRAY_SIZE];

    public QuickSortVisualizer() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initArray();

        // Алгоритмді бөлек ағында (Thread) іске қосу, Swing интерфейсі қатып қалмауы үшін
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Бастамас бұрын 1 секунд күту
                quickSort(0, ARRAY_SIZE - 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void initArray() {
        Random rand = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(HEIGHT);
        }
    }

    private void quickSort(int low, int high) throws InterruptedException {
        if (low >= high) return;

        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;

                // Экранды қайта сызу және кідіріс жасау
                repaint();
                Thread.sleep(20);
            }
        }

        if (low < j) quickSort(low, j);
        if (high > i) quickSort(i, high);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN); // Бағаналардың түсі
        for (int i = 0; i < ARRAY_SIZE; i++) {
            int x = i * BAR_WIDTH;
            int y = HEIGHT - array[i];
            g.fillRect(x, y, BAR_WIDTH - 1, array[i]);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quick Sort Visualizer");
        QuickSortVisualizer visualizer = new QuickSortVisualizer();
        frame.add(visualizer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
