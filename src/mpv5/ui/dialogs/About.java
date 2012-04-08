/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplashScreen.java
 *
 * Created on 30.03.2009, 21:55:52
 */
package mpv5.ui.dialogs;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import mpv5.db.common.Context;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.QueryHandler;
import mpv5.globals.Constants;
import mpv5.logging.Log;
import mpv5.ui.misc.Position;

/**
 *
 *  
 */
public class About extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private Image image;
    private Image grayImage;

    /** Creates new form SplashScreen
     * @param imageIcon
     */
    public About(ImageIcon imageIcon) {
        initComponents();
        jPanel1.setOpaque(false);
//        jScrollPane1.setOpaque(false);
//        jScrollPane1.getViewport().setOpaque(false);
//        ((DefaultListCellRenderer)jList1.getCellRenderer()).setOpaque( false );
//        jList1.setOpaque(false);
        setInfo(Constants.VERSION);
        setDBVersion();
        website.setText(Constants.WEBSITE);
        DefaultListModel m = new DefaultListModel();
        String[] cons = Constants.CONTRIBUTORS;
        for (int i = 0; i < cons.length; i++) {
            String string = cons[i];
            m.addElement(string);
        }
        jList1.setModel(m);
        title.setText(Constants.TITLE);
        image = imageIcon.getImage();
//        grayImage = GrayFilter.createDisabledImage(image);
        new Position(this);
        setAlwaysOnTop(true);
        setVisible(true);
        Log.Debug(this, "About window loaded");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 =  new javax.swing.JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, this);
                super.paintComponent(g);
            }
        };
        info = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        website = new javax.swing.JLabel();
        db_version = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        setTitle(bundle.getString("About.title")); // NOI18N
        setResizable(false);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("OptionPane.errorDialog.border.background"));

        info.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        info.setText(bundle.getString("About.info.text")); // NOI18N

        title.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title.setText(bundle.getString("About.title.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jLabel1.setText(bundle.getString("About.jLabel1.text")); // NOI18N

        jList1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 0, 51));
        jScrollPane1.setViewportView(jList1);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 204));
        jLabel2.setText(bundle.getString("About.jLabel2.text")); // NOI18N

        website.setForeground(new java.awt.Color(255, 255, 204));
        website.setText(bundle.getString("About.website.text")); // NOI18N

        db_version.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        db_version.setText(bundle.getString("About.db_version.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(website, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(db_version, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(info)
                    .addComponent(title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(db_version))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(website)
                        .addGap(2, 2, 2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel db_version;
    private javax.swing.JLabel info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel title;
    private javax.swing.JLabel website;
    // End of variables declaration//GEN-END:variables


    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the grayImage
     */
    public Image getGrayImage() {
        return grayImage;
    }

    /**
     * @param grayImage the grayImage to set
     */
    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info.getText();
    }

    /**
     * @param info the info to set
     */
    public final void setInfo(String info) {
        this.info.setText(info);
    }
    
    /**
     * @param info the info to set
     */
    public final void setDBVersion() {
        QueryCriteria criteria = new QueryCriteria("CNAME", "yabs_dbversion");
        Object[][] data = null;
        try {
            data = QueryHandler.instanceOf().clone(Context.IDENTITY_GLOBALSETTINGS).select("VALUE", criteria);
        } catch (NodataFoundException ex) {
            Log.Debug(this, ex.getMessage());
        }
        this.db_version.setText("(DB:" + data[0][0].toString()+ ")");
    }
}
