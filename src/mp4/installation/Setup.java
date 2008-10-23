/*
 * mpInstaller.java
 *
 * Created on 25. Oktober 2007, 19:21
 */
/*
 *  This file is part of MP by anti43 /GPL.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mp4.installation;


import mp4.datenbank.verbindung.Conn;
import java.io.IOException;
import java.awt.Cursor;
import java.io.File;
import javax.swing.JFileChooser;
import mp4.datenbank.verbindung.ConnectionTypeHandler;
import mp4.einstellungen.Einstellungen;
import mp4.einstellungen.Programmdaten;
import mp4.globals.Constants;
import mp4.globals.Strings;
import mp4.utils.ui.Position;
import mp4.items.visual.Popup;
import mp4.installation.DesktopIcon;
import mp4.logs.*;
import mp4.frames.LicenseWindow;
import mp4.installation.Verzeichnisse;

import mp4.main.Main;
import mp4.utils.files.FileDirectoryHandler;
import mp4.utils.ui.inputfields.PAnelUtils;

/**
 * @author  anti43
 */
public class Setup extends javax.swing.JFrame implements Constants, Strings {

    private static Setup instance;
    private static Verzeichnisse install_dirs;

    public static Verzeichnisse getInstall_dirs() {
        return install_dirs;
    }
    private JFileChooser fc;

    /**
     * 
     * @return Instance of silent setup (no gui)
     */
    public static Setup instanceOf() {
        if (instance == null) {
            return new Setup(true);
        } else {
            return instance;
        }
    }
    private boolean finished = false;

    /** Creates new form mpInstaller
     */
    public Setup() {

        instance = this;
        install_dirs = new Verzeichnisse();
        initComponents();

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setSelectedFile(new File(Main.APP_DIR));

        File public_dir;

        try {
            public_dir = new File(Main.APP_DIR);
            backuppathtf.setText(public_dir.getCanonicalPath() + File.separator + BACKUPS_SAVE_DIR);
            pdfpathtf.setText(public_dir.getCanonicalPath() + File.separator + PDF);
        } catch (Exception iOException) {
            backuppathtf.setText(USER_HOME);
            pdfpathtf.setText(USER_HOME);
        }

        new Position(this);
        this.setVisible(true);
        Log.Debug(this,JAVA_VERSION);
    }

    /**
     * If silent is set to true, no setup gui is loaded.
     * @param silent
     */
    public Setup(boolean silent) {
        if (silent) {
            try {
                Verzeichnisse.buildPath();
                Verzeichnisse.createDirs();
                install_dirs = new Verzeichnisse();
            } catch (IOException ex) {
                Log.Debug(this,ex);
            }
        } else {
            new Setup();
        }
    }

    public String getBrowser() {
        if (Main.IS_WINDOWS) {
            return "C:\\Programme\\Internet Explorer\\iexplore.exe";
        } else {
            return "/usr/bin/firefox";
        }
    }
    

