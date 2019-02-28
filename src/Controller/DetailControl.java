package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.PropertyManager;
import Model.TaxpayersManager;
import Model.UsersManager;
import View.AgreementDetails;
import View.AgreementsList;
import View.ConceptsList;
import View.PaymentRegistration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez.
 */
public class DetailControl implements ActionListener, MouseListener, KeyListener {

    AgreementDetails agreementDV;
    AgreementsList agreementsLV;
    ConceptsList conceptsLV;
    PaymentRegistration paymentRV;
    Timer refreshTimer;//Timer for refreshing views

    /**
     * Constructor
     */
    public DetailControl() {
        this.agreementsLV = new AgreementsList();
        this.conceptsLV = new ConceptsList();
        this.agreementsLV.setController(this);
        this.conceptsLV.setController(this);
    }

    /**
     * Will get the info of a payment from view.
     *
     * @return
     */
    private EnumMap<AgreementsManager.payment_param, String> getPaymentInfo() {
        EnumMap<AgreementsManager.payment_param, String> payMap
                = new EnumMap<>(AgreementsManager.payment_param.class);
        payMap.put(
                AgreementsManager.payment_param.RECEIPT_NUMBER,
                paymentRV.getTfReceiptNumber());
        payMap.put(
                AgreementsManager.payment_param.AMOUNT,
                paymentRV.getTfAmount());
        payMap.put(
                AgreementsManager.payment_param.DATE_OF_PAYMENT,
                paymentRV.getTfDate());
        payMap.put(
                AgreementsManager.payment_param.AGREEMENT_NUMBER,
                agreementDV.getTfAgreementNumber());
        payMap.put(
                AgreementsManager.payment_param.FEE, paymentRV.getCmbFee());
        return payMap;
    }

