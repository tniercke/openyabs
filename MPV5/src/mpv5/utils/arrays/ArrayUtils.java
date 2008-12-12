/*
 * 
 * 
 */
package mpv5.utils.arrays;

import java.util.ArrayList;

/**
 *
 * @author anti43
 */
public class ArrayUtils {

//    @SuppressWarnings("unchecked")
//  public static ArrayList ArrayToColumnList(Object[][] array, int column, Class aClass) {
//
//        ArrayList list = new ArrayList();
//
//        for (int idx = 0; idx < array.length; idx++) {
//            list.add((aClass.cast(array[idx][column])));
//        }
//
//        return list;
//    }
//
//    @SuppressWarnings("unchecked")
//    public static ArrayList ArrayToDoubleColumnList(Object[][] array, int column) {
//
//        ArrayList list = new ArrayList();
//
//        for (int idx = 0; idx < array.length; idx++) {
//            list.add(Double.valueOf(array[idx][column].toString()));
//        }
//
//        return list;
//    }
//
//    @SuppressWarnings("unchecked")
//      public static ArrayList ArrayToDateColumnList(Object[][] array, int column) {
//
//        ArrayList list = new ArrayList();
//
//        for (int idx = 0; idx < array.length; idx++) {
//            list.add(DateConverter.getDate(array[idx][column].toString()));
//        }
//
//        return list;
//    }
    public static Object[][] merge(Object[][] array1, Object[][] array2) {
        if (array1 == null) {
            array1 = new Object[0][0];
        }
        if (array2 == null) {
            array2 = new Object[0][0];
        }

        int z = 0;
        if (array1 != null && array1.length > 0) {
            z = array1[0].length;
        } else if (array2 != null && array2.length > 0) {
            z = array2[0].length;
        } else {

            z = 0;
        }


        Object[][] mergedArray = new Object[array1.length + array2.length][z];
        int i = 0;

        for (i = 0; i < array1.length; i++) {

            for (int k = 0; k < array1[i].length; k++) {

                mergedArray[i][k] = array1[i][k];


            }

        }

        for (int l = 0; l < array2.length; l++) {

            for (int k = 0; k < array2[l].length; k++) {

                mergedArray[i + l][k] = array2[l][k];


            }

        }

        return mergedArray;

    }

    public static Object[] merge(Object[] array1, Object[] array2) {
        if (array1 == null) {
            array1 = new Object[0];
        }
        if (array2 == null) {
            array2 = new Object[0];
        }

        Object[] mergedArray = new Object[array1.length + array2.length];
        int i = 0;

        for (i = 0; i < array1.length; i++) {
                mergedArray[i] = array1[i];
        }

        for (int l = 0; l < array2.length; l++) {
                mergedArray[i + l] = array2[l];
        }

        return mergedArray;
    }

    public static String[] reverseArray(String[] str) {
        int i = 0, j = str.length - 1;
        while (i < j) {
            String h = str[i];
            str[i] = str[j];
            str[j] = h;
            i++;
            j--;
        }
        return str;

    }

    public static String[][] reverseArray(String[][] str) {
        int i = 0, j = str.length - 1;
        while (i < j) {
            String[] h = str[i];
            str[i] = str[j];
            str[j] = h;
            i++;
            j--;
        }
        return str;

    }

    public static String[][] ObjectToStringArray(Object[][] array1) {
        if (array1 == null) {
            array1 = new Object[0][0];
        }

        String[][] mergedArray = new String[array1.length][array1[0].length];
        int i = 0;
        for (i = 0; i < array1.length; i++) {

            for (int k = 0; k < array1[i].length; k++) {

                mergedArray[i][k] = array1[i][k].toString();
            }
        }
        return mergedArray;
    }

    public static String[] SmallObjectToStringArray(Object[] array1) {
        if (array1 == null) {
            array1 = new Object[0];
        }

        String[] mergedArray = new String[array1.length];
        int i = 0;
        for (i = 0; i < array1.length; i++) {

            mergedArray[i] = array1[i].toString();
        }
        return mergedArray;
    }

    public static Object[] ObjectToSingleColumnArray(Object[][] array1) {
        if (array1 == null) {
            array1 = new Object[0][0];
        }

        Object[] mergedArray = new Object[array1.length];
        int i = 0;
        for (i = 0; i < array1.length; i++) {
            mergedArray[i] = array1[i][0];
        }
        return mergedArray;

    }
}
