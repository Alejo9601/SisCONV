package View;

import Controller.RegistrationControl;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Juarez
 */
public class LandPropertyRegistration extends javax.swing.JDialog {

    /**
     * Creates new form
     *
     * @param parent
     * @param modal
     */
    public LandPropertyRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Will set the controller for this view.
     *
     * @param control
     */
    public void setController(RegistrationControl control) {
        this.btnSaveLandProp.addActionListener(control);
        this.btnSaveLandProp.setActionCommand("SAVE_LANDPROPERTY");
    }

    /**
     * Will verify if the field are completed or not.
     *
     * @return
     */
    public boolean verifyInformation() {
        if (tfApple.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "La nro. de manzana donde se ubica el terreno / propiedad es obligatorio",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfBatch.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El nro. de lote del terreno / propiedad es obligatorio",
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
    public String getTfApple() {
        return tfApple.getText().toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getTfDecree() {
        return tfDecree.getText().toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getTfAddress() {
        return tfAddress.getText().toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getTfBatch() {
        return tfBatch.getText().toUpperCase();
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
        btnSaveLandProp = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        tfApple = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfDecree = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfBatch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 157), 3));
        jPanel1.setLayout(new java.awt.BorderLayout());

        btnSaveLandProp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSaveLandProp.setText("Guardar");

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
                .addContainerGap(316, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveLandProp)
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveLandProp)
                    .addComponent(btnCancel))
                .addGap(14, 14, 14))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO TERRENO / INMUEBLE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        tfApple.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfApple.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfApple.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAppleKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Manzana : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Decreto de adjudicacion :");

        tfDecree.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDecree.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDecree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDecreeKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Direccion :");

        tfAddress.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAddressKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Lote :");

        tfBatch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfBatch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfBatch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBatchKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("(opcional)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("(opcional)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addGap(184, 184, 184))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfApple, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfDecree)
                            .addComponent(tfAddress))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tfBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfApple, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addComponent(tfDecree, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(4, 4, 4)
                .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private void tfAppleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAppleKeyTyped
        if (tfApple.getText().length() == 4) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isDigit(c) || c == 'a' || c == 'b' || c == 'c' || c == 'd') {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfAppleKeyTyped

    /**
     *
     * @param evt
     */
    private void tfBatchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBatchKeyTyped
        if (tfBatch.getText().length() == 4) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isDigit(c) || c == 'a' || c == 'b' || c == 'c' || c == 'd') {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfBatchKeyTyped

    /**
     *
     * @param evt
     */
    private void tfDecreeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDecreeKeyTyped
        if (tfDecree.getText().length() == 10) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (c == '/' || Character.isDigit(c)) {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfDecreeKeyTyped

    /**
     *
     * @param evt
     */
    private void tfAddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAddressKeyTyped
        if (tfAddress.getText().length() == 50) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || Character.isDigit(c) || c == ' ') {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_tfAddressKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSaveLandProp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfApple;
    private javax.swing.JTextField tfBatch;
    private javax.swing.JTextField tfDecree;
    // End of variables declaration//GEN-END:variables
}
