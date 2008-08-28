/*
 * eurEPanel.java
 *
 * Created on 20. Februar 2008, 08:33
 */
package mp4.panels.eur;

import java.awt.Font;
import javax.swing.JTabbedPane;
import mp3.classes.interfaces.Strings;
import mp3.classes.interfaces.panelInterface;
import mp3.classes.layer.People;
import mp3.classes.layer.Popup;
import mp3.classes.layer.visual.DatePick;



//import mp3.classes.objects.ungrouped.MyData;
//import mp3.classes.objects.eur.SKRKonto;
import mp3.classes.utils.FetchDataTask;
import mp3.classes.utils.Formater;
import mp3.classes.utils.Log;
import mp3.classes.visual.util.konten;
import mp4.cache.ObjectCopy;
import mp4.cache.undoCache;
import mp4.items.Ausgabe;
import mp4.items.HistoryItem;
import mp4.einstellungen.Einstellungen;
import mp4.items.SKRKonto;
import mp4.utils.datum.DateConverter;
import mp4.utils.datum.vDate;
import mp4.utils.tabellen.SelectionCheck;
import mp4.utils.zahlen.vDouble;

/**
 *
 * @author  anti43
 */
public class eurAPanel extends javax.swing.JPanel implements panelInterface {

    private Ausgabe curAusgabe;
    private SKRKonto curKonto;
    private String[][] data;

    /** Creates new form eurEPanel */
    public eurAPanel() {
        initComponents();
        curAusgabe = new Ausgabe();
        curKonto = Einstellungen.instanceOf().getAusgabeDefKonto();

        jTextField6.setText(DateConverter.getTodayDefDate());
        jTextField3.setText(Einstellungen.instanceOf().getGlobaltax().toString());
        jTextField4.setText("0");
        jTextField5.setText(Einstellungen.instanceOf().getAusgabeDefKonto().getArt());
//        jTextField3.setInputVerifier(Formater.getDoubleInputVerfier(jTextField3));
//        jTextField4.setInputVerifier(Formater.getDoubleInputVerfier(jTextField4));
        jTextField6.setInputVerifier(Formater.getDateInputVerfier(jTextField6));

        updateTableData();
    }

