/*
This file is part of YaBS.

YaBS is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

YaBS is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * ContactPanel.java
 *
 * Created on Nov 20, 2008, 8:17:28 AM
 */
package mpv5.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import mpv5.db.common.*;
import mpv5.db.objects.Account;
import mpv5.db.objects.Revenue;
import mpv5.globals.Messages;
import mpv5.db.objects.Favourite;
import mpv5.db.objects.Group;
import mpv5.db.objects.Item;
import mpv5.db.objects.Tax;
import mpv5.db.objects.Template;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.toolbars.DataPanelTB;
import mpv5.db.objects.User;
import mpv5.globals.Constants;
import mpv5.globals.Headers;
import mpv5.ui.misc.MPTable;
import mpv5.utils.export.Export;
import mpv5.utils.export.Exportable;
import mpv5.utils.models.MPComboBoxModelItem;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.numberformat.FormatNumber;
import mpv5.ui.misc.TableViewPersistenceHandler;
import mpv5.utils.tables.TableFormat;
import mpv5.utils.ui.TextFieldUtils;

/**
 *
 * 
 */
public class RevenuePanel extends javax.swing.JPanel implements DataPanel {

    private static final long serialVersionUID = 1L;
    private static RevenuePanel me;
    private java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
    private BigDecimal tax = new BigDecimal("0");

    /**
     * Singleton
     * @return
     */
    public static RevenuePanel instanceOf() {
        if (me == null) {
            me = new RevenuePanel();
        }
        me.refresh();
        return me;
    }
    private Revenue dataOwner;
    private DataPanelTB tb;
    private ArrayList<DatabaseObject> accmod;

    /** Creates new form ContactPanel
     */
    public RevenuePanel() {
        initComponents();
        setName("revenuepanel");
        tb = new mpv5.ui.toolbars.DataPanelTB(this);
        tb.disableButton(1);
        tb.disableButton(8);
        tb.disableButton(9);
        toolbarpane.add(tb, BorderLayout.CENTER);
        dataOwner = new Revenue();

        addedby.setText(mpv5.db.objects.User.getCurrentUser().getName());

        groupnameselect.setSearchEnabled(true);
        groupnameselect.setContext(Context.getGroup());
        taxrate.setSearchEnabled(true);
        taxrate.setContext(Context.getTaxes());

//        new calc().start();
        value.getTextField().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                calculate();
            }

            public void keyPressed(KeyEvent ek) {
                calculate();
            }

            public void keyReleased(KeyEvent e) {
                calculate();
            }
        });
        taxrate.getComboBox().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                getTaxRate();
            }
        });
        taxrate.getComboBox().setEditable(false);

        itemtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        itemtable.setFillsViewportHeight(true);

        ((MPTable) itemtable).setDefaultColumns(new Integer[]{100, 333, 100}, new Boolean[]{});
        ((MPTable) itemtable).setPersistanceHandler(new TableViewPersistenceHandler((MPTable) itemtable, this));
    }

    private void calculate() {
        try {
            netvalue.setText(FormatNumber.formatLokalCurrency(FormatNumber.parseDezimal(value.getText()).divide((tax.divide(Constants.BD100, 9, RoundingMode.HALF_UP)).add(BigDecimal.ONE), 9, RoundingMode.HALF_UP)));
        } catch (Exception e) {
            Log.Debug(e);
            netvalue.setText(FormatNumber.formatLokalCurrency(0d));
        }
    }

    @Override
    public DatabaseObject getDataOwner() {
        return dataOwner;
    }

    @Override
    public void setDataOwner(DatabaseObject object, boolean populate) {
        if (object instanceof Revenue) {
            dataOwner = (Revenue) object;
            if (populate) {
                dataOwner.setPanelData(this);

                this.exposeData();
                setTitle();

                tb.setFavourite(Favourite.isFavourite(object));
                tb.setEditable(!object.isReadOnly());

                formatTable();
                if (object.isReadOnly()) {
                    Popup.notice(Messages.LOCKED_BY);
                }

                validate();
            }
        } else if (object instanceof Item) {
        }
    }
    Exportable preloadedExportFile;
    Template preloadedTemplate;

    private void setTitle() {
        if (this.getParent() instanceof JViewport || this.getParent() instanceof JTabbedPane) {
            JTabbedPane jTabbedPane = null;
            String title1 = cname_;
            //this->viewport->scrollpane->tabbedpane
            if (this.getParent().getParent().getParent() instanceof JTabbedPane) {
                jTabbedPane = (JTabbedPane) this.getParent().getParent().getParent();
            } else {
                try {
                    jTabbedPane = (JTabbedPane) this.getParent();
                } catch (Exception e) {
                    //Free floating window
                    ((JFrame) this.getRootPane().getParent()).setTitle(title1);
                }
            }
            if (jTabbedPane != null) {
                jTabbedPane.setTitleAt(jTabbedPane.getSelectedIndex(), title1);
            }
        }
    }

    @Override
    public void showRequiredFields() {
        TextFieldUtils.blinkerRed(value);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        rightpane = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemtable = new  mpv5.ui.misc.MPTable(this) {
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent)c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));
                }
                return c;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        number = new mpv5.ui.beans.LabeledTextField();
        addedby = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        accountselect = new mpv5.ui.beans.LabeledCombobox();
        groupnameselect = new mpv5.ui.beans.MPCombobox();
        jPanel2 = new javax.swing.JPanel();
        value = new mpv5.ui.beans.LabeledTextField();
        netvalue = new mpv5.ui.beans.LabeledTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        notes = new javax.swing.JEditorPane();
        taxrate = new mpv5.ui.beans.LabeledCombobox();
        path = new javax.swing.JLabel();
        labeledDateChooser1 = new mpv5.ui.beans.LabeledDateChooser();
        toolbarpane = new javax.swing.JPanel();

        //\$2java.awt.Color(155, 175, 155));
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("RevenuePanel.border.title_1"))); // NOI18N
        setName("Form"); // NOI18N
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        rightpane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rightpane.setName("rightpane"); // NOI18N

