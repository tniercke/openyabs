package mpv5.ui.panels;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.Formattable;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.QueryHandler;
import mpv5.db.objects.Contact;
import mpv5.db.objects.Item;
import mpv5.db.objects.MailMessage;
import mpv5.db.objects.User;
import mpv5.globals.Messages;
import mpv5.handler.VariablesHandler;
import mpv5.logging.Log;
import mpv5.mail.SimpleMail;
import mpv5.ui.dialogs.BigPopup;
import mpv5.ui.dialogs.DialogForFile;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPView;
import mpv5.utils.export.Export;
import mpv5.utils.files.FileDirectoryHandler;
import mpv5.utils.files.FileReaderWriter;
import mpv5.utils.jobs.Job;
import mpv5.utils.jobs.Waiter;
import mpv5.utils.print.PrintJob;

/**
 *
 * 
 */
public class PreviewPanel extends javax.swing.JPanel implements Waiter {

    private static final long serialVersionUID = 1L;
    private File file;
    private DatabaseObject dataOwner;
    private PDFFile pdffile;
    private PagePanel panel;
    private DataPanel parent;

    /** Creates new form
     */
    public PreviewPanel() {
        initComponents();
    }

    /** Creates new preview for the given file. Currently supported file types:
     * <li>PDF
     * <li>ODT
     * <li>TXT
     * @param file
     */
    public PreviewPanel(File file) {
        initComponents();
        openl(file);
    }