    public String getPdfProgramm() {   
        if (Main.IS_WINDOWS) {
            return "C:\\Program Files\\Adobe\\Reader 9.0\\Reader\\AcroRd32.exe";
        } else {
            return "/opt/kde3/bin/kpdf";
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
private void initComponents() {

buttonGroup1 = new javax.swing.ButtonGroup();
jPanel1 = new javax.swing.JPanel();
jPanel2 = new javax.swing.JPanel();
jScrollPane1 = new javax.swing.JScrollPane();
jTextArea1 = new javax.swing.JTextArea();
jLabel2 = new javax.swing.JLabel();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jLabel3 = new javax.swing.JLabel();
jButton3 = new javax.swing.JButton();
jLabel1 = new javax.swing.JLabel();
jCheckBox2 = new javax.swing.JCheckBox();
jPanel3 = new javax.swing.JPanel();
jLabel7 = new javax.swing.JLabel();
jTextField1 = new javax.swing.JTextField();
jLabel8 = new javax.swing.JLabel();
jTextField2 = new javax.swing.JTextField();
jTextField3 = new javax.swing.JTextField();
jLabel9 = new javax.swing.JLabel();
jRadioButton1 = new javax.swing.JRadioButton();
jRadioButton2 = new javax.swing.JRadioButton();
jRadioButton3 = new javax.swing.JRadioButton();
jLabel10 = new javax.swing.JLabel();
jTextField4 = new javax.swing.JTextField();
jButton6 = new javax.swing.JButton();
jCheckBox3 = new javax.swing.JCheckBox();
jPanel4 = new javax.swing.JPanel();
jLabel5 = new javax.swing.JLabel();
pdfpathtf = new javax.swing.JTextField();
jButton5 = new javax.swing.JButton();
jButton4 = new javax.swing.JButton();
backuppathtf = new javax.swing.JTextField();
jLabel4 = new javax.swing.JLabel();
jCheckBox1 = new javax.swing.JCheckBox();
jLabel6 = new javax.swing.JLabel();

setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
setTitle("MP Installation");
setResizable(false);

jPanel1.setBackground(new java.awt.Color(255, 255, 255));

jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Installation"));

jScrollPane1.setBorder(null);
jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
jScrollPane1.setFocusable(false);
jScrollPane1.setOpaque(false);
jScrollPane1.setRequestFocusEnabled(false);
jScrollPane1.setVerifyInputWhenFocusTarget(false);
jScrollPane1.setWheelScrollingEnabled(false);

jTextArea1.setColumns(20);
jTextArea1.setFont(new java.awt.Font("Arial", 0, 12));
jTextArea1.setLineWrap(true);
jTextArea1.setRows(5);
jTextArea1.setText("Es wurde keine Datenbank gefunden. \nDie Datenbankstruktur und ben�tigte Verzeichnisse werden nun erstellt.");
jTextArea1.setWrapStyleWord(true);
jTextArea1.setFocusable(false);
jTextArea1.setOpaque(false);
jTextArea1.setRequestFocusEnabled(false);
jTextArea1.setVerifyInputWhenFocusTarget(false);
jScrollPane1.setViewportView(jTextArea1);

jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
jLabel2.setText("MP Rechnungs -und Kundenverwaltung - Erster Start");

jButton1.setText("Weiter");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});

jButton2.setText("Abbruch");
jButton2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton2ActionPerformed(evt);
}
});

jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/mp.png"))); // NOI18N
jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

jButton3.setText("Lizenz anzeigen");
jButton3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton3ActionPerformed(evt);
}
});

jLabel1.setText("Sie m�ssen die Bedingungen der GPL akzeptieren, um dieses Programm verwenden zu d�rfen.");

jCheckBox2.setText("Ich akzeptiere diese Bedingungen.");

jPanel3.setBackground(new java.awt.Color(204, 204, 204));
jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
jPanel3.setEnabled(false);

jLabel7.setText("Datenbankpfad:");
jLabel7.setEnabled(false);

jTextField1.setText("localhost:3306");
jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
jTextField1.setEnabled(false);

jLabel8.setText("Benutzer:");
jLabel8.setEnabled(false);

jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
jTextField2.setEnabled(false);

jTextField3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
jTextField3.setEnabled(false);

jLabel9.setText("Passwort:");
jLabel9.setEnabled(false);

jRadioButton1.setBackground(new java.awt.Color(204, 204, 204));
buttonGroup1.add(jRadioButton1);
jRadioButton1.setSelected(true);
jRadioButton1.setText("Embedded Derby");
jRadioButton1.setEnabled(false);

jRadioButton2.setBackground(new java.awt.Color(204, 204, 204));
buttonGroup1.add(jRadioButton2);
jRadioButton2.setText("MySQL");
jRadioButton2.setEnabled(false);

jRadioButton3.setBackground(new java.awt.Color(204, 204, 204));
buttonGroup1.add(jRadioButton3);
jRadioButton3.setText("Benutzerdefiniert");
jRadioButton3.setEnabled(false);

jLabel10.setText("Typ:");
jLabel10.setEnabled(false);

jTextField4.setText("org.apache.derby.jdbc.EmbeddedDriver");
jTextField4.setEnabled(false);

jButton6.setText("W�hlen");
jButton6.setEnabled(false);
jButton6.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton6ActionPerformed(evt);
}
});

org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
jPanel3.setLayout(jPanel3Layout);
jPanel3Layout.setHorizontalGroup(
jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel3Layout.createSequentialGroup()
.addContainerGap()
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jLabel7)
.add(jLabel8)
.add(jLabel9))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel3Layout.createSequentialGroup()
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
.add(jLabel10)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel3Layout.createSequentialGroup()
.add(jRadioButton3)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jTextField4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
.add(jRadioButton2)
.add(jRadioButton1)))
.add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
.add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jButton6)))
.addContainerGap())
);
jPanel3Layout.setVerticalGroup(
jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel3Layout.createSequentialGroup()
.addContainerGap()
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(jLabel7)
.add(jButton6)
.add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel3Layout.createSequentialGroup()
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(jLabel8)
.add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.add(jLabel9)))
.add(jLabel10)
.add(jPanel3Layout.createSequentialGroup()
.add(jRadioButton1)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jRadioButton2)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(jRadioButton3)
.add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
.addContainerGap(11, Short.MAX_VALUE))
);

