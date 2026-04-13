package Heap;

public class HeapSort {
    // A[i] элементінің сол жақ ұрпағының индексін қайтару
    private static int LEFT(int i) {
        return (2 * i + 1);
    }

    // A[i] элементінің оң жақ ұрпағының индексін қайтару
    private static int RIGHT(int i) {
        return (2 * i + 2);
    }

    // Массивтегі екі индекстің орындарын ауыстыруға арналған көмекші әдіс
    private static void swap(int[] sortArr, int i, int j) {
        int swap = sortArr[i];
        sortArr[i] = sortArr[j];
        sortArr[j] = swap;
    }

    // Рекурсивті heapify-down алгоритмі.
// i индексіндегі түйін мен оның екі ұрпағы үйінді қасиетін бұзған жағдайда қолданылады.
    private static void heapify(int[] sortArr, int i, int size) {
        // i түйінінің сол және оң жақ ұрпақтарын алу
        int left = LEFT(i);
        int right = RIGHT(i);
        int largest = i;

        // A[i] элементін ұрпақтарымен салыстырып, ең үлкен мәнді табу
        if (left < size && sortArr[left] > sortArr[i]) largest = left;
        if (right < size && sortArr[right] > sortArr[largest]) largest = right;

        // Егер ең үлкені i болмаса, орындарын ауыстырып, төмен қарай heapify-ды жалғастыру
        if (largest != i) {
            swap(sortArr, i, largest);
            heapify(sortArr, largest, size);
        }
    }

    // Ең жоғары басымдылыққа ие (түбірдегі) элементті өшіру және қайтару функциясы
    public static int pop(int[] sortArr, int size) {
        // Егер үйінді бос болса
        if (size <= 0) {
            return -1;
        }
        int top = sortArr[0];

        // Үйінді түбірін массивтің соңғы элементімен алмастыру
        sortArr[0] = sortArr[size - 1];

        // Түбір түйінде heapify-down функциясын шақыру
        heapify(sortArr, 0, size - 1);
        return top;
    }

    // n өлшемді sortArr массивін пирамидалық сұрыптау функциясы
    public static void heapSort(int[] sortArr) {
        int n = sortArr.length;

        // 1. build-heap: массивтен макс-үйінді құрастыру.
        // Соңғы ішкі түйіннен бастап түбірге дейін heapify шақырылады.
        int i = (n - 2) / 2;
        while (i >= 0) {
            heapify(sortArr, i--, n);
        }

        // 2. Үйінді босағанша элементтерді ретімен шығарып алу
        while (n > 0) {
            // Ең үлкен элементті алып, оны массивтің бос соңғы орнына қою
            sortArr[n - 1] = pop(sortArr, n);
            n--;
        }
    }

    public static void main(String args[]) {
        int[] sortArr = {12, 6, 4, 1, 15, 10};

        // Сұрыптауды бастау
        heapSort(sortArr);

        // Нәтижені шығару
        for(int i = 0; i < sortArr.length; i++){
            System.out.print(sortArr[i] + "\n");
        }
    }
}
