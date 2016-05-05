package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.CopyrightM;

public class CopyrightForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 117423009666312590L;
    private CopyrightM copyrightM;
    private String proposeDate;
    private String receiveDate;

    public CopyrightForm(CopyrightM copyrightM) {
        super();
        this.copyrightM = copyrightM;
    }

    public CopyrightForm() {
        super();
        copyrightM = new CopyrightM();
        // TODO Auto-generated constructor stub
    }

    public CopyrightM getCopyrightM() {
        return copyrightM;
    }

    public void setCopyrightM(CopyrightM copyrightM) {
        this.copyrightM = copyrightM;
    }

    public String getProposeDate() {
        return proposeDate;
    }

    public void setProposeDate(String proposeDate) {
        this.proposeDate = proposeDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

}
