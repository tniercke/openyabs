/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Stockmanager.java
 *
 * Created on Oct 7, 2012, 4:30:52 PM
 */
package mpv5.ui.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.objects.Product;
import mpv5.logging.Log;
import mpv5.ui.beans.UserCheckbox;
import mpv5.ui.dialogs.Notificator;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.TableFormat;

/**
 *
 * @author anti
 */
public class Stockmanager extends javax.swing.JPanel {

   private static Stockmanager me;
   private static final long serialVersionUID = 1L;

   public static Stockmanager instanceOf() {
      if (me == null) {
         me = new Stockmanager();
      }
      return me;
   }
   private final MPTableModel model;

   /**
    * Creates new form Stockmanager
    */
   public Stockmanager() {
      initComponents();
      model = new MPTableModel(new Class[]{String.class, Integer.class, String.class, String.class}, 
              new boolean[]{true, true, false, false}, 
              new Object[]{"Scan", "Amount", "Product", "New Stockvalue"});
      model.removeRow(0);
      mainTable.setModel(model);
//      TableFormat.hideHeader(mainTable);
      TableFormat.resizeCol(mainTable, 1, 50, true);
      setupFilter();
      Object[] mo = new Object[4];
      mo[0]="EAN";
      mo[1]="CNumber";
      mo[2]="CName";
      mo[3]="Description";
      labeledCombobox1.getComboBox().setModel(new DefaultComboBoxModel(mo));
      
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jPanel1 = new javax.swing.JPanel();
      jToolBar1 = new javax.swing.JToolBar();
      labeledCombobox1 = new mpv5.ui.beans.LabeledCombobox();
      jSeparator1 = new javax.swing.JToolBar.Separator();
      jButton1 = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      mainTable = new javax.swing.JTable();
      jScrollPane2 = new javax.swing.JScrollPane();
      jTextArea1 = new javax.swing.JTextArea();
      jButton2 = new javax.swing.JButton();
      labeledTextField1 = new mpv5.ui.beans.LabeledTextField();
      jLabel1 = new javax.swing.JLabel();
      auto = new UserCheckbox("sendscanonenter");

      setName("Form"); // NOI18N
      setLayout(new java.awt.BorderLayout());

      java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
      jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Stockmanager.jPanel1.border.title_1"))); // NOI18N
      jPanel1.setName("jPanel1"); // NOI18N

      jToolBar1.setFloatable(false);
      jToolBar1.setRollover(true);
      jToolBar1.setName("jToolBar1"); // NOI18N

      labeledCombobox1.set_Label(bundle.getString("Stockmanager.labeledCombobox1._Label")); // NOI18N
      labeledCombobox1.setName("labeledCombobox1"); // NOI18N
      jToolBar1.add(labeledCombobox1);

      jSeparator1.setName("jSeparator1"); // NOI18N
      jToolBar1.add(jSeparator1);

      jButton1.setText(bundle.getString("Stockmanager.jButton1.text_1")); // NOI18N
      jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(34, 139, 26), 1, true));
      jButton1.setFocusable(false);
      jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      jButton1.setName("jButton1"); // NOI18N
      jButton1.setPreferredSize(new java.awt.Dimension(173, 28));
      jButton1.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
         }
      });
      jToolBar1.add(jButton1);

      jScrollPane1.setName("jScrollPane1"); // NOI18N

      mainTable.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {

         }
      ));
      mainTable.setName("mainTable"); // NOI18N
      mainTable.addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyTyped(java.awt.event.KeyEvent evt) {
            mainTableKeyTyped(evt);
         }
      });
      jScrollPane1.setViewportView(mainTable);

      jScrollPane2.setName("jScrollPane2"); // NOI18N

      jTextArea1.setColumns(20);
      jTextArea1.setRows(5);
      jTextArea1.setName("jTextArea1"); // NOI18N
      jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyReleased(java.awt.event.KeyEvent evt) {
            jTextArea1KeyReleased(evt);
         }
      });
      jScrollPane2.setViewportView(jTextArea1);

      jButton2.setText(bundle.getString("Stockmanager.jButton2.text_1")); // NOI18N
      jButton2.setName("jButton2"); // NOI18N
      jButton2.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
         }
      });

      labeledTextField1.set_Label(bundle.getString("Stockmanager.labeledTextField1._Label_1")); // NOI18N
      labeledTextField1.setName("labeledTextField1"); // NOI18N

      jLabel1.setText(bundle.getString("Stockmanager.jLabel1.text_1")); // NOI18N
      jLabel1.setName("jLabel1"); // NOI18N

      auto.setText(bundle.getString("Stockmanager.auto.text_1")); // NOI18N
      auto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
      auto.setFocusable(false);
      auto.setName("auto"); // NOI18N
      auto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      auto.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            autoActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jScrollPane2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(auto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
         .addComponent(labeledTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .addComponent(jScrollPane1)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 23, Short.MAX_VALUE))
         .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(2, 2, 2)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(jPanel1Layout.createSequentialGroup()
                  .addGap(2, 2, 2)
                  .addComponent(jButton2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(auto)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labeledTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
      );

      add(jPanel1, java.awt.BorderLayout.CENTER);
   }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int rows = mainTable.getRowCount();
       int cols = mainTable.getColumnCount();
       Object k = labeledCombobox1.getComboBox().getSelectedItem();
       
       for (int x = 0; x < rows; x++) {
          String key = String.valueOf(mainTable.getValueAt(x, 0));
          try {
             if (key.length() > 0 && !key.equals("null")) {
                BigDecimal val = new BigDecimal(String.valueOf(mainTable.getValueAt(x, 1)));
                Product dbo = (Product) DatabaseObject.getObject(Context.getProduct(), k==null?"ean":k.toString().toLowerCase(), key);
                dbo.setStockvalue(dbo.__getStockvalue() == null ? val : dbo.__getStockvalue().add(val));
                dbo.save();
                mainTable.setValueAt(dbo.__getCname()+" ("+dbo.__getIDS()+")", x, 2);
                mainTable.setValueAt(dbo.__getStockvalue().toPlainString(), x, 3);
                mainTable.setValueAt("0", x, 1);
                TableFormat.changeBackground(mainTable, 2, x, Color.green);
             }
          } catch (Exception exception) {
             Notificator.raiseNotification(exception, false);
             TableFormat.changeBackground(mainTable, 2, x, Color.red);
             mainTable.setValueAt(exception.getMessage(), x, 2);
          }
          
          mainTable.invalidate();

       }