jCheckBox3.setText("Datenbankverbindung bearbeiten");
jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jCheckBox3ItemStateChanged(evt);
}
});

jPanel4.setBackground(new java.awt.Color(204, 204, 204));
jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
jPanel4.setEnabled(false);

jLabel5.setText("Wo soll MP Ihre PDF -Dokumente speichern?");

pdfpathtf.setBorder(javax.swing.BorderFactory.createEtchedBorder());
pdfpathtf.setDragEnabled(true);

jButton5.setText("W�hlen");
jButton5.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton5ActionPerformed(evt);
}
});

jButton4.setText("W�hlen");
jButton4.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton4ActionPerformed(evt);
}
});

backuppathtf.setBorder(javax.swing.BorderFactory.createEtchedBorder());
backuppathtf.setDragEnabled(true);

jLabel4.setText("Wo soll MP Ihre Backup -Dateien speichern?");

jCheckBox1.setBackground(new java.awt.Color(204, 204, 204));
jCheckBox1.setSelected(true);
jCheckBox1.setText("Desktopicon anlegen");

org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
jPanel4.setLayout(jPanel4Layout);
jPanel4Layout.setHorizontalGroup(
jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel4Layout.createSequentialGroup()
.addContainerGap()
.add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel4Layout.createSequentialGroup()
.add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
.add(org.jdesktop.layout.GroupLayout.LEADING, jCheckBox1)
.add(org.jdesktop.layout.GroupLayout.LEADING, pdfpathtf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
.add(org.jdesktop.layout.GroupLayout.LEADING, backuppathtf))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jButton5)
.add(jButton4)))
.add(jLabel5)
.add(jLabel4))
.addContainerGap())
);
jPanel4Layout.setVerticalGroup(
jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel4Layout.createSequentialGroup()
.addContainerGap()
.add(jLabel5)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(pdfpathtf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.add(jButton5))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jLabel4)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(backuppathtf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.add(jButton4))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jCheckBox1)
.addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
jLabel6.setText("Einstellungen:");

org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
jPanel2.setLayout(jPanel2Layout);
jPanel2Layout.setHorizontalGroup(
jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
.addContainerGap()
.add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
.add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
.add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
.add(org.jdesktop.layout.GroupLayout.LEADING, jCheckBox3)
.add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
.add(jLabel2)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 153, Short.MAX_VALUE)
.add(jLabel3))
.add(org.jdesktop.layout.GroupLayout.LEADING, jLabel6)
.add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1)
.add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
.add(jButton3)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jCheckBox2)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 69, Short.MAX_VALUE)
.add(jButton2)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jButton1)))
.addContainerGap())
);
jPanel2Layout.setVerticalGroup(
jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel2Layout.createSequentialGroup()
.add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel2Layout.createSequentialGroup()
.addContainerGap()
.add(jLabel2))
.add(jLabel3))
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jCheckBox3)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jLabel6)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
.add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
.add(jLabel1)
.add(18, 18, 18)
.add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
.add(jButton3)
.add(jCheckBox2)
.add(jButton1)
.add(jButton2))
.addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addContainerGap())
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
.addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
.add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);