    /**
     * Shows the given pdf file in the preview panel
     * @param pdf
     */
    public void openPdf(File pdf) {
        PagePanel vp;
        this.file = pdf;
        if (pdf.isFile() && pdf.exists()) {
            try {

                RandomAccessFile raf = new RandomAccessFile(pdf, "r");
                FileChannel channel = raf.getChannel();
                ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                pdffile = new PDFFile(buf);
                buf.clear();
                channel.close();
                raf.close();

                vp = new PagePanel();

                ppanel.removeAll();
                ppanel.add(vp, BorderLayout.CENTER);
                ppanel.validate();

                vp.showPage(pdffile.getPage(0));
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else {
            throw new IllegalArgumentException("File is not existing or a directory: " + pdf);
        }
    }

    /** This me4thod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        ppanel = new javax.swing.JPanel();
        toolbar = new javax.swing.JToolBar();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();

        jSeparator5.setName("jSeparator5"); // NOI18N

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PreviewPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        ppanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ppanel.setName("ppanel"); // NOI18N
        ppanel.setLayout(new java.awt.BorderLayout());

        toolbar.setRollover(true);
        toolbar.setName("toolbar"); // NOI18N

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/printer.png"))); // NOI18N
        jButton27.setText(bundle.getString("PreviewPanel.jButton27.text")); // NOI18N
        jButton27.setToolTipText(bundle.getString("PreviewPanel.jButton27.toolTipText")); // NOI18N
        jButton27.setContentAreaFilled(false);
        jButton27.setFocusable(false);
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton27.setName("jButton27"); // NOI18N
        jButton27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        toolbar.add(jButton27);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/mail_reply.png"))); // NOI18N
        jButton28.setText(bundle.getString("PreviewPanel.jButton28.text")); // NOI18N
        jButton28.setToolTipText(bundle.getString("PreviewPanel.jButton28.toolTipText")); // NOI18N
        jButton28.setContentAreaFilled(false);
        jButton28.setFocusable(false);
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.setName("jButton28"); // NOI18N
        jButton28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        toolbar.add(jButton28);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/acroread.png"))); // NOI18N
        jButton30.setText(bundle.getString("PreviewPanel.jButton30.text")); // NOI18N
        jButton30.setToolTipText(bundle.getString("PreviewPanel.jButton30.toolTipText")); // NOI18N
        jButton30.setContentAreaFilled(false);
        jButton30.setFocusable(false);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton30.setName("jButton30"); // NOI18N
        jButton30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        toolbar.add(jButton30);

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/ark.png"))); // NOI18N
        jButton31.setText(bundle.getString("PreviewPanel.jButton31.text")); // NOI18N
        jButton31.setToolTipText(bundle.getString("PreviewPanel.jButton31.toolTipText")); // NOI18N
        jButton31.setContentAreaFilled(false);
        jButton31.setFocusable(false);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.setName("jButton31"); // NOI18N
        jButton31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        toolbar.add(jButton31);

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/fileexport.png"))); // NOI18N
        jButton32.setText(bundle.getString("PreviewPanel.jButton32.text")); // NOI18N
        jButton32.setToolTipText(bundle.getString("PreviewPanel.jButton32.toolTipText")); // NOI18N
        jButton32.setContentAreaFilled(false);
        jButton32.setFocusable(false);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton32.setName("jButton32"); // NOI18N
        jButton32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        toolbar.add(jButton32);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
            .addComponent(ppanel, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ppanel, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
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

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        if (file != null) {
            try {
                new PrintJob().print(file);
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }
}//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed

        if (dataOwner != null) {
            try {
                file = FileDirectoryHandler.copyFile2(file, new File(FileDirectoryHandler.getTempDir() + ((Formattable) dataOwner).getFormatHandler().toUserString() + ".pdf"));
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }
        MailMessage m = null;
        if (dataOwner != null && dataOwner.isExisting()) {

            QueryCriteria c = new QueryCriteria("usersids", mpv5.db.objects.User.getCurrentUser().__getIDS());
            try {
                m = (MailMessage) Popup.SelectValue(DatabaseObject.getObjects(Context.getMessage(), c), Messages.SELECT_A_TEMPLATE);
            } catch (Exception ex) {
                Log.Debug(this, ex.getMessage());
            }

            if (dataOwner instanceof Item) {
                try {
                    Contact cont = ((Contact) Contact.getObject(Context.getContact(), ((Item) dataOwner).__getContactsids()));
                    if (mpv5.db.objects.User.getCurrentUser().__getMail().contains("@") && mpv5.db.objects.User.getCurrentUser().__getMail().contains(".") && cont.__getMailaddress().contains("@") && cont.__getMailaddress().contains(".")) {
                        SimpleMail pr = new SimpleMail();
                        pr.setMailConfiguration(mpv5.db.objects.User.getCurrentUser().getMailConfiguration());
                        pr.setRecipientsAddress(cont.__getMailaddress());
                        if (m != null && m.__getCname() != null) {
                            pr.setSubject(m.__getCname());
                            pr.setText(VariablesHandler.parse(m.__getDescription(), dataOwner));
                        }
                        try {
                            pr.set(file, (Exception) null);
                        } catch (Exception ex) {
                            Log.Debug(ex);
                        }

                    } else {
                        Popup.notice(Messages.NO_MAIL_DEFINED);
                    }
                } catch (NodataFoundException nodataFoundException) {
                    Log.Debug(nodataFoundException);
                } catch (UnsupportedOperationException unsupportedOperationException) {
                    Popup.notice(Messages.NO_MAIL_CONFIG);
                }
            }
        } else {
            Popup.notice(Messages.NO_TEMPLATE_LOADED + " (" + mpv5.db.objects.User.getCurrentUser() + ")");
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed

        if (dataOwner != null) {
            try {
                file = FileDirectoryHandler.copyFile2(file, new File(FileDirectoryHandler.getTempDir() + ((Formattable) dataOwner).getFormatHandler().toUserString() + ".pdf"));
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }

        if (dataOwner != null && dataOwner.isExisting()) {
            if (QueryHandler.instanceOf().clone(Context.getFiles()).insertFile(file, dataOwner, QueryCriteria.getSaveStringFor(((Formattable) dataOwner).getFormatHandler().toUserString().toString()))) {
                Popup.notice(Messages.FILE_SAVED.toString() + file.getName());
                if (parent != null) {
                    parent.refresh();
                }
            }
        } else {
            Popup.notice(Messages.NOT_POSSIBLE);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        DialogForFile d = new DialogForFile(DialogForFile.FILES_ONLY);

        if (dataOwner != null) {
            try {
                file = FileDirectoryHandler.copyFile2(file, new File(FileDirectoryHandler.getTempDir() + ((Formattable) dataOwner).getFormatHandler().toUserString() + ".pdf"));
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }
        if (dataOwner == null) {
            d.setSelectedFile(new File(file.getName()));
        } else {
            d.setSelectedFile(new File(((Formattable) dataOwner).getFormatHandler().toUserString() + ".pdf"));
        }
        if (d.saveFile()) {
            d.getFile().delete();
            if (file.renameTo(d.getFile())) {
                try {
                    mpv5.YabsViewProxy.instance().addMessage(Messages.FILE_SAVED + d.getFile().getCanonicalPath());
                } catch (IOException ex) {
                    Log.Debug(ex);
                }
            }
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

        if (dataOwner != null) {
            try {
                file = FileDirectoryHandler.copyFile2(file, new File(FileDirectoryHandler.getTempDir() + ((Formattable) dataOwner).getFormatHandler().toUserString() + ".pdf"));
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }
        FileDirectoryHandler.open(file);

}//GEN-LAST:event_jButton30ActionPerformed
    int pagen = 1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPanel ppanel;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

//    public void openOdt(File file) {
//        this.file = file;
//        Log.Debug(this, "Preparing preview for: " + file);
//        OOOPanel op = new OOOPanel();
//        ppanel.removeAll();
//        ppanel.setLayout(new BorderLayout());
//        ppanel.add(op, BorderLayout.CENTER);
//
//        op.constructOOOPanel(file);
//        this.validate();
//    }
    public void open(File file) {
        this.file = file;
        FileReaderWriter f = new FileReaderWriter(file);
        String t = f.read();
        JEditorPane p = new JEditorPane();
        p.setText(t);
        ppanel.removeAll();
        ppanel.add(p, BorderLayout.CENTER);
        ppanel.validate();
    }

    /**
     * Show this panel in a new frame
     * @param title
     */
    public void showInNewFrame(String title) {
        BigPopup.showPopup(this, jPanel1, title, mpv5.YabsViewProxy.instance().getIdentifierFrame().getHeight(), 800);
    }

    /**
     * @return the dataOwner
     */
    public DatabaseObject getDataOwner() {
        return dataOwner;
    }

    /**
     * @param dataOwner the dataOwner to set
     */
    public void setDataOwner(DatabaseObject dataOwner) {
        this.dataOwner = dataOwner;
    }

    public void set(Object object, Exception exception) throws Exception {
        if (exception == null) {
            try {
                setCursor(new Cursor(Cursor.WAIT_CURSOR));

                if (object instanceof List) {
                    showInNewFrame("Export");
                    openl(((Export) ((List) object).get(0)).getTargetFile());
                } else if (object instanceof Export) {
                    showInNewFrame("Export");
                    openl(((Export) object).getTargetFile());
                } else {
                    showInNewFrame(((File) object).getName());
                    openl((File) object);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        } else {
            throw exception;
        }
    }

    private void openl(File file) {
        if (file.getName().split("\\.").length < 2) {
            throw new UnsupportedOperationException("The file must have an extension: " + file);
        }

        this.file = file;

        String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());

        Log.Debug(this, "Found extension: " + extension);

        if (extension.equalsIgnoreCase(".pdf")) {
            try {
                openPdf(file);
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else if (extension.equalsIgnoreCase(".odt")) {
            try {
                FileDirectoryHandler.open(file);
                makeToolBar();
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else if (extension.equalsIgnoreCase(".txt")) {
            try {
                open(file);
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else {
            FileDirectoryHandler.open(file);
            makeToolBar();
        }
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(DataPanel parent) {
        this.parent = parent;
    }

    private void makeToolBar() {
        jPanel1.remove(ppanel);
        validate();
        try {
            BigPopup.pack(this);
            BigPopup.setOnTop(this);
        } catch (Exception exception) {
        }
    }
}
