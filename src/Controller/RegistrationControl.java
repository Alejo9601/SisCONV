package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.PropertyManager;
import Model.TaxpayersManager;
import View.AgreementRegistration;
import View.ConceptRegistration;
import View.LandPropertyRegistration;
import View.LandPropertySelection;
import View.PaymentRegistration;
import View.TaxpayerRegistration;
import View.TaxpayerSelection;
import View.VehicleRegistration;
import View.VehicleSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez
 */
public class RegistrationControl implements ActionListener, KeyListener {

    AgreementRegistration agreementsRV;

    TaxpayerSelection taxpayerSV;
    TaxpayerRegistration taxpayerRV;

    ConceptRegistration conceptRV;
    PaymentRegistration paymentRV;

    VehicleRegistration vehicleRV;
    VehicleSelection vehicleSV;

    LandPropertyRegistration landPropertyRV;
    LandPropertySelection landPropertySV;

    public RegistrationControl() {
        this.agreementsRV = new AgreementRegistration();
        this.agreementsRV.setController(this);
    }

    /**
     * Shows agreements registration view depending the condition.
     *
     * @param condition
     */
    public void showAgreementRegistrationView(Boolean condition) {
        if (condition.equals(true)) {
            ParametersManager pm = new ParametersManager();
            List<EnumMap<ParametersManager.concept_param, String>> conceptsMap = pm.consultAll();
            for (EnumMap<ParametersManager.concept_param, String> ct : conceptsMap) {
                this.agreementsRV.setCmbConcept(ct.get(ParametersManager.concept_param.CONCEPT_CODE)
                        + " : " + ct.get(ParametersManager.concept_param.CONCEPT_NAME));
            }
            AgreementsManager am = new AgreementsManager();
            agreementsRV.setTfAgreementNumber(Long.toString(am.consultLastAgreementNumber()));
            agreementsRV.setVisible(true);
            agreementsRV.setLocationRelativeTo(null);
        } else {
            agreementsRV.dispose();
        }
    }

    /**
     * Gets the information of the agreement from the registration view.
     *
     * @return
     */
    private EnumMap<AgreementsManager.agreement_param, String> getAgreementInfo() {
        EnumMap<AgreementsManager.agreement_param, String> agreementMap
                = new EnumMap<>(AgreementsManager.agreement_param.class);
        agreementMap.put(
                AgreementsManager.agreement_param.AGREEMENT_NUMBER,
                agreementsRV.getTfAgreementNumber());
        agreementMap.put(
                AgreementsManager.agreement_param.AMOUNT_OF_DEBT,
                agreementsRV.getTfAmountOfDebt());
        agreementMap.put(
                AgreementsManager.agreement_param.CREATION_DATE,
                agreementsRV.getTfDateOfCreation());
        agreementMap.put(
                AgreementsManager.agreement_param.EXPIRATION_DATE,
                agreementsRV.getTfDateOfExpiration());
        agreementMap.put(
                AgreementsManager.agreement_param.FEES_NUMBER,
                agreementsRV.getCmbFeesNumber());
        agreementMap.put(
                AgreementsManager.agreement_param.DESCRIPTION,
                agreementsRV.getTfDescription());
        agreementMap.put(
                AgreementsManager.agreement_param.TAXPAYER,
                agreementsRV.getTfTaxPayer());
        agreementMap.put(
                AgreementsManager.agreement_param.CONCEPT,
                agreementsRV.getCmbConcept());

        if ((!agreementsRV.getTfVehicle().equals("")) || (!agreementsRV.getTfLandProperty().equals(""))) {
            if (!agreementsRV.getTfVehicle().equals("")) {
                agreementMap.put(AgreementsManager.agreement_param.VEHICLE,
                        agreementsRV.getTfVehicle());
            } else {
                agreementMap.put(AgreementsManager.agreement_param.LAND_PROPERTY,
                        agreementsRV.getTfLandProperty());
            }
        }
        return agreementMap;
    }

