/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LabeledTextField.java
 *
 * Created on 20.11.2008, 19:26:39
 */
package mpv5.ui.beans;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.handler.MPEnum;
import mpv5.utils.models.*;

/**
 *
 *  
 */
public class LabeledCombobox extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private String _text;
    private String _label;

    /** Creates new form LabeledTextField */
    public LabeledCombobox() {
        initComponents();
    }

    public JComboBox getComboBox() {
        return mPCombobox1.getComboBox();
    }

    /**
     *
     * @return The model
     */
    public MPComboboxModel getModel() {
        return mPCombobox1.getModel();
    }

    /**
     *
     * @param values
     */
    public void setModel(MPEnum[] values) {
        mPCombobox1.setModel(MPComboBoxModelItem.toModel(values));
    }

    /**
     * 
     * @param values
     * @param compareMode
     */
    public void setModel(MPEnum[] values, int compareMode) {
        mPCombobox1.setModel(MPComboBoxModelItem.toModel(values, compareMode));
    }

    /**
     * Uses enum.name() as ID, and enum.toString() as value.
     * @param values
     */
    public void setModel(Enum[] values) {
        String[][] val = new String[values.length][2];
        for (int i = 0; i < values.length; i++) {
            Enum enum1 = values[i];
            val[i][0] = enum1.name();
            val[i][1] = enum1.toString();
        }
        setModel(val);
    }

    /**
     * Delegates to setModel(MPComboBoxModelItem.toModel(data));
     * {id (hidden), value (shown in the list)}
     * @param data
     */
    public void setModel(Object[][] data) {
        mPCombobox1.setModel(MPComboBoxModelItem.toModel(data));
    }

    /**
     *
     * @return An {@link MPComboBoxModelItem} or null if nothing is selected
     */
    public MPComboBoxModelItem getSelectedItem() {
        if (mPCombobox1.getSelectedItem() instanceof MPComboBoxModelItem) {
            return (MPComboBoxModelItem) mPCombobox1.getSelectedItem();
        } else {
            return null;
        }
    }

    /**
     * Set the model. Should contain only {@link MPComboBoxModelItem}s
     * @param model
     */
    public void setModel(MPComboboxModel model) {
        mPCombobox1.setModel(model);
    }

    /**
     * Convenience Method to set a single {@link DatabaseObject} as the model of the combobox.<br/>
     * Will set the DO as the selected item after adding.
     * @param obj
     */
    public void setModel(DatabaseObject obj) {
        setModel(new Vector<DatabaseObject>(Arrays.asList(new DatabaseObject[]{
                    obj
                })));
        setSelectedIndex(0);
    }

    /**
     * Convenience Method to set a {@link List} of {@link DatabaseObject}s as the model of the combobox.<br/>
     * @param vector
     */
    public void setModel(List<DatabaseObject> vector) {
        setModel(new MPComboboxModel(MPComboBoxModelItem.toItems(vector)));
    }

    /**
     * Delegates to getComboBox().setSelectedIndex(itemID);
     * @param itemindex
     */
    public void setSelectedIndex(int itemindex) {
        if (itemindex < getComboBox().getItemCount()) {
            getComboBox().setSelectedIndex(itemindex);
        } else {
            throw new IndexOutOfBoundsException(itemindex + " must be lower than " + getComboBox().getItemCount());
        }
    }

    /**
     * Sets the item with the given value as selected item
     * @param valueOfItem
     */
    public void setSelectedItem(String valueOfItem) {
        mPCombobox1.setSelectedIndex(MPComboBoxModelItem.getItemIDfromValue(valueOfItem, mPCombobox1.getModel()));
    }

    /**
     * Sets the item with the given ID object as selected item
     * @param ID
     */
    public void setSelectedItem(Object ID) {
        mPCombobox1.setSelectedIndex(MPComboBoxModelItem.getItemID(ID, mPCombobox1.getModel()));
    }

    /**
     * If set to true, hitting "Enter" on the text field will trigger a search for the entered value and popup the results if any.
     * <br/>{@link LabeledCombobox#setContext(Context)} must be called before this can work.
     * @param enabled
     */
    public void setSearchOnEnterEnabled(boolean enabled) {
        mPCombobox1.setSearchOnEnterEnabled(enabled);
    }

    /**
     * Set the context for database queries
     * @param c
     */
    public void setContext(Context c) {
        mPCombobox1.setContext(c);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mPCombobox1 = new mpv5.ui.beans.MPCombobox();

        setOpaque(false);

        jLabel1.setText("text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mPCombobox1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(mPCombobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private mpv5.ui.beans.MPCombobox mPCombobox1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the _text
     */
    public MPComboBoxModelItem getValue() {
        return (MPComboBoxModelItem) mPCombobox1.getSelectedItem();
    }

    /**
     * @return the _label
     */
    public String get_Label() {
        return jLabel1.getText();
    }

    /**
     * @param label the _label to set
     */
    public void set_Label(String label) {
        this._label = label;
        jLabel1.setText(_label);
        this.setToolTipText(_text);
        jLabel1.setToolTipText(_text);
    }

    @Deprecated
    public void set_LabelFont(Font font) {
    }

    @Override
    public void setEnabled(boolean enabled) {
        jLabel1.setEnabled(enabled);
        mPCombobox1.setEnabled(enabled);
    }

    /**
    Sets the selected item in the combo box display area to the object in the argument. If anObject is in the list, the display area shows anObject selected.
    If anObject is not in the list and the combo box is uneditable, it will not change the current selection. For editable combo boxes, the selection will change to anObject.
    If this constitutes a change in the selected item, ItemListeners added to the combo box will be notified with one or two ItemEvents. If there is a current selected item, an ItemEvent will be fired and the state change will be ItemEvent.DESELECTED. If anObject is in the list and is not currently selected then an ItemEvent will be fired and the state change will be ItemEvent.SELECTED.
    ActionListeners added to the combo box will be notified with an ActionEvent when this method is called.
    Parameters:
    anObject - the list object to select; use null to clear the selection
     * @param text
     */
    public void setValue(String text) {
        mPCombobox1.setValue(text);
    }

    /**
     * Trigger a search on the combo box
     */
    public void triggerSearch() {
        mPCombobox1.search(true);
    }
}