package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.PropertyManager;
import Model.TaxpayerManager;
import View.AgreementDetails;
import View.AgreementsList;
import View.ConceptsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez.
 */
public class DetailControl implements ActionListener, MouseListener, KeyListener {

    AgreementDetails agreementDV;
    AgreementsList agreementsLV;
    ConceptsList conceptsLV;

    /**
     * Constructor
     */
    public DetailControl() {
        this.agreementDV = new AgreementDetails(true);
        this.agreementsLV = new AgreementsList();
        this.conceptsLV = new ConceptsList();
        this.agreementsLV.setController(this);
        this.conceptsLV.setController(this);
        this.agreementDV.setController(this);
    }

    /**
     * Will fill agreements padron table.
     */
    private void fillAgreementsPadron() {
        AgreementsManager am = new AgreementsManager();
        TaxpayerManager tm = new TaxpayerManager();
        ParametersManager pm = new ParametersManager();
        //Consulting Agreements information.
        List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL = am.consultAllAgreements();
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
            //Consulting the respecting taxpayer for the agreement.
            EnumMap<TaxpayerManager.taxpayer_param, String> tpMap
                    = tm.consult(Long.parseLong(a.get(AgreementsManager.agreement_param.TAXPAYER)));
            //Consulting the respecting landPropMap for the agreement.
            EnumMap<ParametersManager.concept_param, String> ctMap
                    = pm.consult(Integer.parseInt(a.get(AgreementsManager.agreement_param.CONCEPT)));
            //Status of agreement
            String status = "";
            if (a.get(
                    AgreementsManager.agreement_param.STATUS).equals(
                            AgreementsManager.agreement_status.WITHOUT_EFFECT.toString())) {
                status = "SIN EFECTO";
            } else if (a.get(
                    AgreementsManager.agreement_param.STATUS).equals(
                            AgreementsManager.agreement_status.VALID.toString())) {
                status = "VIGENTE";
            } else if (a.get(
                    AgreementsManager.agreement_param.STATUS).equals(
                            AgreementsManager.agreement_status.CANCELED.toString())) {
                status = "CANCELADO";
            }
            //Array of objects representing a row.
            Object nuevo[] = new Object[]{
                tpMap.get(TaxpayerManager.taxpayer_param.NAMES)
                + " " + tpMap.get(TaxpayerManager.taxpayer_param.LASTNAME),
                tpMap.get(TaxpayerManager.taxpayer_param.DOC_NUMBER),
                a.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER),
                a.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT),
                a.get(AgreementsManager.agreement_param.CUOTS_NUMBER),
                ctMap.get(ParametersManager.concept_param.CONCEPT_NAME),
                status};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        agreementsLV.setTableModel(tableModel); //Setting table model to the view.
    }

    /**
     * Will fill agreement payments table.
     */
    private void fillAgreementPaymentsList() {
        AgreementsManager am = new AgreementsManager();
        //Consulting Agreements information.
        List<EnumMap<AgreementsManager.payment_param, String>> paymentsEL
                = am.consultPaymentsForAgreement(Long.parseLong(agreementDV.getTfAgreementNumber()));
        //Table model that is going to be filled with information of the agreements.
        DefaultTableModel tableModel
                = new DefaultTableModel(null, new String[]{
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
            //Array of objects representing a row.
            Object nuevo[] = new Object[]{
                p.get(AgreementsManager.payment_param.FEE),
                p.get(AgreementsManager.payment_param.RECEIPT_NUMBER),
                p.get(AgreementsManager.payment_param.AMOUNT),
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
     * Will show the agreement list view.
     */
    public void ShowAgreementListView() {
        fillAgreementsPadron();
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

    /**
     * Will show the view that contains the complete information of an
     * agreement.
     *
     * @param agreementNumber
     */
    public void showAgreementDetailsView(Long agreementNumber) {
        AgreementsManager am = new AgreementsManager();
        TaxpayerManager tm = new TaxpayerManager();
        ParametersManager pm = new ParametersManager();
        PropertyManager lpm = new PropertyManager();
        //Consulting the agreement.
        EnumMap<AgreementsManager.agreement_param, String> agreementMap
                = am.consultAgreement(agreementNumber);
        agreementDV.setTfagreementNumber(
                agreementMap.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER));
        agreementDV.setTfAmountOfDebt("$ "
                + agreementMap.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT));
        agreementDV.setTfCreationDate(
                agreementMap.get(AgreementsManager.agreement_param.CREATION_DATE));
        agreementDV.setTfExpirationDate(
                agreementMap.get(AgreementsManager.agreement_param.EXPIRATION_DATE));
        agreementDV.setTfCuotsNumber(
                agreementMap.get(AgreementsManager.agreement_param.CUOTS_NUMBER));
        agreementDV.setTfDescription(
                agreementMap.get(AgreementsManager.agreement_param.DESCRIPTION));
        //Consulting the taxpayer.
        EnumMap<TaxpayerManager.taxpayer_param, String> taxpayerMap
                = tm.consult(Long.parseLong(agreementMap.get(AgreementsManager.agreement_param.TAXPAYER)));
        agreementDV.setTfTaxpayerName(
                taxpayerMap.get(TaxpayerManager.taxpayer_param.NAMES));
        agreementDV.setTfTaxpayerLastname(
                taxpayerMap.get(TaxpayerManager.taxpayer_param.LASTNAME));
        agreementDV.setTfTaxpayerDocNumber(
                taxpayerMap.get(TaxpayerManager.taxpayer_param.DOC_NUMBER));
        agreementDV.setTfTaxpayerAddress(
                taxpayerMap.get(TaxpayerManager.taxpayer_param.ADDRESS));
        agreementDV.setTfTaxpayerPhone(
                taxpayerMap.get(TaxpayerManager.taxpayer_param.PHONE_NUMBER));
        //Consulting the landPropMap.
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
        if (agreementMap.get(
                AgreementsManager.agreement_param.STATUS).equals(
                        AgreementsManager.agreement_status.WITHOUT_EFFECT.toString())) {
            agreementDV.visibleAsWithoutEffect();
        } else {
            fillAgreementPaymentsList();
            agreementDV.setLocationRelativeTo(null);
            agreementDV.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        RegistrationControl rc;
        AgreementsManager am;
        switch (ae.getActionCommand()) {
            case "CONCEPT_REGISTRATION":
                rc = new RegistrationControl();
                rc.showConceptRegistrationView(conceptsLV);
                break;
            case "LEAVE_WITHOUT_EFFECT":
                if (JOptionPane.showConfirmDialog(
                        agreementDV,
                        "¿Esta seguro que desea dejar sin efecto el convenio?",
                        "Advertencia!",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    am = new AgreementsManager(); //New agreements manager
                    am.leaveWithoutEffect(Long.parseLong(agreementDV.getTfAgreementNumber()));
                    fillAgreementsPadron(); //Refilling table model again to refresh changes. 
                    agreementDV.dispose();
                }
                break;
            case "PAYMENT_REGISTRATION":
                rc = new RegistrationControl();
                rc.showPaymentRegistrationView(
                        agreementDV,
                        agreementDV.getTfAgreementNumber(),
                        agreementDV.getTfFee());
                fillAgreementPaymentsList();//Refiiling payments table.
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getClickCount() == 2) {
            showAgreementDetailsView(agreementsLV.getSelectedAgreementNumber());
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource().equals(agreementsLV.getTfSearch())) {
            fillAgreementsPadron();//Refilling agreements padron.
            agreementsLV.filterAgreements();
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

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

}
