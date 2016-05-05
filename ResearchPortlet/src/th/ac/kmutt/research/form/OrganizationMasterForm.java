package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.OrganizationM;

public class OrganizationMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private OrganizationM organizationM;

    public OrganizationMasterForm(OrganizationM organizationM) {
        super();
        this.organizationM = organizationM;
    }

    public OrganizationMasterForm() {
        super();
        organizationM = new OrganizationM();
        // TODO Auto-generated constructor stub
    }

    public OrganizationM getOrganizationM() {
        return organizationM;
    }

    public void setOrganizationM(OrganizationM organizationM) {
        this.organizationM = organizationM;
    }

}
