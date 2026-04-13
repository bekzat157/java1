package Quick;

public class QuickSort {
    public static void quickSort(int[] sortArr, int low, int high) {
        // 1. Негізгі шарт: егер массив бос болса немесе бөлетін ештеңе қалмаса (индекстер түйіссе), тоқтату
        if (sortArr.length == 0 || low >= high) return;

        // 2. Тірек элементін (pivot) таңдау
        // Ортаңғы индексті анықтап, сол жердегі мәнді тірек ретінде аламыз
        int middle = low + (high - low) / 2;
        int border = sortArr[middle];

        // 3. Массивті бөліктерге бөлу және элементтердің орнын ауыстыру
        int i = low, j = high;
        while (i <= j) {
            // Тірек элементтен кіші элементті сол жақтан іздеу
            while (sortArr[i] < border) i++;

            // Тірек элементтен үлкен элементті оң жақтан іздеу
            while (sortArr[j] > border) j--;

            // Егер индекстер бір-біріне жетпесе, элементтердің орнын ауыстырамыз
            if (i <= j) {
                int swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                // Келесі элементтерге көшу
                i++;
                j--;
            }
        }

        // 4. Рекурсия: Массивтің сол жақ және оң жақ бөліктерін жеке-жеке сұрыптау
        // Тірек элементінен солға қарай (кішілер бөлігі)
        if (low < j) quickSort(sortArr, low, j);

        // Тірек элементінен оңға қарай (үлкендер бөлігі)
        if (high > i) quickSort(sortArr, i, high);
    }

    public static void main(String args[]) {
        int[] sortArr = {12, 6, 4, 1, 15, 10};

        // Сұрыптауды бастау (массив, басы - 0, соңы - соңғы индекс)
        quickSort(sortArr, 0, sortArr.length - 1);

        // Нәтижені экранға шығару
        for(int i = 0; i < sortArr.length; i++){
            System.out.print(sortArr[i] + "\n");
        }
    }
}
