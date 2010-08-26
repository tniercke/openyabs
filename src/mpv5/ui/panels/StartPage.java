package mpv5.ui.panels;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.ListModel;
import javax.swing.table.TableCellRenderer;
import mpv5.Main;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.objects.Contact;
import mpv5.globals.Constants;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Search2;
import mpv5.ui.frames.MPView;
import mpv5.utils.files.FileReaderWriter;
import mpv5.utils.images.MPIcon;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.TableFormat;

/**
 *
 * 
 */
public class StartPage extends javax.swing.JPanel {

    private java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
    /** Creates new form ListPanel */
    public StartPage() {
        initComponents();

        TableFormat.hideHeader(licensetable);
        setName("startpage");
        jTextArea1.setText(Messages.START_MESSAGE.getValue().replace("*", ""));

        Runnable runnable = new Runnable() {

            public void run() {
                fillFiles();
            }
        };
        new Thread(runnable).start();

        Runnable runnable1 = new Runnable() {

            public void run() {
                syst.setModel(getSysInfo());
            }
        };
        new Thread(runnable1).start();


        contacts();
        items();

        jTaskPaneGroup2.setTitle("UNDER CONSTRUCTION");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     *
     *  DatabaseObject d = DatabaseObject.getObject(Context.getCustomer());
    ((mpv5.db.objects.Contact) d).setisCustomer(true);
    MPView.getIdentifierView().addTab(d, Messages.NEW_CUSTOMER);
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTaskPane1 = new com.l2fprod.common.swing.JTaskPane();
        jTaskPaneGroup1 = new com.l2fprod.common.swing.JTaskPaneGroup();
        jTaskPaneGroup2 = new com.l2fprod.common.swing.JTaskPaneGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        licensetable = new  mpv5.ui.misc.MPTable(this) {
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
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        images = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        syst = new javax.swing.JList();

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
        setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("StartPage.border.title"))); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        jTaskPane1.setName("jTaskPane1"); // NOI18N
        com.l2fprod.common.swing.PercentLayout percentLayout4 = new com.l2fprod.common.swing.PercentLayout();
        percentLayout4.setGap(14);
        percentLayout4.setOrientation(1);
        jTaskPane1.setLayout(percentLayout4);

        jTaskPaneGroup1.setCollapsable(false);
        jTaskPaneGroup1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/evolution-contacts.png"))); // NOI18N
        jTaskPaneGroup1.setName("jTaskPaneGroup1"); // NOI18N
        com.l2fprod.common.swing.PercentLayout percentLayout5 = new com.l2fprod.common.swing.PercentLayout();
        percentLayout5.setGap(2);
        percentLayout5.setOrientation(1);
        jTaskPaneGroup1.getContentPane().setLayout(percentLayout5);
        jTaskPane1.add(jTaskPaneGroup1);

        jTaskPaneGroup2.setCollapsable(false);
        jTaskPaneGroup2.setName("jTaskPaneGroup2"); // NOI18N
        com.l2fprod.common.swing.PercentLayout percentLayout6 = new com.l2fprod.common.swing.PercentLayout();
        percentLayout6.setGap(2);
        percentLayout6.setOrientation(1);
        jTaskPaneGroup2.getContentPane().setLayout(percentLayout6);
        jTaskPane1.add(jTaskPaneGroup2);