    /**
     * Gets the taxpayer information from the view.
     *
     * @return
     */
    private EnumMap<TaxpayersManager.taxpayer_param, String> getTaxpayerInfo() {
        EnumMap<TaxpayersManager.taxpayer_param, String> taxpayerMap
                = new EnumMap<>(TaxpayersManager.taxpayer_param.class);
        taxpayerMap.put(TaxpayersManager.taxpayer_param.NAMES,
                taxpayerRV.getTfTaxpayerNames());
        taxpayerMap.put(TaxpayersManager.taxpayer_param.LASTNAME,
                taxpayerRV.getTfLastname());
        taxpayerMap.put(TaxpayersManager.taxpayer_param.DOC_NUMBER,
                taxpayerRV.getTfDocNumber());
        taxpayerMap.put(TaxpayersManager.taxpayer_param.DOC_TYPE,
                taxpayerRV.getCmbDocType());
        taxpayerMap.put(TaxpayersManager.taxpayer_param.ADDRESS,
                taxpayerRV.getTfAddress());
        taxpayerMap.put(TaxpayersManager.taxpayer_param.PHONE_NUMBER,
                taxpayerRV.getTfPhoneNumber());
        return taxpayerMap;
    }

    /**
     * Gets the concept information from the view.
     *
     * @return
     */
    private EnumMap<ParametersManager.concept_param, String> getConceptInfo() {
        EnumMap<ParametersManager.concept_param, String> conceptMap
                = new EnumMap<>(ParametersManager.concept_param.class);
        conceptMap.put(
                ParametersManager.concept_param.CONCEPT_CODE,
                conceptRV.getTfCode());
        conceptMap.put(
                ParametersManager.concept_param.CONCEPT_NAME,
                conceptRV.getTfName());
        conceptMap.put(
                ParametersManager.concept_param.DESCRIPTION,
                conceptRV.getTfDescription());
        return conceptMap;
    }

    /**
     * Will get the info of a vehicle from view.
     *
     * @return
     */
    private EnumMap<PropertyManager.vehicle_param, String> getVehicleInfo() {
        EnumMap<PropertyManager.vehicle_param, String> vehicleMap
                = new EnumMap<>(PropertyManager.vehicle_param.class);
        vehicleMap.put(
                PropertyManager.vehicle_param.DOMAIN,
                vehicleRV.getDomain());
        vehicleMap.put(
                PropertyManager.vehicle_param.MODEL,
                vehicleRV.getModel());
        vehicleMap.put(
                PropertyManager.vehicle_param.MANUFACTURER,
                vehicleRV.getManufacturer());
        vehicleMap.put(
                PropertyManager.vehicle_param.TYPE,
                vehicleRV.getCmbType());
        return vehicleMap;
    }

    /**
     * Will get the info of a landProperty from view.
     *
     * @return
     */
    private EnumMap<PropertyManager.landProperty_param, String> getLandPropertyInfo() {
        EnumMap<PropertyManager.landProperty_param, String> landPropMap
                = new EnumMap<>(PropertyManager.landProperty_param.class);
        landPropMap.put(
                PropertyManager.landProperty_param.APPLE,
                landPropertyRV.getTfApple());
        landPropMap.put(
                PropertyManager.landProperty_param.BATCH,
                landPropertyRV.getTfBatch());
        landPropMap.put(
                PropertyManager.landProperty_param.DECREE,
                landPropertyRV.getTfDecree());
        landPropMap.put(
                PropertyManager.landProperty_param.ADDRESS,
                landPropertyRV.getTfAddress());
        return landPropMap;
    }

