package View;

import Controller.MainControl;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Alejandro Juarez
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView.
     */
    public MainView() {
        initComponents();
    }

    /**
     *
     */
    public void enableAdminFunctions() {
        menuAdministrator.setEnabled(true);
        btnSystemParameters.setEnabled(true);
    }

    /**
     * Will specify the controller of the view.
     *
     * @param control
     */
    public void setController(MainControl control) {
        btnCreateAgreement.addActionListener(control);
        btnCreateAgreement.setActionCommand("REGISTER_AGREEMENT");
        btnAgreementsList.addActionListener(control);
        btnAgreementsList.setActionCommand("AGREEMENTS_LIST");
        miConceptList.addActionListener(control);
        miConceptList.setActionCommand("CONCEPTS_LIST");
        btnUsuariosDelSistema.addActionListener(control);
        btnUsuariosDelSistema.setActionCommand("USERS_LIST");
        btnChangeUserPassword.addActionListener(control);
        btnChangeUserPassword.setActionCommand("UPDATE_USER_PASSWORD");
        btnActionCommiteds.addActionListener(control);
        btnActionCommiteds.setActionCommand("ACTION_COMMITTEDS_LIST");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAgreementsList = new javax.swing.JButton();
        btnTaxPayerList = new javax.swing.JButton();
        btnCreateAgreement = new javax.swing.JButton();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        btnPaymentsOfToday = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnSystemParameters = new javax.swing.JMenu();
        miConceptList = new javax.swing.JMenuItem();
        menuAdministrator = new javax.swing.JMenu();
        btnUsuariosDelSistema = new javax.swing.JMenuItem();
        btnActionCommiteds = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnChangeUserPassword = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SisCONV - SISTEMA DE GESTION DE CONVENIOS");
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setMaximumSize(new java.awt.Dimension(1980, 747));
        jPanel1.setMinimumSize(new java.awt.Dimension(1980, 747));
        jPanel1.setPreferredSize(new java.awt.Dimension(1980, 747));

        btnAgreementsList.setBackground(new java.awt.Color(0, 102, 153));
        btnAgreementsList.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgreementsList.setForeground(new java.awt.Color(255, 255, 255));
        btnAgreementsList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ListOFAgreements.png"))); // NOI18N
        btnAgreementsList.setText("  Padrón de Convenios");
        btnAgreementsList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 23));
        btnAgreementsList.setFocusPainted(false);
        btnAgreementsList.setFocusable(false);
        btnAgreementsList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgreementsList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAgreementsList.setIconTextGap(12);
        btnAgreementsList.setMaximumSize(new java.awt.Dimension(285, 32));
        btnAgreementsList.setMinimumSize(new java.awt.Dimension(285, 32));
        btnAgreementsList.setPreferredSize(new java.awt.Dimension(285, 32));
        btnAgreementsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgreementsListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgreementsListMouseExited(evt);
            }
        });

        btnTaxPayerList.setBackground(new java.awt.Color(0, 102, 153));
        btnTaxPayerList.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTaxPayerList.setForeground(new java.awt.Color(255, 255, 255));
        btnTaxPayerList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ListOfTaxpayer.png"))); // NOI18N
        btnTaxPayerList.setText("  Padron de Contribuyentes");
        btnTaxPayerList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 23));
        btnTaxPayerList.setEnabled(false);
        btnTaxPayerList.setFocusPainted(false);
        btnTaxPayerList.setFocusable(false);
        btnTaxPayerList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTaxPayerList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTaxPayerList.setIconTextGap(12);
        btnTaxPayerList.setMaximumSize(new java.awt.Dimension(285, 32));
        btnTaxPayerList.setMinimumSize(new java.awt.Dimension(285, 32));
        btnTaxPayerList.setPreferredSize(new java.awt.Dimension(285, 32));
        btnTaxPayerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaxPayerListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaxPayerListMouseExited(evt);
            }
        });

        btnCreateAgreement.setBackground(new java.awt.Color(0, 102, 153));
        btnCreateAgreement.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCreateAgreement.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateAgreement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New.png"))); // NOI18N
        btnCreateAgreement.setText("  Registrar Convenio de Pago");
        btnCreateAgreement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 23));
        btnCreateAgreement.setFocusPainted(false);
        btnCreateAgreement.setFocusable(false);
        btnCreateAgreement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCreateAgreement.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCreateAgreement.setIconTextGap(12);
        btnCreateAgreement.setRequestFocusEnabled(false);
        btnCreateAgreement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreateAgreementMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreateAgreementMouseExited(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/escudo28denoviembre.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jSeparator2.setBackground(new java.awt.Color(0, 51, 102));

        jSeparator3.setBackground(new java.awt.Color(0, 51, 102));

        jSeparator4.setBackground(new java.awt.Color(0, 51, 102));

        btnPaymentsOfToday.setBackground(new java.awt.Color(0, 102, 153));
        btnPaymentsOfToday.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPaymentsOfToday.setForeground(new java.awt.Color(255, 255, 255));
        btnPaymentsOfToday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_Purse_669953.png"))); // NOI18N
        btnPaymentsOfToday.setText("  Historial de Pagos ");
        btnPaymentsOfToday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 23));
        btnPaymentsOfToday.setEnabled(false);
        btnPaymentsOfToday.setFocusable(false);
        btnPaymentsOfToday.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPaymentsOfToday.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPaymentsOfToday.setIconTextGap(12);
        btnPaymentsOfToday.setMaximumSize(new java.awt.Dimension(285, 32));
        btnPaymentsOfToday.setMinimumSize(new java.awt.Dimension(285, 32));
        btnPaymentsOfToday.setPreferredSize(new java.awt.Dimension(285, 32));
        btnPaymentsOfToday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPaymentsOfTodayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPaymentsOfTodayMouseExited(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(0, 51, 102));

        jSeparator6.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCreateAgreement, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator4)
                        .addComponent(btnAgreementsList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaxPayerList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnPaymentsOfToday, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCreateAgreement, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnAgreementsList, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaxPayerList, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPaymentsOfToday, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/28 de Noviembre 2.JPG"))); // NOI18N

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(0, 0, 0));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Settings.png"))); // NOI18N
        jMenu1.setText(" Ajustes");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnSystemParameters.setText("Parametros del Sistema");
        btnSystemParameters.setEnabled(false);
        btnSystemParameters.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        miConceptList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        miConceptList.setText("Conceptos");
        btnSystemParameters.add(miConceptList);

        jMenu1.add(btnSystemParameters);

        jMenuBar1.add(jMenu1);

        menuAdministrator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Administrator.png"))); // NOI18N
        menuAdministrator.setText(" Administrador ");
        menuAdministrator.setEnabled(false);
        menuAdministrator.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnUsuariosDelSistema.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUsuariosDelSistema.setText("Usuarios del sistema ");
        menuAdministrator.add(btnUsuariosDelSistema);

        btnActionCommiteds.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnActionCommiteds.setText("Registro de movimientos de usuario");
        menuAdministrator.add(btnActionCommiteds);

        jMenuBar1.add(menuAdministrator);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Profile.png"))); // NOI18N
        jMenu3.setText(" Mi Perfil ");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnChangeUserPassword.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnChangeUserPassword.setText("Cambiar contraseña");
        jMenu3.add(btnChangeUserPassword);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, Short.MAX_VALUE)
            .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateAgreementMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateAgreementMouseEntered
        btnCreateAgreement.setBackground(new Color(0, 127, 185));
        Border border = new LineBorder(new Color(0, 127, 185), 23);
        btnCreateAgreement.setBorder(border);
    }//GEN-LAST:event_btnCreateAgreementMouseEntered

    private void btnCreateAgreementMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateAgreementMouseExited
        btnCreateAgreement.setBackground(new Color(0, 102, 153));
        Border border = new LineBorder(new Color(0, 102, 153), 23);
        btnCreateAgreement.setBorder(border);
    }//GEN-LAST:event_btnCreateAgreementMouseExited

    private void btnAgreementsListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgreementsListMouseEntered
        btnAgreementsList.setBackground(new Color(0, 127, 185));
        Border border = new LineBorder(new Color(0, 127, 185), 23);
        btnAgreementsList.setBorder(border);
    }//GEN-LAST:event_btnAgreementsListMouseEntered

    private void btnAgreementsListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgreementsListMouseExited
        btnAgreementsList.setBackground(new Color(0, 102, 153));
        Border border = new LineBorder(new Color(0, 102, 153), 23);
        btnAgreementsList.setBorder(border);
    }//GEN-LAST:event_btnAgreementsListMouseExited

    private void btnPaymentsOfTodayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentsOfTodayMouseEntered
        // TODO add your handling code here:
        btnPaymentsOfToday.setBackground(new Color(0, 127, 185));
        Border border = new LineBorder(new Color(0, 127, 185), 23);
        btnPaymentsOfToday.setBorder(border);
    }//GEN-LAST:event_btnPaymentsOfTodayMouseEntered

    private void btnPaymentsOfTodayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentsOfTodayMouseExited
        // TODO add your handling code here:
        btnPaymentsOfToday.setBackground(new Color(0, 102, 153));
        Border border = new LineBorder(new Color(0, 102, 153), 23);
        btnPaymentsOfToday.setBorder(border);
    }//GEN-LAST:event_btnPaymentsOfTodayMouseExited

    private void btnTaxPayerListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaxPayerListMouseExited
        btnTaxPayerList.setBackground(new Color(0, 102, 153));
        Border border = new LineBorder(new Color(0, 102, 153), 23);
        btnTaxPayerList.setBorder(border);
    }//GEN-LAST:event_btnTaxPayerListMouseExited

    private void btnTaxPayerListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaxPayerListMouseEntered
        btnTaxPayerList.setBackground(new Color(0, 127, 185));
        Border border = new LineBorder(new Color(0, 127, 185), 23);
        btnTaxPayerList.setBorder(border);
    }//GEN-LAST:event_btnTaxPayerListMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnActionCommiteds;
    private javax.swing.JButton btnAgreementsList;
    private javax.swing.JMenuItem btnChangeUserPassword;
    private javax.swing.JButton btnCreateAgreement;
    private javax.swing.JButton btnPaymentsOfToday;
    private javax.swing.JMenu btnSystemParameters;
    private javax.swing.JButton btnTaxPayerList;
    private javax.swing.JMenuItem btnUsuariosDelSistema;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JMenu menuAdministrator;
    private javax.swing.JMenuItem miConceptList;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    // End of variables declaration//GEN-END:variables
}