        jPanel4.add(jTaskPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(bundle.getString("StartPage.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(bundle.getString("StartPage.jTextArea1.text")); // NOI18N
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab(bundle.getString("StartPage.jScrollPane1.TabConstraints.tabTitle"), jScrollPane1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setText(bundle.getString("StartPage.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText(bundle.getString("StartPage.jTextArea2.text")); // NOI18N
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea2.setName("jTextArea2"); // NOI18N
        jScrollPane3.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.PAGE_END);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        licensetable.setAutoCreateRowSorter(true);
        licensetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        licensetable.setName("licensetable"); // NOI18N
        jScrollPane2.setViewportView(licensetable);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(bundle.getString("StartPage.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel3.setText(bundle.getString("StartPage.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setName("jScrollPane5"); // NOI18N

        images.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        images.setFont(new java.awt.Font("Dialog", 0, 12));
        images.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "KDE Crystal Diamond Icons set compiled by Paolo Campitelli:", " ", "(Based on \"Crystal Project\", \"Human Kanpio Mod\", \"Vista Inspirate\",  \"Crystal Clear\", \"Nuove XT\", \"OSX\" , \"SnowIsh\", \"Debian Icon\", \"Firefox Alternative\" )", " ", " ", "YaBS Logo by  Jean-Christoph von Oertzen" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        images.setName("images"); // NOI18N
        jScrollPane5.setViewportView(images);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText(bundle.getString("StartPage.jTextArea3.text")); // NOI18N
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane4.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
                        .addGap(13, 13, 13))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab(bundle.getString("StartPage.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        syst.setName("syst"); // NOI18N
        jScrollPane6.setViewportView(syst);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("StartPage.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList images;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.l2fprod.common.swing.JTaskPane jTaskPane1;
    private com.l2fprod.common.swing.JTaskPaneGroup jTaskPaneGroup1;
    private com.l2fprod.common.swing.JTaskPaneGroup jTaskPaneGroup2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTable licensetable;
    private javax.swing.JList syst;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("unchecked")
    private ListModel getSysInfo() {

        DefaultListModel m = new DefaultListModel();
        m.addElement("YaBS Version: " + Constants.VERSION + " " + Constants.TITLE);
        m.addElement("Database: " + LocalSettings.getProperty(LocalSettings.DBPATH));
        m.addElement("Database type: " + LocalSettings.getProperty(LocalSettings.DBTYPE));
        m.addElement("Database driver: " + LocalSettings.getProperty(LocalSettings.DBDRIVER));
        m.addElement("Database minimal version: " + Constants.DATABASE_VERSION);
        m.addElement("Cache directory: " + LocalSettings.getProperty(LocalSettings.CACHE_DIR));
        m.addElement("Use OpenOffice: " + LocalSettings.getProperty(LocalSettings.OFFICE_USE));
        m.addElement("");
        m.addElement("");

        Properties sysprops = System.getProperties();
        Enumeration propn = sysprops.propertyNames();
        List v = Collections.list(propn);
        Collections.sort(v);
        Enumeration p = Collections.enumeration(v);

        while (p.hasMoreElements()) {
            String propname = (String) p.nextElement();
            m.addElement("System env: " + propname.toLowerCase() + ": " + System.getProperty(propname));
        }

        return m;
    }

    private void fillFiles() {
        if (Main.INSTANTIATED) {
            final DefaultListModel m = new DefaultListModel();
            Runnable runnable = new Runnable() {

                public void run() {
                    try {
                        try {
                            File licenses = new File("licenses-list.txt");
                            if (!licenses.exists()) {
                                InputStream inputStream = Main.class.getResourceAsStream("/mpv5/resources/license/licenses-list");
                                OutputStream out = new FileOutputStream(licenses);
                                byte buf[] = new byte[1024];
                                int len;
                                while ((len = inputStream.read(buf)) > 0) {
                                    out.write(buf, 0, len);
                                }
                                out.close();
                                inputStream.close();
                            }

                            String[] data = (new FileReaderWriter(licenses).readLines());
                            Arrays.sort(data);
                            String[][] model = new String[data.length][];
                            for (int i = 0; i < data.length; i++) {
                                String string = data[i];
                                model[i] = string.split(";");
                            }

                            licensetable.setModel(new MPTableModel(model));
                        } catch (Exception ex) {
                            Log.Debug(this, ex.getLocalizedMessage());
                        }

                        DefaultListModel d = new DefaultListModel();
                        ListModel list = images.getModel();

                        for (int i = 0; i < list.getSize(); i++) {
                            d.addElement(list.getElementAt(i));
                        }
                        d.addElement(new MPIcon("/mpv5/resources/images/icon.png"));
                        images.setModel(d);
                    } catch (Exception exception) {
                        Log.Debug(exception);
                    }
                }
            };
            new Thread(runnable).start();
        }

    }

    private void contacts() {
        jTaskPaneGroup1.setTitle(Messages.TASKS_CONTACTS.getValue());

        jTaskPaneGroup1.add(new AbstractAction(Messages.CONTACTS_LIST.getValue()) {

            public void actionPerformed(ActionEvent e) {
                DatabaseObject d = DatabaseObject.getObject(Context.getContact());
                ContactsList t = MPView.getClisttab(Context.getContact());
                t.showType((Contact) d);
                MPView.identifierView.addOrShowTab(t, Messages.CONTACTS_LIST.toString());
            }
        });

        jTaskPaneGroup1.add(new AbstractAction("Add contact...") {

            public void actionPerformed(ActionEvent e) {
                DatabaseObject d = DatabaseObject.getObject(Context.getCustomer());
                ((mpv5.db.objects.Contact) d).setisCustomer(true);
                MPView.getIdentifierView().addTab(d, Messages.NEW_CUSTOMER);
            }
        });

        jTaskPaneGroup1.add(new AbstractAction("Search contact...") {

            public void actionPerformed(ActionEvent e) {
                DatabaseObject d = Search2.showSearchFor(Context.getContact());
                if (d != null) {
                    MPView.getIdentifierView().addTab(d);
                }
            }
        });
    }

    private void items() {
         jTaskPaneGroup1.setTitle(Messages.TASKS_INVOICES.getValue());

        jTaskPaneGroup1.add(new AbstractAction(Messages.CONTACTS_LIST.getValue()) {

            public void actionPerformed(ActionEvent e) {
                DatabaseObject d = DatabaseObject.getObject(Context.getContact());
                ContactsList t = MPView.getClisttab(Context.getContact());
                t.showType((Contact) d);
                MPView.identifierView.addOrShowTab(t, Messages.CONTACTS_LIST.toString());
            }
        });

        jTaskPaneGroup1.add(new AbstractAction("Add contact...") {

            public void actionPerformed(ActionEvent e) {
                DatabaseObject d = DatabaseObject.getObject(Context.getCustomer());
                ((mpv5.db.objects.Contact) d).setisCustomer(true);
                MPView.getIdentifierView().addTab(d, Messages.NEW_CUSTOMER);
            }
        });

        jTaskPaneGroup1.add(new AbstractAction("Search contact...") {

            public void actionPerformed(ActionEvent e) {
                DatabaseObject d = Search2.showSearchFor(Context.getContact());
                if (d != null) {
                    MPView.getIdentifierView().addTab(d);
                }
            }
        });
    }
}
