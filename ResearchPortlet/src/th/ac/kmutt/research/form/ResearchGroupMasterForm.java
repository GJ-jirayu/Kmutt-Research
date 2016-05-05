package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.ResearchGroupM;

public class ResearchGroupMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private ResearchGroupM researchGroupM;

    public ResearchGroupMasterForm(ResearchGroupM researchGroupM) {
        super();
        this.researchGroupM = researchGroupM;
    }

    public ResearchGroupMasterForm() {
        super();
        researchGroupM = new ResearchGroupM();
        // TODO Auto-generated constructor stub
    }

    public ResearchGroupM getResearchGroupM() {
        return researchGroupM;
    }

    public void setResearchGroupM(ResearchGroupM researchGroupM) {
        this.researchGroupM = researchGroupM;
    }

}
