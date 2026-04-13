package Insertion;

public class InsertionSort {
    public static void insertionSort(int[] sortArr) {
        int j;
        // Сұрыптауды екінші элементтен бастаймыз,
        // өйткені бірінші элемент "сұрыпталған" деп есептеледі.
        for (int i = 1; i < sortArr.length; i++) {

            // Ағымдағы элементті (кірістірілетін мәнді) swap айнымалысына сақтап қоямыз
            int swap = sortArr[i];

            // Ішкі цикл арқылы таңдалған элементті өзінен бұрынғы элементтермен салыстырамыз.
            // Егер алдыңғы элемент swap-тан үлкен болса, оны оңға қарай жылжытамыз.
            for (j = i; j > 0 && swap < sortArr[j - 1]; j--) {
                // Сұрыпталған бөліктің элементтерін бір орынға алға жылжыту
                sortArr[j] = sortArr[j - 1];
            }

            // Босаған орынға (тиісті позицияға) swap мәнін қоямыз
            sortArr[j] = swap;
        }
    }

    public static void main(String args[]) {
        // Бастапқы массив
        int[] sortArr = {12, 6, 4, 1, 15, 10};

        // Сұрыптау функциясын шақыру
        insertionSort(sortArr);

        // Нәтижені экранға шығару
        for(int i = 0; i < sortArr.length; i++){
            System.out.print(sortArr[i] + "\n");
        }
    }
}
