package mpv5.ui.panels;

import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import mpv5.Main;
import mpv5.db.common.Context;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.ControlApplet;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Accounts;
import mpv5.ui.dialogs.subcomponents.ControlPanel_AdvancedGlobalProperties;
import mpv5.ui.dialogs.subcomponents.ControlPanel_AdvancedLocalProperties;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Fonts;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Userproperties;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Formats;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Groups;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Konsole;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Local;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Locale;
import mpv5.ui.dialogs.subcomponents.ControlPanel_External;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Plugins;
import mpv5.ui.dialogs.subcomponents.ControlPanel_ProductGroups;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Taxes;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Templates;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Users;
import mpv5.ui.dialogs.subcomponents.ControlPanel_WebShopManager;
import mpv5.ui.frames.MPBabelFish;
import mpv5.ui.frames.MPView;
import mpv5.utils.files.FileDirectoryHandler;

/**
 *
 * 
 */
public class MPControlPanel extends javax.swing.JPanel {

    private static MPControlPanel cpanel;

    /**
     * 
     * @return
     */
    public static synchronized MPControlPanel instanceOf() {
        if (cpanel != null) {
            return cpanel;
        } else {
            cpanel = new MPControlPanel();
            return cpanel;
        }
    }
    private Context context;

    /** Creates new form ListPanel */
    private MPControlPanel() {
        initComponents();
        removeAll();
        add(jScrollPane1, BorderLayout.CENTER);
        validate();
        repaint();
    }

