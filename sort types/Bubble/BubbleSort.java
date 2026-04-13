package Bubble;

public class BubbleSort {
    public static void bubbleSort(int[] sortArr) {
        // Сыртқы цикл: массивтің соңына дейін барады.
        // Әрбір толық айналымнан кейін ең үлкен элемент массивтің соңына "қалқып" шығады.
        for (int i = 0; i < sortArr.length - 1; i++) {

            // Ішкі цикл: көршілес элементтерді салыстыру үшін қолданылады.
            // sortArr.length - i - 1 шарты сұрыпталып қойған (соңғы) элементтерді қайта тексермеу үшін керек.
            for (int j = 0; j < sortArr.length - i - 1; j++) {

                // Егер сол жақтағы элемент оң жақтағыдан ҮЛКЕН болса:
                if (sortArr[j + 1] < sortArr[j]) {

                    // Орындарын ауыстыру (Swap) процесі:
                    // 1. Уақытша айнымалыға (swap) сол жақтағы мәнді сақтаймыз.
                    int swap = sortArr[j];

                    // 2. Сол жақтағы орынға оң жақтағы (кішірек) мәнді қоямыз.
                    sortArr[j] = sortArr[j + 1];

                    // 3. Оң жақтағы орынға уақытша сақталған (үлкенірек) мәнді қоямыз.
                    sortArr[j + 1] = swap;
                }
            }
        }
    }

    public static void main(String args[]) {
        // Сұрыпталатын бастапқы массив
        int[] sortArr = {12, 6, 4, 1, 15, 10};

        // Сұрыптау әдісін шақыру
        bubbleSort(sortArr);

        // Сұрыпталған нәтижені экранға шығару
        for (int i = 0; i < sortArr.length; i++) {
            System.out.print(sortArr[i] + "\n");
        }
    }
}
