package mpv5.ui.dialogs.subcomponents;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JCheckBox;
import mpv5.data.PropertyStore;
import mpv5.db.common.Context;
import mpv5.globals.LocalSettings;
import mpv5.logging.Log;
import mpv5.ui.beans.LightMPComboBox;
import mpv5.ui.dialogs.ControlApplet;
import mpv5.ui.frames.MPView;

/**
 *
 * 
 */
public class ControlPanel_Userproperties extends javax.swing.JPanel implements ControlApplet {

    private static final long serialVersionUID = -8347532498124147821L;
    /**
     * This unique name identifies this control applet
     */
    public final String UNAME = "userproperties";
    private PropertyStore oldvalues;
    private static ControlPanel_Userproperties ident;

    public ControlPanel_Userproperties() {
        initComponents();
        loadSettings();
        defcount.set_ValueClass(Double.class);
        deftax.setSearchOnEnterEnabled(true);
        deftax.setContext(Context.getTaxes());
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        productstobillsproperties = new javax.swing.JPanel();
        cname = new javax.swing.JCheckBox();
        description = new javax.swing.JCheckBox();
        cnumber = new javax.swing.JCheckBox();
        reference = new javax.swing.JCheckBox();
        ean = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        defs = new javax.swing.JPanel();
        defcount = new mpv5.ui.beans.LabeledTextField();
        defunit = new mpv5.ui.beans.LabeledTextField();
        deftax = new mpv5.ui.beans.LabeledCombobox();
        order = new mpv5.ui.beans.LabeledTextField();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ControlPanel_Userproperties.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ControlPanel_Userproperties.jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        productstobillsproperties.setName("productstobillsproperties"); // NOI18N

        cname.setText(bundle.getString("ControlPanel_Userproperties.cname.text")); // NOI18N
        cname.setName("cname"); // NOI18N

        description.setText(bundle.getString("ControlPanel_Userproperties.description.text")); // NOI18N
        description.setName("description"); // NOI18N

        cnumber.setText(bundle.getString("ControlPanel_Userproperties.cnumber.text")); // NOI18N
        cnumber.setName("cnumber"); // NOI18N

        reference.setText(bundle.getString("ControlPanel_Userproperties.reference.text")); // NOI18N
        reference.setName("reference"); // NOI18N

        ean.setText(bundle.getString("ControlPanel_Userproperties.ean.text")); // NOI18N
        ean.setName("ean"); // NOI18N

        jButton3.setText(bundle.getString("ControlPanel_Userproperties.jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productstobillspropertiesLayout = new javax.swing.GroupLayout(productstobillsproperties);
        productstobillsproperties.setLayout(productstobillspropertiesLayout);
        productstobillspropertiesLayout.setHorizontalGroup(
            productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productstobillspropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ean, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(reference, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(jButton3))
                .addContainerGap())
        );
        productstobillspropertiesLayout.setVerticalGroup(
            productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productstobillspropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cname)
                    .addComponent(ean))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(description)
                    .addComponent(reference))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productstobillspropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnumber)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        defs.setName("defs"); // NOI18N

        defcount.set_Label(bundle.getString("ControlPanel_Userproperties.defcount._Label")); // NOI18N
        defcount.setName("defcount"); // NOI18N

        defunit.set_Label(bundle.getString("ControlPanel_Userproperties.defunit._Label")); // NOI18N
        defunit.setName("defunit"); // NOI18N

        deftax.set_Label(bundle.getString("ControlPanel_Userproperties.deftax._Label")); // NOI18N
        deftax.setName("deftax"); // NOI18N

        javax.swing.GroupLayout defsLayout = new javax.swing.GroupLayout(defs);
        defs.setLayout(defsLayout);
        defsLayout.setHorizontalGroup(
            defsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(defsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defunit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defcount, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deftax, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        defsLayout.setVerticalGroup(
            defsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deftax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        order.set_Label(bundle.getString("ControlPanel_Userproperties.order._Label")); // NOI18N
        order.setName("order"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productstobillsproperties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(defs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(order, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(productstobillsproperties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(defs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton2.setText(bundle.getString("ControlPanel_Userproperties.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        jButton1.setText(bundle.getString("ControlPanel_Userproperties.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        add(jPanel6, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        setSettings();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setSettings();
        MPView.getUser().saveProperties();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Component[] t = productstobillsproperties.getComponents();
        String h = "";
        for (int i = 0; i < t.length; i++) {
            Component component = t[i];
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    h += "_$" + component.getName() + "$_ ";
                }
            }
        }
        order.setText(h);
    }//GEN-LAST:event_jButton3ActionPerformed

    @Override
    public void setValues(PropertyStore values) {
        oldvalues = values;
    }

    @Override
    public String getUname() {
        return UNAME;
    }

    @Override
    public void reset() {
        setValues(oldvalues);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cname;
    private javax.swing.JCheckBox cnumber;
    private mpv5.ui.beans.LabeledTextField defcount;
    private javax.swing.JPanel defs;
    private mpv5.ui.beans.LabeledCombobox deftax;
    private mpv5.ui.beans.LabeledTextField defunit;
    private javax.swing.JCheckBox description;
    private javax.swing.JCheckBox ean;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private mpv5.ui.beans.LabeledTextField order;
    private javax.swing.JPanel productstobillsproperties;
    private javax.swing.JCheckBox reference;
    // End of variables declaration//GEN-END:variables

    private void setSettings() {

        try {
            MPView.getUser().getProperties().changeProperty(Context.getProducts() + LightMPComboBox.VALUE_SEARCHFIELDS, order.getText());
            MPView.getUser().getProperties().changeProperty(defcount.getName(), defcount.getText());
            MPView.getUser().getProperties().changeProperty(defunit.getName(), defunit.getText());
            if (deftax.getSelectedItem() != null) {
                MPView.getUser().getProperties().changeProperty(deftax.getName(), deftax.getSelectedItem().getId());
            }
        } catch (Exception e) {
            Log.Debug(e);
        }

    }

    @Override
    public Component getAndRemoveActionPanel() {
        this.remove(jPanel6);
        validate();
        return jPanel6;
    }

    private void loadSettings() {
//        Component[] t = productstobillsproperties.getComponents();
//        for (int i = 0; i < t.length; i++) {
//            Component component = t[i];
//            if (MPView.getUser().getProperties().hasProperty(Context.getBill() + LightMPComboBox.VALUE_SEARCHFIELDS)) {
//                if(component instanceof JCheckBox) {
//                    ((JCheckBox) component).setSelected(MPView.getUser().getProperties().getProperty(Context.getBill() + LightMPComboBox.VALUE_SEARCHFIELDS).contains(component.getName()));
//                }
//            }
//        }
    }
}