/*
 *  This file is part of YaBS.
 *
 *      YaBS is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      YaBS is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * PrinitingComboBox.java
 *
 * Created on 17.02.2009, 09:53:14
 */
package mpv5.ui.beans;

import java.awt.event.ItemEvent;
import java.awt.print.PrinterException;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mpv5.db.common.DatabaseObject;
import mpv5.db.objects.Contact;
import mpv5.logging.Log;
import mpv5.ui.dialogs.DialogForFile;
import mpv5.utils.date.DateConverter;
import mpv5.utils.files.FileActionHandler;
import mpv5.utils.files.TableHtmlWriter;
import mpv5.utils.models.MPComboBoxModelItem;
import mpv5.utils.print.FilePrintJob;
import mpv5.utils.print.PrintJob;

/**
 *
 *  
 */
public class PrinitingComboBox extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    public static final int MODE_DO = 0;
    public static final int MODE_TABLE = 1;
    private int mode = 0;
    private Object dataowner;

    /** Creates new form PrinitingComboBox
     */
    public PrinitingComboBox() {
        initComponents();
    }

    /**
     *
     * @param dataowner DatabaseObject or JTable
     */
    public void init(Object dataowner) {
        if (dataowner instanceof DatabaseObject) {

            jComboBox1.setModel(new DefaultComboBoxModel(new Object[]{new MPComboBoxModelItem(-1, ""),
                        new MPComboBoxModelItem(0, "Printer"),
                        new MPComboBoxModelItem(1, "VCF File"),
                        new MPComboBoxModelItem(2, "CSV File"),
                        new MPComboBoxModelItem(3, "XML File")}));
            mode = MODE_DO;
        } else if (dataowner instanceof JTable) {
            jComboBox1.setModel(new DefaultComboBoxModel(new Object[]{new MPComboBoxModelItem(-1, ""),
                        new MPComboBoxModelItem(0, "Printer"),
                        new MPComboBoxModelItem(1, "HTML File")}));
            mode = MODE_TABLE;
        }
        this.dataowner = dataowner;


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        jComboBox1.setFont(jComboBox1.getFont());
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont());
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
        jLabel1.setText(bundle.getString("PrinitingComboBox.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        JComboBox cb = (JComboBox) evt.getSource();
        // Get the affected item
        MPComboBoxModelItem item = (MPComboBoxModelItem) evt.getItem();

        switch (mode) {
            case MODE_DO:
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    switch (Integer.valueOf(item.getId().toString())) {
                        case 0:
                            new PrintJob().print((DatabaseObject) dataowner);
                            break;
                        case 1:
                            new FilePrintJob((DatabaseObject) dataowner).toVCF();
                            break;
                        case 2:
                            new FilePrintJob((DatabaseObject) dataowner).toCSV();
                            break;
                        case 3:
                            new FilePrintJob((DatabaseObject) dataowner).toXML();
//                            ((Contact) dataowner).toXML();
                            break;
                    }
                }
                break;

            case MODE_TABLE:
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    switch (Integer.valueOf(item.getId().toString())) {
                        case 0:
                            try {
                                ((JTable) dataowner).print();
                            } catch (PrinterException ex) {
                                Log.Debug(this, ex);
                            }
                            break;
                        case 1:
                            DialogForFile dialog = new DialogForFile(DialogForFile.FILES_ONLY, "export-" + DateConverter.getTodayDefDate() + ".html");
                            if (dialog.saveFile()) {
                                File f = new TableHtmlWriter(((DefaultTableModel) ((JTable) dataowner).getModel()), dialog.getFile(),  ((JTable) dataowner).getShowHorizontalLines(),((JTable) dataowner).getShowVerticalLines()).createHtml();
                                FileActionHandler.open(f);
                            }
                            break;
                    }
                    break;
                }
        }

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