    public void setKonto(SKRKonto konto) {
        this.curKonto = konto;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jToolBar2 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Ausgaben"));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nummer", "Betrag", "Datum"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel2.setText("Beschreibung");

        jLabel4.setText("MwSt");

        jLabel5.setText("Gesamtbetrag (Brutto)");

        jLabel6.setText("Konto");

        jLabel7.setText("Datum");

        jButton2.setText("W�hlen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton16.setText("Kalender");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jButton16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton16KeyPressed(evt);
            }
        });

        jScrollPane2.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16))
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4)
                                    .addComponent(jLabel5)))
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jButton3.setText("Speichern");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jToolBar2.add(jButton3);

        jButton4.setText("Als neue Ausgabe");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);
        jToolBar2.add(jSeparator1);

        jButton5.setText("L�schen");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        SelectionCheck selection = new SelectionCheck(jTable1);

        if (selection.checkID()) {
            Log.Debug(selection.getId());
            this.setAusgabe(new Ausgabe(selection.getId()));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new konten(jTextField5, curAusgabe, this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        new DatePick(jTextField6);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton16KeyPressed
    }//GEN-LAST:event_jButton16KeyPressed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked


        if (jButton3.isEnabled()) {

            vDouble  betrag = new vDouble (jTextField4.getText());
            vDouble  steuer = new vDouble (jTextField3.getText());
            vDate datum = new vDate(jTextField6.getText());

            if (betrag.isVerified && betrag.isPositive && steuer.isVerified && steuer.isPositive && datum.isVerified) {

                if (this.curAusgabe != null && curAusgabe.id > 0) {
                    curAusgabe.setDatum(datum.date);
                    curAusgabe.setBeschreibung(jEditorPane1.getText());
                    curAusgabe.setPreis(betrag.value);
                    curAusgabe.setTax(steuer.value);
                    curAusgabe.save();

                    updateTableData();
                    
                    undoCache.instanceOf().addItem(ObjectCopy.copy(curAusgabe), undoCache.CREATE);     
                    new HistoryItem(Strings.EINNAHME, "Einnahme Nummer: " + curAusgabe.getId() + " angelegt.");
                }
             

        } else {
            String text = "";
            if (!betrag.isVerified) {
                text += "Betrag: " + betrag.ovalue + "\n";
            }
            if (!steuer.isVerified || !steuer.isPositive) {
                text += "Steuer: " + steuer.ovalue + "\n";
            }
            if (!datum.isVerified) {
                text += "Datum: " + datum.ovalue;
            }
            Popup.error(text, "�berpr�fen Sie Ihre Angaben.");
        }
        }

    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

            vDouble  betrag = new vDouble (jTextField4.getText());
            vDouble  steuer = new vDouble (jTextField3.getText());
            vDate datum = new vDate(jTextField6.getText());

            if (betrag.isVerified && betrag.isPositive && steuer.isVerified && steuer.isPositive && datum.isVerified) {

            this.setAusgabe(new Ausgabe(curKonto.getId(), jEditorPane1.getText(), betrag.value, steuer.value, datum.date));
            updateTableData();
             
            undoCache.instanceOf().addItem(ObjectCopy.copy(curAusgabe), undoCache.CREATE);
            new HistoryItem(Strings.EINNAHME, "Einnahme Nummer: " + curAusgabe.getId() + " angelegt.");

        } else {
            String text = "";
            if (!betrag.isVerified) {
                text += "Betrag: " + betrag.ovalue + "\n";
            }
            if (!steuer.isVerified || !steuer.isPositive) {
                text += "Steuer: " + steuer.ovalue + "\n";
            }
            if (!datum.isVerified) {
                text += "Datum: " + datum.ovalue;
            }
            Popup.error(text, "�berpr�fen Sie Ihre Angaben.");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (this.curAusgabe != null) {
            
            undoCache.instanceOf().addItem(ObjectCopy.copy(this.curAusgabe), undoCache.DELETE);
            new HistoryItem(Strings.EINNAHME, "Einnahme Nummer: " + curAusgabe.getId() + " gel�scht.");
            
            curAusgabe.disable();
            updateTableData();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public String[][] updateTableData() {
        try {
            FetchDataTask task = new FetchDataTask(this, null, new Ausgabe());
            task.execute();
            data = task.get();

            Formater.formatUneditableTable(getJTable1(), data, new String[]{"kontenid", "Nummer", "Typ", "Betrag", "Datum"});
            Formater.format(jTable1, 1, 80);
            Formater.format(jTable1, 3, 100);
            Formater.format(jTable1, 4, 100);

        } catch (Exception ex) {
            Log.Debug(ex.getMessage());
        }

        return data;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables

    private void setAusgabe(Ausgabe Ausgabe) {
        this.curAusgabe = Ausgabe;

        jTextField6.setText(Ausgabe.getFDatum());

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 12));
        jEditorPane1.setText("<html><font face=Arial size=12px>" + Ausgabe.getBeschreibung() + "<br>");

        jTextField4.setText(Ausgabe.getFPreis());
        jTextField3.setText(Ausgabe.getFTax());

        try {
            jTextField5.setText(new SKRKonto(Ausgabe.getKontenid()).getArt());
        } catch (Exception exception) {
            Log.Debug(exception);
        }
        jButton3.setEnabled(true);
    }

    public javax.swing.JTable getJTable1() {
        return jTable1;
    }

    public String[][] getData() {
        return updateTableData();
    }

    public void updateTables() {
        updateTableData();
    }

    public void save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() {
      ((JTabbedPane) this.getParent()).remove(this);
    }

    public void undo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void redo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void changeTabText(String text) {
          ((JTabbedPane) this.getParent()).setTitleAt(((JTabbedPane) this.getParent()).getSelectedIndex(), text);
    
    }

    public boolean isEdited() {
       return false;
    }

    public void setContact(People contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public People getContact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void switchTab(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
