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
 * HistoryPanel.java
 *
 * Created on 30.03.2009, 12:05:51
 */
package mpv5.ui.panels;

import enoa.handler.TableHandler;
import enoa.handler.TemplateHandler;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import mpv5.db.common.Context;

import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseObject.Entity;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria2;
import mpv5.db.common.QueryHandler;
import mpv5.db.common.QueryParameter;
import mpv5.db.common.Templateable;
import mpv5.db.objects.Account;
import mpv5.db.objects.Contact;
import mpv5.db.objects.Expense;
import mpv5.globals.Headers;
import mpv5.db.objects.Group;
import mpv5.db.objects.Item;
import mpv5.db.objects.Revenue;
import mpv5.db.objects.Template;
import mpv5.db.objects.User;
import mpv5.globals.Constants;
import mpv5.globals.GlobalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.beans.MPCombobox;
import mpv5.ui.dialogs.DialogForFile;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.misc.MPTable;
import mpv5.ui.popups.TablePopUp;
import mpv5.utils.arrays.ArrayUtilities;
import mpv5.utils.date.DateConverter;
import mpv5.utils.date.vTimeframe;
import mpv5.utils.export.DTAFile;
import mpv5.utils.export.Export;
import mpv5.utils.jobs.Job;
import mpv5.utils.jobs.Waitable;
import mpv5.utils.models.MPComboBoxModelItem;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.models.MPTreeModel;
import mpv5.utils.tables.ExcelAdapter;
import mpv5.utils.tables.TableFormat;
import mpv5.utils.text.TypeConversion;
import mpv5.ui.misc.TableViewPersistenceHandler;
import mpv5.utils.numberformat.FormatNumber;

/**
 *
 * FIXME unpaid??
 */
public class JournalPanel extends javax.swing.JPanel implements ListPanel {

   private static JournalPanel t;
   private static final long serialVersionUID = 1L;
   private java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();

   public static JComponent instanceOf() {
      if (t == null) {
         t = new JournalPanel();
      }
      return t;
   }
   private Contact dataOwner;
   private Template journalOrContactTemplate;

   /**
    * Creates new form HistoryPanel
    */
   public JournalPanel() {
      initComponents();
      setName("journalpanel");
      new ExcelAdapter(jTable1);
      setPopup();
      jPanel5.setEnabled(false);
      jTabbedPane1.removeTabAt(0);
      jLabel3.setEnabled(false);
      jLabel4.setEnabled(false);
      jLabel4.setText("");
      jTabbedPane1.setSelectedComponent(jPanel4);
      validate();
      timeframeChooser1.setTime(new vTimeframe(DateConverter.getDate(DateConverter.getYear()), new Date()));
      bydateend.setSelected(User.getCurrentUser().getProperties().getProperty(this, bydateend, Boolean.FALSE));
      expbydateend.setSelected(User.getCurrentUser().getProperties().getProperty(this, expbydateend, Boolean.FALSE));
      groups.setSearchEnabled(true);
      groups.setContext(Context.getGroup());
      groups.triggerSearch();
      users.setSearchEnabled(true);
      users.setContext(Context.getUser());
      users.setModel();

      try {
         DefaultListModel al = new DefaultListModel();
         ArrayList<DatabaseObject> accounts = DatabaseObject.getObjects(Context.getAccounts());
         for (int i = 0; i < accounts.size(); i++) {
            DatabaseObject databaseObject = accounts.get(i);
            al.addElement((Account) databaseObject);
         }
         jList1.setModel(al);
      } catch (NodataFoundException nodataFoundException) {
         Log.Debug(this, nodataFoundException.getMessage());
      }

      statusc.getComboBox().setModel(new DefaultComboBoxModel(new Object[]{Messages.ALL, Messages.STATUS_PAID, Messages.STATUS_UNPAID, Messages.STATUS_CANCELLED}));
      prinitingComboBox1.init(jTable1);
      refresh(null, null);
      jButton4.setEnabled(false);
      loadTemplate();
      ((MPTable) jTable1).setPersistanceHandler(new TableViewPersistenceHandler((MPTable) jTable1, this));

   }

   public JournalPanel(Contact dataOwner) {
      initComponents();
      setPopup();
      jLabel4.setText(dataOwner.__getCname());
      timeframeChooser1.setTime(new vTimeframe(DateConverter.getDate(DateConverter.getYear()), new Date()));
      prinitingComboBox1.init(jTable1);

      this.dataOwner = dataOwner;
      groups.setSearchEnabled(true);
      groups.setContext(Context.getGroup());
      groups.triggerSearch();

      try {
         DefaultListModel al = new DefaultListModel();
         ArrayList<DatabaseObject> accounts = DatabaseObject.getObjects(Context.getAccounts());
         for (int i = 0; i < accounts.size(); i++) {
            DatabaseObject databaseObject = accounts.get(i);
            al.addElement((Account) databaseObject);
         }
         jList1.setModel(al);
      } catch (NodataFoundException nodataFoundException) {
         Log.Debug(this, nodataFoundException.getMessage());
      }

      statusc.getComboBox().setModel(new DefaultComboBoxModel(new Object[]{Messages.ALL, Messages.STATUS_PAID, Messages.STATUS_UNPAID}));

      refresh(null, null);

      jTree1.setCellRenderer(MPTreeModel.getRenderer());
      jTree1.setModel(new MPTreeModel(dataOwner, null));
      jTree1.addMouseListener(MPTreeModel.getDefaultTreeListener(jTree1));
      prinitingComboBox1.init(jTable1);
//        TreeFormat.expandTree(jTree1);
      loadTemplate();
      ((MPTable) jTable1).setPersistanceHandler(new TableViewPersistenceHandler((MPTable) jTable1, this));

   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      buttonGroup1 = new javax.swing.ButtonGroup();
      buttonGroup2 = new javax.swing.ButtonGroup();
      jPanel1 = new javax.swing.JPanel();
      jTabbedPane1 = new javax.swing.JTabbedPane();
      jPanel5 = new javax.swing.JPanel();
      jScrollPane2 = new javax.swing.JScrollPane();
      jTree1 = new javax.swing.JTree();
      jPanel10 = new javax.swing.JPanel();
      filternone = new javax.swing.JRadioButton();
      filterinvoice = new javax.swing.JRadioButton();
      filterorders = new javax.swing.JRadioButton();
      filteroffers = new javax.swing.JRadioButton();
      jPanel4 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jTable1 = new  mpv5.ui.misc.MPTable(this) {
         private static final long serialVersionUID = 1L;
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
      jPanel2 = new javax.swing.JPanel();
      jPanel7 = new javax.swing.JPanel();
      bydateend = new javax.swing.JCheckBox();
      groups = new mpv5.ui.beans.MPCombobox();
      jLabel8 = new javax.swing.JLabel();
      statusc = new mpv5.ui.beans.MPCombobox();
      includechildgroups = new javax.swing.JCheckBox();
      jLabel2 = new javax.swing.JLabel();
      users = new mpv5.ui.beans.MPCombobox();
      jLabel6 = new javax.swing.JLabel();
      expbydateend = new javax.swing.JCheckBox();
      jPanel8 = new javax.swing.JPanel();
      jLabel3 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      jLabel4 = new javax.swing.JLabel();
      timeframeChooser1 = new mpv5.ui.beans.TimeframeChooser();
      jPanel6 = new javax.swing.JPanel();
      jScrollPane3 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();
      jButton1 = new javax.swing.JButton();
      count = new javax.swing.JLabel();
      jPanel3 = new javax.swing.JPanel();
      jButton4 = new javax.swing.JButton();
      prinitingComboBox1 = new mpv5.ui.beans.PrinitingComboBox();
      revenueNetto = new mpv5.ui.beans.LabeledTextField();
      volumeNetto = new mpv5.ui.beans.LabeledTextField();
      volumeBrutto = new mpv5.ui.beans.LabeledTextField();
      taxVolume = new mpv5.ui.beans.LabeledTextField();
      volumeDiscount = new mpv5.ui.beans.LabeledTextField();

      setName("Form"); // NOI18N

      java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();; // NOI18N
      jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("JournalPanel.jPanel1.border.title"))); // NOI18N
      jPanel1.setName("jPanel1"); // NOI18N

