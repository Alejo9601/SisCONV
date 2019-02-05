package Controller;

import Model.AgreementsManager;
import Model.TaxPayerManager;
import View.AgreementDetails;
import View.AgreementsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez.
 */
public class DetailControl implements ActionListener {

    AgreementDetails agreementDV;
    AgreementsList agreementsLV;

    /**
     * Constructor
     */
    public DetailControl() {
        this.agreementDV = new AgreementDetails();
//        this.agreementDV.setController(this);
        this.agreementsLV = new AgreementsList();
    }

    /**
     * Will show the agreement list view.
     */
    public void ShowAgreementListView() {
        AgreementsManager am = new AgreementsManager();
        TaxPayerManager tm = new TaxPayerManager();
        //Agreements information.
        List<EnumMap<AgreementsManager.agreement_param, String>> agreementsEL = am.consultAll();
        //Table model that is going to be filled with information of the agreements.
        DefaultTableModel tableModel
                = new DefaultTableModel(null, new String[]{
            "Nombre Titular",
            "Nro. Doc Titular",
            "Nro. Convenio",
            "Monto deuda",
            "Estado"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on agreementsEL
        for (EnumMap<AgreementsManager.agreement_param, String> a : agreementsEL) {
            //Consulting the respecting taxpayer for the agreement.
            EnumMap<TaxPayerManager.taxpayer_param, String> tpMap
                    = tm.consult(Long.parseLong(a.get(AgreementsManager.agreement_param.TAXPAYER_DNI)));
            //Array of objects representing a row.
            Object nuevo[] = new Object[]{
                tpMap.get(TaxPayerManager.taxpayer_param.NAMES)
                + " " + tpMap.get(TaxPayerManager.taxpayer_param.LASTNAME),
                tpMap.get(TaxPayerManager.taxpayer_param.DOC_NUMBER),
                a.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER),
                a.get(AgreementsManager.agreement_param.AMOUNT_OF_DEBT),
                a.get(AgreementsManager.agreement_param.STATE)};
            tableModel.addRow(nuevo); //Adding new row at the table model.
        }
        agreementsLV.setTableModel(tableModel); //Setting table model to the view.
        agreementsLV.setVisible(true); //Making visible the view.
        agreementsLV.setLocationRelativeTo(null); //Centering the view.
    }

    /**
     * Will show the view that contains the complete information of a an
     * agreement.
     * @param agreementNumber
     */
    public void showAgreementDetailsView(Long agreementNumber) {
        AgreementsManager am = new AgreementsManager();
        TaxPayerManager tm = new TaxPayerManager();
        
        EnumMap<AgreementsManager.agreement_param, String> agreementMap = am.consult(agreementNumber);
        agreementDV.setTfagreementNumber(agreementMap.get(AgreementsManager.agreement_param.AGREEMENT_NUMBER));
        
        EnumMap<> 

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
