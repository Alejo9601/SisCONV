package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.TaxpayerManager;
import View.AgreementRegister;
import View.ConceptRegistration;
import View.TaxpayerRegistration;
import View.TaxpayerSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez
 */
public class RegistrationControl implements ActionListener {

    AgreementRegister agreementsRV;
    TaxpayerSelection taxpayerSV;
    TaxpayerRegistration taxpayerRV;
    ConceptRegistration conceptRV;

    public RegistrationControl() {
        this.agreementsRV = new AgreementRegister();
        this.taxpayerSV = new TaxpayerSelection(agreementsRV, true);
        this.taxpayerRV = new TaxpayerRegistration(agreementsRV, true);
        this.agreementsRV.setController(this);
        this.taxpayerSV.setController(this);
        this.taxpayerRV.setController(this);
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
        EnumMap<AgreementsManager.agreement_param, String> agreementMap = new EnumMap<>(AgreementsManager.agreement_param.class);

        agreementMap.put(AgreementsManager.agreement_param.AGREEMENT_NUMBER, agreementsRV.getTfAgreementNumber());
        agreementMap.put(AgreementsManager.agreement_param.AMOUNT_OF_DEBT, agreementsRV.getTfAmountOfDebt());
        agreementMap.put(AgreementsManager.agreement_param.CREATION_DATE, agreementsRV.getTfDateOfCreation());
        agreementMap.put(AgreementsManager.agreement_param.EXPIRATION_DATE, agreementsRV.getTfDateOfExpiration());
        agreementMap.put(AgreementsManager.agreement_param.CUOTS_NUMBER, agreementsRV.getCmbCuotsNumber());
        agreementMap.put(AgreementsManager.agreement_param.DESCRIPTION, agreementsRV.getTfDescription());
        agreementMap.put(AgreementsManager.agreement_param.TAXPAYER_DNI, agreementsRV.getTfTaxPayer());
        agreementMap.put(AgreementsManager.agreement_param.CONCEPT_CODE, agreementsRV.getCmbConcept());

        if ((!agreementsRV.getTfVehicle().equals("")) || (!agreementsRV.getTfProperty().equals(""))) {
            if (!agreementsRV.getTfVehicle().equals("")) {
                agreementMap.put(AgreementsManager.agreement_param.VEHICLE_DOMAIN, agreementsRV.getTfVehicle());
            } else {
                agreementMap.put(AgreementsManager.agreement_param.PROPERTY_DECRET, agreementsRV.getTfProperty());
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
        EnumMap<TaxpayerManager.taxpayer_param, String> taxpayerMap = new EnumMap<>(TaxpayerManager.taxpayer_param.class);
        taxpayerMap.put(TaxpayerManager.taxpayer_param.NAMES, taxpayerRV.getTfTaxpayerNames());
        taxpayerMap.put(TaxpayerManager.taxpayer_param.LASTNAME, taxpayerRV.getTfLastname());
        taxpayerMap.put(TaxpayerManager.taxpayer_param.DOC_NUMBER, taxpayerRV.getTfDocNumber());
        taxpayerMap.put(TaxpayerManager.taxpayer_param.DOC_TYPE, taxpayerRV.getCmbDocType());
        taxpayerMap.put(TaxpayerManager.taxpayer_param.ADDRESS, taxpayerRV.getTfAddress());
        taxpayerMap.put(TaxpayerManager.taxpayer_param.PHONE_NUMBER, taxpayerRV.getTfPhoneNumber());
        return taxpayerMap;
    }

    /**
     * Gets the concept information from the view.
     *
     * @return
     */
    private EnumMap<ParametersManager.concept_param, String> getConceptInfo() {
        EnumMap<ParametersManager.concept_param, String> conceptMap = new EnumMap<>(ParametersManager.concept_param.class);
        conceptMap.put(ParametersManager.concept_param.CONCEPT_CODE, conceptRV.getTfCode());
        conceptMap.put(ParametersManager.concept_param.CONCEPT_NAME, conceptRV.getTfName());
        conceptMap.put(ParametersManager.concept_param.DESCRIPTION, conceptRV.getTfDescription());
        return conceptMap;
    }

    /**
     * Will show the taxpayer selection view.
     */
    private void showTaxpayerSelectionView() {
        TaxpayerManager tm = new TaxpayerManager();
        List<EnumMap<TaxpayerManager.taxpayer_param, String>> taxpayerEL = tm.consultAll();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Nro. Doc", "Nombre", "Apellido"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on taxpayerEL
        for (EnumMap<TaxpayerManager.taxpayer_param, String> tp : taxpayerEL) {
            Object nuevo[] = new Object[]{
                tp.get(TaxpayerManager.taxpayer_param.DOC_NUMBER),
                tp.get(TaxpayerManager.taxpayer_param.NAMES),
                tp.get(TaxpayerManager.taxpayer_param.LASTNAME)};
            tableModel.addRow(nuevo);
        }
        taxpayerSV.setTableModel(tableModel); //Setting model to the view.
        taxpayerSV.setLocationRelativeTo(null); //Centering the view.
        taxpayerSV.setVisible(true);
    }

    /**
     * Will show taxpayer registration view.
     *
     * @param condition
     */
    public void showTaxpayerRegistrationView(Boolean condition) {
        taxpayerRV.setLocationRelativeTo(null);
        taxpayerRV.setVisible(condition);
    }

    /**
     * Will show concept registratio view.
     *
     * @param parent
     */
    public void showConceptRegistrationView(JFrame parent) {
        conceptRV = new ConceptRegistration(parent, true);
        conceptRV.setController(this);
        conceptRV.setLocationRelativeTo(null);
        conceptRV.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        AgreementsManager am = new AgreementsManager();
        TaxpayerManager tm = new TaxpayerManager();
        ParametersManager pm = new ParametersManager();
        switch (ae.getActionCommand()) {
            case "SAVE_AGREEMENT":
                if (agreementsRV.verifyInformation() == true) {
                    if (am.newAgreement(getAgreementInfo()) == true) {
                        JOptionPane.showMessageDialog(agreementsRV, "Se ha registrado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        showAgreementRegistrationView(false);
                    } else {
                        JOptionPane.showMessageDialog(agreementsRV, "No se ha podido registrar el convenio", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "SELECT_TAXPAYER":
                showTaxpayerSelectionView();
                break;
            case "TAXPAYER_SELECTED":
                agreementsRV.setTfTaxPayer(taxpayerSV.getSelectedRecord());
                taxpayerSV.dispose();
                break;
            case "NEW_TAXPAYER":
                showTaxpayerRegistrationView(true);
                break;
            case "SAVE_TAXPAYER":
                if (taxpayerRV.verifyInformation() == true) {
                    if (tm.newTaxpayer(getTaxpayerInfo()) == true) {
                        JOptionPane.showMessageDialog(agreementsRV, "Se ha registrado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        agreementsRV.setTfTaxPayer(taxpayerRV.getTfDocNumber()
                                + " : " + taxpayerRV.getTfTaxpayerNames()
                                + " : " + taxpayerRV.getTfLastname());
                        showTaxpayerRegistrationView(false);
                    } else {
                        JOptionPane.showMessageDialog(agreementsRV, "No se ha podido registrar el convenio", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "SAVE_CONCEPT":
                pm.newConcept(getConceptInfo());
                break;
        }
    }

}
