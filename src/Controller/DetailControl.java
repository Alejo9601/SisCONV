package Controller;

import Model.AgreementsManager;
import Model.ParametersManager;
import Model.TaxpayerManager;
import View.AgreementDetails;
import View.AgreementsList;
import View.ConceptsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EnumMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez.
 */
public class DetailControl implements ActionListener, MouseListener {

    AgreementDetails agreementDV;
    AgreementsList agreementsLV;
    ConceptsList conceptsLV;

    /**
     * Constructor
     */
    public DetailControl() {
        this.agreementDV = new AgreementDetails();
        this.agreementsLV = new AgreementsList();
        this.conceptsLV = new ConceptsList();
        this.agreementsLV.setController(this);
        this.conceptsLV.setController(this);
        this.agreementDV.setController(this);
    }

    /**
     * Will show the agreement list view.
     */
    public void ShowAgreementListView() {
        AgreementsManager am = new AgreementsManager();
        TaxpayerManager tm = new TaxpayerManager();
        ParametersManager pm = new ParametersManager();
        //Agreements information.
        List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL = am.consultAll();
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
        //For each EnumMap on conceptsEL
        for (EnumMap<AgreementsManager.agreement_param, String> a : agreementsEL) {
            //Consulting the respecting taxpayer for the agreement.
            EnumMap<TaxpayerManager.taxpayer_param, String> tpMap
                    = tm.consult(Long.parseLong(a.get(AgreementsManager.agreement_param.TAXPAYER_DNI)));
            //Consulting the respecting concept for the agreement.
            EnumMap<ParametersManager.concept_param, String> ctMap
                    = pm.consult(Integer.parseInt(a.get(AgreementsManager.agreement_param.CONCEPT_CODE)));
            //Array of objects representing ct row.
            Object nuevo[] = new Object[]{
                tpMap.get(TaxpayerManager.taxpayer_param.NAMES)
                + " " + tpMap.get(TaxpayerManager.taxpayer_param.LASTNAME),
                tpMap.get(TaxpayerManager.taxpayer_param.DOC_NUMBER),
                a.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER),
                a.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT),
                a.get(AgreementsManager.agreement_param.CUOTS_NUMBER),
                ctMap.get(ParametersManager.concept_param.CONCEPT_NAME),
                a.get(AgreementsManager.agreement_param.STATE)};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        agreementsLV.setTableModel(tableModel); //Setting table model to the view.
        agreementsLV.setVisible(true); //Making visible the view.
        agreementsLV.setLocationRelativeTo(null); //Centering the view.
    }

    /**
     * Wil show the view that contains all the concepts info.
     */
    public void showConceptsListView() {
        ParametersManager pm = new ParametersManager();
        //Agreements information.
        List<EnumMap<ParametersManager.concept_param, String>> conceptsEL = pm.consultAll();
        //Table model that is going to be filled with information of the agreements.
        DefaultTableModel tableModel
                = new DefaultTableModel(null, new String[]{
            "Codigo",
            "Nombre",
            "Descripcion"}) {
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
                ct.get(ParametersManager.concept_param.CONCEPT_NAME),
                ct.get(ParametersManager.concept_param.DESCRIPTION)};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        conceptsLV.setTableModel(tableModel); //Setting table model to the view.
        conceptsLV.setVisible(true); //Making visible the view.
        conceptsLV.setLocationRelativeTo(null); //Centering the view.
    }

    /**
     * Will show the view that contains the complete information of ct an
     * agreement.
     *
     * @param agreementNumber
     */
    public void showAgreementDetailsView(Long agreementNumber) {
        AgreementsManager am = new AgreementsManager();
        TaxpayerManager tm = new TaxpayerManager();
        ParametersManager pm = new ParametersManager();

        EnumMap<AgreementsManager.agreement_param, String> agreementMap = am.consult(agreementNumber);

        agreementDV.setTfagreementNumber(agreementMap.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER));
        agreementDV.setTfAmountOfDebt(agreementMap.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT));
        agreementDV.setTfCreationDate(agreementMap.get(AgreementsManager.agreement_param.CREATION_DATE));
        agreementDV.setTfExpirationDate(agreementMap.get(AgreementsManager.agreement_param.EXPIRATION_DATE));
        agreementDV.setTfCuotsNumber(agreementMap.get(AgreementsManager.agreement_param.CUOTS_NUMBER));
        agreementDV.setTfDescription(agreementMap.get(AgreementsManager.agreement_param.DESCRIPTION));

        agreementDV.setTfConcept(agreementMap.get(AgreementsManager.agreement_param.CONCEPT_CODE));

        agreementDV.setVisible(true);
        agreementDV.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "CONCEPT_REGISTRATION":
                RegistrationControl rc = new RegistrationControl();
                rc.showConceptRegistrationView(conceptsLV);
                break;
            case "LEAVE_WITHOUT_EFFECT":
                AgreementsManager am = new AgreementsManager();
                am.leaveWithoutEffect(Long.parseLong(agreementDV.getTfAgreementNumber()));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getClickCount() == 2) {
            showAgreementDetailsView(agreementsLV.getSelectedAgreementNumber());
        }
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
}
