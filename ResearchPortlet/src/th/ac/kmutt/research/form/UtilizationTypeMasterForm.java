package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.CountryM;
import th.ac.kmutt.research.model.UtilizationTypeM;

public class UtilizationTypeMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private UtilizationTypeM utilizationTypeM;

    public UtilizationTypeMasterForm(UtilizationTypeM utilizationTypeM) {
        super();
        this.utilizationTypeM = utilizationTypeM;
    }

    public UtilizationTypeMasterForm() {
        super();
        utilizationTypeM = new UtilizationTypeM();
        // TODO Auto-generated constructor stub
    }

    public UtilizationTypeM getUtilizationTypeM() {
        return utilizationTypeM;
    }

    public void setUtilizationTypeM(UtilizationTypeM utilizationTypeM) {
        this.utilizationTypeM = utilizationTypeM;
    }

}