    /**
     * Will fill agreements padron table.
     */
    private DefaultTableModel fillModelForPadron(List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL) {
        TaxpayersManager tm = new TaxpayersManager();
        ParametersManager pm = new ParametersManager();

        //Will help me to define the format of amount
        DecimalFormat format = new DecimalFormat("##,###,###.00");
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        dfs.setGroupingSeparator(',');
        format.setDecimalFormatSymbols(dfs);

        //Table model that is going to be filled with information of the agreements.
        DefaultTableModel tableModel
                = new DefaultTableModel(null, new String[]{
            "Nombre Titular",
            "Nro. Doc Titular",
            "Nro. Convenio",
            "Monto deuda",
            "Nro. cuotas",
            "Concepto deuda",
            "Estado"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on paymentsEL
        for (EnumMap<AgreementsManager.agreement_param, String> a : agreementsEL) {

            String amount = format.format(Double.valueOf(a.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT)));

            //Consulting the respecting taxpayer for the agreement.
            EnumMap<TaxpayersManager.taxpayer_param, String> tpMap
                    = tm.consult(Long.parseLong(a.get(AgreementsManager.agreement_param.TAXPAYER)));
            //Consulting the respecting landPropMap for the agreement.
            EnumMap<ParametersManager.concept_param, String> ctMap
                    = pm.consult(Integer.parseInt(a.get(AgreementsManager.agreement_param.CONCEPT)));
            //Array of objects representing a row.
            Object nuevo[] = new Object[]{
                tpMap.get(TaxpayersManager.taxpayer_param.NAMES)
                + " " + tpMap.get(TaxpayersManager.taxpayer_param.LASTNAME),
                tpMap.get(TaxpayersManager.taxpayer_param.DOC_NUMBER),
                a.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER),
                "$ " + amount, //amount of debt
                a.get(AgreementsManager.agreement_param.FEES_NUMBER),
                ctMap.get(ParametersManager.concept_param.CONCEPT_NAME),
                a.get(AgreementsManager.agreement_param.STATUS)};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        return tableModel;
    }

    private void fillPadronByTaxpayerDocNumber(long taxpayerDocNumber) {
        AgreementsManager am = new AgreementsManager();
        List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL = am.consultAgreementForTaxpayer(taxpayerDocNumber);
        agreementsLV.setTableModel(fillModelForPadron(agreementsEL));
    }

    private void fillPadronByAgreementNumber(long agreementNumber) {
        AgreementsManager am = new AgreementsManager();
        List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL = am.consultAgreementForNumberLike(agreementNumber);
        agreementsLV.setTableModel(fillModelForPadron(agreementsEL));
    }

    private void fillPadron() {
        AgreementsManager am = new AgreementsManager();
        List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL = am.consultAllAgreements(agreementsLV.consultedRegistriesCount());
        agreementsLV.setTableModel(fillModelForPadron(agreementsEL));
    }

    /**
     * Will show the view that contains the complete information of an
     * agreement.
     *
     * @param agreementNumber
     */
    private void fillAgreementDetailsView(Long agreementNumber) {
        AgreementsManager am = new AgreementsManager();
        TaxpayersManager tm = new TaxpayersManager();
        ParametersManager pm = new ParametersManager();
        PropertyManager lpm = new PropertyManager();

        //Will help me to define the format of amount
        DecimalFormat format = new DecimalFormat("##,###,###.00");
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        dfs.setGroupingSeparator(',');
        format.setDecimalFormatSymbols(dfs);

        //Consulting the agreement.
        EnumMap<AgreementsManager.agreement_param, String> agreementMap
                = am.consultAgreement(agreementNumber);

        String amount = format.format(Double.valueOf(agreementMap.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT)));

        agreementDV.setTfagreementNumber(
                agreementMap.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER));
        agreementDV.setTfAmountOfDebt(
                "$ " + amount);
        agreementDV.setTfCreationDate(
                agreementMap.get(AgreementsManager.agreement_param.CREATION_DATE));
        agreementDV.setTfExpirationDate(
                agreementMap.get(AgreementsManager.agreement_param.EXPIRATION_DATE));
        agreementDV.setTfCuotsNumber(
                agreementMap.get(AgreementsManager.agreement_param.FEES_NUMBER));
        agreementDV.setTfDescription(
                agreementMap.get(AgreementsManager.agreement_param.DESCRIPTION));
        //Consulting the taxpayer.
        EnumMap<TaxpayersManager.taxpayer_param, String> taxpayerMap
                = tm.consult(Long.parseLong(
                        agreementMap.get(AgreementsManager.agreement_param.TAXPAYER)));
        agreementDV.setTfTaxpayerName(
                taxpayerMap.get(TaxpayersManager.taxpayer_param.NAMES));
        agreementDV.setTfTaxpayerLastname(
                taxpayerMap.get(TaxpayersManager.taxpayer_param.LASTNAME));
        agreementDV.setTfTaxpayerDocNumber(
                taxpayerMap.get(TaxpayersManager.taxpayer_param.DOC_NUMBER));
        agreementDV.setTfTaxpayerAddress(
                taxpayerMap.get(TaxpayersManager.taxpayer_param.ADDRESS));
        agreementDV.setTfTaxpayerPhone(
                taxpayerMap.get(TaxpayersManager.taxpayer_param.PHONE_NUMBER));
        //Consulting the concept.
        EnumMap<ParametersManager.concept_param, String> conceptMap
                = pm.consult(Integer.parseInt(agreementMap.get(AgreementsManager.agreement_param.CONCEPT)));
        agreementDV.setTfConcept(conceptMap.get(ParametersManager.concept_param.CONCEPT_CODE) + " : "
                + conceptMap.get(ParametersManager.concept_param.CONCEPT_NAME));
        //If the concept is related to vehicle or land / property
        switch (conceptMap.get(ParametersManager.concept_param.CONCEPT_CODE)) {
            case "1201":
            case "110101":
            case "110201":
                EnumMap<PropertyManager.landProperty_param, String> landPropMap
                        = lpm.consultLandProperty(Long.parseLong(agreementMap.get(AgreementsManager.agreement_param.LAND_PROPERTY)));
                agreementDV.setTfLandPropDecree(
                        landPropMap.get(PropertyManager.landProperty_param.DECREE));
                agreementDV.setTfLandPropAppleBatch(
                        landPropMap.get(PropertyManager.landProperty_param.APPLE)
                        + "/" + landPropMap.get(PropertyManager.landProperty_param.BATCH));
                agreementDV.setTfLandPropAddress(
                        landPropMap.get(PropertyManager.landProperty_param.ADDRESS));
                break;
            case "110104":
                EnumMap<PropertyManager.vehicle_param, String> vehicleMap
                        = lpm.consultVehicle(Long.parseLong(agreementMap.get(AgreementsManager.agreement_param.VEHICLE)));
                agreementDV.setTfVehicleManufacturer(
                        vehicleMap.get(PropertyManager.vehicle_param.MANUFACTURER));
                agreementDV.setTfVehicleType(
                        vehicleMap.get(PropertyManager.vehicle_param.TYPE));
                agreementDV.setTfVehicleDomain(
                        vehicleMap.get(PropertyManager.vehicle_param.DOMAIN));
                agreementDV.setTfVehicleModel(
                        vehicleMap.get(PropertyManager.vehicle_param.MODEL));
                break;
        }
        /**
         * Checking agreement status...
         */
        UsersManager um = UsersManager.getUsersManager();
        fillAgreementPaymentsList();
        if (um.checkUserPermitions() == true) {//If user is administrator
            agreementDV.enableAdminFunctions(true);
        }
        if (agreementMap.get(AgreementsManager.agreement_param.STATUS).equals(AgreementsManager.agreement_status.WITHOUT_EFFECT.getValue())) {
//            agreementDV.setTfStatus(AgreementsManager.agreement_status.WITHOUT_EFFECT.getValue());
            agreementDV.setWithoutEffect(true);
        } else {
            if (Integer.parseInt(agreementDV.getTfFee()) == agreementDV.getNumberOfPayments()) {
                agreementDV.setCancelled(true);
//                agreementDV.setTfStatus(AgreementsManager.agreement_status.CANCELLED.getValue());
            } else {
                agreementDV.setCancelled(false);
//                agreementDV.setTfStatus(AgreementsManager.agreement_status.VALID.getValue());
            }
        }
    }

    /**
     * Will fill agreement payments table.
     */
    private void fillAgreementPaymentsList() {
        AgreementsManager am = new AgreementsManager();
        //Will help me to define the format of amount
        DecimalFormat format = new DecimalFormat("##,###,###.00");
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        dfs.setGroupingSeparator(',');
        format.setDecimalFormatSymbols(dfs);

        //Consulting Agreements information.
        List<EnumMap<AgreementsManager.payment_param, String>> paymentsEL
                = am.consultPaymentsForAgreement(Long.parseLong(agreementDV.getTfAgreementNumber()));
        //Table model that is going to be filled with information of the agreements.
        DefaultTableModel tableModel
                = new DefaultTableModel(null, new String[]{
            "Identificador",
            "Cuota",
            "Nro. recibo",
            "Monto",
            "Fecha"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on paymentsEL
        for (EnumMap<AgreementsManager.payment_param, String> p : paymentsEL) {
            String amount = format.format(Double.valueOf(p.get(AgreementsManager.payment_param.AMOUNT)));
            //Array of objects representing a row.
            Object nuevo[] = new Object[]{
                p.get(AgreementsManager.payment_param.PAYMENT_ID),
                p.get(AgreementsManager.payment_param.FEE),
                p.get(AgreementsManager.payment_param.RECEIPT_NUMBER),
                "$ " + amount,
                p.get(AgreementsManager.payment_param.DATE_OF_PAYMENT)};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        agreementDV.setTableModel(tableModel); //Setting table model to the view.
    }

    /**
     * Fills concepts table
     */
    public void fillConceptsList() {
        ParametersManager pm = new ParametersManager();
        //Agreements information.
        List<EnumMap<ParametersManager.concept_param, String>> conceptsEL = pm.consultAll();
        //Table model that is going to be filled with information of the agreements.
        DefaultTableModel tableModel
                = new DefaultTableModel(null, new String[]{
            "Codigo",
            "Nombre"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on conceptsEL
        for (EnumMap<ParametersManager.concept_param, String> ct : conceptsEL) {
            //Array of objects representing ct row.
            Object nuevo[] = new Object[]{
                ct.get(ParametersManager.concept_param.CONCEPT_CODE),
                ct.get(ParametersManager.concept_param.CONCEPT_NAME)};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        conceptsLV.setTableModel(tableModel); //Setting table model to the view.
    }

    /**
     * Will show payment registration view.
     *
     */
    public void showPaymentRegistrationView() {
        AgreementsManager am = new AgreementsManager();
        paymentRV = new PaymentRegistration(agreementDV, true);

        //Setting amount predifined on amount box
        DecimalFormat format = new DecimalFormat("##,###,###.00");
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();

        dfs.setDecimalSeparator('.');
        dfs.setGroupingSeparator(',');
        format.setDecimalFormatSymbols(dfs);
        paymentRV.setTfAmount(format.format(am.feeValueOf(Double.valueOf(agreementDV.getTfAmountOfDebt()), Integer.parseInt(agreementDV.getTfFee()))));

        paymentRV.setCmbFee(agreementDV.getTfFee());
        paymentRV.setController(this);
        paymentRV.setLocationRelativeTo(null);
        paymentRV.setVisible(true);
    }

    /**
     * Will show the agreement list view.
     */
    public void ShowAgreementListView() {
        AgreementsManager am = new AgreementsManager();
        double agreementsNumber = am.agreementsCount();
        if (agreementsNumber != 0) {

            agreementsLV.setStaticPage((int) Math.ceil(agreementsNumber / 100));
            agreementsLV.incrementChangingPage();

            fillPadron();
            refreshTimer = new Timer(120000, this);
            refreshTimer.setActionCommand("REFRESH_AGREEMENTS_LIST_TABLE");
            refreshTimer.start();
        }
        agreementsLV.setVisible(true); //Making visible the view.
        agreementsLV.setLocationRelativeTo(null); //Centering the view.
    }

    /**
     * Wil show the view that contains all the concepts info.
     */
    public void showConceptsListView() {
        fillConceptsList();
        conceptsLV.setVisible(true); //Making visible the view.
        conceptsLV.setLocationRelativeTo(null); //Centering the view.
    }

    public void showAgreementDetailsView(Long agreementNumber) {
        //Preparing view
        agreementDV = new AgreementDetails();
        agreementDV.setController(this);
        fillAgreementDetailsView(agreementNumber);
        agreementDV.setLocationRelativeTo(null);
        agreementDV.setVisible(true);
    }

    /**
     * Overrides actionPerformed so it can process all actions coming from
     * views.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        RegistrationControl rc;
        AgreementsManager am;
        ParametersManager pm;
        switch (ae.getActionCommand()) {

            case "CONCEPT_REGISTRATION":
                rc = new RegistrationControl();
                rc.showConceptRegistrationView(conceptsLV);
                fillConceptsList();
                break;

            case "LEAVE_WITHOUT_EFFECT":
                if (JOptionPane.showConfirmDialog(
                        agreementDV,
                        "Si deja sin  efecto el convenio, no podra realizar operaciones sobre el mismo ¿Desea continuar?",
                        "Advertencia!",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    am = new AgreementsManager(); //New agreements manager
                    am.changeAgreementStatus(Long.parseLong(agreementDV.getTfAgreementNumber()), AgreementsManager.agreement_status.WITHOUT_EFFECT);
                    fillPadron(); //Refilling table model again to refresh changes. 
                    agreementDV.dispose();
                }
                break;

            case "SHOW_PAYMENT_REGISTRATION_VIEW":
                showPaymentRegistrationView();
                break;

            case "SAVE_PAYMENT":
                am = new AgreementsManager();
                if (paymentRV.verifyInformation()) {//Verifing information is complete
                    //If receipt date is after agreement creation date 
                    if (Date.valueOf(paymentRV.getTfDate()).after(Date.valueOf(agreementDV.getTfCreationDate()))
                            || Date.valueOf(agreementDV.getTfCreationDate()).equals(Date.valueOf(paymentRV.getTfDate()))) {
                        //If amount is less tha fee value for this agreement
                        if (Double.valueOf(paymentRV.getTfAmount()) < am.feeValueOf(Double.valueOf(
                                agreementDV.getTfAmountOfDebt()),
                                Integer.parseInt(agreementDV.getTfFee()))) {
                            if (JOptionPane.showConfirmDialog(
                                    agreementDV,
                                    "Va a ingresar un monto menor al del valor de la cuota ¿Desea continuar?",
                                    "Advertencia!",
                                    JOptionPane.YES_NO_OPTION) == 0) {
                            } else {
                                break; //if no we stop execution
                            }
                        }
                        /**
                         * We must compare if receipt exists... and if exists
                         * then... if its on same agreement, and for the same
                         * fee.
                         */
                        if (am.receiptExists(Long.parseLong(paymentRV.getTfReceiptNumber()))) {
                            //If payment with specified receipt exists on agreement
                            if (am.receiptExistsForAgreement(
                                    Long.parseLong(paymentRV.getTfReceiptNumber()),
                                    Long.parseLong(agreementDV.getTfAgreementNumber()))) {
                                //If payment exists for agreement
                                if (am.paymentExistsForAgreement(getPaymentInfo())) {
                                    JOptionPane.showMessageDialog(paymentRV,
                                            "Un pago por la misma cuota ya se encuentra registrado",
                                            "Advertencia",
                                            JOptionPane.WARNING_MESSAGE);
                                    break;
                                } else {
                                    if (am.newPayment(getPaymentInfo())) {
                                        JOptionPane.showMessageDialog(paymentRV,
                                                "Se ha registrado con exito",
                                                "Informacion",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        paymentRV.dispose();
                                        break;
                                    } else {
                                        JOptionPane.showMessageDialog(paymentRV,
                                                "No se ha podido registrar",
                                                "Advertencia",
                                                JOptionPane.WARNING_MESSAGE);
                                        break;
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(paymentRV,
                                    "El recibo ya se encuentra registrado para otro convenio",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        //If payment exists for agreement
                        if (am.paymentExistsForAgreement(getPaymentInfo())) {
                            JOptionPane.showMessageDialog(paymentRV,
                                    "Un pago por la misma cuota ya se encuentra registrado",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        } else if (am.newPayment(getPaymentInfo())) { //If payment does not exists
                            JOptionPane.showMessageDialog(paymentRV,
                                    "Se ha registrado con exito",
                                    "Informacion",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paymentRV.dispose();
                            fillAgreementDetailsView(Long.parseLong(agreementDV.getTfAgreementNumber()));
                            break;
                        } else {
                            JOptionPane.showMessageDialog(paymentRV,
                                    "No se ha podido registrar",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(paymentRV,
                            "No se puede registrar un pago con una fecha anterior a la de la creacion del convenio",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;

            case "MAKE_AGREEMENTS_PADRON_REPORT":
                am = new AgreementsManager();
                am.agreementsReport();
                break;

            case "MAKE_AGREEMENT_DETAILS_REPORT":
                am = new AgreementsManager();
                am.agreementDetails(Long.parseLong(agreementDV.getTfAgreementNumber()));
                break;

            case "MODIFY_AGREEMENT":
                if (JOptionPane.showConfirmDialog(
                        agreementDV,
                        "Esta ventana se cerrará ¿Desea continuar?",
                        "Informacion!",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    rc = new RegistrationControl();
                    rc.showAgreementModificationView(
                            Long.parseLong(agreementDV.getTfAgreementNumber()));
                    agreementDV.dispose();
                }
                break;

            case "DELETE_PAYMENT":
                if (JOptionPane.showConfirmDialog(
                        agreementDV,
                        "¿Esta seguro de que desea eliminar el pago?",
                        "Advertencia!",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    am = new AgreementsManager();
                    if (am.deletePayment(
                            Long.parseLong(agreementDV.getSelectedPaymentID()),
                            Long.parseLong(agreementDV.getSelectedReceiptNumber())) == true) {
                        JOptionPane.showMessageDialog(
                                agreementDV,
                                "La eliminacion fue correcta",
                                "Informacion!",
                                JOptionPane.INFORMATION_MESSAGE);
                        fillAgreementDetailsView(Long.parseLong(agreementDV.getTfAgreementNumber()));
                    } else {
                        JOptionPane.showMessageDialog(
                                agreementDV,
                                "La eliminacion no se pudo realizar",
                                "Advertencia!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;

            case "DELETE_CONCEPT":
                if (JOptionPane.showConfirmDialog(
                        agreementDV,
                        "¿Esta seguro de que desea eliminar el pago?",
                        "Advertencia!",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    pm = new ParametersManager();
                    if (!pm.isDefaultConcept(Long.parseLong(conceptsLV.getSelectedConcept()))) {
                        am = new AgreementsManager();
                        if (am.countAgreementsForConcept(Long.parseLong(conceptsLV.getSelectedConcept())) == 0) {
                            if (pm.deleteConcept(Long.parseLong(conceptsLV.getSelectedConcept()))) {
                                JOptionPane.showMessageDialog(
                                        conceptsLV,
                                        "Se ha eliminado el concepto",
                                        "Informacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                                fillConceptsList();
                                break;
                            }
                            JOptionPane.showMessageDialog(
                                    conceptsLV,
                                    "No se ha podido eliminar el concepto",
                                    "Advertencia!",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        JOptionPane.showMessageDialog(
                                conceptsLV,
                                "El concepto que intenta eliminar esta siendo usado",
                                "Advertencia!",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    JOptionPane.showMessageDialog(
                            conceptsLV,
                            "La eliminacion de este concepto esta restringida por el sistema",
                            "Advertencia!",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;

            case "REFRESH_AGREEMENTS_LIST_TABLE":
                if (agreementsLV.isVisible()) {
                    fillPadron();
                    System.out.println("ME ACTUALICE");
                } else {
                    refreshTimer.stop();
                }
                break;

            case "FORWARD_AGREEMENTS_LIST_TABLE":
                if (agreementsLV.getStaticPage() != agreementsLV.getChangingPage() && agreementsLV.getChangingPage() != 0) {
                    agreementsLV.incrementConsultedRegistriesCount();
                    fillPadron();
                    agreementsLV.incrementChangingPage();
                }
                break;

            case "BACK_AGREEMENTS_LIST_TABLE":
                if (agreementsLV.getChangingPage() != 1 && agreementsLV.getChangingPage() != 0) {
                    agreementsLV.decrementConsultedRegistriesCount();
                    fillPadron();
                    agreementsLV.decrementChangingPage();
                }
                break;
        }
    }

    /**
     *
     * @param me
     */
    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getClickCount() == 2) {
            showAgreementDetailsView(agreementsLV.getSelectedAgreementNumber());
        }
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        if (agreementsLV != null) {
            if (ke.getSource().equals(agreementsLV.getTfSearch()) && agreementsLV.getTfSearchText().length() > 1) {
                if (agreementsLV.searchByTaxpayerDoc()) {
                    fillPadronByTaxpayerDocNumber(Long.parseLong(agreementsLV.getTfSearchText()));
                } else if (agreementsLV.searchByAgreementNumber()) {
                    fillPadronByAgreementNumber(Long.parseLong(agreementsLV.getTfSearchText()));
                }
            } else {
                fillPadron();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (agreementsLV.getTfSearchText().length() == 8) {
            ke.consume();
        }
        char c = ke.getKeyChar();
        if (Character.isDigit(c)) {
        } else {
            ke.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

}
