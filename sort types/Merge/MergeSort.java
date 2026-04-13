package Merge;

import java.util.Arrays;
public class MergeSort {

        // Негізгі интерфейстік әдіс: сұрыптауды дайындайды және бастайды
        public static int[] mergeSort(int[] sortArr) {
            // buffer1 — бастапқы массивтің көшірмесі
            int[] buffer1 = Arrays.copyOf(sortArr, sortArr.length);
            // buffer2 — аралық нәтижелерді сақтауға арналған көмекші массив
            int[] buffer2 = new int[sortArr.length];
            // Нақты сұрыптау процесін (рекурсияны) бастау
            int[] result = mergeSortInner(buffer1, buffer2, 0, sortArr.length);
            return result;
        }

        /**
         * Ішкі рекурсивті әдіс
         * buffer1 - сұрыпталатын деректер
         * buffer2 - біріктіру кезінде қолданылатын буфер
         */
        public static int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
            // Рекурсияның тоқтау шарты: егер бөлікте 1 ғана элемент қалса
            if (startIndex >= endIndex - 1) {
                return buffer1;
            }

            // 1. Массивті қақ ортасынан бөлу
            int middle = startIndex + (endIndex - startIndex) / 2;

            // Сол жақ және оң жақ бөліктерді жеке-жеке сұрыптау (рекурсия)
            int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
            int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

            // 2. Екі сұрыпталған бөлікті біріктіру (Merge)
            int index1 = startIndex; // Сол жақ бөліктің көрсеткіші
            int index2 = middle;     // Оң жақ бөліктің көрсеткіші
            int destIndex = startIndex; // Нәтижелік массивтің индексі

            // Қай массивке жазатынымызды анықтау (буферлер арасында ауысу)
            int[] result = sorted1 == buffer1 ? buffer2 : buffer1;

            // Екі бөліктің элементтерін салыстыра отырып, кішісін бірінші жазу
            while (index1 < middle && index2 < endIndex) {
                result[destIndex++] = sorted1[index1] < sorted2[index2]
                        ? sorted1[index1++] : sorted2[index2++];
            }

            // Сол жақ бөлікте элементтер қалса, оларды соңына қосу
            while (index1 < middle) {
                result[destIndex++] = sorted1[index1++];
            }

            // Оң жақ бөлікте элементтер қалса, оларды соңына қосу
            while (index2 < endIndex) {
                result[destIndex++] = sorted2[index2++];
            }
            return result;
        }

        public static void main(String args[]) {
            int[] sortArr = {12, 6, 4, 1, 15, 10};
            // Сұрыптауды шақыру
            int[] result = mergeSort(sortArr);
            // Нәтижені массив түрінде шығару
            System.out.println(Arrays.toString(result));
        }
    }

