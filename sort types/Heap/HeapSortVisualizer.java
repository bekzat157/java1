package Heap;


    import javax.swing.*;
import java.awt.*;
import java.util.Random;

    public class HeapSortVisualizer extends JPanel {
        private int[] array;
        private final int SIZE = 50; // Массив көлемі
        private int currentIdx = -1; // Қазіргі өңделіп жатқан элемент
        private int heapSize;

        public HeapSortVisualizer() {
            array = new Random().ints(SIZE, 10, 400).toArray();
            this.heapSize = SIZE;

            // Сұрыптауды бөлек ағында (Thread) іске қосу, интерфейс қатып қалмауы үшін
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    sort();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        private void sort() throws InterruptedException {
            // 1. Build Heap
            for (int i = SIZE / 2 - 1; i >= 0; i--) {
                heapify(SIZE, i);
            }

            // 2. Extract elements
            for (int i = SIZE - 1; i > 0; i--) {
                swap(0, i);
                heapSize--;
                heapify(heapSize, 0);
            }
            currentIdx = -1;
            repaint();
        }

        private void heapify(int n, int i) throws InterruptedException {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && array[left] > array[largest]) largest = left;
            if (right < n && array[right] > array[largest]) largest = right;

            if (largest != i) {
                swap(i, largest);
                heapify(n, largest);
            }
        }

        private void swap(int i, int j) throws InterruptedException {
            currentIdx = j; // Визуалды белгілеу үшін
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            Thread.sleep(100); // Анимация жылдамдығы (мс)
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth() / SIZE;

            for (int i = 0; i < SIZE; i++) {
                if (i == currentIdx) {
                    g.setColor(Color.RED); // Қозғалып жатқан элемент
                } else if (i >= heapSize) {
                    g.setColor(Color.GREEN); // Сұрыпталып қойған бөлігі
                } else {
                    g.setColor(Color.GRAY);
                }

                int height = array[i];
                int x = i * width;
                int y = getHeight() - height;
                g.fillRect(x, y, width - 2, height);
            }
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame("HeapSort Visualizer");
            HeapSortVisualizer visualizer = new HeapSortVisualizer();
            frame.add(visualizer);
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

