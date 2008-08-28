/*
 * eurEPanel.java
 *
 * Created on 20. Februar 2008, 08:33
 */
package mp4.panels.eur;

import java.awt.Font;
import javax.swing.JTabbedPane;
import mp3.classes.layer.People;
import mp4.items.HistoryItem;
import mp4.items.Rechnung;
import mp3.classes.interfaces.Strings;
import mp3.classes.interfaces.panelInterface;
import mp3.classes.layer.Popup;
import mp3.classes.layer.visual.DatePick;

import mp4.items.Customer;
import mp4.items.Einnahme;
import mp4.einstellungen.Einstellungen;
import mp4.items.SKRKonto;
import mp3.classes.utils.FetchDataTask;
import mp3.classes.utils.Formater;
import mp3.classes.utils.Log;
import mp3.classes.visual.util.konten;
import mp4.cache.ObjectCopy;
import mp4.cache.undoCache;
import mp4.utils.datum.DateConverter;
import mp4.utils.datum.vDate;
import mp4.utils.tabellen.SelectionCheck;
import mp4.utils.zahlen.FormatNumber;
import mp4.utils.zahlen.vDouble;

/**
 *
 * @author  anti43
 */
public class eurEPanel extends javax.swing.JPanel implements panelInterface {

    private Einnahme curEinnahme;
    private SKRKonto curKonto;
    private String[][] data;

    /** Creates new form eurEPanel */
    public eurEPanel() {
        initComponents();
        curEinnahme = new Einnahme();
        curKonto = Einstellungen.instanceOf().getEinnahmeDefKonto();

        jTextField6.setText(DateConverter.getTodayDefDate());
        jTextField3.setText(FormatNumber.formatDezimal(Einstellungen.instanceOf().getGlobaltax()));
        jTextField4.setText("0");
        jTextField5.setText(Einstellungen.instanceOf().getEinnahmeDefKonto().getArt());

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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Einnahmen"));

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

        jLabel4.setText("MwSt(%)");

        jLabel5.setText("Gesamtbetrag (Brutto)");

        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField3MouseEntered(evt);
            }
        });

        jLabel6.setText("Konto");

        jTextField5.setEditable(false);

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
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton3);

        jButton4.setText("Als neue Einnahme");
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
            .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

            try {

                if (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)).contains("Rechnung")) {
                    this.setBill(new Rechnung(selection.getId()));
                } else if (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)).contains("Eingabe")) {
                    this.setEinnahme(new Einnahme(selection.getId()));
                }

            } catch (Exception exception) {
                Log.Debug(exception);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new konten(jTextField5, curEinnahme, this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        new DatePick(jTextField6);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton16KeyPressed
    }//GEN-LAST:event_jButton16KeyPressed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        if (jButton3.isEnabled()) {

            vDouble betrag = new vDouble(FormatNumber.parseDezimal(jTextField4.getText()));
            vDouble steuer = new vDouble(FormatNumber.parseDezimal(jTextField3.getText()));
            vDate datum = new vDate(jTextField6.getText());

            if (betrag.isVerified && betrag.isPositive && steuer.isVerified && steuer.isPositive && datum.isVerified) {

                if (this.curEinnahme != null && curEinnahme.id > 0) {

                    undoCache.instanceOf().addItem(ObjectCopy.copy(this.curEinnahme), undoCache.EDIT);

                    curEinnahme.setDatum(datum.date);
                    curEinnahme.setBeschreibung(jEditorPane1.getText());
                    curEinnahme.setPreis(betrag.value);
                    curEinnahme.setTax(steuer.value);
                    curEinnahme.save();
                    updateTableData();

                    new HistoryItem(Strings.EINNAHME, "Einnahme Nummer: " + curEinnahme.getId() + " editiert.");
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

  
            
            vDouble betrag = new vDouble(FormatNumber.parseDezimal(jTextField4.getText()));
            vDouble steuer = new vDouble(FormatNumber.parseDezimal(jTextField3.getText()));
            vDate datum = new vDate(jTextField6.getText());


            if (betrag.isVerified && steuer.isVerified && steuer.isPositive && datum.isVerified) {
                this.setEinnahme(new Einnahme(curKonto.getId(), jEditorPane1.getText(), betrag.value, steuer.value, datum.date));
                updateTableData();

                undoCache.instanceOf().addItem(ObjectCopy.copy(curEinnahme), undoCache.CREATE);
                new HistoryItem(Strings.EINNAHME, "Einnahme Nummer: " + curEinnahme.getId() + " angelegt.");

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
        if (this.curEinnahme != null) {
            undoCache.instanceOf().addItem(ObjectCopy.copy(this.curEinnahme), undoCache.DELETE);

            new HistoryItem(Strings.EINNAHME, "Einnahme Nummer: " + curEinnahme.getId() + " gel�scht.");
            curEinnahme.disable();
            updateTableData();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseEntered
        jTextField3.setEnabled(true);
    }//GEN-LAST:event_jTextField3MouseEntered

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
}//GEN-LAST:event_jButton3ActionPerformed

    public String[][] updateTableData() {
        try {
            FetchDataTask task = new FetchDataTask(this, null, new Einnahme());
            task.execute();
            data = task.get();
            Formater.formatUneditableTable(getJTable1(), data, new String[]{"id", "Nummer", "Typ", "Betrag", "Datum"});
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

    private void setBill(Rechnung bill) {
        this.curEinnahme = null;
        jTextField6.setText(bill.getFDatum());

        Customer c = new Customer(bill.getKundenId());

//        jEditorPane1.setContentType("text/html");
        jEditorPane1.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 12));
        jEditorPane1.setText(c.getFirma() + "\n\n" + c.getAnrede() + " " + c.getVorname() + " " + c.getName() + "\n" + c.getStr() + "\n\n" + c.getPLZ() + " " + c.getOrt() + "\n\n" +
                "Rechnung Nr.: " + bill.getRechnungnummer() + "\n");

        jTextField4.setText(bill.getFGesamtpreis());
        jTextField3.setText("");
        jTextField3.setEnabled(false);
        jTextField5.setText("Rechnung");
        jButton3.setEnabled(false);
    }

    private void setEinnahme(Einnahme einnahme) {

        this.curEinnahme = einnahme;
        jTextField6.setText(einnahme.getFDatum());
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setText("<html><font face=arial size=12px><b>" + einnahme.getBeschreibung() + "</b><<br>");

        jTextField4.setText(einnahme.getFPreis());
        jTextField3.setText(einnahme.getFTax());
        jTextField3.setEnabled(true);

        try {
            jTextField5.setText(new SKRKonto(einnahme.getKontenid()).getArt());
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
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
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
