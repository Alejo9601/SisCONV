package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.TaxPayerManager;
import View.AgreementRegister;
import View.TaxPayerSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez
 */
public class RegistrationControl implements ActionListener {

    AgreementRegister agreementsRV;
    TaxPayerSelection taxpayerSV;

    public RegistrationControl() {
        this.agreementsRV = new AgreementRegister();
        this.taxpayerSV = new TaxPayerSelection(agreementsRV, true);
        this.agreementsRV.setController(this);
        this.taxpayerSV.setController(this);
    }

    /**
     * Shows agreements registration view depending the condition.
     *
     * @param condition
     */
    public void showAgreementRegistrationView(Boolean condition) {
        if (condition.equals(true)) {
            ParametersManager pm = new ParametersManager();
            List<EnumMap<ParametersManager.concept_param, String>> conceptsMap = pm.consultAllConcepts();
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
    public EnumMap<AgreementsManager.agreement_param, String> getAgreementInfo() {
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
     * Will show the taxpayer selection view.
     */
    private void showTaxpayerSelectionView() {
        TaxPayerManager tm = new TaxPayerManager();
        List<EnumMap<TaxPayerManager.taxpayer_param, String>> taxpayerEL = tm.consultAll();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Nro. Doc", "Nombre", "Apellido"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on taxpayerEL
        for (EnumMap<TaxPayerManager.taxpayer_param, String> tp : taxpayerEL) {
            Object nuevo[] = new Object[]{
                tp.get(TaxPayerManager.taxpayer_param.DOC_NUMBER),
                tp.get(TaxPayerManager.taxpayer_param.NAMES),
                tp.get(TaxPayerManager.taxpayer_param.LASTNAME)};
            tableModel.addRow(nuevo);
        }
        taxpayerSV.setTableModel(tableModel); //Setting model to the view.
        taxpayerSV.setLocationRelativeTo(null); //Centering the view.
        taxpayerSV.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae
    ) {
        AgreementsManager am = new AgreementsManager();
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
        }
    }

}
