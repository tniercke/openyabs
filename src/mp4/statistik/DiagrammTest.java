/*
 * DiagrammTest.java
 *
 * Created on 20. August 2008, 20:55
 */

package mp4.statistik;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import mp4.utils.datum.DateConverter;
import mp4.utils.zahlen.FormatNumber;

/**
 *
 * @author  Andreas
 */
public class DiagrammTest extends javax.swing.JFrame {

    /** Creates new form DiagrammTest */
    public DiagrammTest() {
        initComponents();
        addDiagram();
        setVisible(rootPaneCheckingEnabled);
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    private void addDiagram() {
//        ArrayList dates = new ArrayList();
//        
//        dates.add(new Date());
//        dates.add(DateConverter.addYear(new Date()));
//        dates.add(DateConverter.addYear(DateConverter.addYear(new Date())));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date()))));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date())))));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date()))))));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date())))))));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date()))))))));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date())))))))));
//        dates.add(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(DateConverter.addYear(new Date()))))))))));
        
        ArrayList  vals = new ArrayList();
        
        vals.add("Januar");
        vals.add("Februar");
        vals.add("M�rz");
        vals.add("April");
        vals.add("Mai");
        vals.add("Juni");
        vals.add("Juli");
        vals.add("August");
        vals.add("September");
        vals.add("Oktober");
        vals.add("November");
        vals.add("Dezember");
        
        
        ArrayList values = new ArrayList();
        
        values.add(new Double(2300));
        values.add(new Double(500));
        values.add(new Double(7300));
        values.add(new Double(8900));
        values.add(new Double(5600));
        values.add(new Double(4400));
        values.add(new Double(300));
        values.add(new Double(4300));
        values.add(new Double(6700));
        values.add(new Double(78));
        values.add(new Double(437));
        values.add(new Double(60));
        
        
        Diagramme d = new Diagramme(jPanel1);
//        d.erzeugeLinienGrafik(null, new Vector(dates), new Vector(values), " �", "Umsatz", "Zeitraum", "", "Umsatzverlauf",FormatNumber.getDefaultDecimalFormat());
         d.erzeugeBalkenGrafik("", new Vector(vals), new Vector(values), " �", "Umsatz 2007", "Zeitraum", "Mio. Euro",FormatNumber.getDefaultDecimalFormat());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
private void initComponents() {

jPanel1 = new javax.swing.JPanel();

setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

jPanel1.setLayout(new java.awt.BorderLayout());

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
);

pack();
}// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiagrammTest().setVisible(true);
            }
        });
    }

// Variables declaration - do not modify//GEN-BEGIN:variables
private javax.swing.JPanel jPanel1;
// End of variables declaration//GEN-END:variables

}