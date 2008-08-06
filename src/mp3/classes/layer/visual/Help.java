/*
 * Help.java
 *
 * Created on 31. Januar 2008, 18:56
 */

package mp3.classes.layer.visual;

import java.io.File;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLDocument.HTMLReader;
import mp3.classes.layer.*;
import mp4.utils.windows.Position;
import mp3.classes.utils.FileToString;
import mp4.einstellungen.Programmdaten;

/**
 *
 * @author  anti43
 */
public class Help extends javax.swing.JFrame {
    private DefaultHelpModel model;
    
    /** Creates new form Help */
    public Help() {
        initComponents();
        new Position(this);
        this.setVisible(rootPaneCheckingEnabled);
    }
    public Help(DefaultHelpModel model) {
        initComponents();
        new Position(this);
       
        this.setModel(model);
        this.setVisible(rootPaneCheckingEnabled);
    }

    public Help(String helpfile) {
        initComponents();
        new Position(this);
      
        FileToString t = new FileToString(new File(Programmdaten.instanceOf().getPATH_TO_HELPFILES() + helpfile));
        String htitle = "";
        
        if(t.getContent().contains("<title>"))
                htitle = t.getContent().substring(t.getContent().indexOf("<title>")+"<title>".length(), t.getContent().indexOf("</title>"));
        
        this.setModel(new DefaultHelpModel( htitle, t.getContent()));
        this.setVisible(rootPaneCheckingEnabled);
    }
    
    
    public void setModel(DefaultHelpModel model){
        this.jLabel1.setText(model.getThema());
        this.jEditorPane1.setContentType("text/html");
        this.jEditorPane1.setText(model.getText());
        this.model=model;
    
    
    }
    
    /**
     * 
     * @param helpfile
     * @param theme
     */
    public Help(String helpfile, String theme) {
        initComponents();
        new Position(this);
      
        FileToString t = new FileToString("/helpfiles/" + helpfile);
        
        this.setModel(new DefaultHelpModel(theme, t.getContent()));
        this.setVisible(rootPaneCheckingEnabled);
    }
    public DefaultHelpModel getModel(){
        return model;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MP Hilfe");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hilfe: "));

        jLabel1.setText("Thema");

        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