    /**
     * Fills taxpayer selection table.
     */
    private void fillTaxPayerSelectionList() {
        TaxpayersManager tm = new TaxpayersManager();
        List<EnumMap<TaxpayersManager.taxpayer_param, String>> taxpayerEL = tm.consultAll();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(
                null, new String[]{
                    "NRO. DOC.",
                    "NOMBRE",
                    "APELLIDO"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<TaxpayersManager.taxpayer_param, String> tp : taxpayerEL) {
            Object nuevo[] = new Object[]{
                tp.get(TaxpayersManager.taxpayer_param.DOC_NUMBER),
                tp.get(TaxpayersManager.taxpayer_param.NAMES),
                tp.get(TaxpayersManager.taxpayer_param.LASTNAME)};
            tableModel.addRow(nuevo);
        }
        taxpayerSV.setTableModel(tableModel); //Setting model to the view.
    }

    /**
     * Fills vehicle selection table.
     */
    private void fillVehicleSelectionList() {
        PropertyManager plm = new PropertyManager();
        List<EnumMap<PropertyManager.vehicle_param, String>> vehicleEL = plm.consultAllVehicles();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(
                null, new String[]{
                    "IDENTIFICADOR",
                    "DOMINIO",
                    "MODELO",
                    "FABRICANTE",
                    "TIPO"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<PropertyManager.vehicle_param, String> v : vehicleEL) {
            Object nuevo[] = new Object[]{
                v.get(PropertyManager.vehicle_param.ID_VEHICLE),
                v.get(PropertyManager.vehicle_param.DOMAIN),
                v.get(PropertyManager.vehicle_param.MODEL),
                v.get(PropertyManager.vehicle_param.MANUFACTURER),
                v.get(PropertyManager.vehicle_param.TYPE)};
            tableModel.addRow(nuevo);
        }
        vehicleSV.setTableModel(tableModel); //Setting model to the view.
    }

    /**
     * Fills land property selection table.
     */
    private void fillLandPropertySelectionList() {
        PropertyManager plm = new PropertyManager();
        List<EnumMap<PropertyManager.landProperty_param, String>> landPropEL = plm.consultAllLandProperties();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(
                null, new String[]{
                    "IDENTIFICADOR",
                    "MANZANA Y LOTE",
                    "DECRETO"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<PropertyManager.landProperty_param, String> lp : landPropEL) {
            Object nuevo[] = new Object[]{
                lp.get(PropertyManager.landProperty_param.ID_LANDPROPERTY),
                "M" + lp.get(PropertyManager.landProperty_param.APPLE) + "-L" + lp.get(PropertyManager.landProperty_param.BATCH),
                lp.get(PropertyManager.landProperty_param.DECREE)};
            tableModel.addRow(nuevo);
        }
        landPropertySV.setTableModel(tableModel); //Setting model to the view.
    }

    /**
     * Will show the taxpayer selection view.
     */
    private void showTaxpayerSelectionView() {
        taxpayerSV = new TaxpayerSelection(agreementsRV, true);
        fillTaxPayerSelectionList();
        taxpayerSV.setController(this);
        taxpayerSV.setLocationRelativeTo(null); //Centering the view.
        taxpayerSV.setVisible(true);
    }

    /**
     * Will show the vehicle selection view.
     */
    private void showVehicleSelectionView() {
        vehicleSV = new VehicleSelection(agreementsRV, true);
        fillVehicleSelectionList();
        vehicleSV.setController(this);
        vehicleSV.setLocationRelativeTo(null); //Centering the view.
        vehicleSV.setVisible(true);
    }

    /**
     * Will show the land / property selection view.
     */
    private void showLandPropertySelectionView() {
        landPropertySV = new LandPropertySelection(agreementsRV, true);
        fillLandPropertySelectionList();
        landPropertySV.setController(this);
        landPropertySV.setLocationRelativeTo(null); //Centering the view.
        landPropertySV.setVisible(true);
    }

    /**
     * Will show taxpayer registration view.
     *
     */
    public void showTaxpayerRegistrationView() {
        taxpayerRV = new TaxpayerRegistration(agreementsRV, true);
        taxpayerRV.setController(this);
        taxpayerRV.setLocationRelativeTo(null);
        taxpayerRV.setVisible(true);
    }

    /**
     * Will show concept registration view.
     *
     * @param parent
     */
    public void showConceptRegistrationView(JFrame parent) {
        conceptRV = new ConceptRegistration(parent, true);
        conceptRV.setController(this);
        conceptRV.setLocationRelativeTo(null);
        conceptRV.setVisible(true);
    }

    /**
     * Will show vehicle registration view.
     */
    public void showVehicleRegistrationView() {
        vehicleRV = new VehicleRegistration(agreementsRV, true);
        vehicleRV.setController(this);
        vehicleRV.setLocationRelativeTo(null);
        vehicleRV.setVisible(true);
    }

    /**
     * Will show land / property registration view.
     */
    public void showLandPropertyRegistrationView() {
        landPropertyRV = new LandPropertyRegistration(agreementsRV, true);
        landPropertyRV.setController(this);
        landPropertyRV.setLocationRelativeTo(null);
        landPropertyRV.setVisible(true);
    }

    /**
     *
     * @param agreementNumber
     */
    public void showAgreementModificationView(Long agreementNumber) {
        AgreementsManager am = new AgreementsManager();
        TaxpayersManager tm = new TaxpayersManager();
        ParametersManager pm = new ParametersManager();
        PropertyManager lpm = new PropertyManager();
        //Consulting the agreement.
        EnumMap<AgreementsManager.agreement_param, String> agreementMap
                = am.consultAgreement(agreementNumber);
        agreementsRV.setTfAgreementNumber(
                agreementMap.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER));
        agreementsRV.setTfAmountOfDebt(
                "$ " + agreementMap.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT));
        agreementsRV.setTfCreationDate(
                agreementMap.get(AgreementsManager.agreement_param.CREATION_DATE));
        agreementsRV.setTfExpirationDate(
                agreementMap.get(AgreementsManager.agreement_param.EXPIRATION_DATE));
        agreementsRV.setFeesNumber(
                agreementMap.get(AgreementsManager.agreement_param.FEES_NUMBER));
        agreementsRV.setTfDescription(
                agreementMap.get(AgreementsManager.agreement_param.DESCRIPTION));
        //Consulting the taxpayer.
        EnumMap<TaxpayersManager.taxpayer_param, String> taxpayerMap
                = tm.consult(Long.parseLong(agreementMap.get(AgreementsManager.agreement_param.TAXPAYER)));
        agreementsRV.setTfTaxPayer(
                taxpayerMap.get(TaxpayersManager.taxpayer_param.DOC_NUMBER)
                + " : " + taxpayerMap.get(TaxpayersManager.taxpayer_param.NAMES)
                + " : " + taxpayerMap.get(TaxpayersManager.taxpayer_param.LASTNAME));
        //Filling concepts combo box, with all concepts on DB.
        List<EnumMap<ParametersManager.concept_param, String>> conceptsEL = pm.consultAll();
        for (EnumMap<ParametersManager.concept_param, String> ct : conceptsEL) {
            this.agreementsRV.setCmbConcept(ct.get(ParametersManager.concept_param.CONCEPT_CODE)
                    + " : " + ct.get(ParametersManager.concept_param.CONCEPT_NAME));
        }
        //Setting selected concept on combo box
        EnumMap<ParametersManager.concept_param, String> conceptMap
                = pm.consult(Integer.parseInt(agreementMap.get(AgreementsManager.agreement_param.CONCEPT)));
        agreementsRV.cmbConceptSetSelectedItem(conceptMap.get(ParametersManager.concept_param.CONCEPT_CODE)
                + " : " + conceptMap.get(ParametersManager.concept_param.CONCEPT_NAME));
        //If the concept is related to vehicle or land / property
        switch (conceptMap.get(ParametersManager.concept_param.CONCEPT_CODE)) {
            case "1201":
            case "110101":
            case "110201":
                EnumMap<PropertyManager.landProperty_param, String> landPropMap
                        = lpm.consultLandProperty(Long.parseLong(agreementMap.get(AgreementsManager.agreement_param.LAND_PROPERTY)));
                agreementsRV.setTfLandProperty(landPropMap.get(
                        PropertyManager.landProperty_param.ID_LANDPROPERTY)
                        + " : " + landPropMap.get(PropertyManager.landProperty_param.APPLE)
                        + " : " + landPropMap.get(PropertyManager.landProperty_param.BATCH)
                        + " : " + landPropMap.get(PropertyManager.landProperty_param.DECREE));
                break;
            case "110104":
                EnumMap<PropertyManager.vehicle_param, String> vehicleMap
                        = lpm.consultVehicle(Long.parseLong(agreementMap.get(AgreementsManager.agreement_param.VEHICLE)));
                agreementsRV.setTfVehicle(vehicleMap.get(PropertyManager.vehicle_param.ID_VEHICLE)
                        + " : " + vehicleMap.get(PropertyManager.vehicle_param.DOMAIN)
                        + " : " + vehicleMap.get(PropertyManager.vehicle_param.MODEL)
                        + " : " + vehicleMap.get(PropertyManager.vehicle_param.MANUFACTURER)
                        + " : " + vehicleMap.get(PropertyManager.vehicle_param.TYPE));
                break;
        }
        //Consulting payments for this agreement, if there is at least one... we show the view as partial modification.
        List<EnumMap<AgreementsManager.payment_param, String>> paymentsEL = am.consultPaymentsForAgreement(agreementNumber);
        if (!paymentsEL.isEmpty()) {
            agreementsRV.setLocationRelativeTo(null);
            agreementsRV.showAsPartialModificationView();
        } else {
            agreementsRV.setLocationRelativeTo(null);
            agreementsRV.showAsCompleteModificationView();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        AgreementsManager am;
        TaxpayersManager tm;
        ParametersManager pm;
        PropertyManager plm;
        switch (ae.getActionCommand()) {
            case "SELECT_TAXPAYER":
                showTaxpayerSelectionView();
                break;

            case "SELECT_VEHICLE":
                showVehicleSelectionView();
                break;

            case "SELECT_LANDPROPERTY":
                showLandPropertySelectionView();
                break;

            case "TAXPAYER_SELECTED":
                agreementsRV.setTfTaxPayer(taxpayerSV.getSelectedRecord());
                taxpayerSV.dispose();
                break;

            case "VEHICLE_SELECTED":
                agreementsRV.setTfVehicle(vehicleSV.getSelectedRecord());
                vehicleSV.dispose();
                break;

            case "LANDPROPERTY_SELECTED":
                agreementsRV.setTfLandProperty(landPropertySV.getSelectedRecord());
                landPropertySV.dispose();
                break;

            case "NEW_TAXPAYER":
                showTaxpayerRegistrationView();
                break;

            case "NEW_VEHICLE":
                showVehicleRegistrationView();
                break;

            case "NEW_LANDPROPERTY":
                showLandPropertyRegistrationView();
                break;

            case "SAVE_AGREEMENT":
                am = new AgreementsManager();
                if (agreementsRV.verifyInformation()) {
                    if (am.newAgreement(getAgreementInfo())) {
                        JOptionPane.showMessageDialog(agreementsRV,
                                "Se ha registrado con exito",
                                "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        showAgreementRegistrationView(false);
                    } else {
                        JOptionPane.showMessageDialog(agreementsRV,
                                "No se ha podido registrar el convenio",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;

            case "UPDATE_AGREEMENT":
                am = new AgreementsManager();
                if (agreementsRV.verifyInformation()) {
                    if (am.updateAgreement(getAgreementInfo())) {
                        JOptionPane.showMessageDialog(agreementsRV,
                                "Se ha actualizado con exito",
                                "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        showAgreementRegistrationView(false);
                    } else {
                        JOptionPane.showMessageDialog(agreementsRV,
                                "No se ha podido actualizar el convenio",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;

            case "SAVE_TAXPAYER":
                tm = new TaxpayersManager();
                if (taxpayerRV.verifyInformation()) {
                    if (tm.consult(Long.parseLong(taxpayerRV.getTfDocNumber())) == null) {
                        if (tm.newTaxpayer(getTaxpayerInfo())) {
                            JOptionPane.showMessageDialog(taxpayerRV,
                                    "Se ha registrado con exito",
                                    "Informacion",
                                    JOptionPane.INFORMATION_MESSAGE);
                            agreementsRV.setTfTaxPayer(taxpayerRV.getTfDocNumber()
                                    + " : " + taxpayerRV.getTfTaxpayerNames()
                                    + " : " + taxpayerRV.getTfLastname());
                            taxpayerRV.dispose();
                        } else {
                            JOptionPane.showMessageDialog(taxpayerRV,
                                    "No se ha podido registrar el contribuyente",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    }
                    JOptionPane.showMessageDialog(taxpayerRV,
                            "El nro. de documento del contribuyente ya existe",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;

            case "SAVE_CONCEPT":
                pm = new ParametersManager();
                if (conceptRV.verifyInformation()) {
                    if (pm.consult(Integer.parseInt(conceptRV.getTfCode())) == null) {
                        if (pm.consult(conceptRV.getTfName()) == null) {
                            if (pm.newConcept(getConceptInfo())) {
                                JOptionPane.showMessageDialog(conceptRV,
                                        "Se ha registrado con exito",
                                        "Informacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                                conceptRV.dispose();
                            } else {
                                JOptionPane.showMessageDialog(conceptRV,
                                        "No se ha podido registrar",
                                        "Advertencia",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(conceptRV,
                            "El codigo o nombre del concepto ya existe",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;

//            case "SAVE_PAYMENT":
//                am = new AgreementsManager();
//                if (paymentRV.verifyInformation()) {
//                    /**
//                     * We must compare if receipt exists... and if exists
//                     * then... if its on same agreement, and for the same fee.
//                     */
//                    EnumMap<AgreementsManager.payment_param, String> pay = getPaymentInfo();
//                    if (am.receiptExists(Long.parseLong(pay.get(AgreementsManager.payment_param.RECEIPT_NUMBER)))) {
//                        //If payment with specified receipt exists on agreement
//                        if (am.receiptExistsForAgreement(
//                                Long.parseLong(pay.get(AgreementsManager.payment_param.RECEIPT_NUMBER)),
//                                Long.parseLong(pay.get(AgreementsManager.payment_param.AGREEMENT_NUMBER)))) {
//                            //If payment exists for agreement
//                            if (am.paymentExistsForAgreement(pay)) {
//                                JOptionPane.showMessageDialog(paymentRV,
//                                        "Un pago por la misma cuota ya se encuentra registrado",
//                                        "Advertencia",
//                                        JOptionPane.WARNING_MESSAGE);
//                                break;
//                            } else {
//                                if (am.newPayment(getPaymentInfo())) {
//                                    JOptionPane.showMessageDialog(paymentRV,
//                                            "Se ha registrado con exito",
//                                            "Informacion",
//                                            JOptionPane.INFORMATION_MESSAGE);
//                                    paymentRV.dispose();
//                                    break;
//                                } else {
//                                    JOptionPane.showMessageDialog(paymentRV,
//                                            "No se ha podido registrar",
//                                            "Advertencia",
//                                            JOptionPane.WARNING_MESSAGE);
//                                    break;
//                                }
//                            }
//                        }
//                        JOptionPane.showMessageDialog(paymentRV,
//                                "El recibo ya se encuentra registrado para otro convenio",
//                                "Advertencia",
//                                JOptionPane.WARNING_MESSAGE);
//                        break;
//                    }
//                    if (am.paymentExistsForAgreement(pay)) {
//                        JOptionPane.showMessageDialog(paymentRV,
//                                "Un pago por la misma cuota ya se encuentra registrado",
//                                "Advertencia",
//                                JOptionPane.WARNING_MESSAGE);
//                        break;
//                    } else {
//                        if (am.newPayment(getPaymentInfo())) {
//                            JOptionPane.showMessageDialog(paymentRV,
//                                    "Se ha registrado con exito",
//                                    "Informacion",
//                                    JOptionPane.INFORMATION_MESSAGE);
//                            paymentRV.dispose();
//                        } else {
//                            JOptionPane.showMessageDialog(paymentRV,
//                                    "No se ha podido registrar",
//                                    "Advertencia",
//                                    JOptionPane.WARNING_MESSAGE);
//                        }
//                    }
//                }
//                break;
            case "SAVE_VEHICLE":
                plm = new PropertyManager();
                if (vehicleRV.verifyInformation()) {
                    if (plm.consultVehicle(vehicleRV.getDomain()) == null) {
                        if (plm.newVehicle(getVehicleInfo())) {
                            JOptionPane.showMessageDialog(vehicleRV,
                                    "Se ha registrado con exito",
                                    "Informacion",
                                    JOptionPane.INFORMATION_MESSAGE);
                            agreementsRV.setTfVehicle(
                                    plm.consultVehicle(vehicleRV.getDomain()).get(PropertyManager.vehicle_param.ID_VEHICLE)
                                    + " : " + vehicleRV.getDomain()
                                    + " : " + vehicleRV.getModel()
                                    + " : " + vehicleRV.getManufacturer()
                                    + " : " + vehicleRV.getCmbType());
                            vehicleRV.dispose();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(vehicleRV,
                                    "No se ha podido registrar",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    }
                    JOptionPane.showMessageDialog(vehicleRV,
                            "El dominio del vehiculo ya existe",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;

            case "SAVE_LANDPROPERTY":
                plm = new PropertyManager();
                if (landPropertyRV.verifyInformation()) {
                    if (plm.consultLandProperty(landPropertyRV.getTfApple(), landPropertyRV.getTfBatch()) == null) {
                        if (plm.consultLandProperty(landPropertyRV.getTfDecree()) == null) {
                            if (plm.newLandProperty(getLandPropertyInfo())) {
                                JOptionPane.showMessageDialog(landPropertyRV,
                                        "Se ha registrado con exito",
                                        "Informacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                                agreementsRV.setTfLandProperty(
                                        plm.consultLandProperty(landPropertyRV.getTfApple(),
                                                landPropertyRV.getTfBatch()).get(PropertyManager.landProperty_param.ID_LANDPROPERTY)
                                        + " : M" + landPropertyRV.getTfApple() + "-L" + landPropertyRV.getTfBatch()
                                        + " : " + landPropertyRV.getTfDecree());
                                landPropertyRV.dispose();
                                break;
                            }
                            JOptionPane.showMessageDialog(landPropertyRV,
                                    "No se ha podido registrar",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        JOptionPane.showMessageDialog(landPropertyRV,
                                "El nro. de decreto ya existe",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    JOptionPane.showMessageDialog(landPropertyRV,
                            "El terreno / propiedad ya existe",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
        if (Character.isLetter(c) || Character.isDigit(c) || c == '-' || c == '/') {
        } else {
            ke.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (taxpayerSV != null) {
            if (ke.getSource() == taxpayerSV.getTfSearch()) {
                fillTaxPayerSelectionList();
                taxpayerSV.filterTaxpayers();
            }
        }
        if (vehicleSV != null) {
            if (ke.getSource() == vehicleSV.getTfSearch()) {
                fillVehicleSelectionList();
                vehicleSV.filterVehicles();
            }
        }
        if (landPropertySV != null) {
            if (ke.getSource() == landPropertySV.getTfSearch()) {
                fillLandPropertySelectionList();
                landPropertySV.filterLandProperties();
            }
        }
    }

}