      jTabbedPane1.setName("jTabbedPane1"); // NOI18N

      jPanel5.setName("jPanel5"); // NOI18N
      jPanel5.setLayout(new java.awt.BorderLayout());

      jScrollPane2.setName("jScrollPane2"); // NOI18N

      javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
      jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
      jTree1.setName("jTree1"); // NOI18N
      jScrollPane2.setViewportView(jTree1);

      jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

      jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("JournalPanel.jPanel10.border.title"))); // NOI18N
      jPanel10.setName("jPanel10"); // NOI18N
      jPanel10.setPreferredSize(new java.awt.Dimension(874, 45));
      jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

      buttonGroup2.add(filternone);
      filternone.setSelected(true);
      filternone.setText(bundle.getString("JournalPanel.filternone.text")); // NOI18N
      filternone.setName("filternone"); // NOI18N
      filternone.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            filternoneItemStateChanged(evt);
         }
      });
      jPanel10.add(filternone);

      buttonGroup2.add(filterinvoice);
      filterinvoice.setText(bundle.getString("JournalPanel.filterinvoice.text")); // NOI18N
      filterinvoice.setName("filterinvoice"); // NOI18N
      filterinvoice.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            filterinvoiceItemStateChanged(evt);
         }
      });
      jPanel10.add(filterinvoice);

      buttonGroup2.add(filterorders);
      filterorders.setText(bundle.getString("JournalPanel.filterorders.text")); // NOI18N
      filterorders.setName("filterorders"); // NOI18N
      filterorders.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            filterordersItemStateChanged(evt);
         }
      });
      jPanel10.add(filterorders);

      buttonGroup2.add(filteroffers);
      filteroffers.setText(bundle.getString("JournalPanel.filteroffers.text")); // NOI18N
      filteroffers.setName("filteroffers"); // NOI18N
      filteroffers.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            filteroffersItemStateChanged(evt);
         }
      });
      jPanel10.add(filteroffers);

      jPanel5.add(jPanel10, java.awt.BorderLayout.NORTH);

      jTabbedPane1.addTab(bundle.getString("JournalPanel.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

      jPanel4.setName("jPanel4"); // NOI18N
      jPanel4.setLayout(new java.awt.BorderLayout(2, 0));

      jScrollPane1.setName("jScrollPane1"); // NOI18N
      jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 430));

      jTable1.setAutoCreateRowSorter(true);
      jTable1.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {

         }
      ));
      jTable1.setFillsViewportHeight(true);
      jTable1.setName("jTable1"); // NOI18N
      jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
         }
      });
      jScrollPane1.setViewportView(jTable1);

      jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("JournalPanel.jPanel2.border.title"))); // NOI18N
      jPanel2.setName("jPanel2"); // NOI18N
      jPanel2.setPreferredSize(new java.awt.Dimension(734, 200));
      jPanel2.setRequestFocusEnabled(false);

//$2java.awt.Color(255, 255, 255));
      jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      jPanel7.setName("jPanel7"); // NOI18N

//$2java.awt.Color(255, 255, 255));
      bydateend.setFont(bydateend.getFont());
      bydateend.setText(bundle.getString("JournalPanel.bydateend.text")); // NOI18N
      bydateend.setName("bydateend"); // NOI18N
      bydateend.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            bydateendItemStateChanged(evt);
         }
      });

      groups.setFont(groups.getFont());
      groups.setName("groups"); // NOI18N

      jLabel8.setFont(jLabel8.getFont());
      jLabel8.setText(bundle.getString("JournalPanel.jLabel8.text_1")); // NOI18N
      jLabel8.setName("jLabel8"); // NOI18N
      jLabel8.setPreferredSize(new java.awt.Dimension(150, 21));

      statusc.setFont(statusc.getFont());
      statusc.setName("statusc"); // NOI18N

//$2java.awt.Color(255, 255, 255));
      includechildgroups.setFont(includechildgroups.getFont());
      includechildgroups.setSelected(true);
      includechildgroups.setText(bundle.getString("JournalPanel.includechildgroups.text")); // NOI18N
      includechildgroups.setName("includechildgroups"); // NOI18N

      jLabel2.setFont(jLabel2.getFont());
      jLabel2.setText(bundle.getString("JournalPanel.jLabel2.text")); // NOI18N
      jLabel2.setName("jLabel2"); // NOI18N
      jLabel2.setPreferredSize(new java.awt.Dimension(150, 21));

      users.setFont(users.getFont());
      users.setName("users"); // NOI18N

      jLabel6.setFont(jLabel6.getFont());
      jLabel6.setText(bundle.getString("JournalPanel.jLabel6.text")); // NOI18N
      jLabel6.setName("jLabel6"); // NOI18N
      jLabel6.setPreferredSize(new java.awt.Dimension(150, 21));

