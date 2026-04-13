package Merge;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MergeSortVisualizer extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 400;
    private final int BAR_WIDTH = 10;
    private int[] array;
    private int currentIdx = -1; // Қазіргі қарастырылып жатқан элемент
    private int mergingIdx = -1; // Біріктіріліп жатқан элемент

    public MergeSortVisualizer() {
        array = new int[WIDTH / BAR_WIDTH];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(HEIGHT - 50) + 10;
        }

        // Сұрыптауды бөлек ағында (Thread) бастау, әйтпесе интерфейс қатып қалады
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                mergeSort(0, array.length - 1);
                currentIdx = -1;
                mergingIdx = -1;
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void mergeSort(int left, int right) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);

            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) throws InterruptedException {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        System.arraycopy(array, left, leftArr, 0, leftArr.length);
        System.arraycopy(array, mid + 1, rightArr, 0, rightArr.length);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            currentIdx = k;
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            k++;
            updateVisual();
        }

        while (i < leftArr.length) {
            array[k] = leftArr[i];
            i++;
            k++;
            updateVisual();
        }

        while (j < rightArr.length) {
            array[k] = rightArr[j];
            j++;
            k++;
            updateVisual();
        }
    }

    private void updateVisual() throws InterruptedException {
        repaint();
        Thread.sleep(20); // Анимация жылдамдығы (миллисекунд)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            if (i == currentIdx) {
                g.setColor(Color.RED); // Салыстыру немесе өзгерту кезіндегі түс
            } else {
                g.setColor(Color.BLUE); // Қалыпты түс
            }
            // Бағандарды сызу
            g.fillRect(i * BAR_WIDTH, HEIGHT - array[i], BAR_WIDTH - 2, array[i]);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Merge Sort Visualizer");
        MergeSortVisualizer visualizer = new MergeSortVisualizer();
        frame.add(visualizer);
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}