//       for (Object[] d : model.getData()) {
//          try {
//             String key = String.valueOf(d[0]);
//             if (key.length() > 0 && !key.equals("null")) {
//                BigDecimal val = new BigDecimal(String.valueOf(d[1]));
//                Product dbo = (Product) DatabaseObject.getObject(Context.getProduct(), "ean", key);
//                dbo.setStockvalue(val);
//                dbo.save();
//             }
//          } catch (Exception exception) {
//             Log.Debug(exception);
//          }
//       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mainTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mainTableKeyTyped
//        Log.Print(evt);
    }//GEN-LAST:event_mainTableKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       doIt();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
       if (auto.isSelected() && (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER)) {
          doIt();
       }
    }//GEN-LAST:event_jTextArea1KeyReleased

   private void autoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_autoActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JCheckBox auto;
   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton2;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JToolBar.Separator jSeparator1;
   private javax.swing.JTextArea jTextArea1;
   private javax.swing.JToolBar jToolBar1;
   private mpv5.ui.beans.LabeledCombobox labeledCombobox1;
   private mpv5.ui.beans.LabeledTextField labeledTextField1;
   private javax.swing.JTable mainTable;
   // End of variables declaration//GEN-END:variables

   public void start() {
      mainTable.requestFocusInWindow();
      TableFormat.startEditing(mainTable, 0, 0);
   }

   private void doIt() {
      String x = jTextArea1.getText().replace("\n", "").trim();
      Runnable runnable = new Runnable() {
         @Override
         public void run() {
            jTextArea1.setText(null);
            jTextArea1.requestFocusInWindow();
         }
      };
      SwingUtilities.invokeLater(runnable);

      Vector v = ((DefaultTableModel) mainTable.getModel()).getDataVector();
      int row = 0;
      for (Iterator it = v.iterator(); it.hasNext();) {
         Vector a = (Vector) it.next();
         if (String.valueOf(a.elementAt(0)).equals(x)) {
            try {
               ((DefaultTableModel) mainTable.getModel()).setValueAt(Integer.valueOf(String.valueOf(a.elementAt(1))) + 1, row, 1);
            } catch (NumberFormatException numberFormatException) {
               Log.Debug(this, numberFormatException.getMessage());
            }
            return;
         }
         row++;
      }

      ((DefaultTableModel) mainTable.getModel()).addRow(new Object[]{x, "1"});
   }

   private void setupFilter() {
      final JTable table = mainTable;
      final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
      table.setRowSorter(sorter);

      labeledTextField1.getTextField().addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String text = labeledTextField1.getTextField().getText();
            if (text.length() == 0) {
               sorter.setRowFilter(null);
            } else {
               sorter.setRowFilter(RowFilter.regexFilter(text));
            }
         }
      });
   }
}
