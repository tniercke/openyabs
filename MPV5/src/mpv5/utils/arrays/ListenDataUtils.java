/*
 * 
 * 
 */

package mpv5.utils.arrays;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author anti43
 */
 @SuppressWarnings("unchecked")
public class ListenDataUtils {

    public static ArrayList StringArrayToList(String[][] array) {
        ArrayList list = new ArrayList();
        
        for (int i = 0; i < array.length; i++) {
          
            list.add(array[i]);
        }
        
        return list;
    }

    public static ArrayList TableModelToList(JTable mode) {
        ArrayList list = new ArrayList();
        DefaultTableModel model = (DefaultTableModel) mode.getModel();
        String[] str = null;
        
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                str = new String[model.getColumnCount()];
                str[j] = model.getValueAt(i, j).toString();
            }
            list.add(str);
        }
        
        return list;
    }

   
    /**
     * 
     * @param list
     * @return 
     */
    public static Integer[][][] listToIntegerArray(ArrayList list) {

        Integer[][][] str = new Integer[list.size()][][];

        ArrayList a,b ;

        for (int i = 0; i < list.size(); i++) {

            a = (ArrayList) list.get(i);
            str[i] = new Integer[a.size()][];

            for (int m = 0; m < a.size(); m++) {

                b = (ArrayList) a.get(m);

                str[i][m] = new Integer[b.size()];

                for (int k = 0; k < a.size(); k++) {

                    str[i][m][k] = (Integer) b.get(k);
                }


            }

        }

        return str;
    }

    /**
     * 
     * @param list
     * @return 
     */
    public static String[] listToStringArray(ArrayList list) {

        String[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            str[i] = (String) list.get(i);
        }
        return str;
    }

    public static String[][] listToStringArrayArray(ArrayList<String[]> list) {
     
        String[][] str = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i);
        }

        return str;
        
    }

    public static Object[][] listToTableArray(ArrayList list) {

        Object[][] str = new Object[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            str[i] = (Object[]) list.get(i);
        }

        return str;
    }

    public static String[][] StringListToTableArray(ArrayList list) {

        String[][] str = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            str[i] = (String[]) list.get(i);
        }

        return str;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList merge(ArrayList list1, ArrayList list2) {
        Iterator it1 = list1.iterator();
        Iterator it2 = list2.iterator();

        ArrayList<Double> list3 = new ArrayList();

        while (it1.hasNext()) {
            list3.add((Double) it1.next());
        }
        
        while (it2.hasNext()) {
            list3.add((Double) it2.next());
        }
        
      return list3;
   }
    
      
    /**
     * list 1 +2 must have same element count!
     * 
     * @param list1
     * @param list2
     * @return list1.values(n) - list2.values(n)
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public static ArrayList substract(ArrayList<Double> list1, ArrayList<Double> list2) throws Exception {
        Iterator it1 = list1.iterator();
        Iterator it2 = list2.iterator();
        
        if(list1.size() != list2.size()) {
            throw new Exception("list 1 + 2 must have same element count!");
        }
        
        ArrayList<Double> list3 = new ArrayList();

        while (it1.hasNext() && it2.hasNext()) {
            Double value = (Double)it1.next() - (Double)it2.next();
            list3.add(value);
        }
        
      return list3;
    }
    
    /**
     * list 1 + 2 must have same element count!
     * 
     * @param list1
     * @param list2
     * @return list1.values(n) + list2.values(n)
     * @throws Exception 
     */
    public static ArrayList<Double> add(ArrayList<Double> list1, ArrayList<Double> list2) throws Exception {
         
        Iterator it1 = list1.iterator();
        Iterator it2 = list2.iterator();
        
        if(list1.size() != list2.size()) {
            throw new Exception("list 1 + 2 must have same element count!");
        }
        
        @SuppressWarnings("unchecked")
        ArrayList<Double> list3 = new ArrayList();

        while (it1.hasNext() && it2.hasNext()) {
            Double value = (Double)it1.next() + (Double)it2.next();
            list3.add(value);
        }
        
      return list3;
    }

}
