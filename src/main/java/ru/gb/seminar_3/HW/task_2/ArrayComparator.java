package ru.gb.seminar_3.HW.task_2;

public class ArrayComparator {

    public <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length == array2.length) {
            for (int i = 0; i < array1.length; i++) {
                if (!array1[i].equals(array2[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