//$2java.awt.Color(255, 255, 255));
      expbydateend.setFont(expbydateend.getFont());
      expbydateend.setText(bundle.getString("JournalPanel.expbydateend.text")); // NOI18N
      expbydateend.setName("expbydateend"); // NOI18N
      expbydateend.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            expbydateendItemStateChanged(evt);
         }
      });

      javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
      jPanel7.setLayout(jPanel7Layout);
      jPanel7Layout.setHorizontalGroup(
         jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(groups, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
               .addComponent(users, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
               .addComponent(statusc, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(expbydateend, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(bydateend, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(includechildgroups, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
      );
      jPanel7Layout.setVerticalGroup(
         jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel7Layout.createSequentialGroup()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
               .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(includechildgroups))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
               .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(users, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(expbydateend))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
               .addComponent(bydateend)
               .addComponent(statusc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

//$2java.awt.Color(255, 255, 255));
      jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      jPanel8.setName("jPanel8"); // NOI18N

      jLabel3.setFont(jLabel3.getFont());
      jLabel3.setText(bundle.getString("JournalPanel.jLabel3.text")); // NOI18N
      jLabel3.setName("jLabel3"); // NOI18N

      jLabel5.setFont(jLabel5.getFont());
      jLabel5.setText(bundle.getString("JournalPanel.jLabel5.text")); // NOI18N
      jLabel5.setName("jLabel5"); // NOI18N

      jLabel4.setFont(jLabel4.getFont());
      jLabel4.setText(bundle.getString("JournalPanel.jLabel4.text")); // NOI18N
      jLabel4.setName("jLabel4"); // NOI18N

//$2java.awt.Color(255, 255, 255));
      timeframeChooser1.setFont(timeframeChooser1.getFont());
      timeframeChooser1.setName("timeframeChooser1"); // NOI18N

      javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
      jPanel8.setLayout(jPanel8Layout);
      jPanel8Layout.setHorizontalGroup(
         jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(timeframeChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(134, Short.MAX_VALUE))
      );
      jPanel8Layout.setVerticalGroup(
         jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
               .addComponent(jLabel3)
               .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
               .addComponent(timeframeChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel5))
            .addContainerGap())
      );

      jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("JournalPanel.jPanel6.border.title"))); // NOI18N
      jPanel6.setName("jPanel6"); // NOI18N
      jPanel6.setLayout(new java.awt.BorderLayout());

      jScrollPane3.setName("jScrollPane3"); // NOI18N

      jList1.setFont(jList1.getFont().deriveFont(jList1.getFont().getStyle() | java.awt.Font.BOLD));
      jList1.setName("jList1"); // NOI18N
      jList1.setSelectionBackground(new java.awt.Color(51, 51, 255));
      jScrollPane3.setViewportView(jList1);

      jPanel6.add(jScrollPane3, java.awt.BorderLayout.CENTER);

      jButton1.setFont(jButton1.getFont());
      jButton1.setText(bundle.getString("JournalPanel.jButton1.text")); // NOI18N
      jButton1.setName("jButton1"); // NOI18N
      jButton1.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
         }
      });

      count.setFont(count.getFont().deriveFont(count.getFont().getStyle() | java.awt.Font.BOLD));
      count.setForeground(new java.awt.Color(102, 102, 102));
      count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      count.setText(bundle.getString("JournalPanel.count.text")); // NOI18N
      count.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      count.setName("count"); // NOI18N

      javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
      jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel2Layout.createSequentialGroup()
                  .addGap(100, 100, 100)
                  .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(jPanel2Layout.createSequentialGroup()
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(jPanel2Layout.createSequentialGroup()
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
               .addGroup(jPanel2Layout.createSequentialGroup()
                  .addComponent(jPanel6, 0, 0, Short.MAX_VALUE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jButton1))
               .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                  .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(11, 11, 11)
            .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
      );

      jPanel4.add(jPanel2, java.awt.BorderLayout.NORTH);

      jPanel3.setName("jPanel3"); // NOI18N

      jButton4.setText(bundle.getString("JournalPanel.jButton4.text")); // NOI18N
      jButton4.setName("jButton4"); // NOI18N
      jButton4.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
         }
      });

      prinitingComboBox1.setName("prinitingComboBox1"); // NOI18N

      revenueNetto.set_Label(bundle.getString("JournalPanel.revenueNetto._Label")); // NOI18N
      revenueNetto.setName("revenueNetto"); // NOI18N

      volumeNetto.set_Label(bundle.getString("JournalPanel.volumeNetto._Label")); // NOI18N
      volumeNetto.setName("volumeNetto"); // NOI18N

      volumeBrutto.set_Label(bundle.getString("JournalPanel.volumeBrutto._Label")); // NOI18N
      volumeBrutto.setName("volumeBrutto"); // NOI18N

      taxVolume.set_Label(bundle.getString("JournalPanel.taxVolume._Label")); // NOI18N
      taxVolume.setName("taxVolume"); // NOI18N

      volumeDiscount.set_Label(bundle.getString("JournalPanel.volumeDiscount._Label")); // NOI18N
      volumeDiscount.setName("volumeDiscount"); // NOI18N

      javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
      jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                  .addComponent(prinitingComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jButton4)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                  .addComponent(volumeBrutto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(volumeDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                  .addGap(0, 0, Short.MAX_VALUE)
                  .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(taxVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(volumeNetto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(revenueNetto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(jPanel3Layout.createSequentialGroup()
                  .addGap(0, 0, Short.MAX_VALUE)
                  .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                     .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(prinitingComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGroup(jPanel3Layout.createSequentialGroup()
                  .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                     .addComponent(volumeNetto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(revenueNetto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                  .addComponent(taxVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(volumeDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(volumeBrutto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(18, 18, 18))
      );

      jPanel4.add(jPanel3, java.awt.BorderLayout.SOUTH);

      jTabbedPane1.addTab(bundle.getString("JournalPanel.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jTabbedPane1)
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
   }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

       preview();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       setData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void expbydateendItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_expbydateendItemStateChanged
       User.getCurrentUser().getProperties().changeProperty(this, expbydateend, expbydateend.isSelected());
    }//GEN-LAST:event_expbydateendItemStateChanged

    private void bydateendItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bydateendItemStateChanged
       User.getCurrentUser().getProperties().changeProperty(this, bydateend, bydateend.isSelected());
    }//GEN-LAST:event_bydateendItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if (evt.getClickCount() > 1) {
          try {
             int index = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
             DatabaseObject obj = DatabaseObject.getObject((DatabaseObject.Entity<?, ?>) jTable1.getModel().getValueAt(index, 0));
             mpv5.YabsViewProxy.instance().addTab(obj);
          } catch (NodataFoundException ex) {
             Log.Debug(ex);
          }
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void filteroffersItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filteroffersItemStateChanged
       filter();
    }//GEN-LAST:event_filteroffersItemStateChanged

    private void filterordersItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterordersItemStateChanged
       filter();
    }//GEN-LAST:event_filterordersItemStateChanged

    private void filterinvoiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterinvoiceItemStateChanged
       filter();
    }//GEN-LAST:event_filterinvoiceItemStateChanged

    private void filternoneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filternoneItemStateChanged
       filter();
    }//GEN-LAST:event_filternoneItemStateChanged
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.ButtonGroup buttonGroup1;
   private javax.swing.ButtonGroup buttonGroup2;
   private javax.swing.JCheckBox bydateend;
   private javax.swing.JLabel count;
   private javax.swing.JCheckBox expbydateend;
   private javax.swing.JRadioButton filterinvoice;
   private javax.swing.JRadioButton filternone;
   private javax.swing.JRadioButton filteroffers;
   private javax.swing.JRadioButton filterorders;
   private mpv5.ui.beans.MPCombobox groups;
   private javax.swing.JCheckBox includechildgroups;
   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton4;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JList jList1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel10;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel5;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JPanel jPanel7;
   private javax.swing.JPanel jPanel8;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JTabbedPane jTabbedPane1;
   private javax.swing.JTable jTable1;
   private javax.swing.JTree jTree1;
   private mpv5.ui.beans.PrinitingComboBox prinitingComboBox1;
   private mpv5.ui.beans.LabeledTextField revenueNetto;
   private mpv5.ui.beans.MPCombobox statusc;
   private mpv5.ui.beans.LabeledTextField taxVolume;
   private mpv5.ui.beans.TimeframeChooser timeframeChooser1;
   private mpv5.ui.beans.MPCombobox users;
   private mpv5.ui.beans.LabeledTextField volumeBrutto;
   private mpv5.ui.beans.LabeledTextField volumeDiscount;
   private mpv5.ui.beans.LabeledTextField volumeNetto;
   // End of variables declaration//GEN-END:variables

   private Object[][] parse(Object[][] data) {
      //#entity date group account number type status value
      double revenuenet = 0d;
      double volumenet = 0d;
      double taxvolume = 0d;
      double volumebrut = 0d;
      double discountvolume = 0d;
      Object[][] d = new Object[data.length][18];
      try {
         for (int i = 0; i < d.length; i++) {
            d[i][1] = data[i][1];
            d[i][2] = data[i][2];
            d[i][3] = data[i][3];
            d[i][4] = data[i][4];
            d[i][5] = data[i][5];
            //d[i][14] = data[i][14];
            d[i][8] = data[i][8];
            d[i][9] = data[i][9];
            d[i][7] = data[i][7];
            d[i][6] = DateConverter.getDefDateString(DateConverter.getDate(data[i][6].toString()));

            d[i][0] = data[i][0];

            int type = Integer.valueOf(data[i][10].toString());
            if (type == Revenue.TYPE_REVENUE) {
               d[i][10] = Messages.TYPE_REVENUE.toString();
            } else if (type == Expense.TYPE_EXPENSE) {
               d[i][10] = Messages.TYPE_EXPENSE.toString();
            } else {
               d[i][10] = Item.getTypeString(type);
            }

            double netVolume = Double.valueOf(data[i][12].toString());
            double taxVal = Double.valueOf(data[i][13].toString());
            double brutVolume = Double.valueOf(data[i][14].toString());
            double discountVolume = Double.valueOf(data[i][15].toString());
            d[i][11] = Item.getStatusString(Integer.valueOf(data[i][11].toString()));

            if (Integer.valueOf(data[i][11].toString()).intValue() == Item.STATUS_PAID) {
               revenuenet += netVolume;
               d[i][15] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(netVolume);
            } else if (Integer.valueOf(data[i][11].toString()).intValue() == 1000) {
               d[i][15] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(-1 * netVolume);
               revenuenet -= netVolume;
               d[i][11] = Item.getStatusString(Item.STATUS_PAID);
            } else if (Integer.valueOf(data[i][11].toString()).intValue() == 2000) {
               d[i][15] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(-1 * netVolume);
               revenuenet -= netVolume;
               d[i][11] = Item.getStatusString(Item.STATUS_IN_PROGRESS);
            } else {
               d[i][15] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(0d);
            }
            d[i][12] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(netVolume);
            d[i][13] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(taxVal);
            d[i][14] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(brutVolume);
            d[i][16] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(discountVolume);
            d[i][17] = "<html><p align=center>" + data[i][16];
            volumenet += netVolume;
            volumebrut += brutVolume;
            taxvolume += taxVal;
            discountvolume += discountVolume;
         }
      } catch (Exception numberFormatException) {
         Log.Debug(numberFormatException);
         Log.Debug(this, Arrays.asList(data[0]));
      }

      volumeNetto.setText(mpv5.utils.numberformat.FormatNumber.formatDezimal(volumenet));
      revenueNetto.setText(mpv5.utils.numberformat.FormatNumber.formatDezimal(revenuenet));
      volumeBrutto.setText(mpv5.utils.numberformat.FormatNumber.formatDezimal(volumebrut));
      taxVolume.setText(mpv5.utils.numberformat.FormatNumber.formatDezimal(taxvolume));
      volumeDiscount.setText(mpv5.utils.numberformat.FormatNumber.formatDezimal(discountvolume));

      return d;
   }

   private void refresh(final Group forGroup, final User forUser) {
      Runnable runnable = new Runnable() {
         @Override
         public void run() {

            try {
               QueryCriteria2 itemsParams = new QueryCriteria2();
               Object[][] d = new Object[0][0];
               Object[][] d1 = new Object[0][0];
               Object[][] d2 = new Object[0][0];

               if (!includechildgroups.isSelected()) {
                  if (forGroup != null && !forGroup.__getCname().equals("")) {
                     itemsParams.and(new QueryParameter(Context.getItem(), forGroup.getDbIdentity() + "ids", forGroup.__getIDS(), QueryParameter.EQUALS));
                  }
               } else {
                  if (forGroup != null && !forGroup.__getCname().equals("")) {
                     List<Group> gs = forGroup.getChildGroups();
                     QueryParameter[] params = new QueryParameter[gs.size()];
                     QueryParameter param1 = (new QueryParameter(Context.getItem(), forGroup.getDbIdentity() + "ids", forGroup.__getIDS(), QueryParameter.EQUALS));
                     if (gs.size() >= 1) {
                        for (int i = 0; i < gs.size(); i++) {
                           Group group = gs.get(i);
                           params[i] = (new QueryParameter(Context.getItem(), group.getDbIdentity() + "ids", group.__getIDS(), QueryParameter.EQUALS));
                        }
                        //System.err.println(params);
                        itemsParams.or(param1, params);
                     } else {
                        itemsParams.and(param1);
                     }
                  }
               }

               if (forUser != null && !forUser.__getCname().equals("")) {
                  itemsParams.and(new QueryParameter(Context.getItem(), "intaddedby", forUser.__getIDS(), QueryParameter.EQUALS));
               }


               ArrayList<QueryParameter> l = new ArrayList<QueryParameter>();

               for (int i = 0; i < jList1.getSelectedValues().length; i++) {
                  l.add(new QueryParameter(Context.getItem(), "accountsids", ((Account) jList1.getSelectedValues()[i]).__getIDS(), QueryParameter.EQUALS));
               }
               if (l.size() > 1) {
                  itemsParams.or(l);
               } else {
                  itemsParams.and(l.toArray(new QueryParameter[0]));
               }

               if (dataOwner != null) {
                  itemsParams.and(new QueryParameter(dataOwner.getContext(), "ids", dataOwner.__getIDS(), QueryParameter.EQUALS));
               }

               boolean additional = true;
               if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_PAID)) {
                  itemsParams.and(new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_PAID, QueryParameter.EQUALS));
               } else if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_UNPAID)) {
//                        additional = false;
                  itemsParams.or(
                        new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_QUEUED, QueryParameter.EQUALS),
                        new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_PAUSED, QueryParameter.EQUALS),
                        new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_IN_PROGRESS, QueryParameter.EQUALS),
                        new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_FINISHED, QueryParameter.EQUALS));
               } else if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_CANCELLED)) {
//                        additional = false;
                  itemsParams.and(new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_CANCELLED, QueryParameter.EQUALS));
               }

               try {
                  Context c = Context.getItem();
                  itemsParams.setOrder("accountsids", true);
                  c.addReference(Context.getGroup());
                  c.addReference(Context.getAccounts());
                  c.addReference(Context.getContact());

                  try {
                     String datecriterium = "dateadded";
                     if (bydateend.isSelected()) {
                        datecriterium = "dateend";
                     }
                     d = QueryHandler.instanceOf().clone(c).select(Context.DETAILS_JOURNAL.replace("{date}", datecriterium), itemsParams, timeframeChooser1.getTime(), datecriterium).getData();
                  } catch (NodataFoundException nodataFoundException) {
                     d = new Object[0][17];
                  }

                  DatabaseObject.Entity<?, ?>[] es = new DatabaseObject.Entity<?, ?>[d.length];
                  for (int i = 0; i < d.length; i++) {
                     es[i] = new DatabaseObject.Entity<Context, Integer>(Context.getItem(), Integer.valueOf(d[i][0].toString()));
                  }

                  d = ArrayUtilities.replaceColumn(d, 0, es);
                  if (!additional) {
                     d = ArrayUtilities.removeRows(d, 11, Item.STATUS_PAID);
                  }
               } catch (Exception ex) {
                  Log.Debug(ex);
               }
               if (dataOwner == null && additional) {
                  try {
                     Context c = Context.getExpense();
                     itemsParams.setOrder("dateadded", true);
                     c.addReference(Context.getGroup());
                     c.addReference(Context.getAccounts());
                     QueryCriteria2 expensesParams = new QueryCriteria2();
                     ArrayList<QueryParameter> l1 = new ArrayList<QueryParameter>();
                     for (int i = 0; i < jList1.getSelectedValues().length; i++) {
                        l1.add(new QueryParameter(Context.getExpense(), "accountsids", ((Account) jList1.getSelectedValues()[i]).__getIDS(), QueryParameter.EQUALS));
                     }
                     if (l1.size() > 1) {
                        expensesParams.or(l1);
                     } else {
                        expensesParams.and(l1.toArray(new QueryParameter[0]));
                     }
                     if (forUser != null && !forUser.__getCname().equals("")) {
                        expensesParams.and(new QueryParameter(c, "intaddedby", forUser.__getIDS(), QueryParameter.EQUALS));
                     }
                     if (!includechildgroups.isSelected()) {
                        if (forGroup != null && !forGroup.__getCname().equals("")) {
                           expensesParams.and(new QueryParameter(c, forGroup.getDbIdentity() + "ids", forGroup.__getIDS(), QueryParameter.EQUALS));
                        }

                     } else {
                        if (forGroup != null) {
                           List<Group> gs = forGroup.getChildGroups();
                           QueryParameter[] params = new QueryParameter[gs.size()];
                           QueryParameter params1 = (new QueryParameter(c, forGroup.getDbIdentity() + "ids", forGroup.__getIDS(), QueryParameter.EQUALS));
                           if (gs.size() >= 1) {
                              for (int i = 0; i < gs.size(); i++) {
                                 Group group = gs.get(i);
                                 params[i] = (new QueryParameter(c, group.getDbIdentity() + "ids", group.__getIDS(), QueryParameter.EQUALS));
                              }
                              itemsParams.or(params1, params);
                           } else {
                              expensesParams.and(params1);
                           }
                        }
                     }
                     if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_PAID)) {
                        expensesParams.and(new QueryParameter(c, "ispaid", true, QueryParameter.EQUALS));
                     } else if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_UNPAID)) {
                        expensesParams.and(new QueryParameter(c, "ispaid", false, QueryParameter.EQUALS));
                     }

                     try {
                        String datecriterium = "dateadded";
                        if (expbydateend.isSelected()) {
                           datecriterium = "dateend";
                        }
                        d1 = QueryHandler.instanceOf().clone(c).select(Context.DETAILS_JOURNAL3.replace("{date}", datecriterium), expensesParams, timeframeChooser1.getTime(), datecriterium).getData();
                     } catch (NodataFoundException nodataFoundException) {
                        d1 = new Object[0][17];
                     }

