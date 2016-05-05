package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.FundingResourceTypeM;

public class FundingResourceTypeMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private FundingResourceTypeM fundingResourceTypeM;

    public FundingResourceTypeMasterForm(FundingResourceTypeM fundingResourceTypeM) {
        super();
        this.fundingResourceTypeM = fundingResourceTypeM;
    }

    public FundingResourceTypeMasterForm() {
        super();
        fundingResourceTypeM = new FundingResourceTypeM();
        // TODO Auto-generated constructor stub
    }

    public FundingResourceTypeM getFundingResourceTypeM() {
        return fundingResourceTypeM;
    }

    public void setFundingResourceTypeM(FundingResourceTypeM fundingResourceTypeM) {
        this.fundingResourceTypeM = fundingResourceTypeM;
    }


}
