package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.PropertyManager;
import Model.TaxpayerManager;
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
                AgreementsManager.agreement_param.CUOTS_NUMBER,
                agreementsRV.getCmbCuotsNumber());
        agreementMap.put(
                AgreementsManager.agreement_param.DESCRIPTION,
                agreementsRV.getTfDescription());
        agreementMap.put(AgreementsManager.agreement_param.TAXPAYER,
                agreementsRV.getTfTaxPayer());
        agreementMap.put(AgreementsManager.agreement_param.CONCEPT,
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
    private EnumMap<TaxpayerManager.taxpayer_param, String> getTaxpayerInfo() {
        EnumMap<TaxpayerManager.taxpayer_param, String> taxpayerMap
                = new EnumMap<>(TaxpayerManager.taxpayer_param.class);
        taxpayerMap.put(
                TaxpayerManager.taxpayer_param.NAMES,
                taxpayerRV.getTfTaxpayerNames());
        taxpayerMap.put(
                TaxpayerManager.taxpayer_param.LASTNAME,
                taxpayerRV.getTfLastname());
        taxpayerMap.put(
                TaxpayerManager.taxpayer_param.DOC_NUMBER,
                taxpayerRV.getTfDocNumber());
        taxpayerMap.put(
                TaxpayerManager.taxpayer_param.DOC_TYPE,
                taxpayerRV.getCmbDocType());
        taxpayerMap.put(
                TaxpayerManager.taxpayer_param.ADDRESS,
                taxpayerRV.getTfAddress());
        taxpayerMap.put(
                TaxpayerManager.taxpayer_param.PHONE_NUMBER,
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
                paymentRV.getLblAgreementNumber());
        payMap.put(
                AgreementsManager.payment_param.FEE, paymentRV.getCmbFee());
        return payMap;
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
        TaxpayerManager tm = new TaxpayerManager();
        List<EnumMap<TaxpayerManager.taxpayer_param, String>> taxpayerEL = tm.consultAll();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(
                null, new String[]{
                    "Nro. Doc",
                    "Nombre",
                    "Apellido"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<TaxpayerManager.taxpayer_param, String> tp : taxpayerEL) {
            Object nuevo[] = new Object[]{
                tp.get(TaxpayerManager.taxpayer_param.DOC_NUMBER),
                tp.get(TaxpayerManager.taxpayer_param.NAMES),
                tp.get(TaxpayerManager.taxpayer_param.LASTNAME)};
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
                    "Id_Vehiculo",
                    "Dominio",
                    "Modelo",
                    "Fabricante",
                    "Tipo"}) {
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
                    "Identificador",
                    "Manzana",
                    "Lote",
                    "Decreto"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<PropertyManager.landProperty_param, String> lp : landPropEL) {
            Object nuevo[] = new Object[]{
                lp.get(PropertyManager.landProperty_param.ID_LANDPROPERTY),
                lp.get(PropertyManager.landProperty_param.APPLE),
                lp.get(PropertyManager.landProperty_param.BATCH),
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
     * Will show payment registration view.
     *
     * @param parent
     * @param agreementNumber
     * @param feesNumber
     */
    public void showPaymentRegistrationView(JFrame parent, String agreementNumber, String feesNumber) {
        paymentRV = new PaymentRegistration(parent, true);
        paymentRV.setLblAgreementNumber(agreementNumber);
        paymentRV.setCmbFee(feesNumber);
        paymentRV.setController(this);
        paymentRV.setLocationRelativeTo(null);
        paymentRV.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        AgreementsManager am = new AgreementsManager();
        TaxpayerManager tm = new TaxpayerManager();
        ParametersManager pm = new ParametersManager();
        PropertyManager plm = new PropertyManager();
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
                if (agreementsRV.verifyInformation() == true) {
                    if (am.newAgreement(getAgreementInfo()) == true) {
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
            case "SAVE_TAXPAYER":
                if (taxpayerRV.verifyInformation() == true) {
                    if (tm.newTaxpayer(getTaxpayerInfo()) == true) {
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
                                "No se ha podido registrar el convenio",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "SAVE_CONCEPT":
                if (conceptRV.verifyInformation() == true) {
                    if (pm.newConcept(getConceptInfo()) == true) {
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
                }
                break;
            case "SAVE_PAYMENT":
                if (paymentRV.verifyInformation() == true) {
                    if (am.newPayment(getPaymentInfo()) == true) {
                        JOptionPane.showMessageDialog(paymentRV,
                                "Se ha registrado con exito",
                                "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        paymentRV.dispose();
                    } else {
                        JOptionPane.showMessageDialog(paymentRV,
                                "No se ha podido registrar",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "SAVE_VEHICLE":
                if (vehicleRV.verifyInformation() == true) {
                    if (plm.newVehicle(getVehicleInfo())) {
                        JOptionPane.showMessageDialog(vehicleRV,
                                "Se ha registrado con exito",
                                "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        vehicleRV.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vehicleRV,
                                "No se ha podido registrar",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "SAVE_LANDPROPERTY":
                if (landPropertyRV.verifyInformation() == true) {
                    if (plm.newLandProperty(getLandPropertyInfo()) == true) {
                        JOptionPane.showMessageDialog(landPropertyRV,
                                "Se ha registrado con exito",
                                "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        landPropertyRV.dispose();
                    } else {
                        JOptionPane.showMessageDialog(landPropertyRV,
                                "No se ha podido registrar",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

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
    }

}