//$2java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        itemtable.setAutoCreateRowSorter(true);
        itemtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        itemtable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        itemtable.setName("itemtable"); // NOI18N
        itemtable.setSurrendersFocusOnKeystroke(true);
        itemtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemtableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(itemtable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

//$2java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        number.set_Label(bundle.getString("RevenuePanel.number._Label")); // NOI18N
        number.setEnabled(false);
        number.setFocusable(false);
        number.setFont(number.getFont());
        number.setName("number"); // NOI18N

        addedby.setFont(addedby.getFont());
        addedby.setText(bundle.getString("RevenuePanel.addedby.text")); // NOI18N
        addedby.setToolTipText(bundle.getString("RevenuePanel.addedby.toolTipText")); // NOI18N
        addedby.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addedby.setEnabled(false);
        addedby.setName("addedby"); // NOI18N

        jLabel4.setFont(jLabel4.getFont());
        jLabel4.setText(bundle.getString("RevenuePanel.jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        accountselect.set_Label(bundle.getString("RevenuePanel.accountselect._Label")); // NOI18N
        accountselect.setName("accountselect"); // NOI18N
        accountselect.setSearchOnEnterEnabled(false);

        groupnameselect.setName("groupnameselect"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N

        value.set_Label(bundle.getString("RevenuePanel.value._Label")); // NOI18N
        value.set_Text(bundle.getString("RevenuePanel.value._Text")); // NOI18N
        value.setName("value"); // NOI18N

        netvalue.set_Label(bundle.getString("RevenuePanel.netvalue._Label")); // NOI18N
        netvalue.set_Text(bundle.getString("RevenuePanel.netvalue._Text")); // NOI18N
        netvalue.setEnabled(false);
        netvalue.setName("netvalue"); // NOI18N

        jLabel1.setText(bundle.getString("RevenuePanel.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

//$2java.awt.Color(254, 254, 254));
        notes.setName("notes"); // NOI18N
        jScrollPane1.setViewportView(notes);

        taxrate.set_Label(bundle.getString("RevenuePanel.taxrate._Label")); // NOI18N
        taxrate.setName("taxrate"); // NOI18N
        taxrate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taxrateMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taxrateMouseExited(evt);
            }
        });

        path.setText(bundle.getString("RevenuePanel.path.text")); // NOI18N
        path.setName("path"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(path, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(taxrate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(netvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(netvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        labeledDateChooser1.set_Label(bundle.getString("RevenuePanel.labeledDateChooser1._Label")); // NOI18N
        labeledDateChooser1.setName("labeledDateChooser1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labeledDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(accountselect, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addedby, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(groupnameselect, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addedby, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(groupnameselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labeledDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rightpaneLayout = new javax.swing.GroupLayout(rightpane);
        rightpane.setLayout(rightpaneLayout);
        rightpaneLayout.setHorizontalGroup(
            rightpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightpaneLayout.setVerticalGroup(
            rightpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightpaneLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(rightpane, java.awt.BorderLayout.CENTER);

        toolbarpane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        toolbarpane.setName("toolbarpane"); // NOI18N
        toolbarpane.setLayout(new java.awt.BorderLayout());
        add(toolbarpane, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void itemtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemtableMouseClicked
        try {
            setDataOwner((DatabaseObject) itemtable.getModel().getValueAt(itemtable.getSelectedRow(), 0), true);
        } catch (Exception e) {
            Log.Debug(this, e.getMessage());
        }
    }//GEN-LAST:event_itemtableMouseClicked

    private void taxrateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxrateMouseClicked
        calculate();
    }//GEN-LAST:event_taxrateMouseClicked

    private void taxrateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxrateMouseExited
        calculate();
    }//GEN-LAST:event_taxrateMouseExited
    MPTableModel omodel = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mpv5.ui.beans.LabeledCombobox accountselect;
    private javax.swing.JLabel addedby;
    private javax.swing.ButtonGroup buttonGroup1;
    private mpv5.ui.beans.MPCombobox groupnameselect;
    private javax.swing.JTable itemtable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private mpv5.ui.beans.LabeledDateChooser labeledDateChooser1;
    private mpv5.ui.beans.LabeledTextField netvalue;
    private javax.swing.JEditorPane notes;
    private mpv5.ui.beans.LabeledTextField number;
    private javax.swing.JLabel path;
    private javax.swing.JPanel rightpane;
    private mpv5.ui.beans.LabeledCombobox taxrate;
    private javax.swing.JPanel toolbarpane;
    private mpv5.ui.beans.LabeledTextField value;
    // End of variables declaration//GEN-END:variables
    public String cname_;
    public String description_;
    public String cnumber_;
    public int intaddedby_;
    public int ids_;
    public Date dateadded_;
    public Group group_;
    public BigDecimal netvalue_;
    public BigDecimal taxpercentvalue_;
    public BigDecimal brutvalue_;
    public int accountsids_;

    @Override
    public synchronized boolean collectData() {
        calculate();

        try {
            accountsids_ = Integer.valueOf(accountselect.getSelectedItem().getId());
            mpv5.db.objects.User.getCurrentUser().getProperties().changeProperty(me, "accountselect", accountsids_);
        } catch (Exception e) {
            accountsids_ = 1;
        }

        if (groupnameselect.getSelectedItem() != null) {
              try {
                  int group = Integer.valueOf(groupnameselect.getSelectedItem().getId());
                  group_ = (Group) DatabaseObject.getObject(Context.getGroup(), group);
                  Log.Debug(this, groupnameselect.getSelectedItem().getId());
              } catch (NodataFoundException ex) {
                  Log.Debug(this, ex);
              }
          }

        dateadded_ = labeledDateChooser1.getDate();

        intaddedby_ = User.getUserId(addedby.getText());
        description_ = notes.getText();
        try {
            netvalue_ = FormatNumber.parseDezimal(netvalue.getText());
        } catch (Exception e) {
            netvalue_ = BigDecimal.ZERO;
        }
        try {
            brutvalue_ = FormatNumber.parseDezimal(value.getText());
        } catch (Exception e) {
            brutvalue_ = BigDecimal.ZERO;
        }
        try {
            taxpercentvalue_ = Tax.getTaxValue(Integer.valueOf(taxrate.getSelectedItem().getId()));
        } catch (Exception numberFormatException) {
            taxpercentvalue_ = BigDecimal.ZERO;
        }
        return true;
    }

    @Override
    public void exposeData() {
        number.setText(cname_);
        notes.setText(description_);
        value.setText(FormatNumber.formatDezimal(brutvalue_));
        netvalue.setText(FormatNumber.formatDezimal(netvalue_));
        try {
            Account a = (Account) DatabaseObject.getObject(Context.getAccounts(), accountsids_);
            accountselect.setSelectedItem(a.__getIDS());
            path.setText(a.__getHierarchypath());
        } catch (NodataFoundException ex) {
            Log.Debug(this, ex.getMessage());
        }
        try {
            groupnameselect.setModel( group_);
        } catch ( Exception ex) {
            Log.Debug(this, ex.getMessage());
        }
        addedby.setText(User.getUsername(intaddedby_));
        labeledDateChooser1.setDate((dateadded_));
        taxrate.setSelectedItem(Tax.getTaxId(taxpercentvalue_));
        getTaxRate();
    }

    @Override
    public void refresh() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                groupnameselect.triggerSearch();
                taxrate.triggerSearch();
                try {
                    itemtable.setModel(new MPTableModel(Revenue.getRevenues(), Headers.EXPENSE));
                } catch (NodataFoundException e) {
                    Log.Debug(this, e.getMessage());
                }
                try {
                    accmod = DatabaseObject.getObjects(Context.getAccounts(), new QueryCriteria("intaccounttype", Account.INCOME));
                    accountselect.setModel(accmod);
                } catch (NodataFoundException e) {
                    Log.Debug(this, e.getMessage());
                }
                try {
                    accountselect.setSelectedItem(mpv5.db.objects.User.getCurrentUser().getProperties().getProperty(me, "accountselect", 1));
                    formatTable();
                } catch (Exception e) {
                    Log.Debug(this, e.getMessage());
                }
                getTaxRate();
            }
        };

        SwingUtilities.invokeLater(runnable);
    }

    /**
     * 
     */
    public void formatTable() {
//        TableFormat.resizeCols(itemtable, new Integer[]{100, 333, 100}, false);
    }

    @Override
    public void paste(DatabaseObject... dbo) {
        mpv5.YabsViewProxy.instance().addMessage(Messages.NOT_POSSIBLE.toString() + Messages.ACTION_PASTE.toString(), Color.RED);
    }

    @Override
    public void showSearchBar(boolean show) {
    }

    @Override
    public void actionAfterSave() {
        Runnable runnable = new Runnable() {

            public void run() {
                try {

                    itemtable.setModel(new MPTableModel(Revenue.getRevenues(), Headers.EXPENSE));
                    formatTable();

                } catch (NodataFoundException ex) {
                }
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    @Override
    public void actionAfterCreate() {
        Runnable runnable = new Runnable() {

            public void run() {
                try {

                    itemtable.setModel(new MPTableModel(Revenue.getRevenues(), Headers.EXPENSE));
                    formatTable();

                } catch (NodataFoundException ex) {
                }
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    public void actionBeforeCreate() {
    }

    public void actionBeforeSave() {
    }

    public void mail() {
    }

    public void print() {
        Export.print(this);
    }

    private void getTaxRate() {
        try {
            MPComboBoxModelItem t = taxrate.getValue();
            if (t == null) {
                tax = BigDecimal.ZERO;
                taxrate.setSelectedIndex(-1);
            } else {
                tax = Tax.getTaxValue(Integer.valueOf(t.getId()));
            }
            Log.Debug(this, "Selected Taxrate: " + tax);
        } catch (Exception ex) {
            Log.Debug(this, "Reading Taxrate failed: Assuming Zero");
            tax = BigDecimal.ZERO;
        }
        calculate();
    }
//    class calc extends Thread {
//
//        @Override
//        public void run() {
//            while (true) {
//                while (isShowing()) {
//                    BigDecimal tax = new BigDecimal("0");
//                    try {
//                        MPComboBoxModelItem t = taxrate.getValue();
//                        tax = Tax.getTaxValue(Integer.valueOf(t.getId()));
//                    } catch (Exception e) {
//                        try {
//                            tax = new BigDecimal(taxrate.getText());
//                        } catch (Exception numberFormatException) {
//                            tax = new BigDecimal("0");
//                        }
//                    }
//
//                    try {
//                        netvalue.setText(FormatNumber.formatLokalCurrency(FormatNumber.parseDezimal(value.getText()).divide((tax.divide(new BigDecimal("100"))).add(BigDecimal.ONE))));
//                    } catch (Exception e) {
//                        netvalue.setText(FormatNumber.formatLokalCurrency(0d));
//                    }
//                    try {
//                        sleep(333);
//                    } catch (InterruptedException ex) {
//                    }
//                }
//            }
//        }
//    }
}
