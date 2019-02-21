package View;

import Controller.RegistrationControl;
import datechooser.beans.DateChooserDialog;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Juarez
 */
public class AgreementRegistration extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public AgreementRegistration() {
        initComponents();
    }

    private boolean isNumeric(String expression) {
        try {
            Double.valueOf(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Will show this view to mofidy an agreement, enabling partially its
     * components.
     */
    public void showAsPartialModificationView() {
        btnSaveAgreement.setActionCommand("UPDATE_AGREEMENT");
        tfAmountOfDebt.setEnabled(false);
        tfDateOfCreation.setEnabled(false);
        btnDateOfCreation.setEnabled(false);
        tfDateOfExpiration.setEnabled(false);
        btnDateOfExpiration.setEnabled(false);
        cmbSetFees.setEnabled(false);
        cmbConcept.setEnabled(false);

        this.setVisible(true);
    }

    /**
     * Will show this view to mofidy an agreement, enabling all components.
     */
    public void showAsCompleteModificationView() {
        btnSaveAgreement.setActionCommand("UPDATE_AGREEMENT");
        tfAmountOfDebt.setEnabled(true);
        tfDateOfCreation.setEnabled(true);
        btnDateOfCreation.setEnabled(true);
        tfDateOfExpiration.setEnabled(true);
        btnDateOfExpiration.setEnabled(true);
        cmbSetFees.setEnabled(true);
        cmbConcept.setEnabled(true);

        this.setVisible(true);
    }

    /**
     *
     * @param condition
     */
    public void enableVehicleSelection(boolean condition) {
        btnSetVehicle.setEnabled(condition);
        btnEraseVehicle.setEnabled(condition);
        btnNewVehicle.setEnabled(condition);
    }

    /**
     *
     * @param condition
     */
    public void enableLandPropertySelection(boolean condition) {
        btnSetLandProperty.setEnabled(condition);
        btnEraseLandProperty.setEnabled(condition);
        btnNewLandProperty.setEnabled(condition);
    }

    /**
     * Set the controller of this view.
     *
     * @param control
     */
    public void setController(RegistrationControl control) {
        this.btnSaveAgreement.addActionListener(control);
        this.btnSaveAgreement.setActionCommand("SAVE_AGREEMENT");

        this.btnNewTaxpayer.addActionListener(control);
        this.btnNewTaxpayer.setActionCommand("NEW_TAXPAYER");
        this.btnSetTaxpayer.addActionListener(control);
        this.btnSetTaxpayer.setActionCommand("SELECT_TAXPAYER");

        this.btnNewVehicle.addActionListener(control);
        this.btnNewVehicle.setActionCommand("NEW_VEHICLE");
        this.btnSetVehicle.addActionListener(control);
        this.btnSetVehicle.setActionCommand("SELECT_VEHICLE");

        this.btnNewLandProperty.addActionListener(control);
        this.btnNewLandProperty.setActionCommand("NEW_LANDPROPERTY");
        this.btnSetLandProperty.addActionListener(control);
        this.btnSetLandProperty.setActionCommand("SELECT_LANDPROPERTY");
    }

    /**
     * Adds one concept to the concept combo box.
     *
     * @param concept
     */
    public void setCmbConcept(String concept) {
        this.cmbConcept.addItem(concept);
    }

    /**
     * Adds one concept to the concept combo box.
     *
     * @param concept
     */
    public void cmbConceptSetSelectedItem(String concept) {
        this.cmbConcept.setSelectedItem(concept);
    }

    /**
     * Sets the tfTaxPayer.
     *
     * @param taxpayer
     */
    public void setTfTaxPayer(String taxpayer) {
        this.tfTaxPayer.setText(taxpayer);
    }

    /**
     * Sets the tfAgreementNumber.
     *
     * @param number
     */
    public void setTfAgreementNumber(String number) {
        this.tfAgreementNumber.setText(number);
    }

    /**
     * Sets the tfVehicle.
     *
     * @param vehicle
     */
    public void setTfVehicle(String vehicle) {
        this.tfVehicle.setText(vehicle);
    }

    /**
     * Sets the tfLandPoperty.
     *
     * @param landProperty
     */
    public void setTfLandProperty(String landProperty) {
        this.tfLandProperty.setText(landProperty);
    }

    /**
     *
     * @param agreementNumber
     */
    public void setTfAmountOfDebt(String agreementNumber) {
        tfAmountOfDebt.setText(agreementNumber);
    }

    /**
     *
     * @param dateOfCreation
     */
    public void setTfCreationDate(String dateOfCreation) {
        tfDateOfCreation.setText(dateOfCreation);
    }

    /**
     *
     * @param dateOfExpiration
     */
    public void setTfExpirationDate(String dateOfExpiration) {
        tfDateOfExpiration.setText(dateOfExpiration);
    }

    /**
     *
     * @param description
     */
    public void setTfDescription(String description) {
        tfDescription.setText(description);
    }

    /**
     *
     * @param feesNumber
     */
    public void setFeesNumber(String feesNumber) {
        for (int i = 0; i < Integer.parseInt(feesNumber) + 1; i++) {
            cmbSetFees.addItem(Integer.toString(i + 1));
        }
        cmbSetFees.setSelectedItem(feesNumber);
    }

    /**
     * Verifies the information is completed.
     *
     * @return
     */
    public boolean verifyInformation() {
        if (tfAmountOfDebt.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El monto de la deuda es obligatorio",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!isNumeric(tfAmountOfDebt.getText().split(" ")[1])) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor ingrese un numero valido como monto de la deuda",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfDateOfCreation.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "La fecha de efectuacion del convenio es obligatoria",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfDateOfExpiration.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "La fecha de vencimiento del convenio es obligatoria",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfTaxPayer.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "La seleccion del titular del convenio es obligatoria",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        switch (cmbConcept.getSelectedItem().toString().split(" : ")[0]) {
            case "1201":
            case "110101":
            case "110201":
                if (tfLandProperty.getText().equals("")) {
                    JOptionPane.showMessageDialog(
                            this,
                            "La seleccion del terreno / inmueble es obligatorio para este concepto",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                break;
            case "110104":
                if (tfVehicle.getText().equals("")) {
                    JOptionPane.showMessageDialog(
                            this,
                            "La seleccion del vehiculo es obligatorio para este concepto",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * Gets the agreement number from text field.
     *
     * @return
     */
    public String getTfAgreementNumber() {
        return tfAgreementNumber.getText();
    }

    /**
     * Gets the amount of the debt.
     *
     * @return
     */
    public String getTfAmountOfDebt() {
        return tfAmountOfDebt.getText().split(" ")[1];
    }

    /**
     * Gets the agreement date of creation.
     *
     * @return
     */
    public String getTfDateOfCreation() {
        return tfDateOfCreation.getText();
    }

    /**
     * Gets the agreement date of expiration.
     *
     * @return
     */
    public String getTfDateOfExpiration() {
        return tfDateOfExpiration.getText();
    }

    /**
     * Gets the agreement description from text field.
     *
     * @return
     */
    public String getTfDescription() {
        return tfDescription.getText().replace("    ", "");
    }

    /**
     * Gets the total number of cuots.
     *
     * @return
     */
    public String getCmbFeesNumber() {
        return cmbSetFees.getSelectedItem().toString();
    }

    /**
     * Gets the domain of the vehicle.
     *
     * @return
     */
    public String getCmbConcept() {
        return cmbConcept.getSelectedItem().toString().split(" : ")[0];
    }

    /**
     * Gets the domain of the vehicle.
     *
     * @return //
     */
    public String getTfVehicle() {
        return tfVehicle.getText().split(" : ")[0];
    }

    /**
     * Gets the domain of the vehicle.
     *
     * @return
     */
    public String getTfLandProperty() {
        return tfLandProperty.getText().split(" : ")[0];
    }

    /**
     * Gets the domain of the vehicle.
     *
     * @return
     */
    public String getTfTaxPayer() {
        return tfTaxPayer.getText().split(" : ")[0];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSaveAgreement = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tfAgreementNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfDateOfExpiration = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfAmountOfDebt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnSetTaxpayer = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        tfTaxPayer = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tfVehicle = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfLandProperty = new javax.swing.JTextField();
        cmbSetFees = new javax.swing.JComboBox<>();
        btnSetVehicle = new javax.swing.JButton();
        btnSetLandProperty = new javax.swing.JButton();
        btnEraseTaxPayer = new javax.swing.JButton();
        btnEraseVehicle = new javax.swing.JButton();
        btnEraseLandProperty = new javax.swing.JButton();
        btnDateOfCreation = new javax.swing.JButton();
        btnDateOfExpiration = new javax.swing.JButton();
        tfDateOfCreation = new javax.swing.JTextField();
        btnNewTaxpayer = new javax.swing.JButton();
        btnNewVehicle = new javax.swing.JButton();
        btnNewLandProperty = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfDescription = new javax.swing.JTextArea();
        cmbConcept = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO DE CONVENIO");
        setMinimumSize(new java.awt.Dimension(738, 776));
        setResizable(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(null);

        btnSaveAgreement.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSaveAgreement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Guardar.png"))); // NOI18N
        btnSaveAgreement.setText("Guardar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(600, Short.MAX_VALUE)
                .addComponent(btnSaveAgreement)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnSaveAgreement)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        tfAgreementNumber.setEditable(false);
        tfAgreementNumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfAgreementNumber.setForeground(new java.awt.Color(0, 0, 0));
        tfAgreementNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nro. Convenio :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Concepto :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Fecha Vencimiento :");

        tfDateOfExpiration.setEditable(false);
        tfDateOfExpiration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDateOfExpiration.setForeground(new java.awt.Color(0, 0, 0));
        tfDateOfExpiration.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Cantidad de cuotas :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Fecha Efectuacion  :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Monto de la deuda :");

        tfAmountOfDebt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfAmountOfDebt.setForeground(new java.awt.Color(0, 0, 0));
        tfAmountOfDebt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfAmountOfDebt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAmountOfDebtKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Descripcion :");

        btnSetTaxpayer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSetTaxpayer.setText("...");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Titular :");

        tfTaxPayer.setEditable(false);
        tfTaxPayer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTaxPayer.setForeground(new java.awt.Color(0, 0, 0));
        tfTaxPayer.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Vehiculo:");

        tfVehicle.setEditable(false);
        tfVehicle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfVehicle.setForeground(new java.awt.Color(0, 0, 0));
        tfVehicle.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Terreno / Inmueble :");

        tfLandProperty.setEditable(false);
        tfLandProperty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfLandProperty.setForeground(new java.awt.Color(0, 0, 0));
        tfLandProperty.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cmbSetFees.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbSetFees.setForeground(new java.awt.Color(0, 0, 0));
        cmbSetFees.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        btnSetVehicle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSetVehicle.setText("...");

        btnSetLandProperty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSetLandProperty.setText("...");

        btnEraseTaxPayer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEraseTaxPayer.setForeground(new java.awt.Color(255, 0, 0));
        btnEraseTaxPayer.setText("X");
        btnEraseTaxPayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseTaxPayerActionPerformed(evt);
            }
        });

        btnEraseVehicle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEraseVehicle.setForeground(new java.awt.Color(255, 0, 0));
        btnEraseVehicle.setText("X");
        btnEraseVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseVehicleActionPerformed(evt);
            }
        });

        btnEraseLandProperty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEraseLandProperty.setForeground(new java.awt.Color(255, 0, 0));
        btnEraseLandProperty.setText("X");

        btnDateOfCreation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDateOfCreation.setText("...");
        btnDateOfCreation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateOfCreationActionPerformed(evt);
            }
        });

        btnDateOfExpiration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDateOfExpiration.setText("...");
        btnDateOfExpiration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateOfExpirationActionPerformed(evt);
            }
        });

        tfDateOfCreation.setEditable(false);
        tfDateOfCreation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDateOfCreation.setForeground(new java.awt.Color(0, 0, 0));
        tfDateOfCreation.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnNewTaxpayer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewTaxpayer.setText("Nuevo");

        btnNewVehicle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewVehicle.setText("Nuevo");

        btnNewLandProperty.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewLandProperty.setText("Nuevo");

        tfDescription.setColumns(20);
        tfDescription.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDescription.setForeground(new java.awt.Color(0, 0, 0));
        tfDescription.setRows(5);
        jScrollPane1.setViewportView(tfDescription);

        cmbConcept.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbConcept.setForeground(new java.awt.Color(0, 0, 0));
        cmbConcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbConceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTaxPayer)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfAgreementNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDateOfCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDateOfCreation))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDateOfExpiration, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDateOfExpiration)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfAmountOfDebt)
                            .addComponent(cmbSetFees, 0, 153, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbConcept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfVehicle)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewLandProperty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetLandProperty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEraseLandProperty))
                    .addComponent(tfLandProperty)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewTaxpayer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetTaxpayer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEraseTaxPayer))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewVehicle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetVehicle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEraseVehicle)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAgreementNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbConcept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(btnDateOfCreation)
                            .addComponent(tfDateOfCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDateOfExpiration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(btnDateOfExpiration)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tfAmountOfDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cmbSetFees, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(btnNewTaxpayer)
                    .addComponent(btnSetTaxpayer)
                    .addComponent(btnEraseTaxPayer))
                .addGap(6, 6, 6)
                .addComponent(tfTaxPayer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(btnNewVehicle)
                    .addComponent(btnSetVehicle)
                    .addComponent(btnEraseVehicle))
                .addGap(6, 6, 6)
                .addComponent(tfVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(btnNewLandProperty)
                    .addComponent(btnSetLandProperty)
                    .addComponent(btnEraseLandProperty))
                .addGap(6, 6, 6)
                .addComponent(tfLandProperty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfAmountOfDebtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAmountOfDebtKeyReleased
        if ((!tfAmountOfDebt.getText().equals("")) && (!tfAmountOfDebt.getText().contains("$"))) {
            tfAmountOfDebt.setText("$ " + tfAmountOfDebt.getText());
        } else if(tfAmountOfDebt.getText().equals("$ ")) {
            tfAmountOfDebt.setText("");
        }
    }//GEN-LAST:event_tfAmountOfDebtKeyReleased

    /**
     * Shows the date chooser and captures the selected date.
     */
    public void showDateChooser() {
        this.dateChooserDialog1 = new DateChooserDialog();
        this.dateChooserDialog1.showDialog(null);
    }

    private void btnDateOfCreationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateOfCreationActionPerformed
        showDateChooser();
        Calendar calendar = this.dateChooserDialog1.getSelectedDate();
        this.tfDateOfCreation.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1)
                + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }//GEN-LAST:event_btnDateOfCreationActionPerformed

    private void btnDateOfExpirationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateOfExpirationActionPerformed
        showDateChooser();
        Calendar calendar = this.dateChooserDialog1.getSelectedDate();
        this.tfDateOfExpiration.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1)
                + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }//GEN-LAST:event_btnDateOfExpirationActionPerformed

    private void btnEraseVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseVehicleActionPerformed
        this.tfVehicle.setText("");
    }//GEN-LAST:event_btnEraseVehicleActionPerformed

    private void btnEraseTaxPayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseTaxPayerActionPerformed
        this.tfTaxPayer.setText("");
    }//GEN-LAST:event_btnEraseTaxPayerActionPerformed

    private void cmbConceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConceptActionPerformed
        switch (cmbConcept.getSelectedItem().toString().split(" : ")[0]) {
            case "1201":
            case "110101":
            case "110201":
                enableLandPropertySelection(true);
                enableVehicleSelection(false);
                tfVehicle.setText("");
                break;
            case "110104":
                enableVehicleSelection(true);
                enableLandPropertySelection(false);
                tfLandProperty.setText("");
                break;
            default:
                enableVehicleSelection(false);
                tfVehicle.setText("");
                enableLandPropertySelection(false);
                tfLandProperty.setText("");
                break;
        }
    }//GEN-LAST:event_cmbConceptActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDateOfCreation;
    private javax.swing.JButton btnDateOfExpiration;
    private javax.swing.JButton btnEraseLandProperty;
    private javax.swing.JButton btnEraseTaxPayer;
    private javax.swing.JButton btnEraseVehicle;
    private javax.swing.JButton btnNewLandProperty;
    private javax.swing.JButton btnNewTaxpayer;
    private javax.swing.JButton btnNewVehicle;
    private javax.swing.JButton btnSaveAgreement;
    private javax.swing.JButton btnSetLandProperty;
    private javax.swing.JButton btnSetTaxpayer;
    private javax.swing.JButton btnSetVehicle;
    private javax.swing.JComboBox<String> cmbConcept;
    private javax.swing.JComboBox<String> cmbSetFees;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfAgreementNumber;
    private javax.swing.JTextField tfAmountOfDebt;
    private javax.swing.JTextField tfDateOfCreation;
    private javax.swing.JTextField tfDateOfExpiration;
    private javax.swing.JTextArea tfDescription;
    private javax.swing.JTextField tfLandProperty;
    private javax.swing.JTextField tfTaxPayer;
    private javax.swing.JTextField tfVehicle;
    // End of variables declaration//GEN-END:variables
}
