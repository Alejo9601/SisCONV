package View;

import Controller.DetailControl;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alejandro Juarez
 */
public class AgreementsList extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public AgreementsList() {
        initComponents();
    }

//    /**
//     * Will filter agreements table.
//     */
//    public void filterAgreements() {
//        TableRowSorter trsFiltro = new TableRowSorter(tblAgreements.getModel());
//        if (chkAgreementNumber.isSelected() || chkTaxPayerDoc.isSelected() || chkTaxPayerLastname.isSelected()) {
//            if (chkAgreementNumber.isSelected()) {
//                trsFiltro.setRowFilter(RowFilter.regexFilter(tfSearch.getText().toUpperCase(), 2));
//            } else {
//                if (chkTaxPayerDoc.isSelected()) {
//                    trsFiltro.setRowFilter(RowFilter.regexFilter(tfSearch.getText().toUpperCase(), 1));
//                } else {
//                    trsFiltro.setRowFilter(RowFilter.regexFilter(tfSearch.getText().toUpperCase(), 0));
//                }
//            }
//            tblAgreements.setRowSorter(trsFiltro);
//        }
//        lblNumberAgreements.setText(Integer.toString(tblAgreements.getRowCount()));
//    }
    /**
     *
     */
    public void incrementChangingPage() {
        this.changingPage.setText(Integer.toString(Integer.parseInt(changingPage.getText()) + 1));
    }

    /**
     *
     */
    public void decrementChangingPage() {
        this.changingPage.setText(Integer.toString(Integer.parseInt(changingPage.getText()) - 1));
    }

    /**
     *
     * @param page
     */
    public void setStaticPage(int page) {
        this.staticPage.setText(Integer.toString(page));
    }

    /**
     *
     * @return
     */
    public int getChangingPage() {
        return Integer.parseInt(changingPage.getText());
    }

    /**
     *
     * @return
     */
    public int getStaticPage() {
        return Integer.parseInt(staticPage.getText());
    }

    /**
     *
     * @return
     */
    public long consultedRegistriesCount() {
        return Long.parseLong(lblConsultedRegistersCount.getText());
    }

    /**
     *
     * @param count
     */
    public void setConsultedRegistriesCount(long count) {
        lblConsultedRegistersCount.setText(Long.toString(count));
    }

    /**
     *
     */
    public void incrementConsultedRegistriesCount() {
        lblConsultedRegistersCount.setText(Long.toString(Long.parseLong(lblConsultedRegistersCount.getText()) + 100));
        System.out.print(lblConsultedRegistersCount.getText());
    }

    /**
     *
     */
    public void decrementConsultedRegistriesCount() {
        lblConsultedRegistersCount.setText(Long.toString(Long.parseLong(lblConsultedRegistersCount.getText()) - 100));
        System.out.println(lblConsultedRegistersCount.getText());
    }

    /**
     * Will set the controller of this view.
     *
     * @param control
     */
    public void setController(DetailControl control) {
        tblAgreements.addMouseListener(control);
        tfSearch.addKeyListener(control);
        btnMakeReport.addActionListener(control);
        btnMakeReport.setActionCommand("MAKE_AGREEMENTS_PADRON_REPORT");
        btnForward.addActionListener(control);
        btnForward.setActionCommand("FORWARD_AGREEMENTS_LIST_TABLE");
        btnBack.addActionListener(control);
        btnBack.setActionCommand("BACK_AGREEMENTS_LIST_TABLE");
    }

    public boolean searchByAgreementNumber() {
        return chkAgreementNumber.isSelected();
    }

    public boolean searchByTaxpayerDoc() {
        return chkTaxPayerDoc.isSelected();
    }

    /**
     * Gets the agreement number of the selected row.
     *
     * @return
     */
    public Long getSelectedAgreementNumber() {
        return Long.parseLong((String) tblAgreements.getValueAt(tblAgreements.getSelectedRow(), 2));
    }

    /**
     * Sets the model for the table.
     *
     * @param model
     */
    public void setTableModel(DefaultTableModel model) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER); //CENTER o LEFT
        tblAgreements.setModel(model);  //Setting model
        tblAgreements.setRowHeight(25); //Setting rows height
        tblAgreements.setFont(new java.awt.Font("Tahoma", 0, 18)); //Changing font
        tblAgreements.getTableHeader().setReorderingAllowed(false); //Disabling reordering columns
        for (int i = 0; i < tblAgreements.getColumnCount(); i++) {
            tblAgreements.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        lblNumberAgreements.setText(Integer.toString(tblAgreements.getRowCount()));
    }

    /**
     *
     * @return
     */
    public JTextField getTfSearch() {
        return this.tfSearch;
    }

    /**
     *
     * @return
     */
    public String getTfSearchText() {
        return this.tfSearch.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConsultedRegistersCount = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNumberAgreements = new javax.swing.JLabel();
        chkTaxPayerLastname = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnMakeReport = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        changingPage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        staticPage = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgreements = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        chkAgreementNumber = new javax.swing.JCheckBox();
        chkTaxPayerDoc = new javax.swing.JCheckBox();

        lblConsultedRegistersCount.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Registros en la tabla:");

        lblNumberAgreements.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblNumberAgreements.setText("-");

        chkTaxPayerLastname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkTaxPayerLastname.setText("Busqueda por apellido del titular");
        chkTaxPayerLastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTaxPayerLastnameActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PADRON GENERAL DE CONVENIOS");
        setMinimumSize(new java.awt.Dimension(1366, 768));

        jPanel1.setLayout(new java.awt.BorderLayout());

        btnMakeReport.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMakeReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/PDF.png"))); // NOI18N
        btnMakeReport.setText("Generar reporte");

        btnForward.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnForward.setText(">>");

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBack.setText("<<");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Pagina:");

        changingPage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        changingPage.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("de");

        staticPage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        staticPage.setText("0");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnForward)
                .addGap(101, 101, 101)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(changingPage)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(staticPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 675, Short.MAX_VALUE)
                .addComponent(btnMakeReport))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMakeReport)
                            .addComponent(btnForward)
                            .addComponent(btnBack)
                            .addComponent(jLabel2)
                            .addComponent(changingPage)
                            .addComponent(jLabel3)
                            .addComponent(staticPage))
                        .addGap(14, 14, 14))))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        tblAgreements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblAgreements);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tfSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        chkAgreementNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkAgreementNumber.setSelected(true);
        chkAgreementNumber.setText("Busqueda por Nro. Convenio");
        chkAgreementNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAgreementNumberActionPerformed(evt);
            }
        });

        chkTaxPayerDoc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkTaxPayerDoc.setText("Busqueda por doc. titular");
        chkTaxPayerDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTaxPayerDocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(chkAgreementNumber)
                .addGap(18, 18, 18)
                .addComponent(chkTaxPayerDoc)
                .addGap(18, 18, 18)
                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(449, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAgreementNumber)
                    .addComponent(chkTaxPayerDoc))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkAgreementNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAgreementNumberActionPerformed
        chkTaxPayerDoc.setSelected(false);
        chkTaxPayerLastname.setSelected(false);
    }//GEN-LAST:event_chkAgreementNumberActionPerformed

    private void chkTaxPayerDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTaxPayerDocActionPerformed
        chkAgreementNumber.setSelected(false);
        chkTaxPayerLastname.setSelected(false);
    }//GEN-LAST:event_chkTaxPayerDocActionPerformed

    private void chkTaxPayerLastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTaxPayerLastnameActionPerformed
        chkAgreementNumber.setSelected(false);
        chkTaxPayerDoc.setSelected(false);
    }//GEN-LAST:event_chkTaxPayerLastnameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnForward;
    private javax.swing.JButton btnMakeReport;
    private javax.swing.JLabel changingPage;
    private javax.swing.JCheckBox chkAgreementNumber;
    private javax.swing.JCheckBox chkTaxPayerDoc;
    private javax.swing.JCheckBox chkTaxPayerLastname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblConsultedRegistersCount;
    private javax.swing.JLabel lblNumberAgreements;
    private javax.swing.JLabel staticPage;
    private javax.swing.JTable tblAgreements;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
