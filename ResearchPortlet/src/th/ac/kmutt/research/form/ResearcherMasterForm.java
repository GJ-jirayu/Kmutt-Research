package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.ResearcherM;

public class ResearcherMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private ResearcherM researcherM;

    public ResearcherMasterForm(ResearcherM researcherM) {
        super();
        this.researcherM = researcherM;
    }

    public ResearcherMasterForm() {
        super();
        researcherM = new ResearcherM();
        // TODO Auto-generated constructor stub
    }

    public ResearcherM getResearcherM() {
        return researcherM;
    }

    public void setResearcherM(ResearcherM researcherM) {
        this.researcherM = researcherM;
    }
}