pack();
}// </editor-fold>//GEN-END:initComponents

    private void install() {
        Verzeichnisse.setBackuppathtftext(backuppathtf.getText());
        Verzeichnisse.setPdfpathtftext(pdfpathtf.getText());

        if (jCheckBox3.isSelected()) {

            Main.settings.setDBPath(jTextField1.getText());
            Main.settings.setDBUser(jTextField2.getText());
            Main.settings.setDBPassword(jTextField3.getText());

            if (jRadioButton1.isSelected()) {
                Main.settings.setDBDriver(ConnectionTypeHandler.DERBY_DRIVER);
            } else if (jRadioButton2.isSelected()) {
                Main.settings.setDBDriver(ConnectionTypeHandler.MYSQL_DRIVER);
            } else if (jRadioButton3.isSelected()) {
                Main.settings.setDBDriver(jTextField4.getText());
            }
            
        }

        try {
            Verzeichnisse.buildPath();
            Verzeichnisse.createDirs();

            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            if (createDatabase()) {
                setStartValues();
                if (jCheckBox1.isSelected()) {
                    Setup.writeDesktopIcon();
                }
                if (!Main.FORCE_NO_FILE_COPY) {
                    Verzeichnisse.copyFiles();
                }
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                new Popup("Sie k�nnen das Programm nun starten.", Popup.NOTICE);
                finished = true;
                new Main();
                this.dispose();
            } else {
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                jButton2.setText("Beenden");
                jButton1.setEnabled(false);
                Popup.error("Datenbankfehler", "Fehler bei der Installation der Tabellen.");
                Main.settings.destroy();
            }
        } catch (Exception ex) {
            jButton2.setText("Beenden");
            jButton1.setEnabled(false);
            Popup.error(ex.getMessage(), "Fehler bei der Installation.");
            Log.Debug(this,ex);
        } finally {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Main.settings.destroy();
       System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!finished) {
            if (jCheckBox2.isSelected()) {
                install();
            } else {
                Popup.notice("Sie muessen die Lizenzbedingungen akzeptieren,\num das Programm zu installieren.");
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    LicenseWindow l = new LicenseWindow();
    new Position(l);
    l.setVisible(rootPaneCheckingEnabled);
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        try {
            pdfpathtf.setText(fc.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            Log.Debug(this,ex);
        }
    }
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        try {
            backuppathtf.setText(fc.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            Log.Debug(this,ex);
        }
    }
}//GEN-LAST:event_jButton4ActionPerformed

private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
    if (jCheckBox3.isSelected()) {
        PAnelUtils.enableSubComponents(jPanel3);
    } else {
        PAnelUtils.disableSubComponents(jPanel3);
    }
}//GEN-LAST:event_jCheckBox3ItemStateChanged

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        try {
            jTextField1.setText(fc.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            Log.Debug(this,ex);
        }
    }
}//GEN-LAST:event_jButton6ActionPerformed
    /**
     * @param args the command line arguments
     */
// Variables declaration - do not modify//GEN-BEGIN:variables
javax.swing.JTextField backuppathtf;
javax.swing.ButtonGroup buttonGroup1;
javax.swing.JButton jButton1;
javax.swing.JButton jButton2;
javax.swing.JButton jButton3;
javax.swing.JButton jButton4;
javax.swing.JButton jButton5;
javax.swing.JButton jButton6;
javax.swing.JCheckBox jCheckBox1;
javax.swing.JCheckBox jCheckBox2;
javax.swing.JCheckBox jCheckBox3;
javax.swing.JLabel jLabel1;
javax.swing.JLabel jLabel10;
javax.swing.JLabel jLabel2;
javax.swing.JLabel jLabel3;
javax.swing.JLabel jLabel4;
javax.swing.JLabel jLabel5;
javax.swing.JLabel jLabel6;
javax.swing.JLabel jLabel7;
javax.swing.JLabel jLabel8;
javax.swing.JLabel jLabel9;
javax.swing.JPanel jPanel1;
javax.swing.JPanel jPanel2;
javax.swing.JPanel jPanel3;
javax.swing.JPanel jPanel4;
javax.swing.JRadioButton jRadioButton1;
javax.swing.JRadioButton jRadioButton2;
javax.swing.JRadioButton jRadioButton3;
javax.swing.JScrollPane jScrollPane1;
javax.swing.JTextArea jTextArea1;
javax.swing.JTextField jTextField1;
javax.swing.JTextField jTextField2;
javax.swing.JTextField jTextField3;
javax.swing.JTextField jTextField4;
javax.swing.JTextField pdfpathtf;
// End of variables declaration//GEN-END:variables
    /**
     * Creates the database on the given path in mpsettings
     * @return true if creating the database was true
     */
    public boolean createDatabase() {

        if (!Main.FORCE_NO_DATABASE) {
            Conn c = null;
            try {
                c = Conn.instanceOf();
                c.createDatabase();
            } catch (Exception ex) {
                Log.Debug(this,ex);
                Popup.warn(ex.getMessage(), Popup.ERROR);
                return false;
            }
            return Conn.isTablesCreated();
        }
        return false;
    }

    /**
     * Calls the creation of a desktop icon for MP, depending on the OS
     */
    public static void writeDesktopIcon() {
        if (Main.IS_WINDOWS) {
            DesktopIcon.createWindowsDesktopIcon();
        } else {
            DesktopIcon.createLinuxDesktopIcon();
        }
    }

    private void setStartValues() {
       Programmdaten.instanceOf().setBILLPANEL_CHECKBOX_NETTOPREISE(true);
       Programmdaten.instanceOf().setBILLPANEL_CHECKBOX_MITFIRMENNAME(true);
    }
}
