package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.WorkTypeM;

public class WorkTypeMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private WorkTypeM workTypeM;

    public WorkTypeMasterForm(WorkTypeM workTypeM) {
        super();
        this.workTypeM = workTypeM;
    }

    public WorkTypeMasterForm() {
        super();
        workTypeM = new WorkTypeM();
        // TODO Auto-generated constructor stub
    }

    public WorkTypeM getWorkTypeM() {
        return workTypeM;
    }

    public void setWorkTypeM(WorkTypeM workTypeM) {
        this.workTypeM = workTypeM;
    }


}
