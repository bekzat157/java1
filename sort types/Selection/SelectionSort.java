package Selection;

public class SelectionSort {
    public static void selectionSort(int[] sortArr) {
        // Массивтің әрбір элементін кезегімен аралап шығамыз
        for (int i = 0; i < sortArr.length; i++) {
            // Ағымдағы i-ші индексті және оның мәнін "ең кіші" деп уақытша белгілейміз
            int pos = i;
            int min = sortArr[i];

            // Ең кіші элементті іздеу циклі (i+1-ден бастап соңына дейін)
            for (int j = i + 1; j < sortArr.length; j++) {
                // Егер қазіргі min-нан кішірек сан табылса:
                if (sortArr[j] < min) {
                    // Сол жаңа кіші санның индексін (pos) және мәнін (min) сақтаймыз
                    pos = j;
                    min = sortArr[j];
                }
            }

            // Орын ауыстыру (Swap):
            // Табылған ең кіші элемент тұрған орынға (pos) i-ші элементті қоямыз
            sortArr[pos] = sortArr[i];
            // Ал i-ші орынға табылған ең кіші санды (min) қоямыз
            sortArr[i] = min;
        }
    }

    public static void main(String args[]) {
        // Бастапқы массив
        int[] sortArr = {12, 6, 4, 1, 15, 10};

        // Сұрыптау әдісін шақыру
        selectionSort(sortArr);

        // Нәтижені экранға басып шығару
        for(int i = 0; i < sortArr.length; i++){
            System.out.print(sortArr[i] + "\n");
        }
    }
}
