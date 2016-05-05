package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.OrganizationExtM;

public class OrganizationExtMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private OrganizationExtM organizationExtM;

    public OrganizationExtMasterForm(OrganizationExtM organizationExtM) {
        super();
        this.organizationExtM = organizationExtM;
    }

    public OrganizationExtMasterForm() {
        super();
        organizationExtM = new OrganizationExtM();
        // TODO Auto-generated constructor stub
    }

    public OrganizationExtM getOrganizationExtM() {
        return organizationExtM;
    }

    public void setOrganizationExtM(OrganizationExtM organizationExtM) {
        this.organizationExtM = organizationExtM;
    }

}