    /** This me4thod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        buttons = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        details = new javax.swing.JPanel();
        scroller = new javax.swing.JScrollPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        actions = new javax.swing.JPanel();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(343, 303));

        buttons.setBackground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        buttons.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MPControlPanel.buttons.border.title"))); // NOI18N
        buttons.setAutoscrolls(true);
        buttons.setMaximumSize(new java.awt.Dimension(32767, 400));
        buttons.setMinimumSize(new java.awt.Dimension(30, 150));
        buttons.setName("buttons"); // NOI18N
        buttons.setPreferredSize(new java.awt.Dimension(500, 250));

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/preferences_plugin.png"))); // NOI18N
        jButton16.setText(bundle.getString("MPControlPanel.jButton16.text")); // NOI18N
        jButton16.setToolTipText(bundle.getString("MPControlPanel.jButton16.toolTipText")); // NOI18N
        jButton16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton16.setContentAreaFilled(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setIconTextGap(1);
        jButton16.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton16.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton16.setName("jButton16"); // NOI18N
        jButton16.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        buttons.add(jButton16);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/internet.png"))); // NOI18N
        jButton1.setText(bundle.getString("MPControlPanel.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("MPControlPanel.jButton1.toolTipText")); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(1);
        jButton1.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton1.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        buttons.add(jButton1);

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/advancedsettings.png"))); // NOI18N
        jButton15.setText(bundle.getString("MPControlPanel.jButton15.text")); // NOI18N
        jButton15.setToolTipText(bundle.getString("MPControlPanel.jButton15.toolTipText")); // NOI18N
        jButton15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton15.setContentAreaFilled(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setIconTextGap(1);
        jButton15.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton15.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton15.setName("jButton15"); // NOI18N
        jButton15.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        buttons.add(jButton15);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/openofficeorg-20.png"))); // NOI18N
        jButton7.setText(bundle.getString("MPControlPanel.jButton7.text")); // NOI18N
        jButton7.setToolTipText(bundle.getString("MPControlPanel.jButton7.toolTipText")); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton7.setContentAreaFilled(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setIconTextGap(1);
        jButton7.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton7.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        buttons.add(jButton7);

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/advancedsettings.png"))); // NOI18N
        jButton20.setText(bundle.getString("MPControlPanel.jButton20.text")); // NOI18N
        jButton20.setToolTipText(bundle.getString("MPControlPanel.jButton20.toolTipText")); // NOI18N
        jButton20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton20.setContentAreaFilled(false);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setIconTextGap(1);
        jButton20.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton20.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton20.setName("jButton20"); // NOI18N
        jButton20.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        buttons.add(jButton20);

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/advancedsettings.png"))); // NOI18N
        jButton21.setText(bundle.getString("MPControlPanel.jButton21.text")); // NOI18N
        jButton21.setToolTipText(bundle.getString("MPControlPanel.jButton21.toolTipText")); // NOI18N
        jButton21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton21.setContentAreaFilled(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setIconTextGap(1);
        jButton21.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton21.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton21.setName("jButton21"); // NOI18N
        jButton21.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        buttons.add(jButton21);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/flag.png"))); // NOI18N
        jButton10.setText(bundle.getString("MPControlPanel.jButton10.text")); // NOI18N
        jButton10.setToolTipText(bundle.getString("MPControlPanel.jButton10.toolTipText")); // NOI18N
        jButton10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setIconTextGap(1);
        jButton10.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton10.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        buttons.add(jButton10);

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/gkrellm2.png"))); // NOI18N
        jButton12.setText(bundle.getString("MPControlPanel.jButton12.text")); // NOI18N
        jButton12.setToolTipText(bundle.getString("MPControlPanel.jButton12.toolTipText")); // NOI18N
        jButton12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton12.setContentAreaFilled(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setIconTextGap(1);
        jButton12.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton12.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton12.setName("jButton12"); // NOI18N
        jButton12.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        buttons.add(jButton12);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/locale.png"))); // NOI18N
        jButton2.setText(bundle.getString("MPControlPanel.jButton2.text")); // NOI18N
        jButton2.setToolTipText(bundle.getString("MPControlPanel.jButton2.toolTipText")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setIconTextGap(1);
        jButton2.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton2.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttons.add(jButton2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/looknfeel.png"))); // NOI18N
        jButton3.setText(bundle.getString("MPControlPanel.jButton3.text")); // NOI18N
        jButton3.setToolTipText(bundle.getString("MPControlPanel.jButton3.toolTipText")); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.setContentAreaFilled(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(1);
        jButton3.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton3.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        buttons.add(jButton3);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/babelfish.png"))); // NOI18N
        jButton5.setText(bundle.getString("MPControlPanel.jButton5.text")); // NOI18N
        jButton5.setToolTipText(bundle.getString("MPControlPanel.jButton5.toolTipText")); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setIconTextGap(1);
        jButton5.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton5.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        buttons.add(jButton5);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/evolution-contacts.png"))); // NOI18N
        jButton6.setText(bundle.getString("MPControlPanel.jButton6.text")); // NOI18N
        jButton6.setToolTipText(bundle.getString("MPControlPanel.jButton6.toolTipText")); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton6.setContentAreaFilled(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setIconTextGap(1);
        jButton6.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton6.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        buttons.add(jButton6);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/draw-eraser.png"))); // NOI18N
        jButton8.setText(bundle.getString("MPControlPanel.jButton8.text")); // NOI18N
        jButton8.setToolTipText(bundle.getString("MPControlPanel.jButton8.toolTipText")); // NOI18N
        jButton8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton8.setContentAreaFilled(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setIconTextGap(1);
        jButton8.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton8.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        buttons.add(jButton8);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/fonts.png"))); // NOI18N
        jButton9.setText(bundle.getString("MPControlPanel.jButton9.text")); // NOI18N
        jButton9.setToolTipText(bundle.getString("MPControlPanel.jButton9.toolTipText")); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton9.setContentAreaFilled(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setIconTextGap(1);
        jButton9.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton9.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        buttons.add(jButton9);

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/preferences_system_session_services.png"))); // NOI18N
        jButton17.setText(bundle.getString("MPControlPanel.jButton17.text")); // NOI18N
        jButton17.setToolTipText(bundle.getString("MPControlPanel.jButton17.toolTipText")); // NOI18N
        jButton17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton17.setContentAreaFilled(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton17.setIconTextGap(1);
        jButton17.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton17.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton17.setName("jButton17"); // NOI18N
        jButton17.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        buttons.add(jButton17);

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/blockdevice.png"))); // NOI18N
        jButton13.setText(bundle.getString("MPControlPanel.jButton13.text")); // NOI18N
        jButton13.setToolTipText(bundle.getString("MPControlPanel.jButton13.toolTipText")); // NOI18N
        jButton13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton13.setContentAreaFilled(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setIconTextGap(1);
        jButton13.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton13.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton13.setName("jButton13"); // NOI18N
        jButton13.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        buttons.add(jButton13);

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/konsole.png"))); // NOI18N
        jButton14.setText(bundle.getString("MPControlPanel.jButton14.text")); // NOI18N
        jButton14.setToolTipText(bundle.getString("MPControlPanel.jButton14.toolTipText")); // NOI18N
        jButton14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton14.setContentAreaFilled(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setIconTextGap(1);
        jButton14.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton14.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton14.setName("jButton14"); // NOI18N
        jButton14.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        buttons.add(jButton14);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/usb.png"))); // NOI18N
        jButton11.setText(bundle.getString("MPControlPanel.jButton11.text")); // NOI18N
        jButton11.setToolTipText(bundle.getString("MPControlPanel.jButton11.toolTipText")); // NOI18N
        jButton11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton11.setContentAreaFilled(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setIconTextGap(1);
        jButton11.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton11.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        buttons.add(jButton11);

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/krfb.png"))); // NOI18N
        jButton19.setText(bundle.getString("MPControlPanel.jButton19.text")); // NOI18N
        jButton19.setToolTipText(bundle.getString("MPControlPanel.jButton19.toolTipText")); // NOI18N
        jButton19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton19.setContentAreaFilled(false);
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setIconTextGap(1);
        jButton19.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton19.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton19.setName("jButton19"); // NOI18N
        jButton19.setPreferredSize(new java.awt.Dimension(80, 70));
        jButton19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        buttons.add(jButton19);

        jScrollPane1.setViewportView(buttons);

        add(jScrollPane1, java.awt.BorderLayout.NORTH);

        details.setBackground(new java.awt.Color(255, 255, 255));
        details.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MPControlPanel.details.border.title"))); // NOI18N
        details.setMinimumSize(new java.awt.Dimension(300, 400));
        details.setName("details"); // NOI18N
        details.setPreferredSize(new java.awt.Dimension(400, 400));
        details.setLayout(new java.awt.BorderLayout());

        scroller.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setName("scroller"); // NOI18N
        details.add(scroller, java.awt.BorderLayout.CENTER);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/adept_keep.png"))); // NOI18N
        jButton4.setText(bundle.getString("MPControlPanel.jButton4.text")); // NOI18N
        jButton4.setToolTipText(bundle.getString("MPControlPanel.jButton4.toolTipText")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        details.add(jToolBar1, java.awt.BorderLayout.NORTH);

        add(details, java.awt.BorderLayout.CENTER);

        actions.setName("actions"); // NOI18N
        actions.setLayout(new java.awt.BorderLayout());
        add(actions, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        openDetails(new ControlPanel_Local());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MPView.setWaiting(true);
        TinyLookAndFeel.controlPanelInstantiated = true;
        new de.muntjak.tinylookandfeel.controlpanel.ControlPanel(Main.getApplication());
        MPView.setWaiting(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        if (Popup.Y_N_dialog(Messages.REALLY_WIPE)) {
            FileDirectoryHandler.deleteFile(LocalSettings.getLocalFile(), false);
            Popup.notice(Messages.WIPED_LOCALSETTINGS);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openDetails(new ControlPanel_Locale());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MPView.setWaiting(true);
        new MPBabelFish();
        MPView.setWaiting(false);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        openDetails(new ControlPanel_Fonts());
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        openDetails(new ControlPanel_Users());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        openDetails(new ControlPanel_Groups());

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        openDetails(new ControlPanel_Plugins());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        openDetails(new ControlPanel_Accounts());
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        openDetails(new ControlPanel_Formats(mpv5.db.objects.User.getCurrentUser()));
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        openDetails(new ControlPanel_Konsole());
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        openDetails(new ControlPanel_AdvancedGlobalProperties());
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        openDetails(new ControlPanel_Userproperties());
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        openDetails(new ControlPanel_ProductGroups());
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        openDetails(new ControlPanel_WebShopManager());
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        openDetails(new ControlPanel_Templates());
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        openDetails(new ControlPanel_External());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        removeAll();
        add(jScrollPane1, BorderLayout.CENTER);
        validate();
        repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        openDetails(new ControlPanel_Taxes());
    }//GEN-LAST:event_jButton21ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actions;
    private javax.swing.JPanel buttons;
    private javax.swing.JPanel details;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane scroller;
    // End of variables declaration//GEN-END:variables

    /**
     * This method adds a button to the Properties panel, with given
     * Icon and Text. A click on the generated button creates a new instance of the given class.
     * @param icon
     * @param text
     * @param clazz
     */
    public void addShortcut(Icon icon, String text, final Class clazz) {
        JButton button = new JButton(text, icon);
        button.setToolTipText(text); // NOI18N
        button.setFont(new java.awt.Font(LocalSettings.getProperty("defaultfont"), 0, 10)); // NOI18N
        button.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        button.setContentAreaFilled(false);
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setMaximumSize(new java.awt.Dimension(90, 90));
        button.setMinimumSize(new java.awt.Dimension(90, 80));
        button.setPreferredSize(new java.awt.Dimension(90, 80));
        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        button.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    clazz.newInstance();
                } catch (InstantiationException ex) {
                    Log.Debug(this, ex);
                } catch (IllegalAccessException ex) {
                    Log.Debug(this, ex);
                }
            }
        });

        buttons.add(button);
    }

    /**
     * This method adds a button to the Properties panel, with given
     * Icon and Text. A click on the generated button places the JPanel on the details pane.
    //     * <b>Bring your own scrollpane!<b>
     * @param icon
     * @param text
     * @param panel 
     */
    public void addShortcut(Icon icon, String text, final ControlApplet panel) {
        JButton button = new JButton(text, icon);
        button.setToolTipText(text); // NOI18N
        button.setFont(new java.awt.Font(LocalSettings.getProperty("defaultfont"), 0, 10)); // NOI18N
        button.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        button.setContentAreaFilled(false);
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setMaximumSize(new java.awt.Dimension(90, 90));
        button.setMinimumSize(new java.awt.Dimension(90, 80));
        button.setPreferredSize(new java.awt.Dimension(90, 80));
        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openDetails(panel);
                } catch (Exception ex) {
                    Log.Debug(this, ex);
                }
            }
        });

        buttons.add(button);
        validate();
        repaint();
    }

    /**
     *  Show a Control Panel Applet
     * @param panel
     */
    public synchronized void openDetails(ControlApplet panel) {
        MPView.setWaiting(true);

        try {
            actions.removeAll();
            actions.add(((JPanel) panel.getAndRemoveActionPanel()));
            actions.validate();
            scroller.add((Component) panel);
            scroller.setViewportView((Component) panel);

            removeAll();
            add(details, BorderLayout.CENTER);
            add(actions, BorderLayout.SOUTH);
            validate();
            repaint();

            ((Component) panel).validate();

        } catch (Exception e) {
            //No rights for this!
        } finally {
            MPView.setWaiting(false);
            MPView.getIdentifierFrame().validate();
        }
    }
}