//                            try {
//                                d1 = QueryHandler.instanceOf().clone(c).select(Context.DETAILS_JOURNAL3, expensesParams, timeframeChooser1.getTime()).getData();
//                            } catch (NodataFoundException nodataFoundException) {
//                                d1 = new Object[0][14];
//                            }
                     for (int i = 0; i < d1.length; i++) {
                        d1[i][10] = Expense.TYPE_EXPENSE;

                        if (TypeConversion.stringToBoolean(String.valueOf(d1[i][11]))) {
                           d1[i][11] = 1000;
                        } else {
                           d1[i][11] = 2000;
                        }
                     }

                     DatabaseObject.Entity<?, ?>[] es = new DatabaseObject.Entity<?, ?>[d1.length];
                     for (int i = 0; i < d1.length; i++) {
                        es[i] = new DatabaseObject.Entity<Context, Integer>(Context.getExpense(), Integer.valueOf(d1[i][0].toString()));
                     }
                     d1 = ArrayUtilities.replaceColumn(d1, 0, es);

                  } catch (Exception ex) {
                     Log.Debug(this, ex);
                  }

                  try {
                     Context c = Context.getRevenue();
                     itemsParams.setOrder("dateadded", true);
                     c.addReference(Context.getGroup());
                     c.addReference(Context.getAccounts());
                     QueryCriteria2 revenuesParams = new QueryCriteria2();
                     ArrayList<QueryParameter> l2 = new ArrayList<QueryParameter>();
                     for (int i = 0; i < jList1.getSelectedValues().length; i++) {
                        l2.add(new QueryParameter(Context.getRevenue(), "accountsids", ((Account) jList1.getSelectedValues()[i]).__getIDS(), QueryParameter.EQUALS));
                     }
                     if (l2.size() > 1) {
                        revenuesParams.or(l2);
                     } else {
                        revenuesParams.and(l2.toArray(new QueryParameter[0]));
                     }
                     if (forUser != null && !forUser.__getCname().equals("")) {
                        revenuesParams.and(new QueryParameter(c, "intaddedby", forUser.__getIDS(), QueryParameter.EQUALS));
                     }
                     if (!includechildgroups.isSelected()) {
                        if (forGroup != null && !forGroup.__getCname().equals("")) {
                           revenuesParams.and(new QueryParameter(c, forGroup.getDbIdentity() + "ids", forGroup.__getIDS(), QueryParameter.EQUALS));
                        }
                     } else {
                        if (forGroup != null) {
                           List<Group> gs = forGroup.getChildGroups();
                           QueryParameter[] params = new QueryParameter[gs.size()];
                           QueryParameter params1 = (new QueryParameter(c, forGroup.getDbIdentity() + "ids", forGroup.__getIDS(), QueryParameter.EQUALS));
                           if (gs.size() >= 1) {
                              for (int i = 0; i < gs.size(); i++) {
                                 Group group = gs.get(i);
                                 params[i] = (new QueryParameter(c, group.getDbIdentity() + "ids", group.__getIDS(), QueryParameter.EQUALS));
                              }
                              revenuesParams.or(params1, params);
                           } else {
                              revenuesParams.and(params1);
                           }
                        }
                     }
//                            if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_PAID)) {
//                                revenuesParams.and(new QueryParameter(c, "intstatus", Item.STATUS_PAID, QueryParameter.EQUALS));
//                            } else if (statusc.getComboBox().getSelectedItem().equals(Messages.STATUS_UNPAID)) {
////                        additional = false;
//                                revenuesParams.or(new QueryParameter(Context.getExpense(), "intstatus", Item.STATUS_PAID, QueryParameter.EQUALS),
//                                        new QueryParameter(c, "intstatus", Item.STATUS_FINISHED, QueryParameter.EQUALS));
//                            }
                     try {
                        d2 = QueryHandler.instanceOf().clone(c).select(Context.DETAILS_JOURNAL2, revenuesParams, timeframeChooser1.getTime()).getData();
                     } catch (NodataFoundException nodataFoundException) {
                        d2 = new Object[0][17];
                     }
                     for (int i = 0; i < d2.length; i++) {
                        d2[i][10] = Revenue.TYPE_REVENUE;
                        d2[i][11] = Item.STATUS_PAID;//revenues are currently always paid :-/
                     }

                     DatabaseObject.Entity<?, ?>[] es = new DatabaseObject.Entity<?, ?>[d2.length];
                     for (int i = 0; i < d2.length; i++) {
                        es[i] = new DatabaseObject.Entity<Context, Integer>(Context.getRevenue(), Integer.valueOf(d2[i][0].toString()));
                     }
                     d2 = ArrayUtilities.replaceColumn(d2, 0, es);

                  } catch (Exception ex) {
                     Log.Debug(this, ex);
                  }

                  d = ArrayUtilities.merge(ArrayUtilities.merge(d, d1), d2);
               }
               d = parse(d);
               jTable1.setModel(new MPTableModel(d, Headers.JOURNAL.getValue(),
                     new Class[]{Entity.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}));
               TableFormat.stripColumn(jTable1, DatabaseObject.Entity.class);
               count.setText("" + d.length);
            } catch (Exception e) {
               Log.Debug(this, e);
            }
         }
      };
      SwingUtilities.invokeLater(runnable);
   }

   @Override
   public void refresh() {
      refresh(null, null);
   }

   @Override
   public void flush() {
      jTable1.setModel(new DefaultTableModel());
   }

   private void setPopup() {
      TablePopUp tablePopUp = new TablePopUp(jTable1,
            new String[]{Messages.DELETE.toString(),
               null, Messages.RELOAD.getValue(),
               null, Messages.DTA_CREATE.getValue(),
               Messages.PDF_CREATE.getValue(),
               Messages.ODT_CREATE.getValue(),
               null, Messages.SET_STATUS_PAID.getValue(),},
            new ActionListener[]{new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (Popup.Y_N_dialog(Messages.REALLY_DELETE2 + " (" + jTable1.getSelectedRowCount() + ")")) {
                  int[] rows = jTable1.getSelectedRows();
                  for (int i = 0; i < rows.length; i++) {
                     try {
                        int index = jTable1.convertRowIndexToModel(rows[i]);
                        DatabaseObject obj = DatabaseObject.getObject((DatabaseObject.Entity<?, ?>) jTable1.getModel().getValueAt(index, 0));
                        obj.delete();
                        jTable1.getSelectionModel().removeSelectionInterval(rows[i] - 1, rows[i]);
                     } catch (NodataFoundException ex) {
                        Log.Debug(ex);
                     } catch (Exception exc) {
                        Log.Debug(exc);
                        Popup.error(exc);
                     }
                  }
                  setData();
               }
            }
         }, null, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setData();
            }
         },
               null,
               new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dta();
            }
         },
               new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               pdf();
            }
         },
               new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               odt();
            }
         },
               null,
               new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (Popup.Y_N_dialog(Messages.REALLY_CHANGE2 + " (" + jTable1.getSelectedRowCount() + ")")) {
                  int[] rows = jTable1.getSelectedRows();
                  for (int i = 0; i < rows.length; i++) {
                     try {
                        int index = jTable1.convertRowIndexToModel(rows[i]);
                        DatabaseObject obj = DatabaseObject.getObject((DatabaseObject.Entity<?, ?>) jTable1.getModel().getValueAt(index, 0));
                        if (obj instanceof Item) {
                           Item dbi = (Item) obj;
                           if (dbi.__getIntstatus() != Item.STATUS_PAID) {
                              dbi.setIntstatus(Item.STATUS_PAID);
                              dbi.save();
                           }
                        } else if (obj instanceof Expense) {
                           Expense dbi = (Expense) obj;
                           if (!dbi.__getIspaid()) {
                              dbi.setIspaid(true);
                              dbi.save();
                           }
                        }
                        jTable1.getSelectionModel().removeSelectionInterval(rows[i] - 1, rows[i]);
                     } catch (NodataFoundException ex) {
                        Log.Debug(ex);
                     } catch (Exception exc) {
                        Log.Debug(exc);
                        Popup.error(exc);
                     }
                  }
                  setData();
               }
            }
         }
            });


   }

   private void setData() {
      try {
         Group g = null;
         User u = null;

         if (groups.getSelectedItem().isValid()) {
            try {
               g = ((Group) Group.getObject(Context.getGroup(), Integer.valueOf(groups.getSelectedItem().getId())));
            } catch (Exception nodataFoundException) {
               g = null;
            }
         }
         try {
            if (users.getSelectedItem().isValid()) {
               u = ((User) User.getObject(Context.getUser(), Integer.valueOf(users.getSelectedItem().getId())));
            }
         } catch (Exception nodataFoundException) {
            u = null;
         }
         refresh(g, u);
      } catch (Exception ignore) {
         refresh(null, null);
      }
   }

   private void preview() {
      PreviewPanel pr;
      if (journalOrContactTemplate != null) {
         pr = new PreviewPanel();
         pr.setDataOwner(dataOwner);
         TreeMap<String, Object> map = new TreeMap<String, Object>();
         map.put("journal.netvalue", volumeNetto.getText());
         map.put("journal.taxvalue", taxVolume.getText());
         map.put("journal.grosvalue", volumeBrutto.getText());
         map.put("journal.revenuevalue", revenueNetto.getText());
         map.put("journal.discountvalue", volumeDiscount.getText());
         map.put("journal.datenow", DateConverter.getDefDateString(new Date()));
         map.put("journal.datefrom", DateConverter.getDefDateString(timeframeChooser1.getTime().getStart()));
         map.put("journal.dateto", DateConverter.getDefDateString(timeframeChooser1.getTime().getEnd()));
         if (groups.getSelectedItem().isValid()) {
            try {
               Group g = (Group) Group.getObject(Context.getGroup(), Integer.valueOf(groups.getSelectedItem().getId()));
               map.put("journal.group", g.__getCname());
               map.put("journal.childgroups", String.valueOf(g.getChildGroups()));
            } catch (Exception nodataFoundException) {
            }
         }
         try {
            map.put("journal.user", users.getSelectedValue());
            map.put("journal.accounts", Arrays.asList(jList1.getSelectedValues()).toString());
         } catch (Exception e) {
            Log.Debug(e);
         }
         Log.Debug(this, map.keySet());
         Log.Debug(this, map.values());

         journalOrContactTemplate.injectData(map);
         MPTableModel xx = (MPTableModel) jTable1.getModel();
         Object[][] xxd = xx.getData();
         Object[][] d = new Object[xxd.length][];
         for (int i = 0; i < xxd.length; i++) {
            Object[] objects = xxd[i];
            Object[] nd = new Object[objects.length];
            for (int j = 1; j < objects.length; j++) {
               Object object = objects[j];
               nd[j - 1] = object;
            }
            d[i] = nd;
         }
         Log.Debug(this, xx.getColumnIdentifiers());
         journalOrContactTemplate.injectTable(TableHandler.KEY_TABLE + 1, new MPTableModel(d));
         if (GlobalSettings.getBooleanProperty("org.openyabs.exportproperty.journalasodt")) {
            new Job(Export.createFile(journalOrContactTemplate, dataOwner), pr).execute();
         } else {
            new Job(Export.sourceFile(journalOrContactTemplate, dataOwner), pr).execute();
         }
      } else {
         Popup.notice(Messages.NO_TEMPLATE_LOADED + " (" + mpv5.db.objects.User.getCurrentUser() + ")");
      }
   }

   private void loadTemplate() {
      Runnable runnable = new Runnable() {
         public void run() {
            if (dataOwner != null) {
               journalOrContactTemplate = TemplateHandler.loadTemplate(Long.valueOf(dataOwner.templateGroupIds()), Constants.TYPE_CONTACT);
               jButton4.setEnabled(true);
            } else {
               journalOrContactTemplate = TemplateHandler.loadTemplate(Long.valueOf(User.getCurrentUser().__getGroupsids()), Constants.TYPE_JOURNAL);
               jButton4.setEnabled(true);
            }
         }
      };
      new Thread(runnable).start();
   }

   private void dta() {
      if (jTable1.getSelectedRowCount() < 1) {
         Popup.notice(Messages.SELECT_AN_INVOICE);

      } else {
         ArrayList<Item> items = new ArrayList<Item>();
         for (int i = 0; i < jTable1.getSelectedRows().length; i++) {
            try {
               int index = jTable1.convertRowIndexToModel(jTable1.getSelectedRows()[i]);
               DatabaseObject obj = DatabaseObject.getObject((DatabaseObject.Entity<?, ?>) jTable1.getModel().getValueAt(index, 0));
               if (obj.getContext().equals(Context.getItem())) {
                  Item item = (Item) obj;
                  if (item.__getIntstatus() != Item.STATUS_PAID) {
                     items.add(item);
                  }
               }
            } catch (NodataFoundException ex) {
               Log.Debug(ex);
            }
         }

         HashMap<String, Object> map = new HashMap<String, Object>();
         for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            map.put(item.__getCnumber(), item);
         }

         DialogForFile d = new DialogForFile(DialogForFile.FILES_ONLY);
         DTAFile dta = new DTAFile(map);
         Job job = new Job(dta, d, "DTAUS " + Messages.SAVED);
         job.execute();

      }
   }

   private void pdf() {
      if (jTable1.getSelectedRowCount() < 1) {
         Popup.notice(Messages.SELECT_AN_INVOICE);

      } else {
         ArrayList<Templateable> items = new ArrayList<Templateable>();
         for (int i = 0; i < jTable1.getSelectedRows().length; i++) {
            try {
               int index = jTable1.convertRowIndexToModel(jTable1.getSelectedRows()[i]);
               DatabaseObject obj = DatabaseObject.getObject((DatabaseObject.Entity<?, ?>) jTable1.getModel().getValueAt(index, 0));
               if (obj instanceof Templateable) {
                  Templateable item = (Templateable) obj;
                  items.add(item);
               }
            } catch (NodataFoundException ex) {
               Log.Debug(ex);
            }
         }

         List<Waitable> files = new ArrayList<Waitable>();
         for (int i = 0; i < items.size(); i++) {
            try {
               final Templateable item = items.get(i);
               if (TemplateHandler.loadTemplate(item) == null) {
                  Popup.warn(Messages.NO_TEMPLATE_DEFINDED + "\n" + TemplateHandler.getName(item.templateType())
                        + "\n" + Messages.IN_GROUP + " " + DatabaseObject.getObject(Context.getGroup(), ((DatabaseObject) item).__getGroupsids()));
               } else {
                  files.add(Export.createFile(item.getFormatHandler().toUserString(), TemplateHandler.loadTemplate(item), ((DatabaseObject) item)));
               }
            } catch (Exception nodataFoundException) {
               Log.Debug(nodataFoundException);
            }
         }

         if (!files.isEmpty()) {
            DialogForFile d = new DialogForFile(DialogForFile.DIRECTORIES_ONLY);
            Job job = new Job(files, d, files.size() + " PDF " + Messages.SAVED);
            job.execute();
         }
      }
   }

   private void odt() {
      if (jTable1.getSelectedRowCount() < 1) {
         Popup.notice(Messages.SELECT_AN_INVOICE);

      } else {
         ArrayList<Templateable> items = new ArrayList<Templateable>();
         for (int i = 0; i < jTable1.getSelectedRows().length; i++) {
            try {
               int index = jTable1.convertRowIndexToModel(jTable1.getSelectedRows()[i]);
               DatabaseObject obj = DatabaseObject.getObject((DatabaseObject.Entity<?, ?>) jTable1.getModel().getValueAt(index, 0));
               if (obj instanceof Templateable) {
                  Templateable item = (Templateable) obj;
                  items.add(item);
               }
            } catch (Exception ex) {
               Log.Debug(ex);
            }
         }

         List<Waitable> files = new ArrayList<Waitable>();
         for (int i = 0; i < items.size(); i++) {
            try {
               final Templateable item = items.get(i);
               if (TemplateHandler.loadTemplate(item) == null) {
                  Popup.warn(Messages.NO_TEMPLATE_DEFINDED + "\n" + TemplateHandler.getName(item.templateType())
                        + "\n" + Messages.IN_GROUP + " " + DatabaseObject.getObject(Context.getGroup(), ((DatabaseObject) item).__getGroupsids()));
               } else {
                  files.add(Export.sourceFile(item.getFormatHandler().toUserString(), TemplateHandler.loadTemplate(item), ((DatabaseObject) item)));
               }
            } catch (Exception nodataFoundException) {
               Log.Debug(nodataFoundException);
            }
         }

         if (!files.isEmpty()) {
            DialogForFile d = new DialogForFile(DialogForFile.DIRECTORIES_ONLY);

            Job job = new Job(files, d, files.size() + " ODT " + Messages.SAVED);
            job.execute();
         }
      }
   }

   private void filter() {
      if (filterinvoice.isSelected()) {
         QueryCriteria2 p = new QueryCriteria2();
         p.and(new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_PAID, QueryParameter.NOTEQUAL));
         p.and(new QueryParameter(Context.getItem(), "inttype", Item.TYPE_BILL, QueryParameter.EQUALS));
         jTree1.setModel(new MPTreeModel(dataOwner, p));
      } else if (filteroffers.isSelected()) {
         QueryCriteria2 p = new QueryCriteria2();
         p.and(new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_FINISHED, QueryParameter.NOTEQUAL));
         p.and(new QueryParameter(Context.getItem(), "inttype", Item.TYPE_OFFER, QueryParameter.EQUALS));
         jTree1.setModel(new MPTreeModel(dataOwner, p));
      } else if (filterorders.isSelected()) {
         QueryCriteria2 p = new QueryCriteria2();
         p.and(new QueryParameter(Context.getItem(), "intstatus", Item.STATUS_FINISHED, QueryParameter.NOTEQUAL));
         p.and(new QueryParameter(Context.getItem(), "inttype", Item.TYPE_ORDER, QueryParameter.EQUALS));
         jTree1.setModel(new MPTreeModel(dataOwner, p));
      } else if (filternone.isSelected()) {
         jTree1.setModel(new MPTreeModel(dataOwner, null));
      }
   }
}
