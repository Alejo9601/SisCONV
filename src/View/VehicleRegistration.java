package View;

import Controller.RegistrationControl;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Juarez
 */
public class VehicleRegistration extends javax.swing.JDialog {

    /**
     * Creates new form
     *
     * @param parent
     * @param modal
     */
    public VehicleRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Will set the controller for this view.
     *
     * @param control
     */
    public void setController(RegistrationControl control) {
        this.btnSaveVehicle.addActionListener(control);
        this.btnSaveVehicle.setActionCommand("SAVE_VEHICLE");
    }

    /**
     * Will verify if the field are completed or not.
     *
     * @return
     */
    public boolean verifyInformation() {

        if (tfDomain.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El dominio del vehiculo es obligatorio", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfModel.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "EL modelo del vehiculo es obligatorio",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfManufacturer.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El fabricante del vehiculo es obligatorio",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;

    }

    /**
     *
     * @return
     */
    public String getDomain() {
        return tfDomain.getText().toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getModel() {
        return tfModel.getText().toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getManufacturer() {
        return tfManufacturer.getText().toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getCmbType() {
        return cmbType.getSelectedItem().toString().toUpperCase();
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
        jPanel2 = new javax.swing.JPanel();
        btnSaveVehicle = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        tfDomain = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfModel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfManufacturer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 157), 3));
        jPanel1.setLayout(new java.awt.BorderLayout());

        btnSaveVehicle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSaveVehicle.setText("Guardar");

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(409, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveVehicle)
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveVehicle)
                    .addComponent(btnCancel))
                .addGap(14, 14, 14))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO VEHICULO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        tfDomain.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDomain.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDomain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDomainKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Dominio :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Modelo :");

        tfModel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfModel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfModel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfModelKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fabricante :");

        tfManufacturer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfManufacturer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfManufacturer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfManufacturerKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tipo :");

        cmbType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AUTOMOTOR", "CICLOMOTOR", "OTROS" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(tfManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cmbType, 0, 167, Short.MAX_VALUE))
                    .addComponent(tfDomain)
                    .addComponent(tfModel, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfDomain, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfModel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Will close this view.
     *
     * @param evt
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     *
     * @param evt
     */
    private void tfDomainKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDomainKeyTyped
        if (tfDomain.getText().length() == 10) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || Character.isDigit(c)) {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfDomainKeyTyped

    /**
     *
     * @param evt
     */
    private void tfModelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfModelKeyTyped
        if (tfModel.getText().length() == 48) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || Character.isDigit(c) || c == ' ' || c == '.' || c == '-') {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfModelKeyTyped

    /**
     *
     * @param evt
     */
    private void tfManufacturerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfManufacturerKeyTyped
        if (tfManufacturer.getText().length() == 19) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || Character.isDigit(c) || c == ' ') {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfManufacturerKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSaveVehicle;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField tfDomain;
    private javax.swing.JTextField tfManufacturer;
    private javax.swing.JTextField tfModel;
    // End of variables declaration//GEN-END:variables
}
